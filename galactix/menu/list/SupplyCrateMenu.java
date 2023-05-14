package bukkit.galactix.menu.list;

import org.bukkit.event.inventory.InventoryClickEvent;

import bukkit.galactix.droptable.DropTables;
import bukkit.galactix.menu.Menu;
import bukkit.galactix.player.GalactixPlayer;

public class SupplyCrateMenu extends Menu {
	private boolean legendary;
	
	public SupplyCrateMenu(boolean legendary) {
		super(legendary ? "Legendary Supply Crate" : "Supply Crate", 1);
		this.legendary = legendary;
	}

	@Override
	public void loadIcons(GalactixPlayer p) {
		int slot = 2;
		
		while(slot <=6) {
			
			this.getInventory().setItem(slot, DropTables.NORMAL_SUPPLY_DROP.getTable().rollRewards());
			
			if(this.legendary)
				this.getInventory().setItem(slot, DropTables.LEGENDARY_SUPPLY_DROP.getTable().rollRewards());
			
			slot++;
			
		}
		
		
	}

	@Override
	public void onInventoryClick(GalactixPlayer p, InventoryClickEvent e) {
		// TODO Auto-generated method stub
		
	}

}
