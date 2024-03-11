package org.barrelmc.barrel.network.translator.java;

import com.github.steveice10.mc.protocol.codec.MinecraftPacket;
import org.barrelmc.barrel.network.translator.interfaces.JavaPacketTranslator;
import org.barrelmc.barrel.player.Player;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;

public class SwingPacket implements JavaPacketTranslator {

    @Override
    public void translate(MinecraftPacket pk, Player player) {
        AnimatePacket animatePacket = new AnimatePacket();

        animatePacket.setAction(AnimatePacket.Action.SWING_ARM);
        animatePacket.setRuntimeEntityId(player.getRuntimeEntityId());
        player.getBedrockClientSession().sendPacket(animatePacket);
    }
}
