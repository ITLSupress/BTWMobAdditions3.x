package com.itlesports.mobadditions.init;

import net.minecraft.src.ModelSquid;
import com.itlesports.mobadditions.entity.mob.aquatic.squid.GlowSquidEntity;
import com.itlesports.mobadditions.entity.mob.aquatic.squid.RenderGlowSquid;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.RenderManager;

@Environment(EnvType.CLIENT)
public class MobAdditionsClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //AddEntityRenderer();
    }

    @Environment(EnvType.CLIENT)
    private void AddEntityRenderer()
    {
        RenderManager.addEntityRenderer(GlowSquidEntity.class, new RenderGlowSquid(new ModelSquid()));
    }

}
