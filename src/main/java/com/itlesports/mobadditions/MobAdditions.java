package com.itlesports.mobadditions;

import btw.AddonHandler;
import btw.BTWAddon;
import com.itlesports.mobadditions.block.ModBlocks;
import com.itlesports.mobadditions.entity.mob.aquatic.squid.GlowSquidEntity;
import com.itlesports.mobadditions.entity.mob.aquatic.squid.LavaSquidEntity;
import com.itlesports.mobadditions.entity.mob.aquatic.squid.RenderGlowSquid;
import com.itlesports.mobadditions.entity.mob.fox.ArcticFoxEntity;
import com.itlesports.mobadditions.entity.mob.fox.FoxEntity;
import com.itlesports.mobadditions.entity.mob.wolf.*;
import com.itlesports.mobadditions.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Map;

public class MobAdditions extends BTWAddon {

    private static MobAdditions instance = new MobAdditions();
    private Map<String, String> propertyValues;
    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
        AddBiomeSpawn();
        AddEntityRenderer();
        ModBlocks.registerModBlocks();
        ModItems.registerModItems();
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
