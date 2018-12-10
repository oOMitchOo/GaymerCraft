package oomitchoo.gaymercraft.helper.handlers;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import oomitchoo.gaymercraft.GaymerCraft;
import oomitchoo.gaymercraft.block.BlockColoredWater;
import oomitchoo.gaymercraft.entity.EntityUnicorn;
import oomitchoo.gaymercraft.init.ModBlocks;
import oomitchoo.gaymercraft.init.ModItems;
import oomitchoo.gaymercraft.item.ItemRainbowStar;
import oomitchoo.gaymercraft.reference.Reference;

import java.util.Random;

/**
 * Created by oOMitchOo on 29.11.2018.
 */
@Mod.EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Entity target = event.getTarget();
        World world = event.getWorld();
        Entity player = event.getEntityPlayer();
        ItemStack heldItemStack = event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);

        if(heldItemStack.getItem() instanceof ItemRainbowStar) {
            if (!world.isRemote && event.getHand() == EnumHand.MAIN_HAND && target instanceof EntityHorse) {
                // Lösung damit keine Items verloren gehen beim converten? Nicht-zahme Pferde haben keine Items am Körper.
                if (!((EntityHorse) target).isTame() && heldItemStack.getMetadata() == 0) { // TODO: else Warnhinweis auf Spieler-Bildschirm
                    // TODO: Irgendwie hilft das abfangen der Werte nicht. Das Unicorn hat trotzdem nicht dieselbe Ausrichtung wie das Pferd. Muss ich weiter testen.
                    EntityUnicorn replaceWith = new EntityUnicorn(world);
                    replaceWith.setLocationAndAngles(target.posX, target.posY, target.posZ, target.rotationYaw, target.rotationPitch);
                    replaceWith.setRenderYawOffset(((EntityHorse) target).renderYawOffset);
                    replaceWith.setRotationYawHead(target.getRotationYawHead());

                    // TODO: Hier würde ich auch gerne das Alter setzen, aber forcedAge ist protected in EntityAgeable.
                    replaceWith.setHorseTamed(true);

                    // TODO: Trying to get particles to work. HALP!
                    Random random = new Random();
                    for (int i = 0; i < 7; ++i)
                    {
                        double d0 = random.nextGaussian() * 0.02D;
                        double d1 = random.nextGaussian() * 0.02D;
                        double d2 = random.nextGaussian() * 0.02D;
                        double d3 = random.nextDouble() * (double)target.width * 2.0D - (double)target.width;
                        double d4 = 0.5D + random.nextDouble() * (double)target.height;
                        double d5 = random.nextDouble() * (double)target.width * 2.0D - (double)target.width;
                        world.spawnParticle(EnumParticleTypes.HEART, target.posX + d3, target.posY + d4, target.posZ + d5, d0, d1, d2);
                    }

                    target.setDead();
                    world.spawnEntity(replaceWith);
                    if(!event.getEntityPlayer().isCreative())
                        heldItemStack.setItemDamage(1);
                }
            }
        }

        // LogHelper.info(event.getTarget().getEntityId());
    }

    @SubscribeEvent
    public static void onConfigChanged (ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(Reference.MOD_ID)) {
            // Resync configs
            ConfigHandler.loadConfig();
        }
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();
        // ITEMS
        r.register(ModItems.RAINBOW_STAR);
        // ITEMBLOCKS
        r.register(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_1);
        r.register(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_2);
        r.register(ModBlocks.ITEMBLOCK_WOOD_VERT_SLAB_1);
        r.register(ModBlocks.ITEMBLOCK_WOOD_VERT_SLAB_2);
        r.register(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_NEW);
        r.register(ModBlocks.ITEMBLOCK_PURPUR_VERT_SLAB);
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> r = event.getRegistry();
        // BLOCKS
        r.register(ModBlocks.STONE_VERT_SLAB_1);
        r.register(ModBlocks.DOUBLE_STONE_VERT_SLAB_1);
        r.register(ModBlocks.STONE_VERT_SLAB_2);
        r.register(ModBlocks.DOUBLE_STONE_VERT_SLAB_2);
        r.register(ModBlocks.WOOD_VERT_SLAB_1);
        r.register(ModBlocks.DOUBLE_WOOD_VERT_SLAB_1);
        r.register(ModBlocks.WOOD_VERT_SLAB_2);
        r.register(ModBlocks.DOUBLE_WOOD_VERT_SLAB_2);
        r.register(ModBlocks.STONE_VERT_SLAB_NEW);
        r.register(ModBlocks.DOUBLE_STONE_VERT_SLAB_NEW);
        r.register(ModBlocks.PURPUR_VERT_SLAB);
        r.register(ModBlocks.DOUBLE_PURPUR_VERT_SLAB);
        // FLUID BLOCKS

        r.register(ModBlocks.BLOCK_WHITE_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_WHITE_WATER, ((BlockColoredWater) ModBlocks.BLOCK_WHITE_WATER).getFluid());
        r.register(ModBlocks.BLOCK_ORANGE_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_ORANGE_WATER, ((BlockColoredWater) ModBlocks.BLOCK_ORANGE_WATER).getFluid());
        r.register(ModBlocks.BLOCK_MAGENTA_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_MAGENTA_WATER, ((BlockColoredWater) ModBlocks.BLOCK_MAGENTA_WATER).getFluid());
        r.register(ModBlocks.BLOCK_LIGHT_BLUE_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_LIGHT_BLUE_WATER, ((BlockColoredWater) ModBlocks.BLOCK_LIGHT_BLUE_WATER).getFluid());
        r.register(ModBlocks.BLOCK_YELLOW_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_YELLOW_WATER, ((BlockColoredWater) ModBlocks.BLOCK_YELLOW_WATER).getFluid());
        r.register(ModBlocks.BLOCK_LIME_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_LIME_WATER, ((BlockColoredWater) ModBlocks.BLOCK_LIME_WATER).getFluid());
        r.register(ModBlocks.BLOCK_PINK_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_PINK_WATER, ((BlockColoredWater) ModBlocks.BLOCK_PINK_WATER).getFluid());
        r.register(ModBlocks.BLOCK_GRAY_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_GRAY_WATER, ((BlockColoredWater) ModBlocks.BLOCK_GRAY_WATER).getFluid());
        r.register(ModBlocks.BLOCK_LIGHT_GRAY_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_LIGHT_GRAY_WATER, ((BlockColoredWater) ModBlocks.BLOCK_LIGHT_GRAY_WATER).getFluid());
        r.register(ModBlocks.BLOCK_CYAN_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_CYAN_WATER, ((BlockColoredWater) ModBlocks.BLOCK_CYAN_WATER).getFluid());
        r.register(ModBlocks.BLOCK_PURPLE_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_PURPLE_WATER, ((BlockColoredWater) ModBlocks.BLOCK_PURPLE_WATER).getFluid());
        r.register(ModBlocks.BLOCK_BLUE_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_BLUE_WATER, ((BlockColoredWater) ModBlocks.BLOCK_BLUE_WATER).getFluid());
        r.register(ModBlocks.BLOCK_BROWN_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_BROWN_WATER, ((BlockColoredWater) ModBlocks.BLOCK_BROWN_WATER).getFluid());
        r.register(ModBlocks.BLOCK_GREEN_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_GREEN_WATER, ((BlockColoredWater) ModBlocks.BLOCK_GREEN_WATER).getFluid());
        r.register(ModBlocks.BLOCK_RED_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_RED_WATER, ((BlockColoredWater) ModBlocks.BLOCK_RED_WATER).getFluid());
        r.register(ModBlocks.BLOCK_BLACK_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_BLACK_WATER, ((BlockColoredWater) ModBlocks.BLOCK_BLACK_WATER).getFluid());
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        // ITEM-MODELS
        GaymerCraft.proxy.registerItemRendererNoSubt(ModItems.RAINBOW_STAR, 0, "_loaded", "inventory"); //Item item, int metaData, String regNameAddition, String id
        GaymerCraft.proxy.registerItemRendererNoSubt(ModItems.RAINBOW_STAR, 1, "_unloaded", "inventory");
        // ITEMBLOCK-MODELS
        GaymerCraft.proxy.registerItemRendererWithSubt(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_1, "inventory");
        GaymerCraft.proxy.registerItemRendererWithSubt(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_2, "inventory");
        GaymerCraft.proxy.registerItemRendererWithSubt(ModBlocks.ITEMBLOCK_WOOD_VERT_SLAB_1, "inventory");
        GaymerCraft.proxy.registerItemRendererWithSubt(ModBlocks.ITEMBLOCK_WOOD_VERT_SLAB_2, "inventory");
        GaymerCraft.proxy.registerItemRendererNoSubt(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_NEW, 0, "", "inventory");
        GaymerCraft.proxy.registerItemRendererNoSubt(ModBlocks.ITEMBLOCK_PURPUR_VERT_SLAB, 0, "", "inventory");
    }
}