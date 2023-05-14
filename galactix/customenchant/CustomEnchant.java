package bukkit.galactix.customenchant;

import java.util.ArrayList;

import java.util.List;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import bukkit.galactix.customenchant.handlers.CustomEnchantHandler.EnchantmentType;
import bukkit.galactix.player.GalactixPlayer;

public abstract class CustomEnchant {
	private String name;
	private int id;

	private static List<Integer> conflicts = new ArrayList<Integer>();

	public CustomEnchant(String name) {
		this.name = name;
	}
	
	public void setID(int id) {
		this.id = id;
	}

	public abstract int getMinLvl();

	public abstract int getMaxLvl();

	public abstract EnchantmentType getType();

	public abstract String getDescription();

	public abstract String getEnchantmentColor();

	public abstract void onHoldInHandLoop(GalactixPlayer p, ItemStack i, int enchLevel);

	public abstract void onWearingLoop(GalactixPlayer p, ItemStack i, int enchLevel);

	public abstract void onDeathWhileWearing(GalactixPlayer p, ItemStack i, PlayerDeathEvent e, int enchLevel); // done

	public abstract void onTakeDamageWhileWearing(GalactixPlayer p, ItemStack i, EntityDamageByEntityEvent e,
			int enchLevel); // done

	public abstract void onDamageWith(GalactixPlayer p, ItemStack i, EntityDamageByEntityEvent e, int enchLevel); // done

	public abstract void onBowShoot(GalactixPlayer p, ItemStack i, EntityShootBowEvent e, int enchLevel); // not yet

	public abstract void onInteract(GalactixPlayer p, ItemStack i, PlayerInteractEvent e, int enchLevel); // not yet

	public abstract void onBlockBreak(GalactixPlayer p, ItemStack i, BlockBreakEvent e, int enchLevel);

	public CustomEnchant addConflictingEnchantID(int id) {
		conflicts.add(id);
		return this;
	}

	public List<Integer> getConflictingEnchantIDs() {
		return conflicts;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

}
