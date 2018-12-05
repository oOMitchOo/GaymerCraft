package oomitchoo.gaymercraft.block.doubleslabs;

import oomitchoo.gaymercraft.block.baseslabs.BlockWoodVertSlab1;

/**
 * Created by oOMitchOo on 28.11.2018.
 */
public class BlockDoubleWoodVertSlab1 extends BlockWoodVertSlab1 {
    public boolean isDouble()
    {
        return true;
    }

    public BlockDoubleWoodVertSlab1 (String unlName, String regName) {
        super(unlName, regName);
    }
}