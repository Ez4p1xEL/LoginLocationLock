package p1xel.minecraft.bukkit.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import p1xel.minecraft.bukkit.Storage.Config;
import p1xel.minecraft.bukkit.Storage.LLLGroupManager;

import javax.annotation.ParametersAreNonnullByDefault;

public class Cmd implements CommandExecutor {

    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("LoginLocationLock " + Config.getVersion());
            sender.sendMessage("/LoginLocationBack help View help.");
            return true;
        }

        if (args.length == 1) {

            if (args[0].equalsIgnoreCase("help")) {
                if (!sender.hasPermission("loginlocationlock.help")) {
                    sender.sendMessage("no perm!");
                    return true;
                }
                sender.sendMessage("/LoginLocationLock tp [group] Teleport to [group]'s login location.");
                sender.sendMessage("/LoginLocationLock set [group] Set [group]'s login location.");
                sender.sendMessage("/LoginLocationLock usetp Toggle default login location teleportation.");
                sender.sendMessage("/LoginLocationLock reload Reload the configuration.");
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("loginlocationback.reload")) {
                    sender.sendMessage("No perm!");
                    return true;
                }
                Config.reloadConfig();
                sender.sendMessage("Configuration reloaded successfully!");
                return true;
            }

            if (args[0].equalsIgnoreCase("usetp")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("You must be a player to do that!");
                    return true;
                }

                if (!sender.hasPermission("loginlocationlock.usetp")) {
                    sender.sendMessage("No perm");
                    return true;
                }

                Player p = (Player) sender;
                String uuid = p.getUniqueId().toString();

                if (LLLGroupManager.getDefualtGroupIgnoreList().contains(uuid)) {

                    LLLGroupManager.removeUUIDFromIgnoreList(uuid);
                    sender.sendMessage("You enabled login teleportation.");
                    return true;

                } else {
                    LLLGroupManager.addUUIDToIgnoreList(uuid);
                    sender.sendMessage("You disabled login teleportation.");
                    return true;
                }
            }

        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to do that!");
            return true;
        }

        Player p = (Player) sender;

        if (args.length <= 2) {

            if (args[0].equalsIgnoreCase("tp")) {

                if (!sender.hasPermission("loginlocationback.tp")) {
                    sender.sendMessage("No perm!");
                    return true;
                }

                if (args.length == 2) {

                    String group = args[1];

                    if (!LLLGroupManager.isGroupLocationSet(group)) {
                        sender.sendMessage("That group hasn't set the location yet.");
                        return true;
                    }

                    p.teleport(LLLGroupManager.getGroupLocation(group));
                    sender.sendMessage("You teleported to " + group + "'s login location!");
                    return true;

                }

                if (!LLLGroupManager.isGroupLocationSet("default")) {
                    sender.sendMessage("The default group hasn't set the location yet.");
                    return true;
                }

                p.teleport(LLLGroupManager.getGroupLocation("default"));
                sender.sendMessage("You teleported to default's login location");
                return true;

            }

            if (args[0].equalsIgnoreCase("set")) {

                if (!sender.hasPermission("loginlocationback.set")) {
                    sender.sendMessage("No perm!");
                    return true;
                }

                if (args.length == 2) {

                    String group = args[1];

                    LLLGroupManager.setGroupLocation(group, p.getLocation());
                    sender.sendMessage("Location set!");
                    return true;

                }

                LLLGroupManager.setGroupLocation("default", p.getLocation());
                sender.sendMessage("Location set!");
                return true;

            }
        }



        return false;

    }

}
