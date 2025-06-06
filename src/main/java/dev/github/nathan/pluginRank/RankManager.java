package dev.github.nathan.pluginRank;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class RankManager {

    public static void setRanks(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        for (Rank rank : Rank.values()) {
            Team team = player.getScoreboard().registerNewTeam(rank.name());
            team.setPrefix(rank.getDisplay());
        }

        Rank rank = getPlayerRank(player);
        for (Player alt : Bukkit.getOnlinePlayers()) {
            alt.getScoreboard().getTeam(rank.name()).addEntry(player.getName());
        }
    }

    private static Rank getPlayerRank(Player player) {
        for (Rank rank : Rank.values()) {
            if (player.hasPermission(rank.getPermission())) {
                return rank;
            }
        }
        return Rank.MEMBER;
    }

    public static void newRank(Player player) {
        Rank rank = getPlayerRank(player);
        for (Player alt : Bukkit.getOnlinePlayers()) {
            alt.getScoreboard().getTeam(rank.name()).addEntry(player.getName());
        }
    }

    public static void removeRank(Player player) {
        for (Player alt : Bukkit.getOnlinePlayers()) {
            alt.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }
    }

    public static void updatePlayerTag(Player player, Rank selectedRank) {
        for (Team team : player.getScoreboard().getTeams()) {
            team.removeEntry(player.getName());
        }

        Team team = player.getScoreboard().getTeam(selectedRank.name());
        if (team != null) {
            team.addEntry(player.getName());
        }
    }
}