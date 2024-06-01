package com.itlesports.mobadditions.entity.mob.rideable;

import btw.entity.mob.KickingAnimal;
import net.minecraft.src.EntityAgeable;
import net.minecraft.src.World;

public class GoatEntity extends KickingAnimal {
    public GoatEntity(World par1World) {
        super(par1World);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return null;
    }
}
