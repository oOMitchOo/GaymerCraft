package oomitchoo.gaymercraft.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.ObjectHolder;
import oomitchoo.gaymercraft.entity.UnicornEntity;
import oomitchoo.gaymercraft.reference.Reference;

@ObjectHolder(Reference.MOD_ID)
public class ModEntities {
    // TODO: Change this to being null again and register like everything else, when the SpawnEggItem works better (with suppliers???)
    public static final EntityType<UnicornEntity> UNICORN = EntityType.Builder
            .create(UnicornEntity::new, EntityClassification.CREATURE)
            .size(1.3964844F, 1.6F)
            .setShouldReceiveVelocityUpdates(true)
            .setTrackingRange(80)
            .setUpdateInterval(3)
            .build("gaymercraft:unicorn");
}
