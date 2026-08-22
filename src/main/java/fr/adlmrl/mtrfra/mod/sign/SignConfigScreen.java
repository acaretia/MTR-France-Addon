package fr.adlmrl.mtrfra.mod.sign;

import fr.adlmrl.mtrfra.mod.registry.MTRFRARegistryClient;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.ClickableWidget;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.TextFieldWidget;
import org.mtr.mapping.mapper.ButtonWidgetExtension;
import org.mtr.mapping.mapper.ScreenExtension;
import org.mtr.mapping.mapper.TextHelper;

public class SignConfigScreen extends ScreenExtension {

    private static final int FIELD_WIDTH = 200;
    private static final int FIELD_HEIGHT = 20;
    private static final int TOP_OFFSET = 44;
    private static final int ROW_GAP = 4;
    private static final int BOTTOM_ROW_GAP = 12;

    private final BlockPos pos;
    private final int maxCategory;
    private final String initialTitle;
    private final String initialSubtitle;
    private int category;

    private TextFieldWidget titleField;
    private TextFieldWidget subtitleField;
    private int categoryButtonX;
    private int categoryButtonY;

    public SignConfigScreen(BlockPos pos, String title, String subtitle, int category, int maxCategory) {
        super(TextHelper.translatable("gui.mtrfranceaddon.panneau.config.title"));
        this.pos = pos;
        this.category = category;
        this.maxCategory = maxCategory;
        this.initialTitle = title;
        this.initialSubtitle = subtitle;
    }

    @Override
    protected void init2() {
        super.init2();
        final int x = (width - FIELD_WIDTH) / 2;

        titleField = new TextFieldWidget(getTextRendererMapped(), x, TOP_OFFSET, FIELD_WIDTH, FIELD_HEIGHT, org.mtr.mapping.holder.Text.cast(TextHelper.translatable("gui.mtrfranceaddon.panneau.config.title_field")));
        titleField.setMaxLength(64);
        titleField.setText(initialTitle);
        addChild(new ClickableWidget(titleField.data));

        subtitleField = new TextFieldWidget(getTextRendererMapped(), x, TOP_OFFSET + FIELD_HEIGHT + ROW_GAP, FIELD_WIDTH, FIELD_HEIGHT, org.mtr.mapping.holder.Text.cast(TextHelper.translatable("gui.mtrfranceaddon.panneau.config.subtitle_field")));
        subtitleField.setMaxLength(64);
        subtitleField.setText(initialSubtitle);
        addChild(new ClickableWidget(subtitleField.data));

        if (maxCategory > 0) {
            categoryButtonX = x;
            categoryButtonY = TOP_OFFSET + 2 * (FIELD_HEIGHT + ROW_GAP);
            addChild(new ClickableWidget(new ButtonWidgetExtension(
                    categoryButtonX, categoryButtonY, FIELD_WIDTH, FIELD_HEIGHT,
                    TextHelper.translatable("gui.mtrfranceaddon.panneau.config.category." + category),
                    pressed -> cycleCategoryForward()
            )));
        }

        final int y = TOP_OFFSET + (maxCategory > 0 ? 3 : 2) * (FIELD_HEIGHT + ROW_GAP) + BOTTOM_ROW_GAP;
        final int pairWidth = (FIELD_WIDTH - ROW_GAP) / 2;
        addChild(new ClickableWidget(new ButtonWidgetExtension(
                x, y, pairWidth, FIELD_HEIGHT, TextHelper.translatable("gui.mtrfranceaddon.panneau.config.save"),
                pressed -> save()
        )));
        addChild(new ClickableWidget(new ButtonWidgetExtension(
                x + pairWidth + ROW_GAP, y, pairWidth, FIELD_HEIGHT, TextHelper.translatable("gui.mtrfranceaddon.panneau.config.discard"),
                pressed -> MinecraftClient.getInstance().openScreen(null)
        )));
    }

    private void cycleCategoryForward() {
        category = category >= maxCategory ? 0 : category + 1;
        MinecraftClient.getInstance().openScreen(new org.mtr.mapping.holder.Screen(new SignConfigScreen(pos, titleField.getText(), subtitleField.getText(), category, maxCategory)));
    }

    private void cycleCategoryBackward() {
        category = category <= 0 ? maxCategory : category - 1;
        MinecraftClient.getInstance().openScreen(new org.mtr.mapping.holder.Screen(new SignConfigScreen(pos, titleField.getText(), subtitleField.getText(), category, maxCategory)));
    }

    @Override
    public boolean mouseClicked2(double mouseX, double mouseY, int button) {
        if (button == 1 && maxCategory > 0
                && mouseX >= categoryButtonX && mouseX < categoryButtonX + FIELD_WIDTH
                && mouseY >= categoryButtonY && mouseY < categoryButtonY + FIELD_HEIGHT) {
            cycleCategoryBackward();
            return true;
        }
        return super.mouseClicked2(mouseX, mouseY, button);
    }

    @Override
    public void onClose2() {
        save();
    }

    private void save() {
        MTRFRARegistryClient.REGISTRY_CLIENT.sendPacketToServer(new PacketSaveSignConfig(pos, titleField.getText(), subtitleField.getText(), category));
        MinecraftClient.getInstance().openScreen(null);
    }

}
