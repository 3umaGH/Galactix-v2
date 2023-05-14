package bukkit.galactix.scripteditem;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import bukkit.galactix.customenchant.CustomEnchant;
import bukkit.galactix.customenchant.CustomEnchantHandler;
import bukkit.galactix.scripteditem.handlers.ItemHandler;
import bukkit.galactix.utils.Util;

public class Item {

	private String name, description;
	private int tier,id; // 0 Common, 1 Uncommon, 2 Rare, 3 Very Rare, 4 Legendary
	private ItemStack item;
	private boolean isScriptedItem;

	public Item(int id, String name, String description, int tier, boolean showTier, Material item, short data) {
		this.name = name;
		this.description = description;
		this.tier = tier;
		this.id = id;
		this.isScriptedItem = false;
		

		if(showTier) 
			this.item = ItemHandler.createItem(item, 1, data, Util.tierToColor(tier) + name,"&fTier: " + ItemHandler.tierToText(tier)," ", "&f" + description);
			else 
				this.item = ItemHandler.createItem(item, 1, data, Util.tierToColor(tier) + name, "&f" + description);
		
	}
	
	public Item setGlowing(boolean glowing) {
		if(glowing)
			this.addVanillaEnchantment(CustomEnchantHandler.getGlow(), 1);
		else
			this.item.removeEnchantment(CustomEnchantHandler.getGlow());
		return this;
	}
	
	public Item addCustomEnchantment(CustomEnchant e, int level) {
		CustomEnchantHandler.enchantItemStack(this.item, e, level);
		return this;
	}
	
	public Item addVanillaEnchantment(Enchantment e, int level) {
		this.item.addUnsafeEnchantment(e, level);
		return this;
	}
	
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getTier() {
		return tier;
	}

	public ItemStack getItemStack() {
		return item;
	}
	
	public ItemStack getNewItemStack(int amount) {
		ItemStack newItemStack = new ItemStack(item);
		newItemStack.setAmount(amount);
		
		return newItemStack;
	}

	public int getId() {
		return id;
	}


	public boolean isScriptedItem() {
		return isScriptedItem;
	}


	public void setScriptedItem(boolean scriptedItem) {
		this.isScriptedItem = scriptedItem;
	}
	
	
	
	
}
