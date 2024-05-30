package com.itlesports.backportedwolves.entity.mob.wolf;

import net.minecraft.src.*;

public class WoodsWolfEntity extends EntityWolf {

    public WoodsWolfEntity(World world) {
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
        else if (!(par1EntityAnimal instanceof WoodsWolfEntity))
        {
            return false;
        }
        else
        {
            WoodsWolfEntity var2 = (WoodsWolfEntity)par1EntityAnimal;
            return !var2.isTamed() ? false : (var2.isSitting() ? false : this.isInLove() && var2.isInLove());
        }
    }
    @Override
    public WoodsWolfEntity spawnBabyAnimal(EntityAgeable parent) {
        return new WoodsWolfEntity(this.worldObj);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        return this.spawnBabyAnimal(par1EntityAgeable);
    }
}

