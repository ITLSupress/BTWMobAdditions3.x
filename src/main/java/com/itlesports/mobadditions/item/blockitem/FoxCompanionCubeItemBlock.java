package com.itlesports.mobadditions.item.blockitem;

import btw.block.blocks.CompanionCubeBlock;
import btw.util.MiscUtils;
import btw.world.util.BlockPos;
import com.itlesports.mobadditions.block.ModBlocks;
import com.itlesports.mobadditions.block.custom.FoxCompanionCubeBlock;
import net.minecraft.src.*;

public class FoxCompanionCubeItemBlock extends ItemBlock {
    public FoxCompanionCubeItemBlock(int iItemID) {
        super(iItemID);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("foxCompanionCube");
    }

    public int getMetadata(int iItemDamage) {
        return this.getIsSlab(iItemDamage) ? 8 : 0;
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        return itemstack.getItemDamage() > 0 ? super.getUnlocalizedName() + "." + "slab" : super.getUnlocalizedName() + "." + "cube";
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int i, int j, int k, int iFacing, float fClickX, float fClickY, float fClickZ) {
        if (!this.getIsSlab(itemStack.getItemDamage())) {
            return super.onItemUse(itemStack, player, world, i, j, k, iFacing, fClickX, fClickY, fClickZ);
        } else if (itemStack.stackSize == 0) {
            return false;
        } else if (!player.canPlayerEdit(i, j, k, iFacing, itemStack)) {
            return false;
        } else if (this.attemptToCombineWithBlock(itemStack, player, world, i, j, k, iFacing, true)) {
            return true;
        } else {
            BlockPos targetPos = new BlockPos(i, j, k);
            targetPos.addFacingAsOffset(iFacing);
            return this.attemptToCombineWithBlock(itemStack, player, world, targetPos.x, targetPos.y, targetPos.z, iFacing, false) ? true : super.onItemUse(itemStack, player, world, i, j, k, iFacing, fClickX, fClickY, fClickZ);
        }
    }

    public boolean getIsSlab(int iItemDamage) {
        return iItemDamage != 0;
    }

    public boolean canCombineWithBlock(World world, int i, int j, int k, int iItemDamage) {
        int iBlockID = world.getBlockId(i, j, k);
        if (this.getIsSlab(iItemDamage) && iBlockID == ModBlocks.foxCompanionCube.blockID) {
            FoxCompanionCubeBlock foxCompanionCube = (FoxCompanionCubeBlock)((FoxCompanionCubeBlock) Block.blocksList[iBlockID]);
            if (foxCompanionCube != null && foxCompanionCube.getIsSlab(world, i, j, k)) {
                return true;
            }
        }

        return false;
    }

    public boolean convertToFullBlock(EntityPlayer player, World world, int i, int j, int k) {
        int iBlockID = world.getBlockId(i, j, k);
        if (iBlockID == ModBlocks.foxCompanionCube.blockID) {
            FoxCompanionCubeBlock foxCompanionCube = (FoxCompanionCubeBlock)((FoxCompanionCubeBlock)Block.blocksList[iBlockID]);
            if (foxCompanionCube != null && foxCompanionCube.getIsSlab(world, i, j, k)) {
                CompanionCubeBlock.spawnHearts(world, i, j, k);
                world.playSoundEffect((double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), foxCompanionCube.stepSound.getPlaceSound(), (foxCompanionCube.stepSound.getPlaceVolume() + 1.0F) / 2.0F, foxCompanionCube.stepSound.getPlacePitch() * 0.8F);
                int iTargetBlockID = ModBlocks.foxCompanionCube.blockID;
                int iTargetMetadata = 0;
                int iFacing = MiscUtils.convertPlacingEntityOrientationToBlockFacingReversed(player);
                iTargetMetadata = foxCompanionCube.setFacing(iTargetMetadata, iFacing);
                return world.setBlockAndMetadataWithNotify(i, j, k, iTargetBlockID, iTargetMetadata);
            }
        }

        return false;
    }

    public boolean isSlabUpsideDown(int iBlockID, int iMetadata) {
        if (iBlockID == ModBlocks.foxCompanionCube.blockID) {
            FoxCompanionCubeBlock foxCompanionCube = (FoxCompanionCubeBlock)((FoxCompanionCubeBlock)Block.blocksList[iBlockID]);
            if (foxCompanionCube != null) {
                return foxCompanionCube.getIsUpsideDownSlabFromMetadata(iMetadata);
            }
        }

        return false;
    }

    public boolean attemptToCombineWithBlock(ItemStack itemStack, EntityPlayer player, World world, int i, int j, int k, int iFacing, boolean bFacingTest) {
        if (this.canCombineWithBlock(world, i, j, k, itemStack.getItemDamage())) {
            int iTargetBlockID = world.getBlockId(i, j, k);
            Block targetBlock = Block.blocksList[iTargetBlockID];
            if (targetBlock != null) {
                int iTargetMetadata = world.getBlockMetadata(i, j, k);
                boolean bIsTargetUpsideDown = this.isSlabUpsideDown(iTargetBlockID, iTargetMetadata);
                if (!bFacingTest || iFacing == 1 && !bIsTargetUpsideDown || iFacing == 0 && bIsTargetUpsideDown) {
                    if (world.checkNoEntityCollision(targetBlock.getCollisionBoundingBoxFromPool(world, i, j, k)) && this.convertToFullBlock(player, world, i, j, k)) {
                        --itemStack.stackSize;
                        Block newBlock = Block.blocksList[world.getBlockId(i, j, k)];
                        if (newBlock != null) {
                            world.notifyNearbyAnimalsOfPlayerBlockAddOrRemove(player, newBlock, i, j, k);
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }

    public boolean canPlaceItemBlockOnSide(World world, int i, int j, int k, int iFacing, EntityPlayer player, ItemStack itemStack) {
        BlockPos targetPos = new BlockPos(i, j, k);
        if (this.canCombineWithBlock(world, targetPos.x, targetPos.y, targetPos.z, itemStack.getItemDamage())) {
            int iTargetBlockID = world.getBlockId(targetPos.x, targetPos.y, targetPos.z);
            int iTargetMetadata = world.getBlockMetadata(targetPos.x, targetPos.y, targetPos.z);
            boolean bIsUpsideDown = this.isSlabUpsideDown(iTargetBlockID, iTargetMetadata);
            if (iFacing == 1 && !bIsUpsideDown || iFacing == 0 && bIsUpsideDown) {
                return true;
            }
        }

        targetPos.addFacingAsOffset(iFacing);
        if (this.canCombineWithBlock(world, targetPos.x, targetPos.y, targetPos.z, itemStack.getItemDamage())) {
            return true;
        } else {
            return super.canPlaceItemBlockOnSide(world, i, j, k, iFacing, player, itemStack);
        }
    }
}

