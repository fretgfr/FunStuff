package funstuff.funstuff.runnables;

import funstuff.funstuff.FunStuff;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Projectile;

public class SnowballThrowRunnable {
    private int taskId;

    public SnowballThrowRunnable(Projectile snowball, FunStuff main) {
        taskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            int counter = 0;
            @Override
            public void run() {
                snowball.getWorld().spawnParticle(Particle.CRIT, snowball.getLocation(), 10);
                if(counter % 5 == 0) {
                    snowball.getWorld().playSound(snowball.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 100, 10);
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
