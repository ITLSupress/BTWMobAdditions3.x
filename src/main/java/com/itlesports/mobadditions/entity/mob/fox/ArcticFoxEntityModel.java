package com.itlesports.mobadditions.entity.mob.fox;

import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

public class ArcticFoxEntityModel extends ModelBase {
    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer tail;
    private float headRotation;

    public ArcticFoxEntityModel() {
        textureWidth = 48;
        textureHeight = 32;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 16.0F, 0.0F);
        setRotation(body, 1.5708F, 0.0F, 0.0F);
        this.body.setTextureOffset(24, 15).addBox(-3.0F, -3.0F, -3.0F, 6, 11, 6, 0.0F);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 16.0F, -3.0F);
        this.head.setTextureOffset(1, 5).addBox(-4.0F, -2.0F, -6.0F, 8, 6, 6, 0.0F);
        this.head.setTextureOffset(8, 1).addBox(-4.0F, -4.0F, -5.0F, 2, 2, 1, 0.0F);
        this.head.setTextureOffset(15, 1).addBox(2.0F, -4.0F, -5.0F, 2, 2, 1, 0.0F);
        this.head.setTextureOffset(6, 18).addBox(-2.0F, 2.0F, -9.0F, 4, 2, 3, 0.0F);

        leg0 = new ModelRenderer(this);
        leg0.setRotationPoint(-3.0F, 18.0F, 6.0F);
        this.leg0.setTextureOffset(13, 24).addBox(-0.005F, 0.0F, -1.0F, 2, 6, 2, 0.0F);

        leg1 = new ModelRenderer(this);
        leg1.setRotationPoint(1.0F, 18.0F, 6.0F);
        this.leg1.setTextureOffset(4, 24).addBox(0.005F, 0.0F, -1.0F, 2, 6, 2, 0.0F);

        leg2 = new ModelRenderer(this);
        leg2.setRotationPoint(-3.0F, 18.0F, -1.0F);
        this.leg2.setTextureOffset(13, 24).addBox(-0.005F, 0.0F, -1.0F, 2, 6, 2, 0.0F);

        leg3 = new ModelRenderer(this);
        leg3.setRotationPoint(1.0F, 18.0F, -1.0F);
        this.leg3.setTextureOffset(4, 24).addBox(0.005F, 0.0F, -1.0F, 2, 6, 2, 0.0F);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(0.0F, 16.0F, 7.0F);
        setRotation(tail, 1.5708F, 0.0F, 0.0F);
        this.tail.setTextureOffset(30, 0).addBox(-2.0F, 1.0F, -2.25F, 4, 9, 5, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        if (this.isChild)
        {
            float var8 = 2.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 5.0F * f5, 2.0F * f5);
            this.head.renderWithRotation(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
            GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
            this.body.render(f5);
            this.leg0.render(f5);
            this.leg1.render(f5);
            this.leg2.render(f5);
            this.leg3.render(f5);
            this.tail.renderWithRotation(f5);
            GL11.glPopMatrix();
        }
        else
        {
            body.render(f5);
            head.render(f5);
            leg0.render(f5);
            leg1.render(f5);
            leg2.render(f5);
            leg3.render(f5);
            tail.render(f5);
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, f and f1 are used for animating the movement of arms
     * and legs, where f represents the time(so that arms and legs swing back and forth) and f1 represents how
     * "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
        this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.tail.rotateAngleX = par3;
        head.rotateAngleX = headRotation;
    }

    /**
     *	Sets the rotation of a ModelRenderer. Only called if the ModelRenderer has a rotation
     */
    public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    public void setLivingAnimations(EntityLiving par1EntityLiving, float par2, float par3, float par4) {
        ArcticFoxEntity var5 = (ArcticFoxEntity)par1EntityLiving;
        if (var5.isAngry()|| var5.isWildAndHostile() )
        {
            this.tail.rotateAngleY = 0.0F;
        }
        else
        {
            this.tail.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
        }
        if (var5.isSitting())
        {
            this.body.setRotationPoint(-1.0F, 16F, -3.0F);
            this.body.rotateAngleX = ((float)Math.PI * 2F / 5F);
            this.tail.setRotationPoint(-1.0F, 18.0F, 4.0F);
            this.leg0.setRotationPoint(-2.5F, 20.0F, 2.0F);
            this.leg0.rotateAngleX = ((float)Math.PI * 3F / 2F);
            this.leg1.setRotationPoint(0.5F, 20.0F, 2.0F);
            this.leg1.rotateAngleX = ((float)Math.PI * 3F / 2F);
            this.leg2.rotateAngleX = 5.811947F;
            this.leg2.setRotationPoint(-2.49F, 17.0F, -4.0F);
            this.leg3.rotateAngleX = 5.811947F;
            this.leg3.setRotationPoint(0.51F, 17.0F, -4.0F);
        }
        else if (ArcticFoxEntity.isSleeping()) {
            this.body.rotateAngleZ = -1.5707964F;
            this.body.setRotationPoint(0.0F, 21.0F, -6.0F);
            this.tail.rotateAngleX = -2.6179938F;
            this.tail.setRotationPoint(-1.0F, 21.0F, 2.0F);
            this.leg0.setRotationPoint(-2.5F, 21.0F, 0.0F);
            this.leg1.setRotationPoint(0.5F, 21.0F, 0.0F);
            if (this.isChild) {
                this.tail.rotateAngleX = -2.1816616F;
                this.body.setRotationPoint(0.0F, 21.0F, -2.0F);
            }
        }
        else
        {
            this.body.setRotationPoint(0.0F, 16.0F, 0F);
            this.body.rotateAngleX = ((float)Math.PI / 2F);
            this.tail.setRotationPoint(-1.0F, 16.0F, 7.0F);
            this.leg0.setRotationPoint(-2.5F, 16.0F, 7.0F);
            this.leg1.setRotationPoint(0.5F, 16.0F, 7.0F);
            this.leg2.setRotationPoint(-2.5F, 16.0F, -4.0F);
            this.leg3.setRotationPoint(0.5F, 16.0F, -4.0F);
            this.leg0.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
            this.leg1.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
            this.leg2.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float)Math.PI) * 1.4F * par3;
            this.leg3.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
        }
        this.head.rotateAngleZ = var5.getInterestedAngle(par4) + var5.getShakeAngle(par4, 0.0F);
        this.body.rotateAngleZ = var5.getShakeAngle(par4, -0.16F);
        this.tail.rotateAngleZ = var5.getShakeAngle(par4, -0.2F);

        this.head.rotationPointY = 13.5F + ((ArcticFoxEntity)par1EntityLiving).getGrazeHeadVerticalOffset(par4) * 5.0F;
        headRotation = ((ArcticFoxEntity)par1EntityLiving).getGrazeHeadRotation(par4);
        this.head.rotateAngleZ += var5.getPossessionHeadRotation();
    }
}
