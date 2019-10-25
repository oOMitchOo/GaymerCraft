package oomitchoo.gaymercraft.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import oomitchoo.gaymercraft.reference.Reference;

public class CreativeTab {

    public ItemGroup itemGroup = new ItemGroup(Reference.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.RAINBOW_STAR_CHARGED);
        }
    };
}
