package com.itlesports.mobadditions.recipe;

import btw.crafting.recipe.RecipeManager;
import com.itlesports.mobadditions.item.ModItems;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.ItemStack;

public class SmeltingRecipeList {
    public static void addRecipes() {
        addCampfireRecipes();
        addSmeltingRecipes();
    }
    public static void addSmeltingRecipes() {
        FurnaceRecipes.smelting().addSmelting(ModItems.rawFoxChop.itemID, new ItemStack(ModItems.cookedFoxChop), 0);
    }
    public static void addCampfireRecipes() {
        RecipeManager.addCampfireRecipe(ModItems.rawFoxChop.itemID, new ItemStack(ModItems.cookedFoxChop));
    }
}
