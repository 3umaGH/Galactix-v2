package bukkit.galactix.customenchant.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import bukkit.galactix.chat.ChatHandler;
import bukkit.galactix.customenchant.CustomEnchant;
import bukkit.galactix.customenchant.CustomEnchantments;
import bukkit.galactix.customenchant.list.DebugEnchantment;
import bukkit.galactix.customenchant.list.Glow;
import bukkit.galactix.item.Item;
import bukkit.galactix.logger.Logger;
import bukkit.galactix.logger.Logger.Log;
import bukkit.galactix.main.Main;
import bukkit.galactix.player.PlayerHandler;
import bukkit.galactix.utils.Util;

public class CustomEnchantHandler {
	private static Enchantment glow;
	
	public static enum EnchantmentType {
		HELMET("Helmet"), BODY("Chestplate"), LEGGINGS("Leggings"), BOOTS("Boots"), SWORD("Sword"), PICKAXE("Pickaxe"),
		HOE("Hoe"), SPADE("Shovel"), BOW("Bow");

		private String type;

		EnchantmentType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public static boolean isRightType(EnchantmentType type, Material mat) {
			switch (type) {
			case HELMET:
				if (mat.toString().contains("_HELMET"))
					return true;
			case BODY:
				if (mat.toString().contains("_CHESTPLATE"))
					return true;
			case LEGGINGS:
				if (mat.toString().contains("_LEGGINGS"))
					return true;
			case BOOTS:
				if (mat.toString().contains("_BOOTS"))
					return true;
			case SWORD:
				if (mat.toString().contains("_SWORD"))
					return true;
			case PICKAXE:
				if (mat.toString().contains("_PICKAXE"))
					return true;
			case HOE:
				if (mat.toString().contains("_HOE"))
					return true;
			case SPADE:
				if (mat.toString().contains("_SHOVEL"))
					return true;
			case BOW:
				return mat.equals(Material.BOW.toString());
			default:
				return false;
			}

		}
	}

	public static void initEnchantments() {
		Glow.registerGlow();
		glow = Enchantment.getByKey(new NamespacedKey(Main.getInstance(), "Glow"));

		startEnchantmentCheckLoop();
		Logger.sendLog(Log.INFO, "initalized " + CustomEnchantments.getEnchantmentIDs().size() + " custom enchantments.");
	}

	private static void startEnchantmentCheckLoop() {
		new BukkitRunnable() {
			public void run() {

				for (Player p : Bukkit.getOnlinePlayers()) {
					ItemStack inHand = p.getInventory().getItemInMainHand();

					if (!(inHand == null || inHand.getType().equals(Material.AIR) || inHand.getType().equals(Material.BOOK))) {
						HashMap<CustomEnchant, Integer> enchantments = CustomEnchantHandler
								.getItemStackEnchantments(inHand);

						for (CustomEnchant e : enchantments.keySet())
							e.onHoldInHandLoop(PlayerHandler.getGalactixPlayer(p), inHand, enchantments.get(e));
					}

					for (ItemStack i : p.getInventory().getArmorContents()) {

						if (i == null || i.getType().equals(Material.AIR) || inHand.getType().equals(Material.BOOK))
							continue;

						HashMap<CustomEnchant, Integer> enchantments = CustomEnchantHandler.getItemStackEnchantments(i);

						for (CustomEnchant e : enchantments.keySet())
							e.onWearingLoop(PlayerHandler.getGalactixPlayer(p), i, enchantments.get(e));
					}
				}

			}
		}.runTaskTimer(Main.getInstance(), 100L, 100L);
	}

	public static void enchantItemStack(ItemStack i, CustomEnchant e, int level) {
		ItemMeta curMeta = i.getItemMeta();
		List<String> loreList = new ArrayList<String>();

		loreList.add(e.getEnchantmentColor().replace("&", "§") + e.getName() + " " + Util.arabicToRoman(level));

		if (curMeta.hasLore())
			loreList.addAll(curMeta.getLore());

		curMeta.setLore(loreList);
		i.setItemMeta(curMeta);
	}

	public static void removeEnchantment(ItemStack i, CustomEnchant e) {
		ItemMeta curMeta = i.getItemMeta();
		List<String> loreList = new ArrayList<String>();

		if (!curMeta.hasLore())
			return;

		loreList = curMeta.getLore();

		for (int ii = 0; ii <= loreList.size(); ii++)
			if (ChatColor.stripColor(loreList.get(ii)).startsWith(e.getName()))
				loreList.remove(ii);

		curMeta.setLore(loreList);
		i.setItemMeta(curMeta);
	}

	public static HashMap<CustomEnchant, Integer> getItemStackEnchantments(ItemStack i) {
		HashMap<CustomEnchant, Integer> itemEnchants = new HashMap<CustomEnchant, Integer>();

		if (!i.hasItemMeta() || !i.getItemMeta().hasLore())
			return itemEnchants;

		for (String s : i.getItemMeta().getLore()) {
			if (s.length() > 2) {
				String[] temp = ChatColor.stripColor(s).split(" ");

				if (temp.length <= 0)
					continue;

				CustomEnchant ench = CustomEnchantments.getEnchantByName(temp[0].toLowerCase());
				if (ench == null)
					continue;

				int level = Util.romanToArabic(temp[1]);

				itemEnchants.put(ench, level);
			}

		}

		return itemEnchants;
	}

	public static boolean hasEnchantment(ItemStack i, CustomEnchant e) {
		return getItemStackEnchantments(i).keySet().contains(e);
	}

	public static boolean isRightMaterialForEnchantment(ItemStack i, CustomEnchant e) {
		return EnchantmentType.isRightType(e.getType(), i.getType());
	}

	public static boolean hasConflictingEnchants(ItemStack i, CustomEnchant e) {
		for (CustomEnchant ie : getItemStackEnchantments(i).keySet())
			if (ie.getConflictingEnchantIDs().contains(e.getId()))
				return true;

		return false;
	}


	public static Enchantment getGlow() {
		return glow;
	}

}
