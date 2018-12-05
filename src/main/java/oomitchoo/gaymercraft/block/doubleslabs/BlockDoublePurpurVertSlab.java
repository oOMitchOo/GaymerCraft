package oomitchoo.gaymercraft.block.doubleslabs;

import oomitchoo.gaymercraft.block.baseslabs.BlockPurpurVertSlab;

/**
 * Created by oOMitchOo on 30.11.2018.
 */
public class BlockDoublePurpurVertSlab extends BlockPurpurVertSlab {
    @Override
    public boolean isDouble() {
        return true;
    }

    public BlockDoublePurpurVertSlab (String unlName, String regName) {
        super(unlName, regName);
    }
}