package oomitchoo.gaymercraft.client;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.FoliageColors;

public class ItemColorsHedge implements IItemColor {
    private static final IItemColor INSTANCE = new ItemColorsHedge();
    public static IItemColor getInstance() {
        return INSTANCE;
    }

    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        ResourceLocation regName = stack.getItem().getRegistryName();

        if (regName.equals(new ResourceLocation("gaymercraft", "spruce_hedge"))) {
            return FoliageColors.getSpruce();
        } else if (regName.equals(new ResourceLocation("gaymercraft", "birch_hedge"))) {
            return FoliageColors.getBirch();
        } else {
            return FoliageColors.getDefault();
        }
    }
}
