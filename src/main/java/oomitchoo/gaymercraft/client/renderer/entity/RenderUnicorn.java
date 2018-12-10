package oomitchoo.gaymercraft.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import oomitchoo.gaymercraft.client.model.ModelUnicorn;
import oomitchoo.gaymercraft.entity.EntityUnicorn;
import oomitchoo.gaymercraft.reference.Reference;

import javax.annotation.Nullable;

/**
 * Created by oOMitchOo on 08.12.2018.
 */
public class RenderUnicorn extends RenderLiving<EntityUnicorn> {

    public RenderUnicorn(RenderManager rendermanagerIn) {
        // 0.75F ist anscheinend die Schattengröße.
        super(rendermanagerIn, new ModelUnicorn(), 0.75F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityUnicorn entity) {
        return new ResourceLocation(Reference.MOD_ID+":"+"textures/entity/unicorn/"+"unicorn.png");
    }
}