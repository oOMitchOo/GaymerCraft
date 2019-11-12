package oomitchoo.gaymercraft.common.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import oomitchoo.gaymercraft.entity.UnicornEntity;
import oomitchoo.gaymercraft.init.ModEntities;
import oomitchoo.gaymercraft.init.ModItems;
import oomitchoo.gaymercraft.reference.Reference;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class RandomCommonEvents {

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Entity target = event.getTarget();
        World world = event.getWorld();
        PlayerEntity player = event.getPlayer();
        // This should only contain one item, since we're checking for MAIN_HAND?
        Iterable<ItemStack> allHeldItemStack = player.getHeldEquipment();

        if(event.getHand() == Hand.MAIN_HAND)
            allHeldItemStack.forEach(itemStack -> exchangeHorseWithUnicornIfRainbowItem(itemStack, world, player, target));
    }

    private static void exchangeHorseWithUnicornIfRainbowItem(ItemStack itemStack, World world, PlayerEntity player, Entity target) {

        if(itemStack.getItem().equals(ModItems.RAINBOW_STAR_CHARGED)) {
            if (target instanceof HorseEntity && !((HorseEntity) target).isTame()) {
                if (!world.isRemote()) {
                    UnicornEntity replaceWith = new UnicornEntity(ModEntities.UNICORN, world);
                    replaceWith.setLocationAndAngles(target.posX, target.posY, target.posZ, target.rotationYaw, target.rotationPitch);
                    replaceWith.setRotationYawHead(target.getRotationYawHead());
                    replaceWith.setGrowingAge(((HorseEntity) target).getGrowingAge());

                    replaceWith.setRandomUnicornVariant();
                    replaceWith.setHorseTamed(true);
                    replaceWith.setTamedBy(player);

                    target.remove();
                    world.addEntity(replaceWith);
                    replaceWith.makeMad();

                    if(!player.isCreative())
                        player.setHeldItem(Hand.MAIN_HAND, ModItems.RAINBOW_STAR_UNCHARGED.getDefaultInstance());
                } else {
                    Random rand = new Random();
                    for (int i = 0; i < 30; i++) {
                        double d0 = rand.nextGaussian() * 0.02D;
                        double d1 = rand.nextGaussian() * 0.02D;
                        double d2 = rand.nextGaussian() * 0.02D;
                        double d3 = rand.nextDouble() * (double)target.getWidth() * 2.0D - (double)target.getWidth();
                        double d4 = 0.5D + rand.nextDouble() * (double)target.getHeight();
                        double d5 = rand.nextDouble() * (double)target.getWidth() * 2.0D - (double)target.getWidth();
                        world.addParticle((i < 15) ? ParticleTypes.POOF : ParticleTypes.HEART, target.posX + d3, target.posY + d4, target.posZ + d5, d0, d1, d2);
                    }
                }
            }
        }
    }
}
