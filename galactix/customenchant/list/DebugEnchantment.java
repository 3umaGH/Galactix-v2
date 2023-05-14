package bukkit.galactix.customenchant.list;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import bukkit.galactix.customenchant.CustomEnchant;
import bukkit.galactix.customenchant.handlers.CustomEnchantHandler.EnchantmentType;
import bukkit.galactix.player.GalactixPlayer;

public class DebugEnchantment extends CustomEnchant {

	public DebugEnchantment() {
		super("DebugEnch");
	}


	@Override
	public int getMaxLvl() {
		return 1;
	}

	@Override
	public int getMinLvl() {
		return 1;
	}

	@Override
	public EnchantmentType getType() {
		return EnchantmentType.SPADE;
	}
	
	@Override
	public String getDescription() {
		return "This is a debug enchantment used for /ndebugging and testing shitz";
	}
	
	@Override
	public String getEnchantmentColor() {
		return "&6";
	}

	@Override
	public void onHoldInHandLoop(GalactixPlayer p, ItemStack i, int enchLevel) {
		p.getPlayer().sendMessage("ur holding test ench item!"); //done
		
	}

	@Override
	public void onWearingLoop(GalactixPlayer p, ItemStack i, int enchLevel) {
		p.getPlayer().sendMessage("yay, ur wearing shit that spams ur that crap"); //done
		
	}

	@Override
	public void onDeathWhileWearing(GalactixPlayer p, ItemStack i, PlayerDeathEvent e, int enchLevel) {
		p.getPlayer().sendMessage("you died while wearing testench"); //done
		
	}

	@Override
	public void onBowShoot(GalactixPlayer p, ItemStack i, EntityShootBowEvent e, int enchLevel) {
		p.getPlayer().sendMessage("you shot with test ench");
		
	}

	@Override
	public void onInteract(GalactixPlayer p, ItemStack i, PlayerInteractEvent e, int enchLevel) {
		p.getPlayer().sendMessage("you interacted with testench!");
		
	}

	@Override
	public void onBlockBreak(GalactixPlayer p, ItemStack i, BlockBreakEvent e, int enchLevel) {
		p.getPlayer().sendMessage("broke a block with testench"); //done
		
	}


	@Override
	public void onTakeDamageWhileWearing(GalactixPlayer p, ItemStack i, EntityDamageByEntityEvent e, int enchLevel) {
		p.getPlayer().sendMessage("Took damage");//done
		
	}


	@Override
	public void onDamageWith(GalactixPlayer p, ItemStack i, EntityDamageByEntityEvent e, int enchLevel) {
		p.getPlayer().sendMessage("Done damage"); //done
		
	}

}
