package org.barrelmc.barrel.network.translator.bedrock;

import com.github.steveice10.mc.protocol.packet.ingame.clientbound.entity.ClientboundRemoveEntitiesPacket;
import org.barrelmc.barrel.network.translator.interfaces.BedrockPacketTranslator;
import org.barrelmc.barrel.player.Player;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

public class RemoveEntityPacket implements BedrockPacketTranslator {

    @Override
    public void translate(BedrockPacket pk, Player player) {
        org.cloudburstmc.protocol.bedrock.packet.RemoveEntityPacket packet = (org.cloudburstmc.protocol.bedrock.packet.RemoveEntityPacket) pk;

        int[] entityIds = new int[1];
        entityIds[0] = (int) packet.getUniqueEntityId();
        player.getJavaSession().send(new ClientboundRemoveEntitiesPacket(entityIds));
    }
}
