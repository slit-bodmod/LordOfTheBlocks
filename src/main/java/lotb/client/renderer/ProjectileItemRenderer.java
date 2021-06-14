package lotb.client.renderer;

import lotb.common.entities.item.ThrowableToolEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;

public class ProjectileItemRenderer extends SpriteRenderer<ThrowableToolEntity> {

    public ProjectileItemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, Minecraft.getInstance().getItemRenderer());
    }
}
