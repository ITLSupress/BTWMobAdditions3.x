package com.itlesports.mobadditions.entity.mob.aquatic.squid;

import net.minecraft.src.*;

public class EntityLavaMob extends EntityCreature implements IAnimals
{
    private int fire;
    public boolean inLava;
    public boolean isInLava()
    {
        return this.inLava;
    }
    public EntityLavaMob(World par1World)
    {
        super(par1World);
        this.firstUpdate = true;
        this.fire = 0;
    }

    public boolean canBreatheUnderlava()
    {
        return true;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.worldObj.checkNoEntityCollision(this.boundingBox);
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval()
    {
        return 120;
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return true;
    }

    /**
     * Get the experience points the entity currently has.
     */
    protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
    {
        return 1 + this.worldObj.rand.nextInt(3);
    }

    /**
     * Gets called every tick from main Entity class
     */
    public void onEntityUpdate()
    {
        int var1 = this.getAir();
        super.onEntityUpdate();

        if (this.isEntityAlive() && !inLava)
        {
            --var1;
            this.setAir(var1);

            if (this.getAir() == -20)
            {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.drown, 2);
            }
        }
        else
        {
            this.setAir(300);
        }
    }
    private boolean firstUpdate;
    public boolean handleLavaMovement()
    {
        if (this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.4000000059604645D, 0.0D).contract(0.001D, 0.001D, 0.001D), Material.lava, this))
        {
            if (!this.inLava && !this.firstUpdate)
            {
                float var1 = MathHelper.sqrt_double(this.motionX * this.motionX * 0.20000000298023224D + this.motionY * this.motionY + this.motionZ * this.motionZ * 0.20000000298023224D) * 0.2F;

                if (var1 > 1.0F)
                {
                    var1 = 1.0F;
                }

                this.playSound("liquid.splash", var1, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                float var2 = (float)MathHelper.floor_double(this.boundingBox.minY);
                int var3;
                float var4;
                float var5;

                for (var3 = 0; (float)var3 < 1.0F + this.width * 20.0F; ++var3)
                {
                    var4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
                    var5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
                    this.worldObj.spawnParticle("bubble", this.posX + (double)var4, (double)(var2 + 1.0F), this.posZ + (double)var5, this.motionX, this.motionY - (double)(this.rand.nextFloat() * 0.2F), this.motionZ);
                }

                for (var3 = 0; (float)var3 < 1.0F + this.width * 20.0F; ++var3)
                {
                    var4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
                    var5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
                    this.worldObj.spawnParticle("splash", this.posX + (double)var4, (double)(var2 + 1.0F), this.posZ + (double)var5, this.motionX, this.motionY, this.motionZ);
                }
            }

            this.fallDistance = 0.0F;
            this.inLava = true;
            this.fire = 0;
        }
        else
        {
            this.inLava = false;
        }

        return this.inLava;
    }
}

