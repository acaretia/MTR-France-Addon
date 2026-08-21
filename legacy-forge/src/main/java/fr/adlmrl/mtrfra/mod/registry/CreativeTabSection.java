package fr.adlmrl.mtrfra.mod.registry;

import org.mtr.mapping.holder.MutableText;

public final class CreativeTabSection {

    public final String id;
    public final MutableText title;

    public final String texturePath;
    public final int titleColor;

    public final int backgroundColor;

    public CreativeTabSection(String id, MutableText title, String texturePath, int titleColor) {
        this(id, title, texturePath, titleColor, 0xAA000000);
    }

    public CreativeTabSection(String id, MutableText title, String texturePath, int titleColor, int backgroundColor) {
        this.id = id;
        this.title = title;
        this.texturePath = texturePath;
        this.titleColor = titleColor;
        this.backgroundColor = backgroundColor;
    }

}
