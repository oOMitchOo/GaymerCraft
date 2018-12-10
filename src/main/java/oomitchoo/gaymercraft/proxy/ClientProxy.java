package oomitchoo.gaymercraft.proxy;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import oomitchoo.gaymercraft.block.baseslabs.BlockStoneVertSlab1;
import oomitchoo.gaymercraft.block.baseslabs.BlockStoneVertSlab2;
import oomitchoo.gaymercraft.block.baseslabs.BlockWoodVertSlab1;
import oomitchoo.gaymercraft.block.baseslabs.BlockWoodVertSlab2;
import oomitchoo.gaymercraft.client.renderer.entity.RenderUnicorn;
import oomitchoo.gaymercraft.entity.EntityUnicorn;
import oomitchoo.gaymercraft.reference.Reference;

import javax.annotation.Nonnull;

/**
 * Created by oOMitchOo on 21.11.2018.
 */
public class ClientProxy extends CommonProxy{

    @Override
    public void registerItemRendererNoSubt(Item item, int metaData, String regNameAddition, String id) {
        ModelLoader.setCustomModelResourceLocation(item, metaData, new ModelResourceLocation(item.getRegistryName()+regNameAddition, id));
    }

    @Override
    public void registerItemRendererWithSubt(ItemBlock itemBlock, String id) { //todo: UNBEDINGT elegantere Lösung, damit ich nicht für jedes Item(-Block) mit Subtypes ein eigenes if-statement machen muss.
        if (itemBlock.getBlock() instanceof BlockStoneVertSlab1) {
            for (BlockStoneVertSlab1.EnumType blockslabs$enumtype : BlockStoneVertSlab1.EnumType.values()) {
                ModelLoader.setCustomModelResourceLocation(itemBlock, blockslabs$enumtype.getMetadata(), new ModelResourceLocation(itemBlock.getRegistryName()+"_"+blockslabs$enumtype.getName(), id));
            }
        } else if (itemBlock.getBlock() instanceof BlockStoneVertSlab2) {
            for (BlockStoneVertSlab2.EnumType blockslabs$enumtype : BlockStoneVertSlab2.EnumType.values()) {
                ModelLoader.setCustomModelResourceLocation(itemBlock, blockslabs$enumtype.getMetadata(), new ModelResourceLocation(itemBlock.getRegistryName()+"_"+blockslabs$enumtype.getName(), id));
            }
        } else if (itemBlock.getBlock() instanceof BlockWoodVertSlab1) {
            for (BlockWoodVertSlab1.EnumType blockslabs$enumtype : BlockWoodVertSlab1.EnumType.values()) {
                ModelLoader.setCustomModelResourceLocation(itemBlock, blockslabs$enumtype.getMetadata(), new ModelResourceLocation(itemBlock.getRegistryName()+"_"+blockslabs$enumtype.getName(), id));
            }
        } else if (itemBlock.getBlock() instanceof BlockWoodVertSlab2) {
            for (BlockWoodVertSlab2.EnumType blockslabs$enumtype : BlockWoodVertSlab2.EnumType.values()) {
                ModelLoader.setCustomModelResourceLocation(itemBlock, blockslabs$enumtype.getMetadata(), new ModelResourceLocation(itemBlock.getRegistryName()+"_"+blockslabs$enumtype.getName(), id));
            }
        }
    }

    @Override
    public void mapFluidState(Block block, Fluid fluid) {
        Item item = Item.getItemFromBlock(block);
        FluidStateMapper mapper = new FluidStateMapper(fluid);
        if(item!= Items.AIR)
        {
            ModelLoader.registerItemVariants(item);
            ModelLoader.setCustomMeshDefinition(item, mapper);
        }
        ModelLoader.setCustomStateMapper(block, mapper);
    }

    @Override
    public void registerEntityRenderer() {
        RenderingRegistry.registerEntityRenderingHandler(EntityUnicorn.class, RenderUnicorn::new);
    }

    private static class FluidStateMapper extends StateMapperBase implements ItemMeshDefinition {
        public final ModelResourceLocation location;

        public FluidStateMapper(Fluid fluid) {
            this.location = new ModelResourceLocation(Reference.MOD_ID+":fluid_block", fluid.getName());
        }

        @Nonnull
        @Override
        protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
            return location;
        }

        @Nonnull
        @Override
        public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
            return location;
        }
    }
}
