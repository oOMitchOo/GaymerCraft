package oomitchoo.gaymercraft.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.AbstractChestHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import oomitchoo.gaymercraft.reference.Reference;

import java.util.Random;

/**
 * Created by oOMitchOo on 07.12.2018.
 */
public class EntityUnicorn extends AbstractChestHorse {
    public EntityUnicorn(World worldIn)
    {
        super(worldIn);
    }

    @Override
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_HORSE;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        super.getAmbientSound();
        return SoundEvents.ENTITY_HORSE_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        super.getDeathSound();
        return SoundEvents.ENTITY_HORSE_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        super.getHurtSound(damageSourceIn);
        return SoundEvents.ENTITY_HORSE_HURT;
    }

    @Override
    protected SoundEvent getAngrySound()
    {
        super.getAngrySound();
        return SoundEvents.ENTITY_HORSE_ANGRY;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (new Random().nextInt(100) < Reference.Config.configUnicornLoveAmount) {

            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            double d3 = this.rand.nextDouble() * (double)this.width * 2.0D - (double)this.width;
            double d4 = 0.5D + this.rand.nextDouble() * (double)this.height;
            double d5 = this.rand.nextDouble() * (double)this.width * 2.0D - (double)this.width;
            this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + d3, this.posY + d4, this.posZ + d5, d0, d1, d2);
        }
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    @Override
    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this) {
            return false;
        } else {
            return otherAnimal instanceof EntityUnicorn && this.canMate() && ((EntityUnicorn) otherAnimal).canMate(); // Only mates with other unicorns.
        }
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        EntityUnicorn youngUnicorn = new EntityUnicorn(this.world);
        this.setOffspringAttributes(ageable, youngUnicorn);
        return youngUnicorn;
    }

    /**
     * Applies movement speed and the sort.
     */
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Reference.Config.configUnicornMaxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(Reference.Config.configUnicornMovementSpeed);
        this.getEntityAttribute(JUMP_STRENGTH).setBaseValue(Reference.Config.configUnicornJumpStrength);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(Reference.Config.configUnicornArmor);
    }
}