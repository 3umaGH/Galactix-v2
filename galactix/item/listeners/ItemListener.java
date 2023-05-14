package bukkit.galactix.item.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import bukkit.galactix.item.Item;
import bukkit.galactix.item.ItemAction;
import bukkit.galactix.item.handlers.ItemHandler;
import bukkit.galactix.player.GalactixPlayer;
import bukkit.galactix.player.PlayerHandler;

public class ItemListener implements Listener {

	@EventHandler
	private void onPlayerInteractEvent(PlayerInteractEvent e) {
		
		if(e.getItem() == null) return;
		
		Item itemInHand = Item.getItemByStack(e.getItem());
		
		if(itemInHand == null)
			return;
		
		ItemAction itemAction = itemInHand.getAction();
		
		if(itemAction == null)
			return;
		
		GalactixPlayer p = PlayerHandler.getGalactixPlayer(e.getPlayer());
		
		if(itemInHand !=null) {
			switch(e.getAction()) {
			case LEFT_CLICK_AIR:
				itemAction.onLeftClickAir(p, e);
				break;
			case LEFT_CLICK_BLOCK:
				itemAction.onLeftClickBlock(p, e);
				break;
			case RIGHT_CLICK_AIR:
				itemAction.onRightClickAir(p, e);
				break;
			case RIGHT_CLICK_BLOCK:
				itemAction.onRightClickBlock(p, e);
				break;
			default:
				break;
			
			}
			
			
		}
			
	}
	
	@EventHandler
	private void onBlockPlace(BlockPlaceEvent e) {
		if(e.getPlayer().getInventory().getItemInMainHand() == null) return;
		
		Item itemInHand = Item.getItemByStack(e.getPlayer().getInventory().getItemInMainHand());
		
		if(itemInHand == null)
			return;
		
		ItemAction itemAction = itemInHand.getAction();
		
		if(itemAction == null)
			return;
		
		GalactixPlayer p = PlayerHandler.getGalactixPlayer(e.getPlayer());
		
		if(itemInHand == null)
			return;
		
		itemAction.onBlockPlace(p, e);
	}
	
}
