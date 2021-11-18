package funstuff.funstuff.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockSafety {

    public static boolean isBlockSafe(Block block) {
        return block.getRelative(0, 1, 0).getType().equals(Material.AIR) && block.getRelative(0, 2, 0).getType().equals(Material.AIR);
    }
}
