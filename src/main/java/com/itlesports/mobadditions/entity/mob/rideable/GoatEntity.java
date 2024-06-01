package com.itlesports.mobadditions.entity.mob.rideable;

import btw.client.fx.BTWEffectManager;
import btw.entity.mob.KickingAnimal;
import btw.entity.mob.behavior.*;
import btw.item.BTWItems;
import btw.util.MiscUtils;
import com.itlesports.mobadditions.entity.mob.aquatic.squid.GlowSquidEntity;
import com.itlesports.mobadditions.entity.mob.aquatic.squid.LavaSquidEntity;
import com.itlesports.mobadditions.entity.mob.fox.FoxEntity;
import com.itlesports.mobadditions.entity.mob.rideable.ai.EntityAIRam;
import com.itlesports.mobadditions.item.ModItems;
import net.minecraft.src.*;

public class GoatEntity extends KickingAnimal {
    protected static final int GOT_MILK_DATA_WATCHER_ID = 26;
    private static final int FULL_MILK_ACCUMULATION_COUNT = MiscUtils.TICKS_PER_GAME_DAY;
    private int milkAccumulationCount = 0;

    public GoatEntity(World par1World) {
        super(par1World);
        this.setSize(0.9F, 1.4F);
        this.getNavigator().setAvoidsWater(true);

        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new AnimalFleeBehavior(this, 2.2F));
        tasks.addTask(2, new EntityAIMate(this, 1.0F));
        tasks.addTask(3, new MultiTemptBehavior(this, 1.35F));
        tasks.addTask(4, new GrazeBehavior(this));
        tasks.addTask(5, new MoveToLooseFoodBehavior(this, 1.1F));
        tasks.addTask(6, new MoveToGrazeBehavior(this, 1.1F));
        tasks.addTask(7, new EntityAIFollowParent(this, 1.35F));
        tasks.addTask(8, new SimpleWanderBehavior(this, 1.35F));
        tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6F));
        tasks.addTask(10, new EntityAILookIdle(this));
        tasks.addTask(11, new EntityAIRam());
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.20000000298023224D);
    }

    @Override
    protected String getLivingSound() {
        if (!isStarving()) {
            return "mob.goat.say";
        }
        else {
            return "mob.goat.hurt";
        }
    }

    protected String getHurtSound() {
        return "mob.goat.hurt";
    }

    protected String getDeathSound() {
        return "mob.goat.hurt";
    }

    protected void playStepSound(int par1, int par2, int par3, int par4) {
        this.playSound("mob.cow.step", 0.10F, 1.0F);
    }
    protected float getSoundVolume() {
        return  0.3F;
    }

    protected int getDropItemId() {
        return Item.appleRed.itemID;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return this.spawnBabyAnimal(entityAgeable);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataWatcher.addObject(GOT_MILK_DATA_WATCHER_ID, (byte) 0);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);

        par1NBTTagCompound.setBoolean("fcGotMilk", gotMilk());
        par1NBTTagCompound.setInteger("fcMilkCount", milkAccumulationCount);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.hasKey("fcGotMilk")) {
            setGotMilk(par1NBTTagCompound.getBoolean("fcGotMilk"));
        }

        if (par1NBTTagCompound.hasKey("fcMilkCount")) {
            milkAccumulationCount = par1NBTTagCompound.getInteger("fcMilkCount");
        }
    }
    @Override
    public boolean isAIEnabled() {
        return !getWearingBreedingHarness();
    }
    @Override
    protected void dropFewItems(boolean killedByPlayer, int lootingModifier) {
        if (!isStarving()) {
            int numDrops = rand.nextInt(3) + rand.nextInt(1 + lootingModifier) + 1;

            if (isFamished()) {
                numDrops = numDrops / 2;
            }

            for (int i = 0; i < numDrops; ++i) {
                dropItem(Item.appleRed.itemID, 1);
            }

            if (!hasHeadCrabbedSquid()) {
                numDrops = rand.nextInt(3) + 1 + rand.nextInt(1 + lootingModifier);

                if (isFamished()) {
                    numDrops = numDrops / 2;
                }

                for (int iTempCount = 0; iTempCount < numDrops; ++iTempCount) {
                    if (isBurning()) {
                        if (worldObj.getDifficulty().shouldBurningMobsDropCookedMeat()) {
                            dropItem(ModItems.goatCooked.itemID, 1);
                        }
                        else {
                            dropItem(BTWItems.burnedMeat.itemID, 1);
                        }
                    }
                    else {
                        dropItem(ModItems.goatRaw.itemID, 1);
                    }
                }
            }
        }
    }
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.itemID == Item.cake.itemID;
    }
    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack stack = player.inventory.getCurrentItem();

        if (stack != null && stack.itemID == Item.bucketEmpty.itemID) {
            if (gotMilk()) {
                stack.stackSize--;

                if (stack.stackSize <= 0) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Item.bucketMilk));
                }
                else if (!player.inventory.addItemStackToInventory(new ItemStack(Item.bucketMilk))) {
                    player.dropPlayerItem(new ItemStack(Item.bucketMilk.itemID, 1, 0));
                }

                attackEntityFrom(DamageSource.generic, 0);

                if (!worldObj.isRemote) {
                    setGotMilk(false);

                    worldObj.playAuxSFX(BTWEffectManager.COW_MILKING_EFFECT_ID, MathHelper.floor_double(posX), (int) posY, MathHelper.floor_double(posZ), 0);
                }
            }
            else if (this.worldObj.getDifficulty().canMilkingStartleCows()) {
                attackEntityFrom(DamageSource.causePlayerDamage(player), 0);
            }

            return true;
        }

        // skip over EntityCow() super to avoid vanilla milking
        return entityAnimalInteract(player);
    }

    @Override
    public void onGrazeBlock(int i, int j, int k) {
        super.onGrazeBlock(i, j, k);

        if (!getWearingBreedingHarness()) {
        }
    }

    @Override
    public boolean isSubjectToHunger() {
        return true;
    }

    @Override
    public void onBecomeFamished() {
        super.onBecomeFamished();

        if (gotMilk()) {
            setGotMilk(false);
        }

        milkAccumulationCount = 0;
    }

    @Override
    public boolean canGrazeMycelium() {
        return true;
    }

    @Override
    public double getMountedYOffset() {
        return (double) height * 1.2D;
    }

    @Override
    public boolean getCanCreatureTypeBePossessed() {
        return true;
    }

    @Override
    protected void giveBirthAtTargetLocation(EntityAnimal targetMate, double dChildX, double dChildY, double dChildZ) {
        // small chance of normal birth when possessed
        if ((isFullyPossessed() || targetMate.isFullyPossessed()) && rand.nextInt(8) != 0) {
            if (worldObj.provider.dimensionId != 1 && worldObj.rand.nextInt(2) == 0) {
                birthMutant(targetMate, dChildX, dChildY, dChildZ);
            }
            else {
                stillBirth(targetMate, dChildX, dChildY, dChildZ);
            }
        }
        else {
            super.giveBirthAtTargetLocation(targetMate, dChildX, dChildY, dChildZ);
        }
    }


    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData data) {
        entityLivingOnSpawnWithEgg(data);
        initHungerWithVariance();

        if (!isChild()) {
            milkAccumulationCount = worldObj.rand.nextInt(FULL_MILK_ACCUMULATION_COUNT + (FULL_MILK_ACCUMULATION_COUNT / 4) + 1);

            if (milkAccumulationCount >= FULL_MILK_ACCUMULATION_COUNT) {
                milkAccumulationCount = 0;

                setGotMilk(true);
            }
        }
        return data;
    }

    @Override
    public boolean isValidZombieSecondaryTarget(EntityZombie zombie) {
        return true;
    }
    public GoatEntity spawnBabyAnimal(EntityAgeable parent) {
        return new GoatEntity(worldObj);
    }
    @Override
    public void updateHungerState() {
        if (!gotMilk() && isFullyFed() && !isChild() && !getWearingBreedingHarness()) {
            // producing milk consumes extra food. Hunger will be validated in super method
            hungerCountdown--;
            milkAccumulationCount++;

            if (milkAccumulationCount >= FULL_MILK_ACCUMULATION_COUNT) {
                setGotMilk(true);
                milkAccumulationCount = 0;
                worldObj.playAuxSFX(BTWEffectManager.COW_REGEN_MILK_EFFECT_ID, MathHelper.floor_double(posX), (int) posY + 1, MathHelper.floor_double(posZ), 0);
            }
        }

        // must call super method after extra hunger consumed above to validate

        super.updateHungerState();
    }

    @Override
    public float knockbackMagnitude() {
        return 0.15F;
    }
    public boolean gotMilk() {
        byte bGotMilk = dataWatcher.getWatchableObjectByte(GOT_MILK_DATA_WATCHER_ID);

        if (bGotMilk != 0) {
            return true;
        }

        return false;
    }

    protected void setGotMilk(boolean bGotMilk) {
        byte byteValue = 0;

        if (bGotMilk) {
            byteValue = 1;
        }

        dataWatcher.updateObject(GOT_MILK_DATA_WATCHER_ID, byteValue);
    }

    private boolean birthMutant(EntityAnimal targetMate, double dChildX, double dChildY, double dChildZ) {
        int randomFactor = rand.nextInt(20);

        EntityLiving childEntity = null;

        if (randomFactor == 0) {
            childEntity = new LavaSquidEntity(worldObj);
        } else if (randomFactor < 4) {
            for (int i = 0; i < 10; i++) {
                childEntity = new FoxEntity(worldObj);
            }
        } else if (randomFactor < 7) {
            for (int i = 0; i < 5; i++) {
                childEntity = new EntitySilverfish(worldObj);
            }
        } else {
            childEntity = new GlowSquidEntity(worldObj);
        }

        if (childEntity != null) {
            childEntity.setLocationAndAngles(dChildX, dChildY, dChildZ, rotationYaw, rotationPitch);
            worldObj.spawnEntityInWorld(childEntity);
        }
        return true;
    }
    protected void stillBirth(EntityAnimal targetMate, double dChildX, double dChildY, double dChildZ) {
        // just a copy of GiveBirthAtTargetLocation() from parent class that kills the baby after birth
        EntityAgeable childEntity = createChild(targetMate);

        if (childEntity != null) {
            childEntity.setGrowingAge(-childEntity.getTicksForChildToGrow());
            childEntity.setLocationAndAngles(dChildX, dChildY, dChildZ, rotationYaw, rotationPitch);
            worldObj.spawnEntityInWorld(childEntity);
            childEntity.attackEntityFrom(DamageSource.generic, 20);
        }
    }
}


