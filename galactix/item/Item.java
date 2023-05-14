package bukkit.galactix.item;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import bukkit.galactix.droptable.DropTables;
import bukkit.galactix.customenchant.CustomEnchant;
import bukkit.galactix.customenchant.CustomEnchantments;
import bukkit.galactix.customenchant.handlers.CustomEnchantHandler;
import bukkit.galactix.item.actions.list.AdminToolAction;
import bukkit.galactix.item.actions.list.RewardChestAction;
import bukkit.galactix.item.handlers.ItemHandler;
import bukkit.galactix.logger.Logger;
import bukkit.galactix.item.actions.*;
import bukkit.galactix.logger.Logger.Log;
import bukkit.galactix.utils.Util;

public enum Item {
	ADMIN_TOOL(1337, "Admin Tool", "", 6, false, Material.PAINTING, 0, true, new AdminToolAction()),

	ENCHANTED_BOOK_BASE(1000, "&eEnchantment Book", "", 0, false, Material.BOOK, 0, true, null),
	
	TEST_REWARD_CHEST(7, "Reward chest", "&7Weird chest, feels warm to touch. /n&7I wonder what happens if i open it.",
			1, true, Material.ENDER_CHEST, 0, false, new RewardChestAction(RewardChestAction.Tier.COMMON)),

	TEST_SHOVEL_0(0, "Test", "test", 0, false, Material.WOODEN_SHOVEL, 0, false, null),
	TEST_SHOVEL_1(1, "Test", "test", 1, false, Material.STONE_SHOVEL, 0, true, null),
	TEST_SHOVEL_2(2, "Test", "test", 2, false, Material.IRON_SHOVEL, 0, true, null),
	TEST_SHOVEL_3(3, "Test", "test", 3, false, Material.GOLDEN_SHOVEL, 0, true, null),
	TEST_SHOVEL_4(4, "Test", "test", 4, false, Material.DIAMOND_SHOVEL, 0, true, null),
	TEST_SHOVEL_5(5, "Test", "test", 5, false, Material.DIAMOND, 0, true, null),
	TEST_SHOVEL_6(6, "Test", "test", 6, false, Material.DIAMOND_BLOCK, 0, true, null),

	BEAUTY_PANE_RED(1340, "", "", 0, false, Material.RED_STAINED_GLASS_PANE, 0, true, null),
	BEAUTY_PANE_LIME(1341, "", "", 0, false, Material.LIME_STAINED_GLASS_PANE, 0, true, null),
	BEAUTY_PANE_GREEN(1342, "", "", 0, false, Material.GREEN_STAINED_GLASS_PANE, 0, true, null),
	BEAUTY_PANE_PURPLE(1343, "", "", 0, false, Material.PURPLE_STAINED_GLASS_PANE, 0, true, null),
	BEAUTY_PANE_PINK(1344, "", "", 0, false, Material.PINK_STAINED_GLASS_PANE, 0, true, null),
	BEAUTY_PANE_BLUE(1345, "", "", 0, false, Material.BLUE_STAINED_GLASS_PANE, 0, true, null),
	BEAUTY_PANE_CYAN(1346, "", "", 0, false, Material.CYAN_STAINED_GLASS_PANE, 0, true, null),
	BEAUTY_PANE_ORANGE(1347, "", "", 0, false, Material.ORANGE_STAINED_GLASS_PANE, 0, true, null),
	BEAUTY_PANE_YELLOW(1348, "", "", 0, false, Material.YELLOW_STAINED_GLASS_PANE, 0, true, null),
	BEAUTY_PANE_WHITE(1349, "", "", 0, false, Material.WHITE_STAINED_GLASS_PANE, 0, true, null),
	BEAUTY_PANE_MAGNETA(1350, "", "", 0, false, Material.MAGENTA_STAINED_GLASS_PANE, 0, false, null);

	private static final HashMap<String, Item> itemList = new HashMap<String, Item>();
	private static final HashMap<Integer, Item> itemIDs = new HashMap<Integer, Item>();

	private ItemStack item;
	private int ID, tier;
	private ItemAction action;
	private String name, description;
	
	private Item(int ID, String name, String description, int tier, boolean showTier, Material item, int data,
			boolean glowing, ItemAction actions) {

		this.tier = tier;
		this.ID = ID;
		this.action = actions;

		this.name = name;
		this.description = description;
		
		this.item = (showTier
				? ItemHandler.createItem(item, 1, (short) data, Util.tierToColor(tier) + name,
						"&fTier: " + ItemHandler.tierToText(tier), " ", "&f" + description)
				: ItemHandler.createItem(item, 1, (short) data, Util.tierToColor(tier) + name, "&f" + description));
		
		if (glowing)
			this.addVanillaEnchantment(CustomEnchantHandler.getGlow(), 1);
		
	}

	static {
		for (Item i : Item.values()) {
			itemList.put(i.item.getItemMeta().getDisplayName(), i);
			itemIDs.put(i.ID, i);
		}
	}

	public static Item getItemByStack(ItemStack i) {
		if (!itemList.containsKey(i.getItemMeta().getDisplayName()))
			return null;

		Item item = itemList.get(i.getItemMeta().getDisplayName());

		if (item != null && item.getItemStack().getType().equals(i.getType()))
			return item;
		else
			return null;
	}

	public static Item getItemById(int id) {
		if (!itemIDs.containsKey(id))
			return null;
		else
			return itemIDs.get(id);
	}

	public static HashMap<String, Item> getItemList() {
		return itemList;
	}

	public static HashMap<Integer, Item> getItemIDs() {
		return itemIDs;
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
		return ID;
	}

	public void setActions(ItemAction action) {
		this.action = action;
	}

	public ItemAction getAction() {
		return this.action;
	}

	public boolean hasActions() {
		return (this.action == null ? true : false);
	}

}
