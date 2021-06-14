package lotb.common.entities.item;

import lotb.common.items.KnifeItem;
import lotb.core.registries.ModEntities;
import lotb.core.registries.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class KnifeEntity extends ThrowableToolEntity {
    public KnifeEntity(EntityType<? extends ThrowableToolEntity> _type, World _world) {
        super(_type, _world);
    }

    public KnifeEntity(World _world, double x, double y, double z, ItemStack item) {
        super(ModEntities.KNIFE.get(), x, y, z, _world);
        if (item.getItem() instanceof KnifeItem)
            this.setItem(item);
        else
            LOGGER.error("tried to use an invalid item");
    }

    public void onCollideWithPlayer(PlayerEntity entityIn) {
        if (!this.world.isRemote && (this.inGround) ){
            if( entityIn.inventory.addItemStackToInventory(this.getItemStack())) {
                entityIn.onItemPickup(this, 1);
                this.remove();
            }
        }
    }

    @Override protected float getAttackDamage() {
        return ((KnifeItem)this.getItemStack().getItem()).getAttackDamage();
    }
    @Override protected Item getDefaultItem() {
        return ModItems.IRON_KNIFE.get();
    }
    @Override public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
