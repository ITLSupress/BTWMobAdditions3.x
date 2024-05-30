package com.itlesports.backportedwolves.entity.mob.wolf;

import net.minecraft.src.*;

public class BlackWolfEntity extends EntityWolf {

    public BlackWolfEntity(World world) {
        super(world);
    }
    @Override
    public boolean canMateWith(EntityAnimal par1EntityAnimal)
    {
        if (par1EntityAnimal == this)
        {
            return false;
        }
        else if (!this.isTamed())
        {
            return false;
        }
        else if (!(par1EntityAnimal instanceof BlackWolfEntity))
        {
            return false;
        }
        else
        {
            BlackWolfEntity var2 = (BlackWolfEntity)par1EntityAnimal;
            return !var2.isTamed() ? false : (var2.isSitting() ? false : this.isInLove() && var2.isInLove());
        }
    }
    @Override
    public BlackWolfEntity spawnBabyAnimal(EntityAgeable parent) {
        return new BlackWolfEntity(this.worldObj);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        return this.spawnBabyAnimal(par1EntityAgeable);
    }
}

