package oomitchoo.gaymercraft.entity;

import net.minecraft.block.SoundType;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.horse.AbstractChestedHorseEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import oomitchoo.gaymercraft.config.Config;
import oomitchoo.gaymercraft.init.ModEntities;

import javax.annotation.Nullable;
import java.util.Random;

public class UnicornEntity extends AbstractChestedHorseEntity {
    private static final DataParameter<Integer> UNICORN_VARIANT = EntityDataManager.<Integer>createKey(UnicornEntity.class, DataSerializers.VARINT);
    private static final String[] UNICORN_TEXTURES = new String[] {"textures/entity/unicorn/unicorn_pink.png", "textures/entity/unicorn/unicorn_white.png", "textures/entity/unicorn/unicorn_blue.png"};
    private String texturePath;

    public UnicornEntity(EntityType<? extends UnicornEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(UNICORN_VARIANT, 0);
    }

    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putInt("Variant", this.getUnicornVariant());
    }

    @Override
    public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        // compound.getInteger returns 0, if the key "Variant" wasn't found (so no fix necessary for old unicorns).
        this.setUnicornVariant(compound.getInt("Variant"));
    }

    private void setUnicornVariant(int variant) {
        this.dataManager.set(UNICORN_VARIANT, variant);
    }

    public int setRandomUnicornVariant() {
        int variant = this.rand.nextInt(3);
        this.setUnicornVariant(variant);
        // It also returns the variant int, because it is used in onInitialSpawn() for the GroupData.
        return variant;
    }

    private int getUnicornVariant() {
        // This can not return null (look readEntityFromNBT()).
        return (Integer) this.dataManager.get(UNICORN_VARIANT);
    }

    @OnlyIn(Dist.CLIENT)
    private void setUnicornTexturePath()
    {
        int i = this.getUnicornVariant();
        // Ensure that i isn't greater than 255 (8 bits), then that it is between 0 and 2.
        int j = (i & 255) % 3;
        this.texturePath = UNICORN_TEXTURES[j];
    }

    @OnlyIn(Dist.CLIENT)
    public String getUnicornTexturePath()
    {
        if (this.texturePath == null)
        {
            this.setUnicornTexturePath();
        }
        return this.texturePath;
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

    @Override
    protected void playGallopSound(SoundType gallopSound)
    {
        super.playGallopSound(gallopSound);

        if (this.rand.nextInt(10) == 0)
        {
            this.playSound(SoundEvents.ENTITY_HORSE_BREATHE, gallopSound.getVolume() * 0.6F, gallopSound.getPitch());
        }
    }

    @Override
    public void livingTick()
    {
        super.livingTick();

        if (new Random().nextInt(100) < Config.UNICORN_LOVE_AMOUNT.get()) {

            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            double d3 = this.rand.nextDouble() * (double)this.getWidth() * 2.0D - (double)this.getWidth();
            double d4 = 0.5D + this.rand.nextDouble() * (double)this.getHeight();
            double d5 = this.rand.nextDouble() * (double)this.getWidth() * 2.0D - (double)this.getWidth();
            this.world.addParticle(ParticleTypes.HEART, this.posX + d3, this.posY + d4, this.posZ + d5, d0, d1, d2);
        }
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    @Override
    public boolean canMateWith(AnimalEntity otherAnimal)
    {
        // Can't mate with itself.
        if (otherAnimal == this) {
            return false;
        } else {
            // Only mates with other unicorns, which canMate() at the moment.
            return otherAnimal instanceof UnicornEntity && this.canMate() && ((UnicornEntity) otherAnimal).canMate();
        }
    }

    @Override
    public AgeableEntity createChild(AgeableEntity ageable)
    {
        UnicornEntity youngUnicorn = ModEntities.UNICORN.create(this.world);

        int rand = this.rand.nextInt(9); // gives 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (10 different values)
        int variant;
        // Basically a 50/50 chance that the child overtakes the variant of the father / mother.
        if (rand < 5) {
            // 'this' is the fatherUnicorn for better understanding.
            variant = this.getUnicornVariant() & 255;
        } else /* 5, 6, 7, 8, 9 */ {
            // ageable is the motherUnicorn for better understanding.
            UnicornEntity motherUnicorn = (UnicornEntity) ageable;
            variant = motherUnicorn.getUnicornVariant() & 255;
        }

        ((UnicornEntity)youngUnicorn).setUnicornVariant(variant);
        this.setOffspringAttributes(ageable, youngUnicorn);
        return youngUnicorn;
    }

    /**
     * Applies movement speed and the sort.
     */
    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double) Config.UNICORN_MAX_HEALTH_HALF_HEARTS.get());
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(Config.UNICORN_MOVEMENT_SPEED.get());
        this.getAttribute(JUMP_STRENGTH).setBaseValue(Config.UNICORN_JUMP_STRENGTH.get());
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(Config.UNICORN_ARMOR.get());
    }

    //DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata
    @Nullable
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
    {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        int i;
        if (spawnDataIn instanceof UnicornEntity.UnicornData) {
            i = ((UnicornEntity.UnicornData)spawnDataIn).variant;
        } else {
            i = this.setRandomUnicornVariant();
            spawnDataIn = new UnicornEntity.UnicornData(i);
        }
        return spawnDataIn;
    }

    public static class UnicornData implements ILivingEntityData
    {
        public final int variant;

        public UnicornData(int variantIn)
        {
            this.variant = variantIn;
        }
    }
}
