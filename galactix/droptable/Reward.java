package bukkit.galactix.droptable;

import org.bukkit.inventory.ItemStack;

import bukkit.galactix.item.Item;
import bukkit.galactix.item.handlers.ItemHandler;
import bukkit.galactix.logger.Logger;
import bukkit.galactix.logger.Logger.Log;
import bukkit.galactix.utils.Util;

public class Reward {
	private int id;
	private int minAmnt, maxAmnt, chance;
	
	public Reward(int id, int minAmnt, int maxAmnt, int chance) {
		this.id = id;
		this.minAmnt = minAmnt;
		this.maxAmnt = maxAmnt;
		this.chance = chance;
	}
	
	public ItemStack getItemStack() {
		return Item.getItemById(id).getNewItemStack(Util.generateRandomBetween(minAmnt, maxAmnt));
	}

	public int getId() {
		return this.id;
	}
	
	public int getMinAmount() {
		return minAmnt;
	}

	public int getMaxAmount() {
		return maxAmnt;
	}

	public int getChance() {
		return chance;
	}
	
	

}
