package bukkit.galactix.scripteditem.items;

import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import bukkit.galactix.droptable.DropTable;
import bukkit.galactix.droptable.DropTableHandler;
import bukkit.galactix.menu.list.RewardChestMenu;
import bukkit.galactix.player.GalactixPlayer;
import bukkit.galactix.scripteditem.ScriptedItem;
import bukkit.galactix.utils.Util;

public class RewardChest extends ScriptedItem {
	private Integer tableID;
	
	public RewardChest(int id, String name, int tier) {
		super(id, name, "&7Weird chest, feels warm to touch. /n&7I wonder what happens if i open it.", tier, true, Material.ENDER_CHEST, (short)0);
	}
	
	public ScriptedItem setDropTable(Integer i) {
		this.tableID = i;
		return this;
	}
	
	private void openRewardMenu(GalactixPlayer p) {
		p.removeOneFromHand();
		new RewardChestMenu(this.getTier(), tableID).openMenu(p);
	}

	@Override
	public void onLeftClickAir(GalactixPlayer p, PlayerInteractEvent e) {
		openRewardMenu(p);
	}

	@Override
	public void onRightClickAir(GalactixPlayer p, PlayerInteractEvent e) {
		openRewardMenu(p);
	}

	@Override
	public void onLeftClickBlock(GalactixPlayer p, PlayerInteractEvent e) {
		openRewardMenu(p);
	}

	@Override
	public void onRightClickBlock(GalactixPlayer p, PlayerInteractEvent e) {
		openRewardMenu(p);
	}
	
	@Override
	public void onBlockPlace(GalactixPlayer p, BlockPlaceEvent e) {
		e.setCancelled(true);
	}
	

}
