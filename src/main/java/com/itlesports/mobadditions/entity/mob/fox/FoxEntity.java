package com.itlesports.mobadditions.entity.mob.fox;

import btw.entity.mob.*;
import btw.entity.mob.behavior.*;
import btw.item.BTWItems;
import com.itlesports.mobadditions.entity.mob.fox.ai.EntityAISleep;
import com.itlesports.mobadditions.entity.mob.wolf.ChestnutWolfEntity;
import com.itlesports.mobadditions.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class FoxEntity extends EntityWolf {
    protected EntityAISleep aiSleep = new EntityAISleep(this);
    private static final float MOVE_SPEED_AGGRESSIVE = 0.45F;
    private static final float MOVE_SPEED_PASSIVE = 0.3F;
    public FoxEntity(World world) {
        super(world);

        //this.texture = "/mobadditions/entity/mob/fox/fox.png";
        tasks.removeAllTasks();
        tasks.addTask( 1, new EntityAISwimming( this ) );
        tasks.addTask( 2, new PanicOnHeadCrabBehavior(this, MOVE_SPEED_AGGRESSIVE));
        tasks.addTask( 3, aiSit );
        tasks.addTask( 4, new EntityAILeapAtTarget( this, 0.4F ) );
        tasks.addTask( 5, new EntityAIAttackOnCollide(this, MOVE_SPEED_PASSIVE, true ));
        tasks.addTask( 6, new EntityAIMate(this, MOVE_SPEED_PASSIVE));
        tasks.addTask( 6, new MultiTemptBehavior(this, MOVE_SPEED_PASSIVE));
        tasks.addTask( 7, new MoveToLooseFoodBehavior(this, MOVE_SPEED_PASSIVE));
        tasks.addTask( 8, new SimpleWanderBehavior(this, MOVE_SPEED_PASSIVE));
        tasks.addTask( 9, new EntityAIBeg( this, 8F ) );
        tasks.addTask( 10, new EntityAIWatchClosest( this, EntityPlayer.class, 8F ) );
        tasks.addTask( 11, new EntityAILookIdle( this ) );
        tasks.addTask(12, aiSleep );

        targetTasks.removeAllTasks();

        targetTasks.addTask( 1, new EntityAIHurtByTarget( this, true ) );

        targetTasks.addTask( 2, new WildWolfTargetIfStarvingOrHostileBehavior( this,
                EntityVillager.class, 16F, 0, false ) );

        targetTasks.addTask( 2, new WildWolfTargetIfStarvingOrHostileBehavior( this,
                EntityPlayer.class, 16F, 0, false ) );

        targetTasks.addTask( 2, new WildWolfTargetIfHungryBehavior( this,
                EntityChicken.class, 16F, 0, false ) );

        targetTasks.addTask( 2, new WildWolfTargetIfHungryBehavior( this,
                EntitySheep.class, 16F, 0, false ) );

        targetTasks.addTask( 2, new WildWolfTargetIfHungryBehavior( this,
                EntityPig.class, 16F, 0, false ) );

        targetTasks.addTask( 2, new WildWolfTargetIfStarvingBehavior( this,
                EntityCow.class, 16F, 0, false ) );

        targetTasks.addTask( 2, new WildWolfTargetIfStarvingBehavior( this,
                EntityHorse.class, 16F, 0, false ) );
    }
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.45F);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(4.0D);
    }
    /*
    @Override
    @Environment(EnvType.CLIENT)
    public String getTexture() {
        if (isTamed()) {
            if (isStarving()) {
                return "/mobadditions/entity/mob/fox/fox_starving.png";
            }

            return "/mobadditions/entity/mob/fox/fox_tame.png";
        } else if (isAngry()) {
            return "/mobadditions/entity/mob/fox/fox_angry.png";
        } else if (isStarving() || hasAttackTarget()) {
            return "/mobadditions/entity/mob/fox/fox_starving.png";
        }

        return texture; // intentionally bypass super method
    }

     */
    protected String getLivingSound()
    {
        if ( isWildAndHostile() )
        {
            return "entity.mob.fox.growl";
        }
        else if ( this.rand.nextInt(3) == 0 )
        {
            if ( isTamed() && ( dataWatcher.getWatchableObjectInt(18) < 10 || !isFullyFed() ) )
            {
                if ( isStarving() )
                {
                    return "entity.mob.fox.growl";
                }
                else
                {
                    return "entity.mob.fox.whine";
                }
            }
            else
            {
                return "entity.mob.fox.panting";
            }
        }
        else
        {
            return "entity.mob.fox.bark";
        }
    }
    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        int worldTime = (int) (this.worldObj.worldInfo.getWorldTime() % 24000L);
        if (worldTime > 17500 && worldTime < 23000) //
        {
            aiSleep.setSleeping(false);
            setSleeping(true);
        }
        else
        {
            aiSleep.setSleeping(false);
            setSleeping(false);
        }
    }
    @Override
    protected int getDropItemId()
    {
        if ( !worldObj.isRemote )
        {
            // yummy yummy foxchops

            if ( isBurning() )
            {
                if (worldObj.getDifficulty().shouldBurningMobsDropCookedMeat()) {
                    dropItem(ModItems.cookedFoxChop.itemID, 1);
                }
                else {
                    dropItem(BTWItems.burnedMeat.itemID, 1);
                }
            }
            else
            {
                return ModItems.rawFoxChop.itemID;
            }
        }

        return -1;
    }
    @Override
    public boolean canMateWith(EntityAnimal par1EntityAnimal)
    {
        if (par1EntityAnimal == this)
        {
            return false;
        }
        else if (!this.isTamed())
        {
            return false;
        }
        else if (!(par1EntityAnimal instanceof FoxEntity))
        {
            return false;
        }
        else
        {
            FoxEntity var2 = (FoxEntity)par1EntityAnimal;
            return !var2.isTamed() ? false : (var2.isSitting() ? false : this.isInLove() && var2.isInLove());
        }
    }
    @Override
    public FoxEntity spawnBabyAnimal(EntityAgeable parent) {
        return new FoxEntity(this.worldObj);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        return this.spawnBabyAnimal(par1EntityAgeable);
    }

    public void initCreature() {
        int worldTime = (int)(this.worldObj.worldInfo.getWorldTime() % 24000L);

        if (worldTime > 17500  && worldTime < 23000) //this.getRNG().nextInt(20) == 0  !this.worldObj.isDaytime() this.worldObj.getMoonPhase()
        {
            aiSleep.setSleeping(true);
            setSleeping(true);
        }
        aiSleep.setSleeping(false);
        setSleeping(false);
    }
    public boolean isSleeping()
    {
        return (this.dataWatcher.getWatchableObjectByte(16 & 1) != 0);
    }

    public void setSleeping(boolean par1) {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);

        if (par1) {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte) (var2 | 1)));
        } else {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte) (var2 & -2)));
        }
    }
    @Override
    public void setAngry( boolean bAngry )
    {
        super.setAngry( bAngry );

        if ( bAngry )
        {
            setSitting(false);
            setSleeping(false);
        }
    }
}