package com.itlesports.mobadditions.entity;

import com.itlesports.mobadditions.entity.mob.aquatic.squid.EntityLavaSquid;
import com.itlesports.mobadditions.entity.mob.aquatic.squid.render.EntityLavaSquidRenderer;
import com.itlesports.mobadditions.entity.mob.fox.*;
import com.itlesports.mobadditions.entity.mob.fox.model.ArcticFoxEntityModel;
import com.itlesports.mobadditions.entity.mob.fox.model.FoxEntityModel;
import com.itlesports.mobadditions.entity.mob.fox.render.ArcticFoxRenderer;
import com.itlesports.mobadditions.entity.mob.fox.render.FoxRenderer;
import com.itlesports.mobadditions.entity.mob.wolf.*;
import com.itlesports.mobadditions.entity.mob.wolf.render.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.ModelWolf;
import net.minecraft.src.RenderManager;

@Environment(EnvType.CLIENT)
public class ModRenderMapper {
    public static void createTileEntityRenderers() {

    }
    public static void createModEntityRenderers() {
        RenderManager.addEntityRenderer(FoxEntity.class, new FoxRenderer(new FoxEntityModel(), new FoxEntityModel(), 0.5F));
        RenderManager.addEntityRenderer(ArcticFoxEntity.class, new ArcticFoxRenderer(new ArcticFoxEntityModel(), new ArcticFoxEntityModel(), 0.5F));
        RenderManager.addEntityRenderer(EntityLavaSquid.class, new EntityLavaSquidRenderer());
        RenderManager.addEntityRenderer(AshenWolfEntity.class, new AshenWolfRenderer(new ModelWolf(), new ModelWolf(), 0.5F));
        RenderManager.addEntityRenderer(BlackWolfEntity.class, new BlackWolfRenderer(new ModelWolf(), new ModelWolf(), 0.5F));
        RenderManager.addEntityRenderer(ChestnutWolfEntity.class, new ChestnutWolfRenderer(new ModelWolf(), new ModelWolf(), 0.5F));
        RenderManager.addEntityRenderer(RustyWolfEntity.class, new RustyWolfRenderer(new ModelWolf(), new ModelWolf(), 0.5F));
        RenderManager.addEntityRenderer(SnowyWolfEntity.class, new SnowyWolfRenderer(new ModelWolf(), new ModelWolf(), 0.5F));
        RenderManager.addEntityRenderer(SpottedWolfEntity.class, new SpottedWolfRenderer(new ModelWolf(), new ModelWolf(), 0.5F));
        RenderManager.addEntityRenderer(StripedWolfEntity.class, new StripedWolfRenderer(new ModelWolf(), new ModelWolf(), 0.5F));
        RenderManager.addEntityRenderer(WoodsWolfEntity.class, new WoodsWolfRenderer(new ModelWolf(), new ModelWolf(), 0.5F));
    }
}
