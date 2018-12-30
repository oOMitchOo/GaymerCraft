package oomitchoo.gaymercraft.common.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oomitchoo.gaymercraft.entity.EntityUnicorn;
import oomitchoo.gaymercraft.item.ItemRainbowStar;
import oomitchoo.gaymercraft.reference.Reference;

/**
 * Created by oOMitchOo on 30.12.2018.
 */
@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class RandomCommonEvents {

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

                    target.setDead();
                    world.spawnEntity(replaceWith);
                    if(!event.getEntityPlayer().isCreative())
                        heldItemStack.setItemDamage(1);
                }
            }
        }
    }
}