package com.itlesports.mobadditions.item.food;

import com.itlesports.mobadditions.entity.mob.rideable.GoatEntity;
import net.minecraft.src.*;

public class potatoOnAStickItem extends Item {
    public potatoOnAStickItem(int par1)
    {
        super(par1);
        this.setCreativeTab(CreativeTabs.tabTransport);
        this.setMaxStackSize(1);
        this.setMaxDamage(25);

        setBuoyant();
        setFilterableProperties(FILTERABLE_NARROW);

        setAsBasicPigFood();

        setUnlocalizedName( "potatoOnAStick" );
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * Returns true if this item should be rotated by 180 degrees around the Y axis when being held in an entities
     * hands.
     */
    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (par3EntityPlayer.isRiding() && par3EntityPlayer.ridingEntity instanceof GoatEntity)
        {
            GoatEntity var4 = (GoatEntity)par3EntityPlayer.ridingEntity;

            if (var4.getAIControlledByPlayer().isControlledByPlayer() && par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage() >= 7)
            {
                var4.getAIControlledByPlayer().boostSpeed();
                par1ItemStack.damageItem(7, par3EntityPlayer);

                if (par1ItemStack.stackSize == 0)
                {
                    ItemStack var5 = new ItemStack(Item.fishingRod);
                    var5.setTagCompound(par1ItemStack.stackTagCompound);
                    return var5;
                }
            }
        }

        return par1ItemStack;
    }
}
