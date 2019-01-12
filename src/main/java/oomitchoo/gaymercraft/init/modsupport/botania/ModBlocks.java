package oomitchoo.gaymercraft.init.modsupport.botania;

import net.minecraft.block.Block;
import oomitchoo.gaymercraft.block.BlockVertSlabBase;
import oomitchoo.gaymercraft.block.modsupport.botania.BlockDoubleVertSlabBotaniaDynVariants;
import oomitchoo.gaymercraft.block.modsupport.botania.BlockHalfVertSlabBotaniaDynVariants;
import oomitchoo.gaymercraft.block.modsupport.botania.EnumVariantSlabBotania;
import oomitchoo.gaymercraft.item.ItemBlockVertSlab;

/**
 * Created by oOMitchOo on 12.01.2019.
 */
public class ModBlocks {
    private static final String BOTANIA_RESLOC_PREFIX = "modsupport/botania/";

    public static final Block[] BOTANIA_SLABS = new BlockVertSlabBase[EnumVariantSlabBotania.getNumberOfVariantsWithMetaZero()];
    public static final Block[] DOUBLE_BOTANIA_SLABS = new BlockVertSlabBase[EnumVariantSlabBotania.getNumberOfVariantsWithMetaZero()];
    public static final ItemBlockVertSlab[] ITEMBLOCK_BOTANIA_SLABS = new ItemBlockVertSlab[EnumVariantSlabBotania.getNumberOfVariantsWithMetaZero()];

    public static void init() {
        int index = 0;
        int blockArrayIndex = 0;

        while (index < EnumVariantSlabBotania.getLookupLength())
        {
            int numberOfSubBlocks = 0;
            do {
                numberOfSubBlocks++;
                index++;
            } while (EnumVariantSlabBotania.byIndex(index).getMetadata()!=0);

            BOTANIA_SLABS[blockArrayIndex] = new BlockHalfVertSlabBotaniaDynVariants(index-numberOfSubBlocks, numberOfSubBlocks, null, "botaniaSlab" + blockArrayIndex, BOTANIA_RESLOC_PREFIX + "slab" + blockArrayIndex);
            DOUBLE_BOTANIA_SLABS[blockArrayIndex] = new BlockDoubleVertSlabBotaniaDynVariants(index-numberOfSubBlocks, numberOfSubBlocks, (BlockVertSlabBase)BOTANIA_SLABS[blockArrayIndex], "botaniaSlab" + blockArrayIndex, BOTANIA_RESLOC_PREFIX + "double_slab" + blockArrayIndex);
            ITEMBLOCK_BOTANIA_SLABS[blockArrayIndex] = new ItemBlockVertSlab((BlockVertSlabBase)BOTANIA_SLABS[blockArrayIndex], (BlockVertSlabBase)BOTANIA_SLABS[blockArrayIndex], (BlockVertSlabBase)DOUBLE_BOTANIA_SLABS[blockArrayIndex], numberOfSubBlocks, "botaniaSlab" + blockArrayIndex, BOTANIA_RESLOC_PREFIX + "slab" + blockArrayIndex);
            blockArrayIndex++;
        }
    }
}