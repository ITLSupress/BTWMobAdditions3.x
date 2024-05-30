package com.itlesports.mobadditions.entity.mob.fox.render;


import com.itlesports.mobadditions.entity.mob.fox.FoxEntity;
import com.prupe.mcpatcher.cc.ColorizeEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

@Environment(EnvType.CLIENT)
public class RenderFox extends RenderLiving
{
    private static final ResourceLocation FOX_DO_NOT_WORRY_TEXTURES = new ResourceLocation("entity/mob/fox/fox_angry.png");
    private static final ResourceLocation FOX_WILD_STARVING_TEXTURES = new ResourceLocation("entity/mob/fox/fox_wild_starving.png");
    private static final ResourceLocation FOX_TAME_STARVING_TEXTURES = new ResourceLocation("entity/mob/fox/fox_tame_starving.png");
    protected static final ResourceLocation foxTextures = new ResourceLocation("entity/mob/fox/fox.png");
    protected static final ResourceLocation tamedFoxTextures = new ResourceLocation("entity/fox/fox_tame.png");
    protected static final ResourceLocation angryFoxTextures = new ResourceLocation("entity/fox/fox_angry.png");
    protected static final ResourceLocation wolfCollarTextures = new ResourceLocation("textures/entity/wolf/wolf_collar.png");

    public RenderFox(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
    {
        super(par1ModelBase, par3);
        this.setRenderPassModel(par2ModelBase);
    }

    protected float getTailRotation(FoxEntity par1FoxEntity, float par2)
    {
        return par1FoxEntity.getTailRotation();
    }

    protected int func_82447_a(FoxEntity par1FoxEntity, int par2, float par3)
    {
        float var4;

        if (par2 == 0 && par1FoxEntity.getWolfShaking())
        {
            var4 = par1FoxEntity.getBrightness(par3) * par1FoxEntity.getShadingWhileShaking(par3);
            //this.loadTexture(par1FoxEntity.getTexture());
            GL11.glColor3f(var4, var4, var4);
            return 1;
        }
        else if (par2 == 1 && par1FoxEntity.isTamed())
        {
            //this.loadTexture(FakeResourceLocation.unwrap(MobRandomizer.randomTexture((Entity)par1FoxEntity, FakeResourceLocation.wrap("/mob/wolf_collar.png"))));
            var4 = 1.0F;
            int var5 = par1FoxEntity.getCollarColor();
            GL11.glColor3f(var4 * ColorizeEntity.getWolfCollarColor(EntitySheep.fleeceColorTable[var5], var5)[0], var4 * ColorizeEntity.getWolfCollarColor(EntitySheep.fleeceColorTable[var5], var5)[1], var4 * ColorizeEntity.getWolfCollarColor(EntitySheep.fleeceColorTable[var5], var5)[2]);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.func_82447_a((FoxEntity) par1EntityLiving, par2, par3);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLiving par1EntityLiving, float par2)
    {
        return this.getTailRotation((FoxEntity) par1EntityLiving, par2);
    }
    protected ResourceLocation func_110914_a(FoxEntity fox) {
        if ( fox.isTamed() )
        {
            if ( fox.isStarving() )
            {
                return FOX_TAME_STARVING_TEXTURES;
            }
            return tamedFoxTextures;
        }
        else if ( fox.isAngry() )
        {
            return angryFoxTextures;
        }
        else if ( fox.isStarving() || fox.hasAttackTarget())
        {
            return FOX_WILD_STARVING_TEXTURES;
        }
        return fox.isTamed() ? tamedFoxTextures : (fox.isAngry() ? angryFoxTextures : foxTextures);
    }
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110914_a((FoxEntity)par1Entity);
    }
}

