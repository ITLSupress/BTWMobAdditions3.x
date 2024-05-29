package com.itlesports.mobadditions.item.food;

import btw.item.items.FoodItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.IconRegister;

public class rawFoxChopItem extends FoodItem {
    public rawFoxChopItem(int iItemID, int iHungerHealed, float fSaturationModifier, boolean bWolfMeat, String sItemName, boolean bZombiesConsume) {
        super(iItemID, iHungerHealed, fSaturationModifier, bWolfMeat, sItemName, bZombiesConsume);

        setStandardFoodPoisoningEffect();
    }
    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons( IconRegister register )
    {
        itemIcon = register.registerIcon("porkchopRaw");
    }
}
