package com.itlesports.mobadditions.block;

import com.itlesports.mobadditions.block.custom.FoxCompanionCubeBlock;
import com.itlesports.mobadditions.block.custom.GlowInkBlock;
import com.itlesports.mobadditions.block.custom.LavaInkBlock;
import com.itlesports.mobadditions.init.lighting.GlowLighting;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;

public class ModBlocks{
    public static int id_lightsourceinvis = 2005;
    public static Block lightsourceinvis;
    public static Block foxCompanionCube;
    public static Block glowInkBlock;
    public static Block lavaInkBlock;
    public static int FoxCompanionCubeID = 2001;
    public static int GlowInkBlockID = 2002;
    public static int LavaInkBlockID = 2003;
    public static void registerModBlocks() {
        foxCompanionCube = new FoxCompanionCubeBlock(FoxCompanionCubeID).setUnlocalizedName("foxCompanionCube").setCreativeTab(CreativeTabs.tabBlock);
        glowInkBlock = new GlowInkBlock(GlowInkBlockID).setUnlocalizedName("glowInkBlock").setCreativeTab(CreativeTabs.tabBlock);
        lavaInkBlock = new LavaInkBlock(LavaInkBlockID).setUnlocalizedName("lavaInkBlock").setCreativeTab(CreativeTabs.tabBlock);
        lightsourceinvis = (new GlowLighting(id_lightsourceinvis - 256));
    }
}
