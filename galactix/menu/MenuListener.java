package bukkit.galactix.menu;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import bukkit.galactix.player.GalactixPlayer;
import bukkit.galactix.player.PlayerHandler;

public class MenuListener implements Listener {

	@EventHandler
	public static void onInventoryClick(InventoryClickEvent e) {
		GalactixPlayer p = PlayerHandler.getGalactixPlayer((Player) e.getWhoClicked());
		Menu menu = Menu.getList().get(e.getInventory());

		if (menu == null)
			return;

		menu.onInventoryClick(p, e);
		p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.BLOCK_LEVER_CLICK, 0.98F, 1F);

	}

	@EventHandler
	public static void onInventoryClose(InventoryCloseEvent e) {
		Menu menu = Menu.getList().get(e.getInventory());

		if (menu == null)
			return;

		Menu.getList().remove(menu);
	}
	
	public static void closeAllInventories() {
		for(Player p : Bukkit.getOnlinePlayers())
			p.closeInventory();
	}

}
