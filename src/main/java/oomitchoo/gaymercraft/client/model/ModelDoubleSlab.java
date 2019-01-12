package oomitchoo.gaymercraft.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oomitchoo.gaymercraft.block.modsupport.botania.EnumVariantSlabBotania;
import oomitchoo.gaymercraft.helper.LogHelper;
import oomitchoo.gaymercraft.reference.Reference;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by oOMitchOo on 10.01.2019.
 */
@SideOnly(Side.CLIENT)
public class ModelDoubleSlab implements IModel {
    // TODO: I could use enumDomainType, if I have different mods using this model
    private final String enumDomainType;
    private final String enumVariantName;

    private boolean isMissingModel;
    private ResourceLocation locationVanillaBlockModel;
    private IModel vanillaBlockModel;

    public ModelDoubleSlab (ResourceLocation modelLocation) {
        this.enumDomainType = setEnumDomainType(modelLocation.getResourcePath());
        this.enumVariantName = setEnumVariantName(modelLocation.toString());
    }

    private String setEnumDomainType(String resourcePath) {
        if (resourcePath.contains("botania")) {
            return "botania";
        }
        else return "vanilla";
    }

    private String setEnumVariantName(String modelLocationString) {
        int indexVariant = modelLocationString.indexOf("variant");
        return modelLocationString.substring(indexVariant + 8); // + 8 because "variant=" is 8 chars
    }

    private void setVanillaBlockModel() {
        try {
            this.vanillaBlockModel = ModelLoaderRegistry.getModel(this.locationVanillaBlockModel);
            this.isMissingModel = false;
        } catch (Exception e) {
            LogHelper.error("Couldn't find IModel for ModelResourceLocation.");
            LogHelper.error("This should not happen. Please contact the author of GaymerCraft!");
            this.isMissingModel = true;
        }
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        if (this.enumDomainType.equals("botania")) {
            EnumVariantSlabBotania enumVariant = EnumVariantSlabBotania.byName(this.enumVariantName);

            if (enumVariant != null){
                this.locationVanillaBlockModel = enumVariant.getLocationModelDouble();
                setVanillaBlockModel();
                return ImmutableList.of(this.locationVanillaBlockModel);
            }
            else {
                LogHelper.error("Did not find the EnumVariant by name: " + enumVariantName + " This should never happen!");
                return ImmutableList.of();
            }
        } else {
            // No other mod supported for now.
            return ImmutableList.of();
        }
    }

    /**
     * This method is actually called before getDependencies(), which makes no sense to me...
     * Maybe, I should just return an empty List here and write my own setTextures(), which will
     * be called in bake(), which is guaranteed to be called after getDependencies().
     */
    @Override
    public Collection<ResourceLocation> getTextures() {
        return ImmutableList.of();
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        if (this.vanillaBlockModel == null) // This should never be the case since getDependencies() is guaranteed to be called before bake().
            setVanillaBlockModel();         // But better be safe than sorry, since this also sets the boolean isMissingModel.

        if (this.isMissingModel)
            return ModelLoaderRegistry.getModelOrLogError(new ResourceLocation(Reference.MOD_ID, "this_should_never_happen_pls_contact_the_mod_author"), "This should never happen. Please contact the mod author of GaymerCraft!").bake(state, format, bakedTextureGetter);

        return this.vanillaBlockModel.bake(state, format, bakedTextureGetter);
    }
}