package com.itlesports.mobadditions.entity.mob.rideable.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.ModelBase;

@Environment(EnvType.CLIENT)
public class GoatEntityRenderer extends RenderGoatEntity {
    public GoatEntityRenderer(ModelBase par1ModelBase,float par2) {
        super(par1ModelBase, par2);
    }
}
