package funstuff.funstuff.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CreatedItem {
    private ItemStack item;

    public CreatedItem(Material type, int amount, String name, String... lore) {
        ItemStack stack = new ItemStack(type, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> newlore = new ArrayList<String>();
        for(String string : lore) {
            newlore.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        meta.setLore(newlore);

        stack.setItemMeta(meta);
        this.item = stack;
    }
    public CreatedItem(Material type, int amount, String name, List<String> lore) {
        ItemStack stack = new ItemStack(type, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> itemlore = new ArrayList<String>();
        itemlore.add(" ");
        for(String s : lore) {
            itemlore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(itemlore);
        stack.setItemMeta(meta);
        this.item = stack;
    }

    public CreatedItem(Material type, int amount, String name) {
        ItemStack stack = new ItemStack(type, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        stack.setItemMeta(meta);
        this.item = stack;
    }

    public ItemStack getItemStack() {
        return this.item;
    }
}
