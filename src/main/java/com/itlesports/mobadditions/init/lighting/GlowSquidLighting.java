package com.itlesports.mobadditions.init.lighting;

import com.itlesports.mobadditions.entity.mob.aquatic.squid.GlowSquidEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public class GlowSquidLighting extends Block{

    public final static int lightSourceTickRate = 10;

    public GlowSquidLighting(int par1) {
        super(par1, Material.air);
        setLightValue(0.9375F);

        setTickRandomly(true);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        return null;
    }

    @Override
    public int tickRate(World world) {
        return lightSourceTickRate;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getMobilityFlag() {
        // disable the ability for piston to push this block
        return 1;
    }

    @Override
    public int idDropped(int i, Random random, int iFortuneModifier) {
        return 0;
    }

    @Override
    public boolean canCollideCheck(int i, boolean flag) {
        return false;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public boolean isAirBlock() {
        return false;
    }

    @Override
    public boolean triggersBuddy() {
        return false;
    }

    @Override
    public void updateTick(World world, int i, int j, int k, Random random) {
        boolean canStay = isGlowSquidNear(world, i, j, k);

        if (!canStay) {
            world.setBlock(i, j, k, 0, 0, 2);
        } else if (!world.isUpdatePendingThisTickForBlock(i, j, k, world.getBlockId(i, j, k))) {
            world.scheduleBlockUpdate(i, j, k, world.getBlockId(i, j, k), lightSourceTickRate);
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean renderBlock(RenderBlocks var1, int var2, int var3, int var4) {
        return false;
    }

    //I think leaving this empty works out fine
    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
//        blockIcon = register.registerIcon( "fcBlockLens_spotlight" );
    }


    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, int iBlockID) {

        if (!world.isUpdatePendingThisTickForBlock(i, j, k, blockID)) {
            world.scheduleBlockUpdate(i, j, k, blockID, lightSourceTickRate);
        }
    }


    private boolean isGlowSquidNear(World world, int i, int j, int k) {
        List list = world.getEntitiesWithinAABB(Entity.class,
                AxisAlignedBB.getAABBPool().getAABB((double) i, (double) j - 0.1, (double) k,
                        (double) (i + 1), (double) (j + 1), (double) (k + 1)));
        // -0.1 helps seeing players that are just below the edge of the block

        if (list != null && list.size() > 0) {
            for (Object o : list) {
                Entity targetEntity = (Entity) o;

                if (targetEntity instanceof GlowSquidEntity && ((GlowSquidEntity) targetEntity).DoesGlow() != false) {
                    return ((GlowSquidEntity) targetEntity).DoesGlow() == true;
                }
            }
        }

        return false;
    }


}
