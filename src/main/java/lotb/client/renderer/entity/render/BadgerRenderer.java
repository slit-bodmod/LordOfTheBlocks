package lotb.client.renderer.entity.render;

import lotb.LotbMod;
import lotb.client.renderer.entity.model.BadgerModel;
import lotb.common.entities.Badger;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BadgerRenderer extends MobRenderer<Badger, BadgerModel<Badger>> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(LotbMod.MODID,"textures/entity/badger.png");
 
 	public BadgerRenderer(EntityRendererManager renderManager) {
	 	super(renderManager,new BadgerModel<Badger>(),0.5f);
 	}
 	@Override
	public ResourceLocation getEntityTexture(Badger entity) {
 		return TEXTURE;
 	}
}
