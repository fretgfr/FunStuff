package funstuff.funstuff.runnables;

import funstuff.funstuff.FunStuff;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Projectile;

public class ArrowLaunchRunnable {
    private int taskId;

    public ArrowLaunchRunnable(Projectile arrow, FunStuff main) {
        this.taskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            int counter = 0;
            @Override
            public void run() {
                arrow.getWorld().spawnParticle(Particle.SPELL, arrow.getLocation(), 10);
                if(counter % 5 == 0) {
                    arrow.getWorld().playSound(arrow.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 100, 10);
                }
                counter++;

                if(counter == 200) {
                    cancelThisTask();
                }
            }
        }, 0, 1);
    }

    public int getTaskId() {
        return this.taskId;
    }

    public void cancelThisTask() {
        Bukkit.getServer().getScheduler().cancelTask(this.taskId);
    }
}
