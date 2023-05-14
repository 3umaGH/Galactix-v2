package bukkit.galactix.supplycrate;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;

import bukkit.galactix.logger.Logger;
import bukkit.galactix.logger.Logger.Log;

public class SupplyCrateHandler {
	private static List<Location> randomDropLocations = new ArrayList<Location>();
	private static List<Location> dropLocations = new ArrayList<Location>();

	public static void initDropLocations() {
		dropLocations.add(new Location(Bukkit.getWorld("world"), 193.0, 121.0, -264.0));

	}

	public static void dropCrate(Location dropLocation) {
		Location spawnLoc = new Location(dropLocation.getWorld(), dropLocation.getX(), dropLocation.getY() + 30.0,
				dropLocation.getZ());
		dropLocation.getWorld().spawnFallingBlock(spawnLoc, Material.OAK_WOOD, (byte) 0x0);

		dropLocations.add(dropLocation.getWorld().getHighestBlockAt(dropLocation).getLocation());

	}

	public static void removeChests() {
		int removed = 0;
		for (int i = 0; i < dropLocations.size(); i++) {
			Block block = dropLocations.get(i).getBlock();

			if (block.getType() != Material.AIR) {
				dropLocations.get(i).getBlock().setType(Material.AIR);
				removed++;
			}

		}
		Logger.sendLog(Log.INFO, "Removed " + removed + " supply crates.");

	}

	public static void turnBlockIntoChest(Block block, boolean legendary) {
		block.getLocation().getWorld().strikeLightning(block.getLocation());

		if (legendary)
			block.setType(Material.ENDER_CHEST);
		else
			block.setType(Material.CHEST);

		block.getLocation().getWorld().playSound(block.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1F, 1F);
		block.getLocation().getWorld().playEffect(block.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
	}

	public static boolean isSupplyCrate(Block block) {
		return dropLocations.contains(block.getLocation());
	}

	public static List<Location> getDropLocations() {
		return dropLocations;
	}

}
