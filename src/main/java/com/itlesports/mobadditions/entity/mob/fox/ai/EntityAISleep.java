package com.itlesports.mobadditions.entity.mob.fox.ai;

import com.itlesports.mobadditions.entity.mob.fox.FoxEntity;
import net.minecraft.src.EntityAIBase;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.EntityTameable;

public class EntityAISleep extends EntityAIBase
{
    private EntityTameable theEntity;
    private FoxEntity foxEntity;

    /** If the EntityTameable is sleeping. */
    private boolean isSleeping = false;

    public EntityAISleep(EntityTameable par1EntityTameable)
    {
        this.theEntity = par1EntityTameable;
        this.setMutexBits(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!this.theEntity.isTamed())
        {
            return false;
        }
        else if (this.theEntity.isInWater())
        {
            return false;
        }
        else if (!this.theEntity.onGround)
        {
            return false;
        }
        else
        {
            EntityLivingBase var1 = this.theEntity.func_130012_q();
// +++START EDIT+++
            return var1 == null ? true : this.isSleeping;
// ---END EDIT---
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.theEntity.getNavigator().clearPathEntity();
        this.foxEntity.setSleeping(true);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.foxEntity.setSleeping(false);
    }

    /**
     * Sets the sleeping flag.
     */
    public void setSleeping(boolean par1)
    {
        this.isSleeping = par1;
    }
    public boolean isTryingToSleep()
    {
        return this.isSleeping;
    }

}