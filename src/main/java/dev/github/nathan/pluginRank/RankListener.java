package dev.github.nathan.pluginRank;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class RankListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        RankManager.setRanks(event.getPlayer());
        RankManager.newRank(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        RankManager.removeRank(event.getPlayer());
    }
}