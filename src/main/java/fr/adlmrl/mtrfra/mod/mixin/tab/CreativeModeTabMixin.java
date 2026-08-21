package fr.adlmrl.mtrfra.mod.mixin.tab;

//? if >=1.20.4 {
import fr.adlmrl.mtrfra.mod.tab.CreativeTabSections;
import fr.adlmrl.mtrfra.mod.registry.ModItemGroups;
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
        if ((CreativeModeTab) (Object) this == ModItemGroups.MAIN.creativeModeTab) {
            this.displayItems = CreativeTabSections.reorderAndPad(this.displayItems);
        }
    }

}
//? } else {
/*import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CreativeModeTab.class)
public abstract class CreativeModeTabMixin {

}
*///? }
