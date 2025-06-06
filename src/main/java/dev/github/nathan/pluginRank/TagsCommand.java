package dev.github.nathan.pluginRank;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TagsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by a player.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            showAvailableTags(player);
        }

        else if (args.length == 2 && args[0].equalsIgnoreCase("select")) {
            String tagName = args[1].toUpperCase();
            applyTag(player, tagName);
        } else {
            player.sendMessage(ChatColor.RED + "Usage: /tags or /tags select <tag>");
        }

        return true;
    }

    private void showAvailableTags(Player player) {
        boolean hasTag = false;
        player.sendMessage(ChatColor.GREEN + "Choose a tag to apply:");

        for (Rank rank : Rank.values()) {
            if (player.hasPermission(rank.getPermission())) {
                hasTag = true;
                TextComponent tagMessage = new TextComponent(ChatColor.WHITE + rank.getDisplay());
                tagMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tags select " + rank.name()));
                player.spigot().sendMessage(tagMessage);
            }
        }

        if (!hasTag) {
            player.sendMessage(ChatColor.RED + "You do not have permission for any tags.");
        }
    }

    private void applyTag(Player player, String tagName) {
        try {
            Rank selectedRank = Rank.valueOf(tagName);

            if (player.hasPermission(selectedRank.getPermission())) {
                player.setDisplayName(selectedRank.getDisplay() + player.getName());
                player.sendMessage(ChatColor.GREEN + "You are now: " + selectedRank.getDisplay());

                RankManager.updatePlayerTag(player, selectedRank);
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission for this tag.");
            }
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Invalid tag name.");
        }
    }
}