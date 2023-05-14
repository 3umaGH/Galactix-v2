package bukkit.galactix.scripteditem.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import bukkit.galactix.player.GalactixPlayer;
import bukkit.galactix.player.PlayerHandler;
import bukkit.galactix.scripteditem.ScriptedItem;
import bukkit.galactix.scripteditem.handlers.ItemHandler;

public class ItemListener implements Listener {

	@EventHandler
	private void onPlayerInteractEvent(PlayerInteractEvent e) {
		
		if(e.getItem() == null) return;
		
		ScriptedItem itemInHand = ItemHandler.getScriptedItem(e.getItem());
		GalactixPlayer p = PlayerHandler.getGalactixPlayer(e.getPlayer());
		
		if(itemInHand !=null) {
			switch(e.getAction()) {
			case LEFT_CLICK_AIR:
				itemInHand.onLeftClickAir(p, e);
				break;
			case LEFT_CLICK_BLOCK:
				itemInHand.onLeftClickBlock(p, e);
				break;
			case RIGHT_CLICK_AIR:
				itemInHand.onRightClickAir(p, e);
				break;
			case RIGHT_CLICK_BLOCK:
				itemInHand.onRightClickBlock(p, e);
				break;
			default:
				break;
			
			}
			
			
		}
			
	}
	
	@EventHandler
	private void onBlockPlace(BlockPlaceEvent e) {
		if(e.getPlayer().getInventory().getItemInMainHand() == null) return;
		
		ScriptedItem itemInHand = ItemHandler.getScriptedItem(e.getPlayer().getInventory().getItemInMainHand());
		GalactixPlayer p = PlayerHandler.getGalactixPlayer(e.getPlayer());
		
		itemInHand.onBlockPlace(p, e);
	}
	
}
