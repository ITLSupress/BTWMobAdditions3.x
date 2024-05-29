package com.itlesports.mobadditions.recipe;

import btw.crafting.recipe.RecipeManager;
import btw.item.BTWItems;
import com.itlesports.mobadditions.item.ModItems;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class CauldronRecipeList {
    public static void addRecipes() {
        RecipeManager.addCauldronRecipe(
                new ItemStack(ModItems.cookedFoxChop, 1),
                new ItemStack[]{
                        new ItemStack(ModItems.rawFoxChop)
                }
        );
        RecipeManager.addCauldronRecipe(
                new ItemStack(BTWItems.heartyStew, 5),
                new ItemStack[] {
                        new ItemStack(BTWItems.boiledPotato),
                        new ItemStack(BTWItems.cookedCarrot),
                        new ItemStack(BTWItems.brownMushroom, 3),
                        new ItemStack(BTWItems.flour),
                        new ItemStack(ModItems.cookedFoxChop),
                        new ItemStack(Item.bowlEmpty, 5)
                });
        RecipeManager.addStokedCauldronRecipe(
                new ItemStack(BTWItems.tallow, 1),
                new ItemStack[] {
                        new ItemStack(ModItems.cookedFoxChop, 8)
                });
        RecipeManager.addStokedCauldronRecipe(
                new ItemStack(BTWItems.tallow, 1),
                new ItemStack[] {
                        new ItemStack(ModItems.rawFoxChop, 8)
                });
    }
}
