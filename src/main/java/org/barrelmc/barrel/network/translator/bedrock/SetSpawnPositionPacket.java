package org.barrelmc.barrel.network.translator.bedrock;

import com.github.steveice10.mc.protocol.packet.ingame.clientbound.level.ClientboundSetDefaultSpawnPositionPacket;
import org.barrelmc.barrel.network.translator.interfaces.BedrockPacketTranslator;
import org.barrelmc.barrel.player.Player;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

public class SetSpawnPositionPacket implements BedrockPacketTranslator {
    @Override
    public void translate(BedrockPacket pk, Player player) {
        org.cloudburstmc.protocol.bedrock.packet.SetSpawnPositionPacket packet = (org.cloudburstmc.protocol.bedrock.packet.SetSpawnPositionPacket) pk;
        Vector3i pos = packet.getSpawnPosition();
        player.getJavaSession().send(new ClientboundSetDefaultSpawnPositionPacket(pos, 0.0f));
    }
}
