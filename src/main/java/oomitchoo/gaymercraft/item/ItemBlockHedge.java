package oomitchoo.gaymercraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import oomitchoo.gaymercraft.block.BlockHedge;
import oomitchoo.gaymercraft.reference.Reference;

/**
 * Created by oOMitchOo on 26.12.2018.
 */
public class ItemBlockHedge extends ItemBlock {

    public ItemBlockHedge(Block block, String unlName, String regName) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(unlName);
        this.setRegistryName(Reference.MOD_ID, regName);
    }

    @Override
    public int getMetadata(int dmg) { return dmg; }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return (this.block.getUnlocalizedName()+"."+BlockPlanks.EnumType.byMetadata(stack.getMetadata()).getUnlocalizedName());
    }
}