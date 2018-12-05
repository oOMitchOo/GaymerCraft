package oomitchoo.gaymercraft.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by oOMitchOo on 21.11.2018.
 */
public class ServerProxy extends CommonProxy {

    @Override
    public void registerItemRendererNoSubt(Item item, String id) {
        // Nothing to see here. Go to ClientProxy!
    }

    @Override
    public void registerItemRendererWithSubt(ItemBlock itemBlock, String id) {
        // Nothing to see here. Go to ClientProxy!
    }

    @Override
    public void mapFluidState(Block block, Fluid fluid) {
        // Nothing to see here. Go to ClientProxy!
    }
}
