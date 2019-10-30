package oomitchoo.gaymercraft.item;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * This is a WallOrFloorItem, which can glow and have a tooltip. Not so special...
 */
public class SpecialWallOrFloorItem extends WallOrFloorItem {
    private final boolean isGlowing;
    private final String langFileToolTip;

    public SpecialWallOrFloorItem(Block floorBlock, Block wallBlockIn, Properties propertiesIn, boolean isGlowing, @Nullable String langFileToolTip) {
        super(floorBlock, wallBlockIn, propertiesIn);
        this.isGlowing = isGlowing;
        this.langFileToolTip = langFileToolTip;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return isGlowing;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (this.langFileToolTip != null) {
            tooltip.add(new TranslationTextComponent(this.langFileToolTip));
        }
    }
}
