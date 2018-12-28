package oomitchoo.gaymercraft.client;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerFoliage;

/**
 * Created by oOMitchOo on 27.12.2018.
 */
public class ItemColorsHedge implements IItemColor {
    private static final IItemColor INSTANCE = new ItemColorsHedge();
    public static IItemColor getInstance() {
        return INSTANCE;
    }

    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        int meta = stack.getMetadata();

        if (meta == 1)
            return ColorizerFoliage.getFoliageColorPine();
        else if (meta == 2)
            return ColorizerFoliage.getFoliageColorBirch();
        else
            return ColorizerFoliage.getFoliageColorBasic();
    }
}