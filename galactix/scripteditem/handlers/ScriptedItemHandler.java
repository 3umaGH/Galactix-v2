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
import bukkit.galactix.utils.Util;

public class ScriptedItemHandler {
	private static HashMap<String, Item> itemList = new HashMap<String, Item>();
	private static HashMap<Integer, Item> itemIDs = new HashMap<Integer, Item>();

	private static EnchantmentBook enchantedBookBase;

	public static void initItems() {
		enchantedBookBase = new EnchantmentBook(1000);

		loadItem(new AdminTool(1337));
		loadItem(enchantedBookBase);

		loadItem(new Item(0, "Test", "test", 0, false, Material.WOODEN_SHOVEL, (short) 0).addVanillaEnchantment(Enchantment.DURABILITY, 3));
		loadItem(new Item(1, "Test", "test", 1, true, Material.STONE_SHOVEL, (short) 0));
		loadItem(new Item(2, "Test", "test", 2, true, Material.IRON_SHOVEL, (short) 0));
		loadItem(new Item(3, "Test", "test", 3, true, Material.GOLDEN_SHOVEL, (short) 0));
		loadItem(new Item(4, "Test", "test", 4, true, Material.DIAMOND_SHOVEL, (short) 0));
		loadItem(new Item(5, "Test", "test", 5, true, Material.DIAMOND, (short) 0));
		loadItem(new Item(6, "Test", "test", 6, true, Material.DIAMOND_BLOCK, (short) 0));

		Logger.sendLog(Log.INFO, "initalized " + itemList.size() + " items.");
	}

	private static void loadItem(Item item) {
		itemList.put(item.getItemStack().getItemMeta().getDisplayName(), item);
		itemIDs.put(item.getId(), item);
	}

	public static Item getItemByStack(ItemStack i) {
		Item item = itemList.get(i.getItemMeta().getDisplayName());

		if (item != null && item.getItemStack().getType().equals(i.getType()))
			return item;
		else
			return null;
	}

	public static Item getItemById(int id) {
		return itemIDs.get(id);
	}

	public static ScriptedItem getScriptedItem(ItemStack i) {
		Item item = getItemByStack(i);

		if (item != null && item.isScriptedItem())
			return (ScriptedItem) item;
		else
			return null;

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

	public static HashMap<String, Item> getItemList() {
		return itemList;
	}

	public static HashMap<Integer, Item> getItemIDs() {
		return itemIDs;
	}

}
