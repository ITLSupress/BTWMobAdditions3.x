package com.itlesports.mobadditions;

import btw.AddonHandler;
import btw.BTWAddon;
import com.itlesports.mobadditions.block.ModBlocks;
import com.itlesports.mobadditions.entity.ModEntities;
import com.itlesports.mobadditions.entity.ModRenderMapper;
import com.itlesports.mobadditions.entity.mob.aquatic.squid.GlowSquidEntity;
import com.itlesports.mobadditions.entity.mob.aquatic.squid.LavaSquidEntity;
import com.itlesports.mobadditions.entity.mob.aquatic.squid.render.RenderGlowSquid;
import com.itlesports.mobadditions.entity.mob.fox.ArcticFoxEntity;
import com.itlesports.mobadditions.entity.mob.fox.FoxEntity;
import com.itlesports.mobadditions.entity.mob.wolf.*;
import com.itlesports.mobadditions.item.ModItems;
import com.itlesports.mobadditions.recipe.CauldronRecipeList;
import com.itlesports.mobadditions.recipe.CraftingRecipeList;
import com.itlesports.mobadditions.recipe.MillstoneRecipeList;
import com.itlesports.mobadditions.recipe.SmeltingRecipeList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.src.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class MobAdditions extends BTWAddon {
    protected ModContainer parentMod;
    protected String addonName;
    protected String versionString;
    protected String modID;
    protected boolean isRequiredClientAndServer = false;

    public MobAdditions() {
    }

    final void setup(@NotNull ModContainer mod){
        this.parentMod  = mod;
        AddonHandler.addMod(this);
        ModMetadata meta = mod.getMetadata();
        this.addonName = meta.getName();
        this.versionString = meta.getVersion().getFriendlyString();
        this.modID = meta.getId();
        this.isRequiredClientAndServer = true;
    }
    public String getName() {
        return "Mob Additions CE 3.x";
    }

    public String getVersionString() {
        return "0.1.1";
    }

    public String getModID() {
        return "mobadditions";
    }

    private static MobAdditions instance = new MobAdditions();
    private Map<String, String> propertyValues;
    @Override
    public void preInitialize() {
        MobAdditions.getInstance();
    }
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
        AddBiomeSpawn();
        AddEntityRenderer();
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
    @Override
    public void handleConfigProperties(Map<String, String> propertyValues) {
        this.propertyValues = propertyValues;
        registerConfigIDs();
    }
    @Environment(EnvType.CLIENT)
    private void AddEntityRenderer()
    {
        RenderManager.addEntityRenderer(GlowSquidEntity.class, new RenderGlowSquid(new ModelSquid()));
    }
    private void registerConfigIDs() {
        this.registerProperty("rawFoxChopItem", "700", "***Item IDs***\n\n");
        this.registerProperty("cookedFoxChopItem", "701", "");

        this.registerProperty("glowInkSacItem", "702", "");



        this.registerProperty("ashenwolfEntity", "701", "***Entity IDs***\n\n");
        this.registerProperty("blackwolfEntity", "702", "");
        this.registerProperty("chestnutwolfEntity", "703", "");
        this.registerProperty("rustywolfEntity", "700", "");
        this.registerProperty("snowywolfEntity", "704", "n");
        this.registerProperty("spottedwolfEntity", "705", "");
        this.registerProperty("stripedwolfEntity", "706", "");
        this.registerProperty("woodswolfEntity", "707", "");
        this.registerProperty("foxEntity", "709", "");

        this.registerProperty("glowsquidEntity", "708", "");
    }
    private void AddBiomeSpawn()
    {
        BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.waterCreature).add(new SpawnListEntry(GlowSquidEntity.class, 40, 4, 4));
        BiomeGenBase.river.getSpawnableList(EnumCreatureType.waterCreature).add(new SpawnListEntry(GlowSquidEntity.class, 40, 4, 4));

        BiomeGenBase.taiga.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(AshenWolfEntity.class, 20, 4, 4));
        BiomeGenBase.taigaHills.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(AshenWolfEntity.class, 20, 4, 4));
        BiomeGenBase.iceMountains.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(AshenWolfEntity.class, 20, 4, 4));
        BiomeGenBase.icePlains.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(AshenWolfEntity.class, 20, 4, 4));

        BiomeGenBase.taigaHills.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(ArcticFoxEntity.class, 10, 4, 4));
        BiomeGenBase.iceMountains.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(ArcticFoxEntity.class, 10, 4, 4));
        BiomeGenBase.icePlains.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(ArcticFoxEntity.class, 30, 4, 4));
        BiomeGenBase.taiga.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(ArcticFoxEntity.class, 30, 4, 4));

        BiomeGenBase.plains.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(FoxEntity.class, 10, 4, 4));
        BiomeGenBase.forest.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(FoxEntity.class, 10, 4, 4));
        BiomeGenBase.forestHills.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(FoxEntity.class, 10, 4, 4));

        BiomeGenBase.extremeHills.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(BlackWolfEntity.class, 20, 4, 4));
        BiomeGenBase.extremeHillsEdge.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(BlackWolfEntity.class, 20, 4, 4));

        BiomeGenBase.mushroomIsland.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(ChestnutWolfEntity.class, 20, 4, 4));
        BiomeGenBase.mushroomIslandShore.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(ChestnutWolfEntity.class, 20, 4, 4));

        BiomeGenBase.jungle.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(RustyWolfEntity.class, 30, 4, 4));
        BiomeGenBase.jungleHills.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(RustyWolfEntity.class, 30, 4, 4));

        BiomeGenBase.icePlains.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(SnowyWolfEntity.class, 20, 4, 4));
        BiomeGenBase.iceMountains.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(SnowyWolfEntity.class, 20, 4, 4));

        BiomeGenBase.swampland.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(SpottedWolfEntity.class, 20, 4, 4));
        BiomeGenBase.plains.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(SpottedWolfEntity.class, 5, 4 ,4));

        BiomeGenBase.desert.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(StripedWolfEntity.class, 20, 4, 4));
        BiomeGenBase.desertHills.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(StripedWolfEntity.class, 20, 4, 4));

        BiomeGenBase.forest.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(WoodsWolfEntity.class, 20, 4, 4));
        BiomeGenBase.forestHills.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(WoodsWolfEntity.class, 20, 4, 4));


        //<biome>.getSpawnableList(EnumCreatureType.<type>).add(new SpawnListEntry(<yourEntity>.class, <weight>, <maxNumber>, <minNumber>));
        BiomeGenBase.hell.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(LavaSquidEntity.class, 100, 4, 4));
    }


    public static MobAdditions getInstance() {
        if (instance == null)
            instance = new MobAdditions();
        return instance;
    }
}
