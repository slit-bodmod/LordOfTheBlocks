package lotb.world.capabilities.relationship;

import com.google.common.collect.Maps;
import lotb.LotbMod;
import lotb.common.entities.npc.AbstractNPCEntity;
import lotb.common.entities.npc.IDataNPC;
import lotb.common.entities.npc.relationship.RelationShip;
import lotb.util.Valid;
import lotb.world.capabilities.ICapabilityData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.capabilities.CapabilityInject;

import java.util.Map;

public interface IPlayerNpcRelationShip extends ICapabilityData<IDataNPC, RelationShip> {

    Map<AbstractNPCEntity, RelationShip> getRelationShips();

    void addRelationShip(AbstractNPCEntity npc, RelationShip relationShip);

    void setRelationShips(Map<AbstractNPCEntity, RelationShip> relationShips);

    void removeRelationShip(AbstractNPCEntity npc);

    void changeRelationShip(AbstractNPCEntity npc, RelationShip newRelationShip);

    boolean hasRelationShip(AbstractNPCEntity npc);

    RelationShip getRelationShip(AbstractNPCEntity npc);

    default ListNBT write() {
        ListNBT list = new ListNBT();
        for (Map.Entry<AbstractNPCEntity, RelationShip> entry : getRelationShips().entrySet()) {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("NpcID", entry.getKey().getEntityId());
            nbt.putInt("RelationShipOrdinal", entry.getValue().ordinal());
            list.add(nbt);
        }
        return list;

    }

    static Map<IDataNPC, RelationShip> readData(ListNBT list) {
        Map<IDataNPC, RelationShip> dataMap = Maps.newHashMap();
        list.forEach(entry -> {
            if (!(entry instanceof CompoundNBT)) {
                LotbMod.LOGGER.error("Illegal entry in player data list, can not read entity data");
                throw new IllegalArgumentException();
            }
            CompoundNBT tag = (CompoundNBT) entry;
            int npcId = tag.getInt("NpcID");
            int relationShipOrdinal = tag.getInt("RelationShipOrdinal");
            dataMap.put(new IDataNPC.IDataNPCImpl(npcId), RelationShip.VALUES[relationShipOrdinal]);
        });
        return dataMap;
    }

    static Map<AbstractNPCEntity, RelationShip> read(ListNBT list) {
        Map<AbstractNPCEntity, RelationShip> relationShips = Maps.newHashMap();
        ClientWorld world = Minecraft.getInstance().world;
        Valid.notNull(world, "Can not load player data, world is null. Class: PlayerNpcRelationShipSerializer");
        Map<IDataNPC, RelationShip> data = readData(list);
        Valid.checkTrue(data.isEmpty(), "Can not load player data, data map is empty");
        for (Map.Entry<IDataNPC, RelationShip> entry : data.entrySet()) {
            relationShips.put(entry.getKey().getNPCEntity(world), entry.getValue());
        }
        return relationShips;
    }

    class Capability {

        @CapabilityInject(IPlayerNpcRelationShip.class)
        public static net.minecraftforge.common.capabilities.Capability<IPlayerNpcRelationShip> PLAYER_NPC_RELATION_SHIP;

        public static net.minecraftforge.common.capabilities.Capability<IPlayerNpcRelationShip> get() {
            return PLAYER_NPC_RELATION_SHIP;
        }

    }

}
