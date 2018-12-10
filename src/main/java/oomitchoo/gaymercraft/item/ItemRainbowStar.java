package oomitchoo.gaymercraft.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by oOMitchOo on 21.11.2018.
 */
public class ItemRainbowStar extends ItemBase{
    public ItemRainbowStar(String unlName, String regName) {
        super(unlName, regName);

        this.setMaxDamage(0);
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
    }

    public String getUnlocalizedName(ItemStack stack) // mit Metadata 0 soll loaded sein (mit Farbe) hingegen mit 1 ist unloaded (schwarz-weiß)
    {
        int i = stack.getMetadata();
        if (i == 0) {
            return super.getUnlocalizedName() + ".loaded";
        } else {
            return super.getUnlocalizedName() + ".unloaded";
        }
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            items.add(new ItemStack(this, 1, 0));
            items.add(new ItemStack(this, 1, 1));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        int meta = stack.getMetadata();
        if (meta == 0) {
            tooltip.add("It holds concentrated §cr§6a§ei§an§bb§9o§dw §7power. I should try it on some animals, wild ones though, it would have no effect on tamed animals.");
        }
        else {
            tooltip.add("Oh noes, it lost all the colors. I should recharge it.");
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        int meta = stack.getMetadata();
        return meta == 0; // Nur der farbige Stern soll Glanz haben.
    }
}
