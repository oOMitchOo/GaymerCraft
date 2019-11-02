package oomitchoo.gaymercraft.item.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NoContainerShapelessRecipe implements ICraftingRecipe {
    private final ResourceLocation id;
    private final String group;
    private final ItemStack recipeOutput;
    private final NonNullList<Ingredient> recipeItems;
    private final boolean isSimple;

    public NoContainerShapelessRecipe(ResourceLocation resLoc, String group, ItemStack recipeOutput, NonNullList<Ingredient> recipeItems) {
        this.id = resLoc;
        this.group = group;
        this.recipeOutput = recipeOutput;
        this.recipeItems = recipeItems;
        this.isSimple = recipeItems.stream().allMatch(Ingredient::isSimple);
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public IRecipeSerializer<?> getSerializer() {
        return IRecipeSerializer.CRAFTING_SHAPELESS;
    }

    public String getGroup() {
        return this.group;
    }

    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInventory p_179532_1_) {
        return NonNullList.withSize(p_179532_1_.getSizeInventory(), ItemStack.EMPTY);
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.recipeItems;
    }

    public boolean matches(CraftingInventory p_77569_1_, World p_77569_2_) {
        RecipeItemHelper recipeitemhelper = new RecipeItemHelper();
        List<ItemStack> inputs = new ArrayList();
        int i = 0;

        for(int j = 0; j < p_77569_1_.getSizeInventory(); ++j) {
            ItemStack itemstack = p_77569_1_.getStackInSlot(j);
            if (!itemstack.isEmpty()) {
                ++i;
                if (this.isSimple) {
                    recipeitemhelper.func_221264_a(itemstack, 1);
                } else {
                    inputs.add(itemstack);
                }
            }
        }

        boolean var10000;
        label43: {
            if (i == this.recipeItems.size()) {
                if (this.isSimple) {
                    if (recipeitemhelper.canCraft(this, (IntList)null)) {
                        break label43;
                    }
                } else if (RecipeMatcher.findMatches(inputs, this.recipeItems) != null) {
                    break label43;
                }
            }

            var10000 = false;
            return var10000;
        }

        var10000 = true;
        return var10000;
    }

    public ItemStack getCraftingResult(CraftingInventory p_77572_1_) {
        return this.recipeOutput.copy();
    }

    public boolean canFit(int p_194133_1_, int p_194133_2_) {
        return p_194133_1_ * p_194133_2_ >= this.recipeItems.size();
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<NoContainerShapelessRecipe> {

        public Serializer() {
        }

        public NoContainerShapelessRecipe read(ResourceLocation resourceLocation, JsonObject jsonObject) {
            String s = JSONUtils.getString(jsonObject, "group", "");
            NonNullList<Ingredient> nonnulllist = readIngredients(JSONUtils.getJsonArray(jsonObject, "ingredients"));
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (nonnulllist.size() > 9) {
                throw new JsonParseException("Too many ingredients for shapeless recipe the max is " + 9);
            } else {
                ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(jsonObject, "result"));
                return new NoContainerShapelessRecipe(resourceLocation, s, itemstack, nonnulllist);
            }
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray p_199568_0_) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for(int i = 0; i < p_199568_0_.size(); ++i) {
                Ingredient ingredient = Ingredient.deserialize(p_199568_0_.get(i));
                if (!ingredient.hasNoMatchingItems()) {
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }

        public NoContainerShapelessRecipe read(ResourceLocation resourceLocation, PacketBuffer buffer) {
            String s = buffer.readString(32767);
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

            for(int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, Ingredient.read(buffer));
            }

            ItemStack itemstack = buffer.readItemStack();
            return new NoContainerShapelessRecipe(resourceLocation, s, itemstack, nonnulllist);
        }

        public void write(PacketBuffer buffer, NoContainerShapelessRecipe recipe) {
            buffer.writeString(recipe.group);
            buffer.writeVarInt(recipe.recipeItems.size());
            Iterator var3 = recipe.recipeItems.iterator();

            while(var3.hasNext()) {
                Ingredient ingredient = (Ingredient)var3.next();
                ingredient.write(buffer);
            }

            buffer.writeItemStack(recipe.recipeOutput);
        }
    }
}
