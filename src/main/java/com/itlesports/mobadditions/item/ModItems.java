package com.itlesports.mobadditions.item;

import btw.item.BTWItems;
import btw.item.items.SinewExtractingItem;
import com.itlesports.mobadditions.item.blockitem.FoxCompanionCubeItemBlock;
import com.itlesports.mobadditions.item.food.cookedFoxChopItem;
import com.itlesports.mobadditions.item.food.goatCookedItem;
import com.itlesports.mobadditions.item.food.goatRawItem;
import com.itlesports.mobadditions.item.food.rawFoxChopItem;
import com.itlesports.mobadditions.item.squid.*;
import net.minecraft.src.*;

public class ModItems extends BTWItems {
    public static Item rawFoxChop;
    public static Item cookedFoxChop;
    public static Item sinewExtractingFox;
    public static Item glowInkSac;
    public static Item lavaInkSac;
    public static Item glowPowder;
    public static Item lavaPowder;
    public static Item glowPaste;
    public static Item lavaPaste;
    public static Item goatRaw;
    public static Item goatCooked;
    public static void registerModItems() {
        rawFoxChop = new rawFoxChopItem(700,4, 0.25F, true, "rawFoxChopItem", true );
        cookedFoxChop = new cookedFoxChopItem(701,5, 0.25F, true, "cookedFoxChopItem", false );

        sinewExtractingFox = new SinewExtractingItem(702, "sinewExtractingFoxItem").setUnlocalizedName("sinewExtractingFoxItem");

        glowInkSac = new glowInkSacItem(705, 4, 0.25F,false,"glowInkSacItem");
        lavaInkSac = new lavaInkSacItem(706, 4, 0.25F,false,"lavaInkSacItem");

        glowPowder = new glowPowderItem(707, 1, 0.1F, false, "glowPowderItem");
        lavaPowder = new lavaPowderItem(708, 1, 0.1F, false, "lavaPowderItem");

        glowPaste = new glowPasteItem(709, 4, 0.25F, false, "glowPasteItem");
        lavaPaste = new lavaPasteItem(710, 4, 0.25F, false, "lavaPasteItem");

        goatRaw = new goatRawItem(711, 6, 0.25F, false, "goatRawItem", true);
        goatCooked = new goatCookedItem(712, 12, 0.45F, false, "goatCookedItem", false);

        ModItems.createAssociatedItemsForModBlocks();
    }
    private static void createAssociatedItemsForModBlocks()
    {
        registerModBlockItems();

        for (int iTempBlockID = 0; iTempBlockID < 4096; iTempBlockID++)
        {
            if (Block.blocksList[iTempBlockID] != null && Item.itemsList[iTempBlockID] == null)
            {
                Item.itemsList[iTempBlockID] = new ItemBlock(iTempBlockID - 256);
            }
        }

    }
    public static void registerModBlockItems() {
        Item.suppressConflictWarnings = true;
        Item.itemsList[2001] = new FoxCompanionCubeItemBlock( 2001 - 256);
        Item.itemsList[2002] = new ItemBlock(2002 - 256);
        Item.itemsList[2003] = new ItemBlock(2003 - 256);
    }

}
