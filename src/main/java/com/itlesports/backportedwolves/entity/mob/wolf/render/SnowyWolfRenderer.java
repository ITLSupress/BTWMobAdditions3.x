package com.itlesports.backportedwolves.entity.mob.wolf.render;

import com.itlesports.backportedwolves.entity.mob.wolf.SnowyWolfEntity;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.ModelBase;
import net.minecraft.src.OpenGlHelper;
import net.minecraft.src.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SnowyWolfRenderer extends RenderSnowyWolf{

    private static final ResourceLocation WOLF_DO_NOT_WORRY_TEXTURES = new ResourceLocation("entity/mob/wolf/snowywolf_angry");
    private static final ResourceLocation WOLF_WILD_STARVING_TEXTURES = new ResourceLocation("entity/mob/wolf/snowywolf_starving.png");
    private static final ResourceLocation WOLF_TAME_STARVING_TEXTURES = new ResourceLocation("entity/mob/wolf/snowywolf_starving.png");

    public SnowyWolfRenderer(ModelBase model, ModelBase modelOverlay, float fShadowSize )
    {
        super( model, modelOverlay, fShadowSize );
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase entity, int iRenderPass, float par3 )
    {
        if ( renderGlowingEyes((SnowyWolfEntity)entity, iRenderPass) )
        {
            return 1;
        }

        return super.shouldRenderPass( entity, iRenderPass, par3 );
    }

    @Override
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
            return anrgyWolfTextures;
        }
        else if ( wolf.isStarving() || wolf.hasAttackTarget())
        {
            return WOLF_WILD_STARVING_TEXTURES;
        }
        return super.func_110914_a(wolf);
    }

    //------------- Class Specific Methods ------------//

    private boolean renderGlowingEyes(SnowyWolfEntity wolf, int iRenderPass)
    {
        if ( iRenderPass == 2 && wolf.areEyesGlowing() )
        {
            this.bindTexture(WOLF_DO_NOT_WORRY_TEXTURES);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            GL11.glDisable(GL11.GL_LIGHTING);

            if ( wolf.isInvisible() )
            {
                GL11.glDepthMask( false );
            }
            else
            {
                GL11.glDepthMask( true );
            }

            char var5 = 61680;
            int var6 = var5 % 65536;
            int var7 = var5 / 65536;

            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var6 / 1.0F, (float)var7 / 1.0F);

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            return true;
        }

        return false;
    }

}
