package xyz.core;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.core.connect.Zobe;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin implements Listener {
    public ConsoleCommandSender console;
    public FileConfiguration playerData;
    public File data;
    private Zobe zobe;
    @Override
    public void onEnable() {
        console = Bukkit.getServer().getConsoleSender();
        createConfig();
        console.sendMessage("VarietyMC CORE Enabling");
        zobe = new Zobe(this);
    }
    private void createConfig() { //Simple way to create/copy a yml file from jar file.
        data = new File(getDataFolder() + File.separator + "data.yml");
        if (!data.exists()) {
            console.sendMessage(org.bukkit.ChatColor.LIGHT_PURPLE + "[VarietyMC] Creating file data.yml");
            this.saveResource("data.yml", false);
        }
        playerData = new YamlConfiguration();
        try {
            playerData.load(data);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onDisable() { //Disconnects the bot when the plugin reloads or server is turned off.
        zobe.jda.shutdownNow();
        console.sendMessage("VarietyMC shutting down");
    }
}