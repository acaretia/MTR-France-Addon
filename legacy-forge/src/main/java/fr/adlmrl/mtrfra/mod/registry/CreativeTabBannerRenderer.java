package fr.adlmrl.mtrfra.mod.registry;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class CreativeTabBannerRenderer {

    private static final int ROW_WIDTH = 9;
    private static final int ITEM_SIZE = 18;
    private static final int VISIBLE_ROWS = 5;

    private static final Map<CreativeTabSection, Integer> SECTION_ROWS = new LinkedHashMap<>();

    public static int currentRow = 0;

    private CreativeTabBannerRenderer() {}

    public static List<ItemStack> reorderAndPad(Collection<ItemStack> original) {
        final Map<Item, CreativeTabSection> itemSections = CreativeTabSections.itemSections();
        final Map<CreativeTabSection, List<ItemStack>> bySection = new LinkedHashMap<>();
        final List<ItemStack> unsectioned = new ArrayList<>();

        for (final ItemStack stack : original) {
            final CreativeTabSection section = itemSections.get(stack.getItem());
            if (section == null) {
                unsectioned.add(stack);
            } else {
                bySection.computeIfAbsent(section, ignored -> new ArrayList<>()).add(stack);
            }
        }

        SECTION_ROWS.clear();
        final List<ItemStack> result = new ArrayList<>();
        int row = 0;
        for (final CreativeTabSection section : ModItemGroups.SECTION_ORDER) {
            final List<ItemStack> items = bySection.get(section);
            if (items == null || items.isEmpty()) {
                continue;
            }
            SECTION_ROWS.put(section, row);
            for (int i = 0; i < ROW_WIDTH; i++) {
                result.add(ItemStack.EMPTY);
            }
            row++;

            result.addAll(items);
            final int rowCount = (items.size() + ROW_WIDTH - 1) / ROW_WIDTH;
            row += rowCount;
            final int padding = (ROW_WIDTH - items.size() % ROW_WIDTH) % ROW_WIDTH;
            for (int i = 0; i < padding; i++) {
                result.add(ItemStack.EMPTY);
            }
        }
        result.addAll(unsectioned);
        return result;
    }

    private static final Map<String, ResourceLocation> BANNER_SPRITES = new LinkedHashMap<>();

    private static ResourceLocation bannerSprite(String texturePath) {
        return BANNER_SPRITES.computeIfAbsent(texturePath, path -> new ResourceLocation(fr.adlmrl.mtrfra.mod.util.Constants.MOD_ID, path));
    }

    public static void renderBanners(GuiGraphics graphics, int left, int top, int mouseX, int mouseY) {
        for (final Map.Entry<CreativeTabSection, Integer> entry : SECTION_ROWS.entrySet()) {
            final int sectionRow = entry.getValue() - currentRow;
            if (sectionRow < 0 || sectionRow >= VISIBLE_ROWS) {
                continue;
            }
            final CreativeTabSection section = entry.getKey();
            final int x = left;
            final int y = top + sectionRow * ITEM_SIZE;
            final int width = ROW_WIDTH * ITEM_SIZE;
            final Component text = (Component) section.title.data;
            final int textWidth = Minecraft.getInstance().font.width(text);

            graphics.blitSprite(bannerSprite(section.texturePath), x, y, width, ITEM_SIZE);

            graphics.fill(x + 2, y + 2, x + textWidth + 8, y + ITEM_SIZE - 2, section.backgroundColor);
            drawAuraText(graphics, text, x + 5, y + 5, section.titleColor, darken(section.titleColor, 0.2f));
        }
    }

    private static int darken(int argb, float factor) {
        final int a = argb >>> 24;
        final int r = (int) (((argb >> 16) & 0xFF) * (1 - factor));
        final int g = (int) (((argb >> 8) & 0xFF) * (1 - factor));
        final int b = (int) ((argb & 0xFF) * (1 - factor));
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    private static void drawAuraText(GuiGraphics graphics, Component text, int x, int y, int color, int auraColor) {
        final Font font = Minecraft.getInstance().font;
        final Window window = Minecraft.getInstance().getWindow();
        final float guiScale = (float) window.getGuiScale();

        graphics.drawString(font, text, x, y, auraColor, true);

        final PoseStack pose = graphics.pose();
        pose.pushPose();
        pose.translate(0, 0, 1);
        final Matrix4f matrix = pose.last().pose();
        final Vector3f topLeft = matrix.transformPosition(new Vector3f(x, y, 0));
        final Vector3f bottomRight = matrix.transformPosition(new Vector3f(x + font.width(text), y + 9.0f / 1.8f, 0));
        topLeft.mul(guiScale);
        bottomRight.mul(guiScale);
        final int scissorHeight = (int) (bottomRight.y - topLeft.y);
        final int scissorWidth = (int) (bottomRight.x - topLeft.x);

        RenderSystem.enableScissor((int) topLeft.x, window.getHeight() - (int) topLeft.y - scissorHeight, scissorWidth, scissorHeight);
        graphics.drawString(font, text, x, y, color, false);
        RenderSystem.disableScissor();
        pose.popPose();
    }

}
