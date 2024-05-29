package com.itlesports.mobadditions.recipe;

import btw.block.BTWBlocks;
import btw.crafting.recipe.RecipeManager;
import btw.util.color.Color;
import com.itlesports.mobadditions.item.ModItems;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class MillstoneRecipeList {
    public static void addRecipes() {
        RecipeManager.addMillStoneRecipe(new ItemStack(ModItems.glowPowder.itemID, 2, 0), new ItemStack(ModItems.glowInkSac));
        RecipeManager.addMillStoneRecipe(new ItemStack(ModItems.lavaPowder.itemID, 2, 0), new ItemStack(ModItems.lavaInkSac));
        RecipeManager.addMillStoneRecipe(
                new ItemStack[] {
                        new ItemStack(Item.silk),
                        new ItemStack(Item.silk),
                        new ItemStack(Item.silk),
                        new ItemStack(Item.silk),
                        new ItemStack(Item.silk),
                        new ItemStack(Item.silk),
                        new ItemStack(Item.silk),
                        new ItemStack(Item.silk),
                        new ItemStack(Item.silk),
                        new ItemStack(Item.silk),
                        new ItemStack(Item.dyePowder, 1, Color.RED.colorID),
                        new ItemStack(Item.dyePowder, 1, Color.RED.colorID),
                        new ItemStack(Item.dyePowder, 1, Color.RED.colorID),
                        new ItemStack(ModItems.rawFoxChop, 1, 0)
                },
                new ItemStack[] {
                        new ItemStack(BTWBlocks.companionCube)
                });

        // companion slab
        RecipeManager.addMillStoneRecipe(new ItemStack(ModItems.rawFoxChop, 1, 0), new ItemStack(BTWBlocks.companionCube, 1, 1));
    }
}
