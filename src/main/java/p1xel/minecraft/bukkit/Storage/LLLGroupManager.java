package p1xel.minecraft.bukkit.Storage;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import p1xel.minecraft.bukkit.LoginLocationLock;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LLLGroupManager {

    public static void createFile() {

        File file = new File(LoginLocationLock.getInstance().getDataFolder(), "groups.yml");
        if (!file.exists()) {
            LoginLocationLock.getInstance().saveResource("groups.yml", false);
        }

    }

    public static FileConfiguration get() {
        File file = new File(LoginLocationLock.getInstance().getDataFolder(), "groups.yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void set(String path, Object value) {
        File file = new File(LoginLocationLock.getInstance().getDataFolder(), "groups.yml");
        FileConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        yaml.set(path,value);
        try {
            yaml.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPlayerGroup(Player p) {
        for (String perm : get().getKeys(false)) {
            if (!perm.equalsIgnoreCase("default")) {
                if (p.hasPermission("loginlocationlock.group." + perm)) {
                    return perm;
                }
            }
        }
        return "default";
    }

    public static boolean isGroupLocLock(String group) {
        return get().getBoolean(group + ".lock");
    }

    public static Location getGroupLocation(String group) {
        return (Location) get().get(group + ".location");
    }

    public static List<String> getDefualtGroupIgnoreList() {
        return get().getStringList("default.ignoreList");
    }

    public static boolean isGroupLocationSet(String group) {
        return  get().get(group + ".location") != null;
    }

    public static void setGroupLocLock(String group, boolean bool) {
        set(group + ".lock", bool);
    }

    public static void setGroupLocation(String group, Location loc) {
        set(group + ".location", loc);
    }

    public static void addUUIDToIgnoreList(String uuid) {
        List<String> list = getDefualtGroupIgnoreList();
        list.add(uuid);
        set("default.ignoreList", list);
    }

    public static void removeUUIDFromIgnoreList(String uuid) {
        List<String> list = getDefualtGroupIgnoreList();
        list.remove(uuid);
        set("default.ignoreList", list);
    }

}
