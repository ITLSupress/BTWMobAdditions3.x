package com.itlesports.mobadditions.entity;

import com.itlesports.mobadditions.entity.mob.aquatic.squid.EntityLavaSquid;
import com.itlesports.mobadditions.entity.mob.aquatic.squid.EntityLavaSquidRenderer;
import com.itlesports.mobadditions.entity.mob.fox.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.RenderManager;

@Environment(EnvType.CLIENT)
public class ModRenderMapper {
    public static void createTileEntityRenderers() {

    }
    public static void createModEntityRenderers() {
        RenderManager.addEntityRenderer(FoxEntity.class, new FoxRenderer(new FoxEntityModel(), new FoxEntityModel(), 0.5F));
        RenderManager.addEntityRenderer(ArcticFoxEntity.class, new ArcticFoxRenderer(new ArcticFoxEntityModel(), new ArcticFoxEntityModel(), 0.5F));
        RenderManager.addEntityRenderer(EntityLavaSquid.class, new EntityLavaSquidRenderer());
    }
}
