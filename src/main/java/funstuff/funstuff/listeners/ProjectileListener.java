package funstuff.funstuff.listeners;

import funstuff.funstuff.FunStuff;
import funstuff.funstuff.runnables.ArrowLaunchRunnable;
import funstuff.funstuff.utils.BlockSafety;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.HashMap;

public class ProjectileListener implements Listener {
    private FunStuff main;

    public ProjectileListener(FunStuff main) {
        this.main = main;
    }

    public static HashMap<Integer, Integer> arrowIds = new HashMap<>();
    public static HashMap<Integer, Player> arrowShooters = new HashMap<>();


    @EventHandler
    public void onArrowShoot(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            Arrow arrow = (Arrow) e.getProjectile();
            ArrowLaunchRunnable runnable = new ArrowLaunchRunnable(arrow, main);
            arrowIds.put(arrow.getEntityId(), runnable.getTaskId());
            arrowShooters.put(arrow.getEntityId(), player);
        }
    }

    @EventHandler
    public void onProjectileLand(ProjectileHitEvent e) {
        if(e.getEntity() instanceof Arrow) {
            if(arrowIds.containsKey(e.getEntity().getEntityId())) {
                Bukkit.getScheduler().cancelTask(arrowIds.get(e.getEntity().getEntityId()));
            }
            if(arrowShooters.containsKey(e.getEntity().getEntityId())) {
                Player player = arrowShooters.get(e.getEntity().getEntityId());
                try {
                    Block block = e.getHitBlock();
                    if(BlockSafety.isBlockSafe(block)) {
                        Location location = block.getLocation().add(0,1,0);
                        location.setDirection(player.getLocation().getDirection());
                        player.teleport(location);
                        player.setLastDamage(0);
                    } else {
                        player.sendMessage(ChatColor.RED + "Unsafe location.");
                    }
                } catch(NullPointerException exception) {
                    player.teleport(e.getHitEntity());
                    player.setLastDamage(0);
                }
            }
        }
    }

}
