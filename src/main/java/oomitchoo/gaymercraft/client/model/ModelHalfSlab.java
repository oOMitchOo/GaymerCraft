package oomitchoo.gaymercraft.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oomitchoo.gaymercraft.GaymerCraft;
import oomitchoo.gaymercraft.block.modsupport.botania.EnumVariantSlabBotania;
import oomitchoo.gaymercraft.helper.LogHelper;
import oomitchoo.gaymercraft.reference.Reference;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.util.vector.Vector3f;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.*;
import java.util.function.Function;

/**
 * Created by oOMitchOo on 07.01.2019.
 */
@SideOnly(Side.CLIENT)
public class ModelHalfSlab implements IModel {
    // TODO: I could use enumDomainType, if I have different mods using this model
    private final String enumDomainType;
    private final String enumVariantName;
    private final EnumFacing enumFacing;

    private boolean isMissingModel;
    private ResourceLocation locationVanillaSlabModel;
    private IModel vanillaSlabModel;
    private Collection<ResourceLocation> textureResourceLocations;



    public ModelHalfSlab(ResourceLocation modelLocation, boolean isItem) {
        this.enumDomainType = setEnumDomainType(modelLocation.getResourcePath());
        this.enumVariantName = setEnumVariantName(modelLocation.toString(), isItem);
        if (isItem)
            this.enumFacing = EnumFacing.SOUTH;
        else // It's a Block
            this.enumFacing = setEnumFacing(modelLocation.toString());
    }

    private String setEnumDomainType(String resourcePath) {
        if (resourcePath.contains("botania")) {
            return "botania";
        }
        else return "vanilla";
    }

    private String setEnumVariantName(String modelLocationString, boolean isItem) {
        int indexStart;
        if (isItem) {
            indexStart = modelLocationString.indexOf("_"); // gets index of FIRST occurrence.
            int indexEnd = modelLocationString.indexOf("#");
            return modelLocationString.substring(indexStart + 1, indexEnd);
        } else {
            indexStart = modelLocationString.indexOf("variant");
            return modelLocationString.substring(indexStart + 8);
        }
    }

    private EnumFacing setEnumFacing(String modelLocationString) {
        int indexFacing = modelLocationString.indexOf("facing");
        int indexVariant = modelLocationString.indexOf("variant");
        EnumFacing enumFacing = EnumFacing.byName(modelLocationString.substring(indexFacing + 7, indexVariant - 1));
        if (enumFacing != null)
            return enumFacing;
        else
            return EnumFacing.SOUTH;
    }

    private void setVanillaSlabModel() {
        try {
            this.vanillaSlabModel = ModelLoaderRegistry.getModel(this.locationVanillaSlabModel);
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
                this.locationVanillaSlabModel = enumVariant.getLocationModelSlab();
                setVanillaSlabModel();
                return ImmutableList.of(this.locationVanillaSlabModel);
            }
            else {
                LogHelper.error("Did not find the EnumVariant by name: " + enumVariantName + " This should never happen!");
                return ImmutableList.of();
            }
        }
        else {
            // No other mod supported for now.
            return ImmutableList.of();
        }
    }

    /**
     * This method is actually called before getDependencies(), which makes no sense to me...
     * I'm just returning an empty List here and wrote setTextureResourceLocations(), which will
     * be called in bake(), which is guaranteed to be called after getDependencies().
     */
    @Override
    public Collection<ResourceLocation> getTextures() {
        return ImmutableList.of();
    }

    private void setTextureResourceLocations() {
        if (!isMissingModel)
            this.textureResourceLocations = this.vanillaSlabModel.getTextures();
    }

    // TODO: It would be ideal, if I could use a normal block ResourceLocation here to get the textures
    //      but the BlockStateMappers didn't run yet, even if I put the blocks ResourceLocation in the getDependencies().
    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        if (this.vanillaSlabModel == null) // This should never be the case since getDependencies() is guaranteed to be called before bake().
            setVanillaSlabModel();         // But better be safe than sorry, since this also sets the boolean isMissingModel.

        setTextureResourceLocations();

        if (this.isMissingModel)
            return ModelLoaderRegistry.getModelOrLogError(new ResourceLocation(Reference.MOD_ID, "this_should_never_happen_oops"), "isMissingModel was true. This should never happen! Baking missingModel...").bake(state, format, bakedTextureGetter);


        int numberOfTextures = 0;
        if (!this.textureResourceLocations.isEmpty())
            numberOfTextures = this.textureResourceLocations.size();

        // only allow a Collection of TextureResourceCollections with 1 or 3 elements.
        if (numberOfTextures > 3) {
            Collection<ResourceLocation> textureMissingNo = new ArrayList<ResourceLocation>(1);
            textureMissingNo.add(TextureMap.LOCATION_MISSING_TEXTURE);
            this.textureResourceLocations = textureMissingNo;
            numberOfTextures = 1;
        } else if (numberOfTextures == 2) {
            this.textureResourceLocations.add(TextureMap.LOCATION_MISSING_TEXTURE);
            numberOfTextures++;
        }

        if (numberOfTextures == 1)
            // uses that one texture for all faces
            return new BakedModel(enumFacing, bakedTextureGetter.apply(textureResourceLocations.iterator().next()));
        else if (numberOfTextures == 3) {
            // uses first texture for top-face, second for sides, third for bottom-face
            Iterator<ResourceLocation> iterator = textureResourceLocations.iterator();
            return new BakedModel(enumFacing, bakedTextureGetter.apply(iterator.next()), bakedTextureGetter.apply(iterator.next()), bakedTextureGetter.apply(iterator.next()));
        }
        else if (numberOfTextures == 0) { // Means, we don't have any textureResourceLocations cached.
            return ModelLoaderRegistry.getModelOrLogError(new ResourceLocation(Reference.MOD_ID, "this_should_never_happen_oops"), "numberOfTextures was 0. This should never happen!").bake(state, format, bakedTextureGetter);
        }
        else {
            return ModelLoaderRegistry.getModelOrLogError(new ResourceLocation(Reference.MOD_ID, "this_should_never_happen_oops"), "numberOfTextures wasn't 0 (fancyMissingModel), 1 or 3 (ModelHalfSlab). This should never happen!").bake(state, format, bakedTextureGetter);
        }

        // OLD STUFF
        /*
        ModelStateComposition rotatedModelState = new ModelStateComposition(this.vanillaSlabModelOrMissingModel.getDefaultState(), new TRSRTransformation(ModelRotation.getModelRotation(90, 90*(this.enumFacing.getHorizontalIndex())).getMatrix()));
        return this.vanillaSlabModelOrMissingModel.bake(rotatedModelState, format, bakedTextureGetter);
        */
    }

    private class BakedModel implements IBakedModel {
        // This is the cache. getQuads(.., side,..) is called for every side which isn't affected by culling (for one specific block in the world)
        //      then it is called with side==null and gets all the faces, which ALWAYS are getting rendered (because they aren't affected by culling).
        // That means this cache will have the Keys UP, DOWN, NORTH, SOUTH, WEST, EAST (minus the opposite direction the vert slab is facing.
        //      because that BakedQuad is in the Key ->), NULL!
        private Map<EnumFacing, List<BakedQuad>> quadCache = new HashMap<EnumFacing, List<BakedQuad>>(6);
        private EnumFacing vertSlabFacing;
        private TextureAtlasSprite topTexture;
        private TextureAtlasSprite sidesTexture;
        private TextureAtlasSprite bottomTexture;

        private BakedModel (EnumFacing vertSlabFacing, TextureAtlasSprite top, TextureAtlasSprite sides, TextureAtlasSprite bottom) {
            this.vertSlabFacing = vertSlabFacing;
            this.topTexture = top;
            this.sidesTexture = sides;
            this.bottomTexture = bottom;
        }

        private BakedModel (EnumFacing vertSlabFacing, TextureAtlasSprite allFaces) {
            this(vertSlabFacing, allFaces, allFaces, allFaces);
        }

        /**
         * This getQuads() method probably isn't the fastest. It would probably faster, if I wouldn't use FaceBakery#makeBakedQuad().
         * But I implemented a cache (and tested it). I'm positive this method is fast after the first couple of call.
         */
        @Override
        public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing face, long rand) {
            // The BakedQuad for oppositeVertSlabFacing (never affected by culling) will already get returned for face==null.
            if (face == vertSlabFacing.getOpposite()) {
                // LogHelper.debug("Returning EMPTY quads list for face " + face);
                return Collections.<BakedQuad>emptyList();
            }

            // Get BakedQuads out of the cache, if the face (including null) already exists in it.
            if (quadCache.containsKey(face)) {
                // LogHelper.debug("Returning quads from CACHE for face " + face);
                return quadCache.get(face);
            }

            // LogHelper.debug("Generating NEW quads for face " + face);

            // I'm using an instance of a Forge class for baking of the quads.
            // Baking the quads myself, would probably be a little better performance wise.
            FaceBakery faceBakery = GaymerCraft.proxy.getFaceBakery();
            // =================================
            // Finding the start and end point of the vertical slab block.
            Vector3f vectorStart = new Vector3f(0F, 0F, 0F);
            Vector3f vectorEnd = new Vector3f(16F, 16F, 16F);
            int horizontalIndexSlab = this.vertSlabFacing.getHorizontalIndex();
            switch (horizontalIndexSlab) {
                case (0): // SOUTH
                    vectorStart.setZ(8);
                    break;
                case (1): // WEST
                    vectorEnd.setX(8);
                    break;
                case (2): // NORTH
                    vectorEnd.setZ(8);
                    break;
                case (3): // EAST
                    vectorStart.setX(8);
                    break;
            }
            // =================================
            // Make sure, I don't use null in makeBakedQuad().
            EnumFacing faceGettingRendered = face;
            if (face == null)
                faceGettingRendered = this.vertSlabFacing.getOpposite();
            // =================================
            // Find the uv starting and endpoints for each face texture.
            float[] uvCoords = new float[]{0F, 0F, 16F, 16F};
            int horizontalIndexFaceRendered = faceGettingRendered.getHorizontalIndex();
            int indexFaceRendered = faceGettingRendered.getIndex();
            if (horizontalIndexFaceRendered == ((horizontalIndexSlab + 1) % 4)) // next side clockwise
                uvCoords[0] = 8F;
            else if (horizontalIndexFaceRendered == ((horizontalIndexSlab + 3) % 4)) // third next side clockwise
                uvCoords[2] = 8F;
            else if (horizontalIndexFaceRendered == -1) { // UP and DOWN
                switch (horizontalIndexSlab) {
                    case (0):   // SOUTH
                        if (indexFaceRendered == 0) // DOWN
                            uvCoords[3] = 8F;
                        else                        // UP
                            uvCoords[1] = 8F;
                        break;
                    case (1):   // WEST
                        uvCoords[2] = 8F;
                        break;
                    case (2):   // NORTH
                        if (indexFaceRendered == 0) // DOWN
                            uvCoords[1] = 8F;
                        else                        // UP
                            uvCoords[3] = 8F;
                        break;
                    case (3):   // EAST
                        uvCoords[0] = 8F;
                        break;
                }
            }
            BlockFaceUV blockFaceUV = new BlockFaceUV(uvCoords, 0);
            // =================================
            // Choose which Texture to use for this face.
            TextureAtlasSprite textureToUse;
            if (indexFaceRendered == 0)
                textureToUse = bottomTexture;
            else if (indexFaceRendered == 1)
                textureToUse = topTexture;
            else
                textureToUse = sidesTexture;
            // =================================
            // The quadList only has a capacity of 1, because this simple model, doesn't have more quads per face.
            List<BakedQuad> quadList = new ArrayList<>(1);
            quadList.add(faceBakery.makeBakedQuad(
                    vectorStart,
                    vectorEnd,
                    // First and third parameter (EnumFacing, String textureIn) aren't used at all. I just added some random ones.
                    new BlockPartFace(EnumFacing.UP, -1, "missingno", blockFaceUV),
                    textureToUse,
                    faceGettingRendered,
                    TRSRTransformation.identity(),
                    new BlockPartRotation(new org.lwjgl.util.vector.Vector3f(), EnumFacing.Axis.Y, 0, false),
                    true, false));

            // Save the new BakedQuad list in the cache before returning it.
            quadCache.put(face, quadList);
            return quadList;
        }

        @Override
        public boolean isAmbientOcclusion() {
            return true;
        }

        @Override
        public boolean isGui3d() {
            return true;
        }

        @Override
        public boolean isBuiltInRenderer() {
            return false;
        }

        @Override
        public TextureAtlasSprite getParticleTexture() {
            return this.sidesTexture;
        }

        /**
         * This method get additional overrides before baking the quads.
         * (see ItemOverrideList class)
         */
        @Override
        public ItemOverrideList getOverrides() {
            return ItemOverrideList.NONE;
        }

        // TODO: Do I need to cache these? Or is it done by Forge?
        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            TRSRTransformation transform = TRSRTransformation.identity();

            switch (cameraTransformType)
            {
                case GUI:
                    transform = new TRSRTransformation(new javax.vecmath.Vector3f(0.5f/16f,-0.8f/16f,0), TRSRTransformation.quatFromXYZDegrees(new javax.vecmath.Vector3f(30, 225, 0)), new javax.vecmath.Vector3f(0.625f, 0.625f, 0.625f), null);
                    break;
                case GROUND:
                    transform = new TRSRTransformation(new javax.vecmath.Vector3f(0,3f/16f,0), null, new javax.vecmath.Vector3f(0.25f, 0.25f, 0.25f), null);
                    break;
                case FIXED:
                    transform = new TRSRTransformation(null, null, new javax.vecmath.Vector3f(0.5f, 0.5f, 0.5f), null);
                    break;
                case THIRD_PERSON_RIGHT_HAND:
                    transform = new TRSRTransformation(new javax.vecmath.Vector3f(0,2.5f/16f,0), TRSRTransformation.quatFromXYZDegrees(new javax.vecmath.Vector3f(75, 45, 0)), new javax.vecmath.Vector3f(0.375f, 0.375f, 0.375f), null);
                    break;
                case THIRD_PERSON_LEFT_HAND:
                    TRSRTransformation flipX = new TRSRTransformation(null, null, new javax.vecmath.Vector3f(-1, 1, 1), null);
                    TRSRTransformation.blockCenterToCorner(flipX.compose(TRSRTransformation.blockCornerToCenter(new TRSRTransformation(new javax.vecmath.Vector3f(0,2.5f/16f,0), TRSRTransformation.quatFromXYZDegrees(new javax.vecmath.Vector3f(75, 45, 0)), new javax.vecmath.Vector3f(0.375f, 0.375f, 0.375f), null))).compose(flipX));
                    break;
                case FIRST_PERSON_RIGHT_HAND:
                    transform = new TRSRTransformation(null, TRSRTransformation.quatFromXYZDegrees(new javax.vecmath.Vector3f(0, 45, 0)), new javax.vecmath.Vector3f(0.4f, 0.4f, 0.4f), null);
                    break;
                case FIRST_PERSON_LEFT_HAND:
                    transform = new TRSRTransformation(null, TRSRTransformation.quatFromXYZDegrees(new javax.vecmath.Vector3f(0, 225, 0)), new javax.vecmath.Vector3f(0.4f, 0.4f, 0.4f), null);
                    break;
                default:
                    break;
            }
            return Pair.of(this, transform.getMatrix());
        }
    }
}