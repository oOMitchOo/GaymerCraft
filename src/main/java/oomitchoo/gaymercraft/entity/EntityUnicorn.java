package oomitchoo.gaymercraft.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.*;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

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
}