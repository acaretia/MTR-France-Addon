package fr.adlmrl.mtrfra.mod.mixin;

import fr.adlmrl.mtrfra.mod.registry.CreativeTabBannerRenderer;
import fr.adlmrl.mtrfra.mod.registry.ModItemGroups;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(CreativeModeTab.class)
public abstract class CreativeModeTabMixin {

    @Shadow
    private Collection<ItemStack> displayItems;

    @Inject(method = "buildContents", at = @At("TAIL"))
    private void mtrfra$sectionBanners(CreativeModeTab.ItemDisplayParameters parameters, CallbackInfo ci) {

        if (ModItemGroups.MAIN.identifier.equals(BuiltInRegistries.CREATIVE_MODE_TAB.getKey((CreativeModeTab) (Object) this))) {
            this.displayItems = CreativeTabBannerRenderer.reorderAndPad(this.displayItems);
        }
    }

}
