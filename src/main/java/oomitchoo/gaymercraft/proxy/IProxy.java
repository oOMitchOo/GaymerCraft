package oomitchoo.gaymercraft.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by oOMitchOo on 21.11.2018.
 */
public interface IProxy {
    void registerItemRendererNoSubt(Item item, int metaData, String regNameAddition, String id);
    void registerItemRendererWithSubt(ItemBlock itemBlock, String id);
    void mapFluidState(Block block, Fluid fluid);
    void registerEntityRenderer();
}
