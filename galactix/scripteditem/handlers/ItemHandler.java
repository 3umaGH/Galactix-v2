package bukkit.galactix.scripteditem.handlers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import bukkit.galactix.logger.Logger;
import bukkit.galactix.logger.Logger.Log;
import bukkit.galactix.scripteditem.Item;
import bukkit.galactix.scripteditem.ScriptedItem;
import bukkit.galactix.scripteditem.items.AdminTool;
import bukkit.galactix.scripteditem.items.EnchantmentBook;
import bukkit.galactix.scripteditem.items.Items;
import bukkit.galactix.scripteditem.items.RewardChest;
import bukkit.galactix.scripteditem.items.Items.CustomItem;
import bukkit.galactix.utils.Util;

public class ItemHandler {
	private static EnchantmentBook enchantedBookBase;
	
	/*
	 * IMPORTANT IDS:
	 * admin tool - 1337
	 * stained glasses for menus- 1340-1350
	 */
	
	public static enum CustomItem {
		ADMIN_TOOL(new AdminTool(1));
		
		private static final HashMap<String, CustomItem> itemList = new HashMap<String, CustomItem>();
		private static final HashMap<Integer, CustomItem> itemIDs = new HashMap<Integer, CustomItem>();
		
		private ItemStack i;
		private Item item;
		private int ID;
		
		private CustomItem(int ID, String name, String description, int tier, boolean showTier, Material item, short data){
			this.ID = ID;
			this.item = new Item(ID, name, description, tier, showTier, item, data);
		}
		
		private CustomItem(Item item) {
			this.ID = item.getId();
			this.item = item;
		}
		
	    static {
	        for (CustomItem i : CustomItem.values()) {
	        	itemList.put(i.item.getItemStack().getItemMeta().getDisplayName(), i);
	        	itemIDs.put(i.ID, i);
	        	
	        }
	    }
	    
	    public Item getItem() {
	    	return this.item;
	    }
	    
		public static Item getItemByStack(ItemStack i) {
			Item item = itemList.get(i.getItemMeta().getDisplayName()).item;

			if (item != null && item.getItemStack().getType().equals(i.getType()))
				return item;
			else
				return null;
		}

		public static Item getItemById(int id) {
			return itemIDs.get(id).item;
		}

		public static ScriptedItem getScriptedItem(ItemStack i) {
			Item item = getItemByStack(i);

			if (item != null && item.isScriptedItem())
				return (ScriptedItem) item;
			else
				return null;

		}
		
		public static HashMap<String, CustomItem> getItemList() {
			return itemList;
		}

		public static HashMap<Integer, CustomItem> getItemIDs() {
			return itemIDs;
		}


	public static void initItems() {
		
		enchantedBookBase = new EnchantmentBook(1000);

		loadItem(new AdminTool(1337).setGlowing(true));
		loadItem(new RewardChest(7, "Some random chest", 6).setDropTable(0));
		loadItem(enchantedBookBase.setGlowing(true));

		loadItem(new Item(0, "Test", "test", 0, false, Material.WOODEN_SHOVEL, (short) 0).addVanillaEnchantment(Enchantment.DURABILITY, 3));
		loadItem(new Item(1, "Test", "test", 1, true, Material.STONE_SHOVEL, (short) 0));
		loadItem(new Item(2, "Test", "test", 2, true, Material.IRON_SHOVEL, (short) 0));
		loadItem(new Item(3, "Test", "test", 3, true, Material.GOLDEN_SHOVEL, (short) 0));
		loadItem(new Item(4, "Test", "test", 4, true, Material.DIAMOND_SHOVEL, (short) 0));
		loadItem(new Item(5, "Test", "test", 5, true, Material.DIAMOND, (short) 0));
		loadItem(new Item(6, "Test", "test", 6, true, Material.DIAMOND_BLOCK, (short) 0));

		loadItem(new Item(1340, "", "", 0, false, Material.RED_STAINED_GLASS_PANE, (short) 0).setGlowing(true));
		loadItem(new Item(1341, "", "", 0, false, Material.LIME_STAINED_GLASS_PANE, (short) 0).setGlowing(true));
		loadItem(new Item(1342, "", "", 0, false, Material.GREEN_STAINED_GLASS_PANE, (short) 0).setGlowing(true));
		loadItem(new Item(1343, "", "", 0, false, Material.PURPLE_STAINED_GLASS_PANE, (short) 0).setGlowing(true));
		loadItem(new Item(1344, "", "", 0, false, Material.PINK_STAINED_GLASS_PANE, (short) 0).setGlowing(true));
		loadItem(new Item(1345, "", "", 0, false, Material.BLUE_STAINED_GLASS_PANE, (short) 0).setGlowing(true));
		loadItem(new Item(1346, "", "", 0, false, Material.CYAN_STAINED_GLASS_PANE, (short) 0).setGlowing(true));
		loadItem(new Item(1347, "", "", 0, false, Material.ORANGE_STAINED_GLASS_PANE, (short) 0).setGlowing(true));
		loadItem(new Item(1348, "", "", 0, false, Material.YELLOW_STAINED_GLASS_PANE, (short) 0).setGlowing(true));
		loadItem(new Item(1349, "", "", 0, false, Material.WHITE_STAINED_GLASS_PANE, (short) 0).setGlowing(true));
		loadItem(new Item(1350, "", "", 0, false, Material.MAGENTA_STAINED_GLASS_PANE, (short) 0).setGlowing(true));

		
		
		Logger.sendLog(Log.INFO, "initalized " + itemList.size() + " items.");
	}

	private static void loadItem(Item item) {
		itemList.put(item.getItemStack().getItemMeta().getDisplayName(), item);
		itemIDs.put(item.getId(), item);
	}

	public static EnchantmentBook getEnchantedBookBaseItem() {
		return enchantedBookBase;
	}

	public static ItemStack createItem(Material material, int amount, short data, String name, String... lore) {
		ItemStack stack = new ItemStack(material, amount, data);
		ItemMeta meta = stack.getItemMeta();

		meta.setDisplayName(Util.colorize(name));
		ArrayList<String> loreList = new ArrayList<String>();

		for (String s : lore) {

			if (s.contains("/n")) {
				String[] text = s.split("/n");
				for (String ss : text) {
					loreList.add(Util.colorize(ss));
				}
			} else
				loreList.add(Util.colorize(s));

		}

		meta.setLore(loreList);
		stack.setItemMeta(meta);

		return stack;
	}

	public static String tierToText(int tier) {
		switch (tier) {
		case 1:
			return Util.tierToColor(tier) + "Common";
		case 2:
			return Util.tierToColor(tier) + "Uncommon";
		case 3:
			return Util.tierToColor(tier) + "Rare";
		case 4:
			return Util.tierToColor(tier) + "Legendary";
		case 5:
			return Util.tierToColor(tier) + "Exotic";
		case 6:
			return Util.tierToColor(tier) + "Unreal";
		default:
			return Util.tierToColor(tier) + "Common";
		}
	}

	public static void setHeadTexture(ItemStack head, String uuid, String texture) {
		SkullMeta meta = (SkullMeta) head.getItemMeta();

		GameProfile profile = new GameProfile(UUID.fromString(uuid), null);
		profile.getProperties().put("textures", new Property("textures", new String(texture)));

		Field profileField = null;

		try {
			profileField = meta.getClass().getDeclaredField("profile");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		profileField.setAccessible(true);
		try {
			profileField.set(meta, profile);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		head.setItemMeta(meta);

	}

}
