package bukkit.galactix.scripteditem;

import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import bukkit.galactix.player.GalactixPlayer;

public abstract class ScriptedItem extends Item {

	public ScriptedItem(int id, String name, String description, int tier, boolean showTier, Material item,
			short data) {
		super(id, name, description, tier, showTier, item, data);
		this.setScriptedItem(true);
	}
	
	public abstract void onLeftClickAir(GalactixPlayer p, PlayerInteractEvent e);
	public abstract void onRightClickAir(GalactixPlayer p, PlayerInteractEvent e);
	public abstract void onLeftClickBlock(GalactixPlayer p, PlayerInteractEvent e);
	public abstract void onRightClickBlock(GalactixPlayer p, PlayerInteractEvent e);
	public abstract void onBlockPlace(GalactixPlayer p, BlockPlaceEvent e);

	
}
