package oomitchoo.gaymercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import oomitchoo.gaymercraft.reference.Reference;

/**
 * Created by oOMitchOo on 21.11.2018.
 */
class BlockBase extends Block {
    BlockBase(Material materialIn, MapColor mapColorIn, String unlName, String regName) {
        super(materialIn, mapColorIn);
        this.setCreativeTab(Reference.creativeTab);
        this.setUnlocalizedName(unlName);
        this.setRegistryName(regName);
    }

    // Don't ask me why.
    @Override
    public BlockBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
