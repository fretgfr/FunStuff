package funstuff.funstuff.listeners;

import funstuff.funstuff.FunStuff;
import funstuff.funstuff.runnables.ArrowLaunchRunnable;
import funstuff.funstuff.runnables.SnowballThrowRunnable;
import funstuff.funstuff.utils.BlockSafety;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;

public class ProjectileListener implements Listener {
    private final FunStuff main;

    public ProjectileListener(FunStuff main) {
        this.main = main;
    }

    public static HashMap<Integer, Integer> arrowIds = new HashMap<>();
    public static HashMap<Integer, Player> arrowShooters = new HashMap<>();
    public static HashMap<Integer, Integer> snowballids = new HashMap<>();


    @EventHandler
    public void onArrowShoot(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if(player.getItemInUse().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aTeleporting Bow"))){
                Arrow arrow = (Arrow) e.getProjectile();
                ArrowLaunchRunnable runnable = new ArrowLaunchRunnable(arrow, main);
                arrowIds.put(arrow.getEntityId(), runnable.getTaskId());
                arrowShooters.put(arrow.getEntityId(), player);
            }
        }
    }

    @EventHandler
    public void onSnowballLaunch(ProjectileLaunchEvent e) {
        if(e.getEntity() instanceof Snowball) {
            Snowball snowball = (Snowball) e.getEntity();
            SnowballThrowRunnable runnable = new SnowballThrowRunnable(snowball, main);
            snowballids.put(snowball.getEntityId(), runnable.getTaskId());
        }
    }

    @EventHandler
    public void onProjectileLand(ProjectileHitEvent e) {
        if(e.getEntity() instanceof Snowball) {
            if(snowballids.containsKey(e.getEntity().getEntityId())) {
                Bukkit.getScheduler().cancelTask(snowballids.get(e.getEntity().getEntityId()));
                try {
                    e.getEntity().getWorld().createExplosion(e.getHitBlock().getLocation(), 0.0f);
                    List<Entity> landedby = (List<Entity>) e.getHitBlock().getWorld().getNearbyEntities(e.getHitBlock().getLocation(), 1.5, 1.5, 1.5);
                    for(Entity entity : landedby) {
                        if(entity instanceof Player) {
                            if(!entity.hasMetadata("NPC")) {
                                e.getEntity().getVelocity();
                                entity.setVelocity(Vector.getRandom());
                            }
                        }
                    }
                    e.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, e.getHitBlock().getLocation(), 31);
                } catch(Exception exception) {
                    e.getEntity().getWorld().createExplosion(e.getHitEntity().getLocation(), 0.0f);
                    if(e.getHitEntity() instanceof Player) {
                        Player player = (Player) e.getHitEntity();
                        if(!player.hasMetadata("NPC")) {
                            player.setVelocity(e.getEntity().getVelocity());
                        }
                    }
                }
            }
        }
        if (e.getEntity() instanceof Arrow) {
            if (arrowIds.containsKey(e.getEntity().getEntityId())) {
                Bukkit.getScheduler().cancelTask(arrowIds.get(e.getEntity().getEntityId()));
            }
            if (arrowShooters.containsKey(e.getEntity().getEntityId())) {
                Player player = arrowShooters.get(e.getEntity().getEntityId());
                try {
                    Block block = e.getHitBlock();
                    if (BlockSafety.isBlockSafe(block)) {
                        Location location = block.getLocation().add(0, 1, 0);
                        location.setDirection(player.getLocation().getDirection());
                        player.teleport(location);
                        player.setLastDamage(0);
                    } else {
                        player.sendMessage(ChatColor.RED + "Unsafe location.");
                    }
                } catch (NullPointerException exception) {
                    player.teleport(e.getHitEntity());
                    player.setLastDamage(0);
                }
            }
        }
    }

}
