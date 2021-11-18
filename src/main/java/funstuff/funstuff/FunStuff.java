package funstuff.funstuff;

import funstuff.funstuff.listeners.ProjectileListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FunStuff extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ProjectileListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
