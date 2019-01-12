package oomitchoo.gaymercraft.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraftforge.fluids.Fluid;
import oomitchoo.gaymercraft.helper.LogHelper;

/**
 * Created by oOMitchOo on 21.11.2018.
 */
public class ServerProxy extends CommonProxy {

    @Override
    public void mapFluidState(Block block, Fluid fluid) {
        // Nothing to see here. Go to ClientProxy!
    }

    @Override
    public void registerEntityRenderer() {
        // Nothing to see here. Go to ClientProxy!
    }

    @Override
    public void registerColoredBlocks() {
        // Nothing to see here. Go to ClientProxy!
    }

    @Override
    public void registerColoredItems() {
        // Nothing to see here. Go to ClientProxy!
    }

    @Override
    public void registerCustomModelLoader() {
        // Nothing to see here. Go to ClientProxy!
    }

    @Override
    public FaceBakery getFaceBakery() {
        LogHelper.error("Server is trying to retrieve the FaceBakery. This should not happen!");
        return null;
    }

    @Override
    public void testingStuff() {
        // Nothing to see here. Go to ClientProxy!
    }
}
