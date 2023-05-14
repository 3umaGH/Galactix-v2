package bukkit.galactix.item.list;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bukkit.galactix.customenchant.CustomEnchant;
import bukkit.galactix.customenchant.CustomEnchantments;
import bukkit.galactix.customenchant.handlers.CustomEnchantHandler;
import bukkit.galactix.item.Item;
import bukkit.galactix.player.GalactixPlayer;

public class EnchantBook {

	public EnchantBook() {}

	public static ItemStack getItemStack(CustomEnchant ench, int level, int successRate) {
		ItemStack enchantedBook = new ItemStack(Item.ENCHANTED_BOOK_BASE.getItemStack());

		ItemMeta curMeta = enchantedBook.getItemMeta();
		List<String> loreList = curMeta.getLore();

		loreList.remove(0);

		loreList.add("§a" + successRate + "% Success Rate");

		String[] text = ench.getDescription().split("/n");

		for (String ss : text)
			loreList.add("§e" + ss);

		if (ench.getConflictingEnchantIDs().size() >= 1) {
			String conflictingEnchantNames = "";

			for (int enchID : ench.getConflictingEnchantIDs())
				conflictingEnchantNames = CustomEnchantments.getEnchantById(enchID).getName() + " "
						+ conflictingEnchantNames;

			loreList.add(" ");
			loreList.add("§cConflicts with: " + conflictingEnchantNames);
		}

		loreList.add(" ");
		loreList.add("§7" + ench.getType().getType() + " Enchantment.");
		loreList.add("§7Drag n' Drop on item to enchant");

		curMeta.setLore(loreList);
		enchantedBook.setItemMeta(curMeta);
		
		CustomEnchantHandler.enchantItemStack(enchantedBook, ench, level);

		return enchantedBook;
	}

	public static int getSuccessRate(ItemStack i) {
		int successRate = 0;

		for (String s : i.getItemMeta().getLore()) {
			if (ChatColor.stripColor(s).contains("% Success Rate")) {
				String[] tempData = ChatColor.stripColor(s).split("%");
				successRate = Integer.valueOf(tempData[0]);
			}
		}

		return successRate;
	}

	public static void setSuccessRate(ItemStack i, int amount) {
		int currentLine = 0;
		ItemMeta curMeta = i.getItemMeta();
		List<String> loreList = curMeta.getLore();

		for (String s : curMeta.getLore()) {
			if (ChatColor.stripColor(s).contains("% Success Rate")) {
				loreList.set(currentLine, "§a" + amount + "% Success Rate");

				curMeta.setLore(loreList);
				i.setItemMeta(curMeta);
			}
			currentLine += 1;
		}
	}

}
