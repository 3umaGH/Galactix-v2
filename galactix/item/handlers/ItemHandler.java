package bukkit.galactix.item.handlers;

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

import bukkit.galactix.customenchant.CustomEnchantments;
import bukkit.galactix.customenchant.handlers.CustomEnchantHandler;
import bukkit.galactix.item.Item;
import bukkit.galactix.item.ItemAction;
import bukkit.galactix.item.actions.list.AdminToolAction;
import bukkit.galactix.item.actions.list.RewardChestAction;
import bukkit.galactix.item.list.EnchantBook;
import bukkit.galactix.logger.Logger;
import bukkit.galactix.logger.Logger.Log;
import bukkit.galactix.utils.Util;

public class ItemHandler {

	/*
	 * IMPORTANT IDS: admin tool - 1337 stained glasses for menus- 1340-1350
	 */

	public static void initItems() {
		Logger.sendLog(Log.INFO, "initalized " + Item.getItemIDs().size() + " items.");
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
