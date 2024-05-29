package com.itlesports.mobadditions.entity.mob.aquatic.squid;

import btw.client.render.entity.SquidRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelBase;
import net.minecraft.src.OpenGlHelper;
import org.lwjgl.opengl.GL11;
public class RenderGlowSquid extends SquidRenderer {

    public RenderGlowSquid(ModelBase var1)
        {
            this.setRenderPassModel(var1);
        }

    protected int shouldRenderPass(EntityLiving var1, int var2, float var3) {
        return this.renderGlow((GlowSquidEntity)var1, var2);
    }
    @Environment(EnvType.CLIENT)
    protected int renderGlow(GlowSquidEntity var1, int var2)
    {
        if (var1.getSquidType() == 0)
        {
            return -1;
        }
        else
        {
            //this.loadTexture("/mobadditions/entity/mob/aquatic/glowsquid.png");
            float var4 = 0.1F;
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            GL11.glDisable(GL11.GL_LIGHTING);

            if (var1.hurtTime > 0)
            {
                return -1;
            }

            if (var1.isInvisible())
            {
                GL11.glDepthMask(false);
            }
            else
            {
                GL11.glDepthMask(true);
            }

            char var5 = 61680;
            int var6 = var5 % 65536;
            int var7 = var5 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var6 / 0.1F, (float)var7 / 0.1F);
            GL11.glColor4f(0.3F, 0.3F, 0.3F, 0.1F);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glColor4f(0.3F, 0.3F, 0.3F, var4);
            return 1;
        }
    }

}
