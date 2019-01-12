package oomitchoo.gaymercraft.proxy;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import oomitchoo.gaymercraft.client.BlockColorsHedge;
import oomitchoo.gaymercraft.client.ItemColorsHedge;
import oomitchoo.gaymercraft.client.model.LoaderModelVertSlab;
import oomitchoo.gaymercraft.client.renderer.entity.RenderUnicorn;
import oomitchoo.gaymercraft.entity.EntityUnicorn;
import oomitchoo.gaymercraft.helper.LogHelper;
import oomitchoo.gaymercraft.init.ModBlocks;
import oomitchoo.gaymercraft.reference.Reference;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by oOMitchOo on 21.11.2018.
 */
public class ClientProxy extends CommonProxy{
    private FaceBakery faceBakery;

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

    @Override
    public void registerColoredBlocks() {
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new BlockColorsHedge(), ModBlocks.BLOCK_HEDGE);
    }

    @Override
    public void registerColoredItems() {
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemColorsHedge(), ModBlocks.BLOCK_HEDGE);
    }

    @Override
    public void registerCustomModelLoader() {
        ModelLoaderRegistry.registerLoader(new LoaderModelVertSlab());
        this.faceBakery = new FaceBakery();
    }

    @Override
    public FaceBakery getFaceBakery() {
        return this.faceBakery;
    }

    @Override
    public void testingStuff() {
        @Nullable
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("botania", "pavement4slab"));
        IBlockState defaultState = null;

        try {
            defaultState = block.getDefaultState();
        } catch (NullPointerException e) {
            LogHelper.warn("Couldn't find defaultState. Block must've been null..." +e);
        }

        if (defaultState != null) {
            ModelResourceLocation location = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getBlockStateMapper().getVariants(block).get(defaultState);
            LogHelper.warn("ModelResourceLocation found: " + location.toString());
            // botania:pavement4slab#half=bottom
        } else LogHelper.warn("No Luck...");

        try {
            IModel model = ModelLoaderRegistry.getModel(new ModelResourceLocation("botania:quartzslabdarkhalf#half=bottom"));
            LogHelper.warn("Found IModel!!! It's class name is " + model.getClass().toString());
            Collection<ResourceLocation> allTextureLocForThisSlab = model.getTextures();
            if (!allTextureLocForThisSlab.isEmpty()) {
                Iterator<ResourceLocation> iterator = allTextureLocForThisSlab.iterator();
                LogHelper.warn("==================== getting TextureLocations ====================");
                int index = 0;
                while (iterator.hasNext()) {
                    LogHelper.warn("TextureLoc number " +index+ " is " +iterator.next().toString());
                    index++;
                }
                LogHelper.warn("================== end getting TextureLocations ==================");
            }
        } catch (Exception e) {
            LogHelper.warn("Couldn't find IModel for ModelResourceLocation");
        }
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
