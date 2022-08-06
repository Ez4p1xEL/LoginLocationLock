package p1xel.minecraft.bukkit.Storage;

import p1xel.minecraft.bukkit.LoginLocationLock;

public class Config {

    public static String getString(String path) {
        return LoginLocationLock.getInstance().getConfig().getString(path);
    }

    public static boolean getBool(String path) {
        return LoginLocationLock.getInstance().getConfig().getBoolean(path);
    }

    public static String getLanguage() {
        return LoginLocationLock.getInstance().getConfig().getString("Language");
    }

    public static void reloadConfig() {

        LoginLocationLock.getInstance().reloadConfig();

    }

    public static String getVersion() {
        return getString("Version");
    }

    public static int getInt(String path) {
        return LoginLocationLock.getInstance().getConfig().getInt(path);
    }

}
