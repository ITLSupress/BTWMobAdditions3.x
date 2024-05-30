package com.itlesports.backportedwolves.entity;

import com.itlesports.backportedwolves.entity.mob.wolf.*;

import net.minecraft.src.EntityList;

public class ModEntities {
    public static int AshenWolfEntityID = 700;
    public static int BlackWolfEntityID = 701;
    public static int ChestnutWolfEntityID = 702;
    public static int RustyWolfEntityID = 703;
    public static int SnowyWolfEntityID = 704;
    public static int SpottedWolfEntityID = 705;
    public static int StripedWolfEntityID = 706;
    public static int WoodsWolfEntityID = 707;
    public static void createModEntityMappings() {
        EntityList.addMapping(AshenWolfEntity.class,"ashenwolfEntity", AshenWolfEntityID,0,0);
        EntityList.addMapping(BlackWolfEntity.class,"blackwolfEntity", BlackWolfEntityID,0,0);
        EntityList.addMapping(ChestnutWolfEntity.class,"chestnutwolfEntity", ChestnutWolfEntityID,0,0);
        EntityList.addMapping(RustyWolfEntity.class,"rustywolfEntity", RustyWolfEntityID,0,0);
        EntityList.addMapping(SnowyWolfEntity.class,"snowywolfEntity", SnowyWolfEntityID,0,0);
        EntityList.addMapping(SpottedWolfEntity.class,"spottedwolfEntity", SpottedWolfEntityID,0,0);
        EntityList.addMapping(StripedWolfEntity.class,"stripedwolfEntity", StripedWolfEntityID,0,0);
        EntityList.addMapping(WoodsWolfEntity.class,"woodswolfEntity", WoodsWolfEntityID,0,0);
    }
}
