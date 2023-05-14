package bukkit.galactix.scripteditem.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import bukkit.galactix.player.GalactixPlayer;
import bukkit.galactix.player.PlayerHandler;
import bukkit.galactix.scripteditem.ScriptedItem;
import bukkit.galactix.scripteditem.handlers.ScriptedItemHandler;

public class ScriptedItemListener implements Listener {

	@EventHandler
	private void onPlayerJoin(PlayerInteractEvent e) {
		
		if(e.getItem() == null) return;
		
		ScriptedItem itemInHand = ScriptedItemHandler.getScriptedItem(e.getItem());
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
	
}
