package fr.adlmrl.mtrfra.mod.itinerary;

import fr.adlmrl.mtrfra.mod.registry.MTRFRARegistryClient;
import fr.adlmrl.mtrfra.mod.util.GuiFill;
import org.mtr.mapping.holder.ClickableWidget;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.Style;
import org.mtr.mapping.holder.TextColor;
import org.mtr.mapping.mapper.ButtonWidgetExtension;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.ScreenExtension;
import org.mtr.mapping.mapper.TextFieldWidgetExtension;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mapping.tool.TextCase;

import java.util.Collections;
import java.util.List;

public class ItineraryScreen extends ScreenExtension {

    private static final int COLOR_BACKGROUND = GuiFill.opaque(0x0D2F5F);
    private static final int COLOR_HEADER = GuiFill.opaque(0x044179);
    private static final int COLOR_ACCENT_ORANGE = GuiFill.opaque(0xFF9F17);
    private static final int COLOR_FOOTER = GuiFill.opaque(0xFFFFFF);
    private static final int COLOR_WHITE = 0xFFFFFF;
    private static final int COLOR_YELLOW = 0xFEF103;
    private static final int COLOR_GRAY = 0xAAAAAA;

    private static final int HEADER_HEIGHT = 22;
    private static final int HEADER_ACCENT_HEIGHT = 2;
    private static final int FOOTER_HEIGHT = 6;
    private static final int FIELD_WIDTH = 150;
    private static final int FIELD_HEIGHT = 20;
    private static final int TOP_OFFSET = HEADER_HEIGHT + HEADER_ACCENT_HEIGHT + 14;
    private static final int RESULTS_TOP = TOP_OFFSET + FIELD_HEIGHT + 30;
    private static final int RESULT_ROW_HEIGHT = 14;

    private TextFieldWidgetExtension fromField;
    private TextFieldWidgetExtension toField;

    private State state = State.IDLE;
    private List<ItineraryFinder.Leg> legs = Collections.emptyList();

    public ItineraryScreen() {
        super(TextHelper.translatable("gui.mtrfranceaddon.itinerary.title"));
    }

    @Override
    protected void init2() {
        super.init2();
        final int fromX = width / 2 - FIELD_WIDTH - 10;
        final int toX = width / 2 + 10;

        fromField = new TextFieldWidgetExtension(fromX, TOP_OFFSET, FIELD_WIDTH, FIELD_HEIGHT, 64, TextCase.DEFAULT, null, null);
        toField = new TextFieldWidgetExtension(toX, TOP_OFFSET, FIELD_WIDTH, FIELD_HEIGHT, 64, TextCase.DEFAULT, null, null);
        addChild(new ClickableWidget(fromField));
        addChild(new ClickableWidget(toField));

        final ButtonWidgetExtension searchButton = new ButtonWidgetExtension(
                width / 2 - 60, TOP_OFFSET + FIELD_HEIGHT + 8, 120, FIELD_HEIGHT,
                TextHelper.translatable("gui.mtrfranceaddon.itinerary.search"),
                pressed -> search()
        );
        addChild(new ClickableWidget(searchButton));
    }

    private void search() {
        final String from = fromField.getText2().trim();
        final String to = toField.getText2().trim();
        if (from.isEmpty() || to.isEmpty()) {
            return;
        }
        state = State.SEARCHING;
        legs = Collections.emptyList();
        MTRFRARegistryClient.REGISTRY_CLIENT.sendPacketToServer(new PacketSearchItinerary(from, to));
    }

    public void applyResult(boolean found, List<ItineraryFinder.Leg> legs) {
        this.legs = legs;
        state = found ? State.RESULT : State.NOT_FOUND;
    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
        GuiFill.fill(graphicsHolder, 0, 0, width, height, COLOR_BACKGROUND);
        GuiFill.fill(graphicsHolder, 0, 0, width, HEADER_HEIGHT, COLOR_HEADER);
        GuiFill.fill(graphicsHolder, 0, HEADER_HEIGHT, width, HEADER_HEIGHT + HEADER_ACCENT_HEIGHT, COLOR_ACCENT_ORANGE);

        final MutableText title = TextHelper.setStyle(
                TextHelper.translatable("gui.mtrfranceaddon.itinerary.title"),
                Style.getEmptyMapped().withBold(true).withColor(TextColor.fromRgb(COLOR_WHITE))
        );
        graphicsHolder.drawText(title, (width - GraphicsHolder.getTextWidth(title)) / 2, (HEADER_HEIGHT - 8) / 2, COLOR_WHITE, false, GraphicsHolder.getDefaultLight());

        drawLabel(graphicsHolder, "gui.mtrfranceaddon.itinerary.from", fromField.getX2());
        drawLabel(graphicsHolder, "gui.mtrfranceaddon.itinerary.to", toField.getX2());

        switch (state) {
            case SEARCHING:
                drawCentered(graphicsHolder, TextHelper.translatable("gui.mtrfranceaddon.itinerary.searching"), RESULTS_TOP, COLOR_GRAY);
                break;
            case NOT_FOUND:
                drawCentered(graphicsHolder, TextHelper.translatable("gui.mtrfranceaddon.itinerary.not_found"), RESULTS_TOP, COLOR_GRAY);
                break;
            case RESULT:
                for (int i = 0; i < legs.size(); i++) {
                    final ItineraryFinder.Leg leg = legs.get(i);
                    final long minutes = Math.max(0, (leg.endTime - leg.startTime) / 60000);
                    final MutableText line = TextHelper.append(
                            TextHelper.setStyle(TextHelper.literal(leg.routeName + ": "), Style.getEmptyMapped().withBold(true).withColor(TextColor.fromRgb(COLOR_YELLOW))),
                            TextHelper.literal(leg.fromStation + " -> " + leg.toStation + " (" + minutes + " min)")
                    );
                    graphicsHolder.drawText(line, width / 2 - FIELD_WIDTH, RESULTS_TOP + i * RESULT_ROW_HEIGHT, COLOR_WHITE, false, GraphicsHolder.getDefaultLight());
                }
                break;
            case IDLE:
            default:
                break;
        }

        GuiFill.fill(graphicsHolder, 0, height - FOOTER_HEIGHT, width, height, COLOR_FOOTER);

        super.render(graphicsHolder, mouseX, mouseY, delta);
    }

    private void drawLabel(GraphicsHolder graphicsHolder, String translationKey, int x) {
        final MutableText label = TextHelper.translatable(translationKey);
        graphicsHolder.drawText(label, x, TOP_OFFSET - 12, COLOR_GRAY, false, GraphicsHolder.getDefaultLight());
    }

    private void drawCentered(GraphicsHolder graphicsHolder, MutableText text, int y, int color) {
        graphicsHolder.drawText(text, (width - GraphicsHolder.getTextWidth(text)) / 2, y, color, false, GraphicsHolder.getDefaultLight());
    }

    @Override
    public boolean isPauseScreen2() {
        return false;
    }

    private enum State {
        IDLE, SEARCHING, RESULT, NOT_FOUND
    }

}
