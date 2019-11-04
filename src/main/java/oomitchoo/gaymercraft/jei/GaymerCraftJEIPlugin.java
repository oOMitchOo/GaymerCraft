package oomitchoo.gaymercraft.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.util.ResourceLocation;
import oomitchoo.gaymercraft.config.Config;
import oomitchoo.gaymercraft.init.ModItems;
import oomitchoo.gaymercraft.reference.Reference;

import java.util.ArrayList;

@JeiPlugin
public class GaymerCraftJEIPlugin implements IModPlugin {

    public GaymerCraftJEIPlugin() {

    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Reference.MOD_ID, "jei_plugin");
    }

    /**
     * If your item has subtypes that depend on NBT or capabilities, use this to help JEI identify those subtypes correctly.
     */
    public void registerItemSubtypes(ISubtypeRegistration registration) {

    }

    /**
     * Register special ingredients, beyond the basic ItemStack and FluidStack.
     */
    public void registerIngredients(IModIngredientRegistration registration) {

    }

    /**
     * Register the categories handled by this plugin.
     * These are registered before recipes so they can be checked for validity.
     */
    public void registerCategories(IRecipeCategoryRegistration registration) {

    }

    /**
     * Register modded extensions to the vanilla crafting recipe category.
     * Custom crafting recipes for your mod should use this to tell JEI how they work.
     */
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {

    }

    /**
     * Register modded recipes.
     */
    public void registerRecipes(IRecipeRegistration registration) {

    }

    /**
     * Register recipe transfer handlers (move ingredients from the inventory into crafting GUIs).
     */
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {

    }

    /**
     * Register recipe catalysts.
     * Recipe Catalysts are ingredients that are needed in order to craft other things.
     * Vanilla examples of Recipe Catalysts are the Crafting Table and Furnace.
     */
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {

    }

    /**
     * Register various GUI-related things for your mod.
     * This includes adding clickable areas in your guis to open JEI,
     * and adding areas on the screen that JEI should avoid drawing.
     */
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {

    }

    /**
     * Register advanced features for your mod plugin.
     */
    public void registerAdvanced(IAdvancedRegistration registration) {

    }

    /**
     * Called when jei's runtime features are available, after all mods have registered.
     */
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        if(!Config.VERTICAL_SLABS_ENABLED.get()) {
            ArrayList collection = new ArrayList();
            collection.add(ModItems.OAK_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.SPRUCE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.BIRCH_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.JUNGLE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.ACACIA_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.DARK_OAK_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.STONE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.SMOOTH_STONE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.SANDSTONE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.CUT_SANDSTONE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.PETRIFIED_OAK_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.COBBLESTONE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.BRICK_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.STONE_BRICK_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.NETHER_BRICK_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.QUARTZ_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.RED_SANDSTONE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.CUT_RED_SANDSTONE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.PURPUR_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.PRISMARINE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.PRISMARINE_BRICK_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.DARK_PRISMARINE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.POLISHED_GRANITE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.SMOOTH_RED_SANDSTONE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.MOSSY_STONE_BRICK_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.POLISHED_DIORITE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.MOSSY_COBBLESTONE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.END_STONE_BRICK_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.SMOOTH_SANDSTONE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.SMOOTH_QUARTZ_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.GRANITE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.ANDESITE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.RED_NETHER_BRICK_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.POLISHED_ANDESITE_VERT_SLAB.getDefaultInstance());
            collection.add(ModItems.DIORITE_VERT_SLAB.getDefaultInstance());

            jeiRuntime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM, collection);
        }
    }
}
