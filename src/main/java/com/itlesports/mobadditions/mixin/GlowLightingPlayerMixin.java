package com.itlesports.mobadditions.mixin;

import com.itlesports.mobadditions.MobAdditions;
import btw.world.util.BlockPos;
import com.itlesports.mobadditions.block.ModBlocks;
import com.itlesports.mobadditions.init.lighting.GlowLighting;
import com.itlesports.mobadditions.item.ModItems;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(EntityPlayer.class)
public abstract class GlowLightingPlayerMixin {
    private int dynamicLightUpdateTimer;
    public boolean isholdingglowinksac;
    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(CallbackInfo info) {
        dynamicLightUpdateTimer = 0;
        isholdingglowinksac = false;
    }

    @Inject(method = "onUpdate", at = @At("RETURN"))
    private void onUpdate(CallbackInfo info) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        if (!player.worldObj.isRemote)
        {
            dynamicLightUpdateTimer++;
            if (isholdingglowinksac || dynamicLightUpdateTimer > 9)
            {
                ItemStack heldItem = player.getHeldItem();
                dynamicLightUpdateTimer = 0;
                if (heldItem!= null && isDynamicLightSource(heldItem.itemID))
                {
                    isholdingglowinksac =true;
//        			System.out.println(this + " is holding " + getHeldItem());
                }
                else
                {
                    isholdingglowinksac =false;
                }

                if (isholdingglowinksac)
                {
                    BlockPos lightpos = new BlockPos(MathHelper.floor_double( player.posX ),
                            MathHelper.floor_double(player.boundingBox.maxY), MathHelper.floor_double( player.posZ));
//					System.out.println(lightpos.x + "," + lightpos.y + "," + lightpos.z +
//							":" + player.worldObj.getBlockId(lightpos.x, lightpos.y, lightpos.z));
                    if (player.worldObj.getBlockId(lightpos.x, lightpos.y, lightpos.z) == 0)
                    {
                        player.worldObj.setBlock(lightpos.x, lightpos.y, lightpos.z,
                                ModBlocks.lightsourceinvis.blockID, 0 , 2);
                        player.worldObj.scheduleBlockUpdate(lightpos.x, lightpos.y, lightpos.z,
                                ModBlocks.lightsourceinvis.blockID, GlowLighting.lightSourceTickRate);
                    }
                }
            }
        }

    }
    public boolean isDynamicLightSource(int itemID)
    {
//    	Block.blocksList[itemID].lightValue>0 TODO
        return itemID == ModItems.glowInkSac.itemID || itemID == ModItems.lavaInkSac.itemID || itemID == ModBlocks.glowInkBlock.blockID || itemID == ModBlocks.lavaInkBlock.blockID || itemID == ModItems.glowPaste.itemID || itemID == ModItems.lavaPaste.itemID;
    }
}
