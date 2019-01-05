package oomitchoo.gaymercraft.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oomitchoo.gaymercraft.client.model.ModelUnicorn;
import oomitchoo.gaymercraft.entity.EntityUnicorn;
import oomitchoo.gaymercraft.reference.Reference;

import javax.annotation.Nullable;

/**
 * Created by oOMitchOo on 08.12.2018.
 */
@SideOnly(Side.CLIENT)
public class RenderUnicorn extends RenderLiving<EntityUnicorn> {

    public RenderUnicorn(RenderManager rendermanagerIn) {
        // 0.75F ist anscheinend die Schattengröße.
        super(rendermanagerIn, new ModelUnicorn(), 0.75F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityUnicorn entity) {
        // after my inspection texturePath will never be null.
        String texturePath = entity.getUnicornTexturePath();
        return new ResourceLocation(Reference.MOD_ID+":"+texturePath);
    }
}