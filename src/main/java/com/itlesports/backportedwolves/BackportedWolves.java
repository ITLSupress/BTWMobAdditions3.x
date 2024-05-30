package com.itlesports.backportedwolves;

import btw.AddonHandler;
import btw.BTWAddon;
import com.itlesports.backportedwolves.entity.ModEntities;
import com.itlesports.backportedwolves.entity.ModRenderMapper;
import com.itlesports.backportedwolves.entity.mob.wolf.*;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.src.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class BackportedWolves extends BTWAddon {
    protected ModContainer parentMod;
    protected String addonName;
    protected String versionString;
    protected String modID;
    protected boolean isRequiredClientAndServer = false;

    public BackportedWolves() {
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
        return "Backported Wolves CE 3.x";
    }

    public String getVersionString() {
        return "1.0.0";
    }

    public String getModID() {
        return "backportedwolves";
    }

    private static BackportedWolves instance = new BackportedWolves();
    private Map<String, String> propertyValues;
    @Override
    public void preInitialize() {
        BackportedWolves.getInstance();
    }
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
        AddBiomeSpawn();
        AddonHandler.logMessage("Backported Wolves Initializing...");
        AddonHandler.logMessage("Backported Wolves Initializing Entites...");
        ModEntities.createModEntityMappings();
        AddonHandler.logMessage("Backported Wolves Entities Initialized!");
        AddonHandler.logMessage("Backported Wolves Initializing Entity Renderers...!");
        ModRenderMapper.createModEntityRenderers();
        ModRenderMapper.createTileEntityRenderers();
        AddonHandler.logMessage("Backported Wolves Entity Renderers Initialized!");
    }
    @Override
    public void handleConfigProperties(Map<String, String> propertyValues) {
        this.propertyValues = propertyValues;
        registerConfigIDs();
    }
    private void registerConfigIDs() {
        this.registerProperty("ashenwolfEntity", "701", "***Entity IDs***\n\n");
        this.registerProperty("blackwolfEntity", "702", "");
        this.registerProperty("chestnutwolfEntity", "703", "");
        this.registerProperty("rustywolfEntity", "700", "");
        this.registerProperty("snowywolfEntity", "704", "n");
        this.registerProperty("spottedwolfEntity", "705", "");
        this.registerProperty("stripedwolfEntity", "706", "");
        this.registerProperty("woodswolfEntity", "707", "");
    }
    private void AddBiomeSpawn()
    {
        BiomeGenBase.taiga.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(AshenWolfEntity.class, 20, 4, 4));
        BiomeGenBase.taigaHills.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(AshenWolfEntity.class, 20, 4, 4));
        BiomeGenBase.iceMountains.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(AshenWolfEntity.class, 20, 4, 4));
        BiomeGenBase.icePlains.getSpawnableList(EnumCreatureType.creature).add(new SpawnListEntry(AshenWolfEntity.class, 20, 4, 4));

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
    }


    public static BackportedWolves getInstance() {
        if (instance == null)
            instance = new BackportedWolves();
        return instance;
    }
}
