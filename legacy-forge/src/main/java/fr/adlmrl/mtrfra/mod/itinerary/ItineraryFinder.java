package fr.adlmrl.mtrfra.mod.itinerary;

import fr.adlmrl.mtrfra.mod.mixin.DirectionsConnectionAccessorMixin;
import fr.adlmrl.mtrfra.mod.mixin.InitAccessorMixin;
import fr.adlmrl.mtrfra.mod.mixin.MainAccessorMixin;
import org.mtr.core.Main;
import org.mtr.core.data.Position;
import org.mtr.core.data.Route;
import org.mtr.core.data.Station;
import org.mtr.core.generated.map.DirectionsConnectionSchema;
import org.mtr.core.map.DirectionsConnection;
import org.mtr.core.map.DirectionsRequest;
import org.mtr.core.simulation.Simulator;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectImmutableList;
import org.mtr.mapping.holder.MinecraftServer;
import org.mtr.mapping.holder.World;
import org.mtr.mod.Init;

import java.util.List;
import java.util.function.Consumer;

public final class ItineraryFinder {

    private ItineraryFinder() {}

    public static void search(World world, MinecraftServer minecraftServer, String fromStationName, String toStationName, Consumer<Result> callback) {
        final Main main = InitAccessorMixin.getMain();
        if (main == null) {
            callback.accept(Result.notFound());
            return;
        }
        final String dimension = Init.getWorldId(world);
        final ObjectImmutableList<Simulator> simulators = ((MainAccessorMixin) main).getSimulators();

        for (final Simulator simulator : simulators) {
            if (!simulator.dimension.equals(dimension)) {
                continue;
            }
            simulator.run(() -> {
                final Station fromStation = findStationByName(simulator, fromStationName);
                final Station toStation = findStationByName(simulator, toStationName);
                if (fromStation == null || toStation == null) {
                    minecraftServer.submit(() -> callback.accept(Result.notFound()));
                    return;
                }

                final Position fromPosition = fromStation.getCenter();
                final Position toPosition = toStation.getCenter();
                simulator.directionsFinder.addRequest(new DirectionsRequest(fromPosition, toPosition, System.currentTimeMillis(), response -> {
                    final List<Leg> legs = new ObjectArrayList<>();
                    for (final DirectionsConnection connection : response.getDirectionsConnections()) {

                        final DirectionsConnectionSchema schema = connection;
                        final DirectionsConnectionAccessorMixin accessor = (DirectionsConnectionAccessorMixin) schema;
                        legs.add(new Leg(
                                routeName(simulator, accessor.getRouteId()),
                                stationName(simulator, accessor.getStartStationId(), accessor.getStartPlatformName()),
                                stationName(simulator, accessor.getEndStationId(), accessor.getEndPlatformName()),
                                accessor.getStartTime(),
                                accessor.getEndTime()
                        ));
                    }
                    minecraftServer.submit(() -> callback.accept(legs.isEmpty() ? Result.notFound() : Result.of(legs)));
                }));
            });
            return;
        }

        callback.accept(Result.notFound());
    }

    private static Station findStationByName(Simulator simulator, String query) {
        final String needle = query.trim().toLowerCase();
        for (final Station station : simulator.stations) {
            final String fullName = station.getName();
            if (fullName.toLowerCase().equals(needle)) {
                return station;
            }
            for (final String part : fullName.split("\\|")) {
                if (part.toLowerCase().equals(needle)) {
                    return station;
                }
            }
        }
        return null;
    }

    private static String routeName(Simulator simulator, String hexRouteId) {
        try {
            final Route route = simulator.routeIdMap.get(Long.parseLong(hexRouteId, 16));
            return route == null ? "?" : displayName(route.getName());
        } catch (NumberFormatException e) {
            return "?";
        }
    }

    private static String stationName(Simulator simulator, String hexStationId, String platformName) {
        try {
            final Station station = simulator.stationIdMap.get(Long.parseLong(hexStationId, 16));
            final String name = station == null ? "?" : displayName(station.getName());
            return platformName == null || platformName.isEmpty() ? name : name + " (" + platformName + ")";
        } catch (NumberFormatException e) {
            return "?";
        }
    }

    private static String displayName(String rawName) {
        final String[] parts = rawName.split("\\|\\|", 2);
        return parts[0].split("\\|")[0];
    }

    public static final class Leg {
        public final String routeName;
        public final String fromStation;
        public final String toStation;
        public final long startTime;
        public final long endTime;

        public Leg(String routeName, String fromStation, String toStation, long startTime, long endTime) {
            this.routeName = routeName;
            this.fromStation = fromStation;
            this.toStation = toStation;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public static final class Result {
        public final boolean found;
        public final List<Leg> legs;

        private Result(boolean found, List<Leg> legs) {
            this.found = found;
            this.legs = legs;
        }

        public static Result of(List<Leg> legs) {
            return new Result(true, legs);
        }

        public static Result notFound() {
            return new Result(false, new ObjectArrayList<>());
        }
    }

}
