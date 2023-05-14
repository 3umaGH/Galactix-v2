package bukkit.galactix.item.actions.list;

import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import bukkit.galactix.droptable.DropTable;
import bukkit.galactix.droptable.DropTables;
import bukkit.galactix.item.ItemAction;
import bukkit.galactix.menu.list.RewardChestMenu;
import bukkit.galactix.player.GalactixPlayer;
import bukkit.galactix.utils.Util;

public class RewardChestAction extends ItemAction {
	
	public enum Tier{
		COMMON(1), UNCOMMON(2), RARE(3), LEGENDARY(4), EXOTIC(5), UNREAL(6);
		
		private int tier;
		Tier(int tier){
			this.tier = tier;
		}
	}
	
	private Tier tier;
	
	public RewardChestAction(Tier tier) {
		this.tier = tier;
	}
	
	private void openRewardMenu(GalactixPlayer p) {
		DropTables table = null;
		
		switch(this.tier) {
		case COMMON:
			table = DropTables.REWARD_CHEST_TIER1;
			break;
		case UNCOMMON:
			table = DropTables.REWARD_CHEST_TIER2;
			break;
		case RARE:
			table = DropTables.REWARD_CHEST_TIER3;
			break;
		case LEGENDARY:
			table = DropTables.REWARD_CHEST_TIER4;
			break;
		case EXOTIC:
			table = DropTables.REWARD_CHEST_TIER5;
			break;
		case UNREAL:
			table = DropTables.REWARD_CHEST_TIER6;
			break;
		}
		
		p.removeOneFromHand();
		new RewardChestMenu(this.tier.tier, table.getTable()).openMenu(p);
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
