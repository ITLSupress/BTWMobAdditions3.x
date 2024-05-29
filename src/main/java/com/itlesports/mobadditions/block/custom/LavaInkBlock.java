package com.itlesports.mobadditions.block.custom;

import com.itlesports.mobadditions.item.ModItems;
import net.minecraft.src.*;

import java.util.Random;

public class LavaInkBlock extends BlockGlowStone
{
    public LavaInkBlock(int iBlockID )
    {
        super( iBlockID, Material.rock );

        setHardness( 0.6F );
        setResistance( 0.5F ); // preserve vanilla resistance

        setPicksEffectiveOn();

        setLightValue( 1F );

        setStepSound( soundStoneFootstep );

        setUnlocalizedName( "lavaInkBlock" );
    }

    @Override
    public boolean hasLargeCenterHardPointToFacing(IBlockAccess blockAccess, int i, int j, int k, int iFacing, boolean bIgnoreTransparency )
    {
        return bIgnoreTransparency;
    }
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return ModItems.lavaPowder.itemID;
    }
    protected Icon getSideIcon(int Par1) {
        return this.blockIcon;
    }
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("lavaInkBlock");
    }
}

