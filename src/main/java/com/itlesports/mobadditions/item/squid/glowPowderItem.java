package com.itlesports.mobadditions.item.squid;

import btw.item.items.FoodItem;
import net.minecraft.src.*;

public class glowPowderItem extends FoodItem {

    public glowPowderItem(int iItemID, int iHungerHealed, float fSaturationModifier, boolean bWolfMeat, String sItemName) {
        super(iItemID, iHungerHealed, fSaturationModifier, bWolfMeat, sItemName);
        this.setCreativeTab(CreativeTabs.tabMaterials);
        // defines main potion effect
        this.setPotionEffect(Potion.nightVision.id, 30, 0, 1.0F);
    }

    public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3) {
        super.onEaten(var1, var2, var3);
        var3.addPotionEffect(new PotionEffect(Potion.confusion.id, 30 * 20, 1));

        return var1;
    }
}


