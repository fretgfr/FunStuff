package funstuff.funstuff.commands;

import funstuff.funstuff.utils.CreatedItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GiveTeleBow implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("telebow")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                player.getInventory().addItem(new CreatedItem(Material.BOW, 1, "&aTeleporting Bow").getItemStack());
                player.sendMessage("Here you go.");
                return true;
            }
        }
        return false;
    }
}
