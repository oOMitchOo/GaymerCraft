package oomitchoo.gaymercraft.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by oOMitchOo on 21.11.2018.
 */
public interface IProxy {
    void mapFluidState(Block block, Fluid fluid);
    void registerEntityRenderer();
    void registerColoredBlocks();
    void registerColoredItems();
    void registerCustomModelLoader();
    FaceBakery getFaceBakery();
    void testingStuff();
}
