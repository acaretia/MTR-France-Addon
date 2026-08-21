package fr.adlmrl.mtrfra.mod.mixin;

import org.mtr.core.generated.map.DirectionsConnectionSchema;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = DirectionsConnectionSchema.class, remap = false)
public interface DirectionsConnectionAccessorMixin {

    @Accessor("routeId")
    String getRouteId();

    @Accessor("startStationId")
    String getStartStationId();

    @Accessor("endStationId")
    String getEndStationId();

    @Accessor("startPlatformName")
    String getStartPlatformName();

    @Accessor("endPlatformName")
    String getEndPlatformName();

    @Accessor("startTime")
    long getStartTime();

    @Accessor("endTime")
    long getEndTime();

}
