package lotb.common.entities.npc.message;

import lotb.common.entities.npc.AbstractNPCEntity;
import lotb.util.IndexedQueue;

public interface IConversation {

    AbstractNPCEntity[] getParticipants();

    IndexedQueue<IMessage> messages();

    void begin();

    void tick();

    void end();

}
