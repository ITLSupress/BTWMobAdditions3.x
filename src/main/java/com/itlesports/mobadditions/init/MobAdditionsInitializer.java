package com.itlesports.mobadditions.init;

import btw.AddonHandler;
import com.itlesports.mobadditions.block.ModBlocks;
import com.itlesports.mobadditions.entity.ModEntities;
import com.itlesports.mobadditions.entity.ModRenderMapper;
import com.itlesports.mobadditions.item.ModItems;
import com.itlesports.mobadditions.recipe.CauldronRecipeList;
import com.itlesports.mobadditions.recipe.CraftingRecipeList;
import com.itlesports.mobadditions.recipe.MillstoneRecipeList;
import com.itlesports.mobadditions.recipe.SmeltingRecipeList;
import net.fabricmc.api.ModInitializer;

public class MobAdditionsInitializer implements ModInitializer {

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		AddonHandler.logMessage("Mob Additions Initializing...");
		AddonHandler.logMessage("Mob Additions Initializing Items...");
		ModItems.registerModItems();
		AddonHandler.logMessage("Mob Additions Items Initialized!");
		AddonHandler.logMessage("Mob Addtions Initializing Blocks...");
		ModBlocks.registerModBlocks();
		AddonHandler.logMessage("Mob Additions Blocks Initialized!");
		AddonHandler.logMessage("Mob Additions Initializing Recipes...");
		CraftingRecipeList.addRecipes();
		CauldronRecipeList.addRecipes();
		MillstoneRecipeList.addRecipes();
		SmeltingRecipeList.addRecipes();
		AddonHandler.logMessage("Mob Additions Recipes Initialized");
		AddonHandler.logMessage("Mob Additions Initializing Entites...");
		ModEntities.createModEntityMappings();
		AddonHandler.logMessage("Mob Additions Entities Initialized!");
		AddonHandler.logMessage("Mob Additions Initializing Entity Renderers...!");
		ModRenderMapper.createModEntityRenderers();
		ModRenderMapper.createTileEntityRenderers();
		AddonHandler.logMessage("Mob Additions Entity Renderers Initialized!");
	}

}
