package bukkit.galactix.item.actions.list;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import bukkit.galactix.item.ItemAction;
import bukkit.galactix.player.GalactixPlayer;
import bukkit.galactix.supplycrate.SupplyCrateHandler;

public class AdminToolAction extends ItemAction {

	public AdminToolAction() {}

	@Override
	public void onLeftClickAir(GalactixPlayer p, PlayerInteractEvent e) {
		checkPlayerPermissions(p);
		// TODO Auto-generated method stub
		p.getPlayer().sendMessage("LEFT CLICK ADMIN TOOL LEEE");
	}

	@Override
	public void onRightClickAir(GalactixPlayer p, PlayerInteractEvent e) {
		checkPlayerPermissions(p);
		


	}

	@Override
	public void onLeftClickBlock(GalactixPlayer p, PlayerInteractEvent e) {
		checkPlayerPermissions(p);
		// TODO Auto-generated method stub
		p.getPlayer().sendMessage("LEFT CLICK ADMIN TOOL LEEE");
	}

	@Override
	public void onRightClickBlock(GalactixPlayer p, PlayerInteractEvent e) {
		checkPlayerPermissions(p);
		// TODO Auto-generated method stub
		SupplyCrateHandler.dropCrate(e.getClickedBlock().getLocation().add(0,1,0));
	}
	
	
	private void checkPlayerPermissions(GalactixPlayer p) {
		if(!p.getPlayer().hasPermission("galactix.admin"))
			p.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.POTATO));
			
		
	}

	@Override
	public void onBlockPlace(GalactixPlayer p, BlockPlaceEvent e) {
		e.setCancelled(true);
	}


}
