package p1xel.minecraft.bukkit;

import org.bukkit.plugin.java.JavaPlugin;
import p1xel.minecraft.bukkit.Command.Cmd;
import p1xel.minecraft.bukkit.Listeners.LoginTeleport;
import p1xel.minecraft.bukkit.Storage.LLLGroupManager;
import p1xel.minecraft.bukkit.bStats.Metrics;

public class LoginLocationLock extends JavaPlugin {

    private static LoginLocationLock instance;
    public static LoginLocationLock getInstance() {return instance;}

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        LLLGroupManager.createFile();
        getServer().getPluginManager().registerEvents(new LoginTeleport(), this);
        getServer().getPluginCommand("LoginLocationLock").setExecutor(new Cmd());

        int pluginId = 15126;
        new Metrics(this, pluginId);

        getLogger().info("Plugin loaded!");
    }

}
