package oomitchoo.gaymercraft.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import oomitchoo.gaymercraft.entity.UnicornEntity;

public class ModEntities {
    public static final EntityType<UnicornEntity> UNICORN = EntityType.Builder
            .create(UnicornEntity::new, EntityClassification.CREATURE)
            .size(1.3964844F, 1.6F)
            .setShouldReceiveVelocityUpdates(true)
            .setTrackingRange(80)
            .setUpdateInterval(3)
            .build("gaymercraft:unicorn");
}
