package org.barrelmc.barrel.network.translator.bedrock;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.protocol.data.game.PlayerListEntry;
import com.github.steveice10.mc.protocol.data.game.entity.player.GameMode;
import com.github.steveice10.mc.protocol.packet.ingame.clientbound.ClientboundPlayerInfoRemovePacket;
import net.kyori.adventure.text.Component;
import org.barrelmc.barrel.network.translator.interfaces.BedrockPacketTranslator;
import org.barrelmc.barrel.player.Player;
import org.barrelmc.barrel.utils.Utils;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerListPacket implements BedrockPacketTranslator {

    @Override
    public void translate(BedrockPacket pk, Player player) {
        org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket packet = (org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket) pk;
        switch (packet.getAction()) {
            case ADD: {
                ArrayList<PlayerListEntry> playerListEntries = new ArrayList<>();

                for (org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket.Entry entry : packet.getEntries()) {
                    GameProfile gameProfile = new GameProfile(entry.getUuid(), Utils.lengthCutter(entry.getName(), 16));
                    playerListEntries.add(new PlayerListEntry(entry.getUuid(), gameProfile, true, 0, GameMode.SURVIVAL, Component.text(Utils.lengthCutter(entry.getName(), 16)), UUID.randomUUID(), 0L, null, null));
                }

                PlayerListEntry[] playerListEntriesL = playerListEntries.toArray(new PlayerListEntry[0]);
                //player.getJavaSession().send(new ClientboundPlayerInfoUpdatePacket(EnumSet.of(PlayerListEntryAction.ADD_PLAYER, PlayerListEntryAction.INITIALIZE_CHAT, PlayerListEntryAction.UPDATE_LISTED, PlayerListEntryAction.UPDATE_LATENCY, PlayerListEntryAction.UPDATE_GAME_MODE, PlayerListEntryAction.UPDATE_DISPLAY_NAME), playerListEntriesL));
                break;
            }
            case REMOVE: {
                ArrayList<UUID> profileIds = new ArrayList<>();

                for (org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket.Entry entry : packet.getEntries()) {
                    profileIds.add(entry.getUuid());
                }
                player.getJavaSession().send(new ClientboundPlayerInfoRemovePacket(profileIds));
                break;
            }
        }
    }
}
