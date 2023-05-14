package bukkit.galactix.supplycrate;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import bukkit.galactix.main.Main;
import bukkit.galactix.menu.list.SupplyCrateMenu;
import bukkit.galactix.player.PlayerHandler;

public class SupplyCrateListener implements Listener {

	@EventHandler
	public void onEntityChangeBlock(EntityChangeBlockEvent e) {

		if (!(e.getEntity() instanceof FallingBlock))
			return;

		if (!SupplyCrateHandler.getDropLocations().contains(e.getBlock().getLocation()))
			return;

		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			public void run() {
				SupplyCrateHandler.turnBlockIntoChest(e.getBlock(), true);
			}

		}, 5);

	}

	@EventHandler
	public void onPlayerChestClick(PlayerInteractEvent e) {

		if (!(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
			return;

		Block block = e.getClickedBlock();
		
		if (!SupplyCrateHandler.isSupplyCrate(e.getClickedBlock()))
			return;

		boolean legendary = false;

		if (e.getClickedBlock().getType().equals(Material.ENDER_CHEST))
			legendary = true;

		block.setType(Material.AIR);
		block.getLocation().getWorld().playSound(block.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1F, 1F);
		block.getLocation().getWorld().playEffect(block.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
		
		new SupplyCrateMenu(legendary).openMenu(PlayerHandler.getGalactixPlayer(e.getPlayer()));

	}

}
