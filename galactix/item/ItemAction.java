package bukkit.galactix.item;

import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import bukkit.galactix.player.GalactixPlayer;

public abstract class ItemAction {
	
	public ItemAction() {}


	public abstract void onLeftClickAir(GalactixPlayer p, PlayerInteractEvent e);

	public abstract void onRightClickAir(GalactixPlayer p, PlayerInteractEvent e);

	public abstract void onLeftClickBlock(GalactixPlayer p, PlayerInteractEvent e);

	public abstract void onRightClickBlock(GalactixPlayer p, PlayerInteractEvent e);

	public abstract void onBlockPlace(GalactixPlayer p, BlockPlaceEvent e);

}
