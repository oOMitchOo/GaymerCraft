package oomitchoo.gaymercraft.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import oomitchoo.gaymercraft.init.ModItems;
import oomitchoo.gaymercraft.reference.Reference;

import javax.annotation.Nonnull;

/**
 * Created by oOMitchOo on 21.11.2018.
 */
public class ModCreativeTab extends CreativeTabs{

    public ModCreativeTab(){
        super(Reference.MOD_ID);
    }

    @Nonnull
    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(ModItems.GAYMER_LOGO);
    }

    @Override
    public ItemStack getTabIconItem() {
        return getIconItemStack();
    }

    @Override
    public boolean hasSearchBar() {
        return false;
    }


}
