package bukkit.galactix.menu.list;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import bukkit.galactix.item.Item;
import bukkit.galactix.item.handlers.ItemHandler;
import bukkit.galactix.menu.Menu;
import bukkit.galactix.player.GalactixPlayer;

public class ItemListMenu extends Menu {
	private int page = 0;
	private HashMap<Integer, Item> itemList = new HashMap<Integer, Item>();

	public ItemListMenu() {
		super("Item List Page: 1", 6);

		int itemSlot = 0;
		for (int id = 0; id <= 10000; id++) {
			Item item = Item.getItemById(id);
			if (item != null) {
				itemList.put(itemSlot, item);
				itemSlot++;
			}
		}

	}

	@Override
	public void loadIcons(GalactixPlayer p) {

		this.getInventory().setItem(52, ItemHandler.createItem(Material.RED_STAINED_GLASS_PANE, 1, (short) 0,
				(page <= 0) ? "&4This is the last page." : "&cBack to page " + page, "&7Go to previous page."));
		this.getInventory().setItem(53, ItemHandler.createItem(Material.GREEN_STAINED_GLASS_PANE, 1, (short) 0,
				"&cNext to page " + (page + 1), "&7Go to next page."));

		for (int slot = 0; slot <= 51; slot++) {
			Item nextItem = itemList.get(slot + page * 52);

			if (nextItem == null)
				break;

			this.getInventory().setItem(slot, nextItem.getItemStack());

		}

	}

	@Override
	public void onInventoryClick(GalactixPlayer p, InventoryClickEvent e) {
		switch (e.getSlot()) {
		case 53:
			page++;
			e.setCancelled(true);
			break;
		case 52:
			if (!(page <= 0))
				page--;

			e.setCancelled(true);
			break;
		}

		this.getInventory().clear();
		loadIcons(p);

	}

}
