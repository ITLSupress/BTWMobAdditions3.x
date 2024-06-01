package com.itlesports.mobadditions.entity.mob.rideable.render;

import com.itlesports.mobadditions.entity.mob.rideable.GoatEntity;
import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderLiving;
import net.minecraft.src.ResourceLocation;

public class RenderGoatEntity extends RenderLiving {
    protected static final ResourceLocation goatTextures = new ResourceLocation("entity/mob/goat/goat.png");

    public RenderGoatEntity(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getGoatTextures(GoatEntity par1EntityGoat)
    {
        return goatTextures;
    }
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getGoatTextures((GoatEntity)par1Entity);
    }
}
