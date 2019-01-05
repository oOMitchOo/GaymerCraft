package oomitchoo.gaymercraft.entity;

import net.minecraft.block.SoundType;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.AbstractChestHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oomitchoo.gaymercraft.reference.Reference;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by oOMitchOo on 07.12.2018.
 */
public class EntityUnicorn extends AbstractChestHorse {
    private static final DataParameter<Integer> UNICORN_VARIANT = EntityDataManager.<Integer>createKey(EntityUnicorn.class, DataSerializers.VARINT);
    private static final String[] UNICORN_TEXTURES = new String[] {"textures/entity/unicorn/unicorn_pink.png", "textures/entity/unicorn/unicorn_white.png", "textures/entity/unicorn/unicorn_blue.png"};
    private String texturePath;

    public EntityUnicorn(World worldIn)
    {
        super(worldIn);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(UNICORN_VARIANT, Integer.valueOf(0));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getUnicornVariant());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        // compound.getInteger returns 0, if the key "Variant" wasn't found (so no fix necessary for old unicorns).
        this.setUnicornVariant(compound.getInteger("Variant"));
    }

    private void setUnicornVariant(int variant) {
        this.dataManager.set(UNICORN_VARIANT, Integer.valueOf(variant));
    }

    public int setRandomUnicornVariant() {
        int variant = this.rand.nextInt(3);
        this.setUnicornVariant(variant);
        // It also returns the variant int, because it is used in onInitialSpawn() for the GroupData.
        return variant;
    }

    private int getUnicornVariant() {
        // This can not return null (look readEntityFromNBT()).
        return ((Integer)this.dataManager.get(UNICORN_VARIANT)).intValue();
    }

    @SideOnly(Side.CLIENT)
    private void setUnicornTexturePath()
    {
        int i = this.getUnicornVariant();
        // Ensure that i isn't greater than 255 (8 bits), then that it is between 0 and 2.
        int j = (i & 255) % 3;
        this.texturePath = UNICORN_TEXTURES[j];
    }

    @SideOnly(Side.CLIENT)
    public String getUnicornTexturePath()
    {
        if (this.texturePath == null)
        {
            this.setUnicornTexturePath();
        }

        return this.texturePath;
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

    @Override
    protected void playGallopSound(SoundType gallopSound)
    {
        super.playGallopSound(gallopSound);

        if (this.rand.nextInt(10) == 0)
        {
            this.playSound(SoundEvents.ENTITY_HORSE_BREATHE, gallopSound.getVolume() * 0.6F, gallopSound.getPitch());
        }
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
        // Can't mate with itself.
        if (otherAnimal == this) {
            return false;
        } else {
            // Only mates with other unicorns, which canMate() at the moment.
            return otherAnimal instanceof EntityUnicorn && this.canMate() && ((EntityUnicorn) otherAnimal).canMate();
        }
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        EntityUnicorn youngUnicorn = new EntityUnicorn(this.world);

        int rand = this.rand.nextInt(9); // gives 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (10 different values)
        int variant;
        // Basically a 50/50 chance that the child overtakes the variant of the father / mother.
        if (rand < 5) {
            // 'this' is the fatherUnicorn for better understanding.
            variant = this.getUnicornVariant() & 255;
        } else /* 5, 6, 7, 8, 9 */ {
            // ageable is the motherUnicorn for better understanding.
            EntityUnicorn motherUnicorn = (EntityUnicorn)ageable;
            variant = motherUnicorn.getUnicornVariant() & 255;
        }

        ((EntityUnicorn)youngUnicorn).setUnicornVariant(variant);
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

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        int i;

        if (livingdata instanceof EntityUnicorn.GroupData)
        {
            i = ((EntityUnicorn.GroupData)livingdata).variant;
        }
        else
        {
            i = this.setRandomUnicornVariant();
            livingdata = new EntityUnicorn.GroupData(i);
        }

        return livingdata;
    }

    public static class GroupData implements IEntityLivingData
    {
        public int variant;

        public GroupData(int variantIn)
        {
            this.variant = variantIn;
        }
    }
}