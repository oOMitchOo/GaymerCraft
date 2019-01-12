package oomitchoo.gaymercraft.block.modsupport.botania;

import oomitchoo.gaymercraft.block.BlockVertSlabBase;

import javax.annotation.Nullable;

/**
 * Created by oOMitchOo on 12.01.2019.
 */
public class BlockHalfVertSlabBotaniaDynVariants extends BlockBaseVertSlabBotaniaDynVariants {

    public BlockHalfVertSlabBotaniaDynVariants(int indexOfFirstEnum, int numberOfVariants, @Nullable BlockVertSlabBase singleSlab, String unlName, String regName) {
        super(indexOfFirstEnum, numberOfVariants, singleSlab, unlName, regName);
    }

    @Override
    public boolean isDouble() {
        return false;
    }
}