package dev.github.nathan.pluginRank;

import org.bukkit.plugin.java.JavaPlugin;

public final class PluginRank extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new RankListener(), this);
        getCommand("tags").setExecutor(new TagsCommand());
        getLogger().info("Plugin successfully started.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin successfully disabled.");
    }
}