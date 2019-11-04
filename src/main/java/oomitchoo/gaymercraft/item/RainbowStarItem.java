package oomitchoo.gaymercraft.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import oomitchoo.gaymercraft.GaymerCraft;

import javax.annotation.Nullable;
import java.util.List;

public class RainbowStarItem extends Item {
    // todo: maybe change rarity for this item? (in Properties)
    private static final Properties properties = new Item.Properties().maxStackSize(1).maxDamage(0).group(GaymerCraft.creativeTab.itemGroup);
    private final boolean isCharged;

    public RainbowStarItem(boolean isCharged) {
        super(properties);
        this.isCharged = isCharged;
    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
        return true;
    }

    @Override
    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return this.isCharged;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (isCharged) {
            tooltip.add(new TranslationTextComponent("message.rainbow.star.charged.1of2"));
            tooltip.add(new TranslationTextComponent("message.rainbow.star.charged.2of2"));
        } else {
            tooltip.add(new TranslationTextComponent("message.rainbow.star.uncharged.1of2"));
            tooltip.add(new TranslationTextComponent("message.rainbow.star.uncharged.2of2"));
        }
    }
}
