package com.itlesports.mobadditions.entity.mob.wolf;

import net.minecraft.src.*;

public class RustyWolfEntity extends EntityWolf {

    public RustyWolfEntity(World world) {
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
        else if (!(par1EntityAnimal instanceof RustyWolfEntity))
        {
            return false;
        }
        else
        {
            RustyWolfEntity var2 = (RustyWolfEntity) par1EntityAnimal;
            return !var2.isTamed() ? false : (var2.isSitting() ? false : this.isInLove() && var2.isInLove());
        }
    }
    @Override
    public RustyWolfEntity spawnBabyAnimal(EntityAgeable parent) {
        return new RustyWolfEntity(this.worldObj);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        return this.spawnBabyAnimal(par1EntityAgeable);
    }
}