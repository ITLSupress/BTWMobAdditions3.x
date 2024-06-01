// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.5.2
// Paste this class into your mod and call render() in your Entity Render class
// Note: You may need to adjust the y values of the 'setRotationPoint's

package com.itlesports.mobadditions.entity.mob.rideable.model;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class GoatEntityModel extends ModelBase {
	private final ModelRenderer left_back_leg;
	private final ModelRenderer right_back_leg;
	private final ModelRenderer right_front_leg;
	private final ModelRenderer left_front_leg;
	private final ModelRenderer body;
	private final ModelRenderer Head;
	private final ModelRenderer Head_r1;
	private final ModelRenderer HeadMain;

	public GoatEntityModel() {
		textureWidth = 64;
		textureHeight = 64;

		left_back_leg = new ModelRenderer(this);
		left_back_leg.setRotationPoint(1.0F, 14.0F, 4.0F);
		this.left_back_leg.setTextureOffset(36, 29).addBox(0.0F, 4.0F, 0.0F, 3, 6, 3, 0.0F);

		right_back_leg = new ModelRenderer(this);
		right_back_leg.setRotationPoint(-3.0F, 14.0F, 4.0F);
		this.right_back_leg.setTextureOffset(49, 29).addBox(0.0F, 4.0F, 0.0F, 3, 6, 3, 0.0F);

		right_front_leg = new ModelRenderer(this);
		right_front_leg.setRotationPoint(-3.0F, 14.0F, -6.0F);
		this.right_front_leg.setTextureOffset(49, 2).addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);

		left_front_leg = new ModelRenderer(this);
		left_front_leg.setRotationPoint(1.0F, 14.0F, -6.0F);
		this.left_front_leg.setTextureOffset(35, 2).addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.body.setTextureOffset(1, 1).addBox(-4.0F, -17.0F, -7.0F, 9, 11, 16, 0.0F);
		this.body.setTextureOffset(0, 28).addBox(-5.0F, -18.0F, -8.0F, 11, 14, 11, 0.0F);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(1.0F, 14.0F, 0.0F);
		setRotation(Head, -0.1047F, 0.0873F, 0.0F);
		this.Head.setTextureOffset(12, 55).addBox(-2.99F, -16.0F, -10.0F, 2, 7, 2, 0.0F);
		this.Head.setTextureOffset(12, 55).addBox(-0.01F, -16.0F, -10.0F, 2, 7, 2, 0.0F);
		this.Head.setTextureOffset(2, 61).addBox(2.0F, -11.0F, -10.0F, 3, 2, 1, 0.0F);
		this.Head.setTextureOffset(2, 61).addBox(-6.0F, -11.0F, -10.0F, 3, 2, 1, 0.0F);
		this.Head.setTextureOffset(23, 52).addBox(-0.5F, -3.0F, -14.0F, 0, 7, 5, 0.0F);

		Head_r1 = new ModelRenderer(this);
		Head_r1.setRotationPoint(0.0F, -8.0F, -8.0F);
		Head.addChild(Head_r1);
		setRotation(Head_r1, 0.9599F, 0.0F, 0.0F);
		this.Head_r1.setTextureOffset(34, 46).addBox(-3.0F, -4.0F, -8.0F, 5, 7, 10, 0.0F);

		HeadMain = new ModelRenderer(this);
		HeadMain.setRotationPoint(0.0F, -8.0F, -8.0F);
		Head.addChild(HeadMain);
		setRotation(HeadMain, 0.9599F, 0.0F, 0.0F);
		this.HeadMain.setTextureOffset(34, 46).addBox(-3.0F, -4.0F, -8.0F, 5, 7, 10, 0.0F);
	}

	/**
	* Sets the models various rotation angles then renders the model.
	*/
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		left_back_leg.render(f5);
		right_back_leg.render(f5);
		right_front_leg.render(f5);
		left_front_leg.render(f5);
		body.render(f5);
		Head.render(f5);
	}

	/**
	* Sets the model's various rotation angles. For bipeds, f and f1 are used for animating the movement of arms
	* and legs, where f represents the time(so that arms and legs swing back and forth) and f1 represents how
	* "far" arms and legs can swing at most.
	*/
	@Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {

    }
	
	/**
	*	Sets the rotation of a ModelRenderer. Only called if the ModelRenderer has a rotation
	*/
    public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}