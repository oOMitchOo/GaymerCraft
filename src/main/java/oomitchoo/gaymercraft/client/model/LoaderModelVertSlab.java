package oomitchoo.gaymercraft.client.model;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oomitchoo.gaymercraft.reference.Reference;

/**
 * Created by oOMitchOo on 07.01.2019.
 */
@SideOnly(Side.CLIENT)
public class LoaderModelVertSlab implements ICustomModelLoader {

    /**
     * Called whenever the resource packs are (re)loaded. In this method, any caches the ICustomModelLoader keeps should be dumped.
     */
    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        // Example for a modelLocation... gaymercraft:modsupport/botania/test_vert_slab#facing=north,variant=forest
        if (modelLocation.getResourceDomain().equals(Reference.MOD_ID) && modelLocation.getResourcePath().startsWith("modsupport/botania/")) {
            return true;
        }
        else return false;
    }

    /**
     * We don't actually need to load anything here (from the ResourceLocation). We can just initialize a new ModelSlab (IModel) and return it.
     * I guess the Exception is for the case, that a JSON is not loaded correctly here? So, we don't need it?
     */
    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws Exception {
        if (modelLocation.getResourcePath().contains("double"))
            return new ModelDoubleSlab(modelLocation);

        if (modelLocation.toString().contains("inventory")) {
            return new ModelHalfSlab(modelLocation, true);
        }

        return new ModelHalfSlab(modelLocation, false);
    }
}