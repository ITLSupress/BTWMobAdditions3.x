package com.itlesports.mobadditions.entity.mob.aquatic.squid;

import btw.item.BTWItems;
import com.itlesports.mobadditions.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.EnumSkyBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class LavaSquidEntity extends EntityLavaSquid {
    public LavaSquidEntity(World world) {
        super(world);
        //this.texture = "/mobadditions/entity/mob/aquatic/lavasquid.png";
        this.isImmuneToFire = true;
    }
public void onLivingUpdate() {
        super.onLivingUpdate();
        addClientLight();
    if (this.isDead) {
        this.worldObj.updateLightByType(EnumSkyBlock.Block,
                (int) this.posX, (int) this.posY, (int) this.posZ);
    }
    if (this.isInLava()) {
        this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
        this.worldObj.spawnParticle( "flame", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
    }
}
    @Override
    protected void dropFewItems(boolean var1, int var2)
    {
        //Glands
        if (this.rand.nextInt(8) - var2 <= 0)
        {
            this.dropItem(BTWItems.mysteriousGland.itemID, 1);
        }

        if (this.worldObj.provider.dimensionId != 1) //not nether and normal squid
        {
            ///Glow Ink Sacks
            int var3 = this.rand.nextInt(3 + var2) + 1;

            for (int var6 = 0; var6 < var3; ++var6)
            {
                this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
                this.entityDropItem(new ItemStack(ModItems.lavaInkSac, 1, 0), 0.0F);
            }
        }

    }
    @Environment(EnvType.CLIENT)
    private void addClientLight() {
        this.worldObj.setLightValue(EnumSkyBlock.Block, (int) this.posX,
                (int) this.posY, (int) this.posZ, 13);
        this.worldObj.markBlockRangeForRenderUpdate((int) this.posX,
                (int) this.posY, (int) this.posX, 8, 8, 8);
        this.worldObj.markBlockForUpdate((int) this.posX, (int) this.posY,
                (int) this.posZ);
        this.worldObj.updateLightByType(EnumSkyBlock.Block, (int) this.posX,
                (int) this.posY + 1, (int) this.posZ);
        this.worldObj.updateLightByType(EnumSkyBlock.Block, (int) this.posX,
                (int) this.posY - 1, (int) this.posZ);
        this.worldObj.updateLightByType(EnumSkyBlock.Block,
                (int) this.posX + 1, (int) this.posY, (int) this.posZ);
        this.worldObj.updateLightByType(EnumSkyBlock.Block,
                (int) this.posX - 1, (int) this.posY, (int) this.posZ);
        this.worldObj.updateLightByType(EnumSkyBlock.Block, (int) this.posX,
                (int) this.posY, (int) this.posZ + 1);
        this.worldObj.updateLightByType(EnumSkyBlock.Block, (int) this.posX,
                (int) this.posY, (int) this.posZ - 1);
    }
    @Override
    @Environment(EnvType.CLIENT)
    public int getBrightnessForRender(float par1)
    {
        return 15728880;
    }
}
