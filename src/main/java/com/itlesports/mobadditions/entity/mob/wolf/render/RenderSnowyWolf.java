package com.itlesports.mobadditions.entity.mob.wolf.render;

import com.itlesports.mobadditions.entity.mob.wolf.SnowyWolfEntity;
import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderWolf;
import net.minecraft.src.ResourceLocation;

public class RenderSnowyWolf extends RenderWolf {
    private static final ResourceLocation WOLF_DO_NOT_WORRY_TEXTURES = new ResourceLocation("entity/mob/wolf/snowywolf_angry.png");
    private static final ResourceLocation WOLF_WILD_STARVING_TEXTURES = new ResourceLocation("entity/mob/wolf/snowywolf_starving.png");
    private static final ResourceLocation WOLF_TAME_STARVING_TEXTURES = new ResourceLocation("entity/mob/wolf/snowywolf_starving.png");
    protected static final ResourceLocation wolfTextures = new ResourceLocation("entity/mob/wolf/snowywolf.png");
    protected static final ResourceLocation tamedWolfTextures = new ResourceLocation("entity/mob/wolf/snowywolf_tame.png");
    protected static final ResourceLocation angryWolfTextures = new ResourceLocation("entity/mob/wolf/snowywolf_angry.png");
    protected static final ResourceLocation wolfCollarTextures = new ResourceLocation("entity/mob/wolf/wolf_collar.png");

    public RenderSnowyWolf(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
        super(par1ModelBase, par2ModelBase, par3);
    }
    protected ResourceLocation func_110914_a(SnowyWolfEntity wolf) {
        if ( wolf.isTamed() )
        {
            if ( wolf.isStarving() )
            {
                return WOLF_TAME_STARVING_TEXTURES;
            }
            return tamedWolfTextures;
        }
        else if ( wolf.isAngry() )
        {
            return angryWolfTextures;
        }
        else if ( wolf.isStarving() || wolf.hasAttackTarget())
        {
            return WOLF_WILD_STARVING_TEXTURES;
        }
        return wolf.isTamed() ? tamedWolfTextures : (wolf.isAngry() ? angryWolfTextures : wolfTextures);
    }
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110914_a((SnowyWolfEntity) par1Entity);
    }
}
