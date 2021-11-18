package funstuff.funstuff.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.List;

public class BlockSafety {

    public static List<Material> safeMaterials = Arrays.asList(Material.AIR, Material.CAVE_AIR, Material.GRASS, Material.TALL_GRASS, Material.WATER, Material.TORCH, Material.REDSTONE_TORCH, Material.SUNFLOWER, Material.REDSTONE_WALL_TORCH, Material.REDSTONE);

    public static boolean isBlockSafe(Block block) {
        return safeMaterials.contains(block.getRelative(0, 1, 0).getType()) && safeMaterials.contains(block.getRelative(0, 2, 0).getType());
    }
}
