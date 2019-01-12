package oomitchoo.gaymercraft.item.debug;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import oomitchoo.gaymercraft.item.ItemBase;

/**
 * Created by oOMitchOo on 09.01.2019.
 */
public class DebugTool extends ItemBase {
    public DebugTool(String unlName, String regName) {
        super(unlName, regName);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IBlockState stateClicking = worldIn.getBlockState(pos);
        Block blockClicking = worldIn.getBlockState(pos).getBlock();

        if (!worldIn.isRemote && blockClicking != Blocks.AIR) {
            player.sendMessage(new TextComponentString("Block right-clicking: " + blockClicking.getRegistryName().toString()));
            try {
                ResourceLocation location = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getBlockStateMapper().getVariants(blockClicking).get(stateClicking);
                player.sendMessage(new TextComponentString("ModelResourceLocation: " + location.toString()));
            } catch (NullPointerException e) {
                player.sendMessage(new TextComponentString("Could not get ModelResourceLocation! :("));
            }
            player.sendMessage(new TextComponentString("Hardness: " + blockClicking.getBlockHardness(blockClicking.getDefaultState(), worldIn, pos)));
            //set::: this.blockResistance = resistance * 3.0F; //get::: return this.blockResistance / 5.0F;
            player.sendMessage(new TextComponentString("Resistance: " + (blockClicking.getExplosionResistance(player) * 5F / 3F)));
            player.sendMessage(new TextComponentString("Material (state): " + tryForWoodAndRockMaterial(stateClicking)));
            player.sendMessage(new TextComponentString("SoundType: " + tryForStoneAndWoodSoundType(blockClicking)));
            player.sendMessage(new TextComponentString("===================================="));

            return EnumActionResult.SUCCESS;
        } else return EnumActionResult.FAIL;
    }

    private String tryForWoodAndRockMaterial (IBlockState state) {
        Material mat = state.getMaterial();
        if (mat == Material.ROCK)
            return "Material.ROCK";
        if (mat == Material.WOOD)
            return "Material.WOOD";

        return "not sure...";
    }

    private String tryForStoneAndWoodSoundType (Block block) {
        SoundType sound = block.getSoundType();
        if (sound == SoundType.STONE)
            return "SoundType.STONE";
        if (sound == SoundType.WOOD)
            return "SoundType.WOOD";

        return "not sure...";
    }
}