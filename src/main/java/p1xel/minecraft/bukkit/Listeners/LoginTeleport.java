package p1xel.minecraft.bukkit.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import p1xel.minecraft.bukkit.Storage.LLLGroupManager;

public class LoginTeleport implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        String group = LLLGroupManager.getPlayerGroup(p);

        if (group.equalsIgnoreCase("default")) {
            if (!LLLGroupManager.getDefualtGroupIgnoreList().contains(p.getUniqueId().toString())) {
                if (LLLGroupManager.isGroupLocationSet(group)) {
                    p.teleport(LLLGroupManager.getGroupLocation(group));
                }
            }
        } else {
            if (LLLGroupManager.isGroupLocationSet(group)) {
                p.teleport(LLLGroupManager.getGroupLocation(group));
            }
        }



    }

}
