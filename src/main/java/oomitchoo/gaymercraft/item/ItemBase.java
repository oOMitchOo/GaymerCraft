package oomitchoo.gaymercraft.item;

import net.minecraft.item.Item;
import oomitchoo.gaymercraft.reference.Reference;

/**
 * Created by oOMitchOo on 21.11.2018.
 */
public class ItemBase extends Item {
    public ItemBase(String unlName, String regName) {
        super();
        this.setCreativeTab(Reference.creativeTab);

        this.setUnlocalizedName(unlName);
        this.setRegistryName(Reference.MOD_ID, regName);
    }
}
