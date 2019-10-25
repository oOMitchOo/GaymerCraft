package oomitchoo.gaymercraft.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import oomitchoo.gaymercraft.client.renderer.entity.model.UnicornModel;
import oomitchoo.gaymercraft.entity.UnicornEntity;
import oomitchoo.gaymercraft.reference.Reference;

import javax.annotation.Nullable;

public class UnicornRenderer extends MobRenderer<UnicornEntity, UnicornModel> {
    public UnicornRenderer(EntityRendererManager manager) {
        super(manager, new UnicornModel(), 0.75F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(UnicornEntity entity) {
        // after my inspection texturePath will never be null.
        String texturePath = entity.getUnicornTexturePath();
        return new ResourceLocation(Reference.MOD_ID+":"+texturePath);
    }

    public static class RenderFactory implements IRenderFactory<UnicornEntity>
    {
        @Override
        public EntityRenderer<? super UnicornEntity> createRenderFor(EntityRendererManager manager)
        {
            return new UnicornRenderer(manager);
        }
    }
}
