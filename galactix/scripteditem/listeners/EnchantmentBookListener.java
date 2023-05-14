package bukkit.galactix.scripteditem.listeners;

import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import bukkit.galactix.customenchant.CustomEnchant;
import bukkit.galactix.customenchant.CustomEnchantHandler;
import bukkit.galactix.scripteditem.handlers.ItemHandler;
import bukkit.galactix.utils.Util;

public class EnchantmentBookListener implements Listener {

	@EventHandler
	public void onEnchantmentBookUse(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack clickedItem = e.getCurrentItem();
		ItemStack holdingItem = e.getCursor();

		if (clickedItem == null || clickedItem.getType().equals(Material.AIR)
				|| clickedItem.getType().equals(Material.BOOK))
			return;

		if (holdingItem == null || holdingItem.getType().equals(Material.AIR))
			return;

		if (!ItemHandler.getItemByStack(holdingItem).equals(ItemHandler.getEnchantedBookBaseItem()))
			return;

		if (!(clickedItem.getAmount() == 1))
			return;

		for (Entry<CustomEnchant, Integer> entry : CustomEnchantHandler.getItemStackEnchantments(holdingItem)
				.entrySet()) {
			Set<CustomEnchant> existingEnchantments = CustomEnchantHandler.getItemStackEnchantments(clickedItem)
					.keySet();

			e.setCancelled(true);

			if (existingEnchantments.contains(entry.getKey())) {
				p.sendMessage("§cThis item already has this enchantment!");
				p.getWorld().playSound(p.getLocation(), Sound.BLOCK_METAL_BREAK, 1, 1);
				return;
			}

			for (CustomEnchant existingEnchant : existingEnchantments) {
				for (int conflictingIDs : existingEnchant.getConflictingEnchantIDs()) {
					if (entry.getKey().getConflictingEnchantIDs().contains(conflictingIDs)) {
						p.sendMessage("§cThis enchantment conflicts with " + existingEnchant.getName() + "!");
						p.getWorld().playSound(p.getLocation(), Sound.BLOCK_METAL_BREAK, 1, 1);
						return;
					}
				}
			}

			if (!CustomEnchantHandler.EnchantmentType.isRightType(entry.getKey().getType(), clickedItem.getType())) {
				p.sendMessage("§cThis enchantment is applicable only to "
						+ entry.getKey().getType().getType().replace("Leggings", "Legging") + "s!");
				p.getWorld().playSound(p.getLocation(), Sound.BLOCK_METAL_BREAK, 1, 1);
				return;
			}

			if (Util.generateRandom(100) < ItemHandler.getEnchantedBookBaseItem().getSuccessRate(holdingItem)) {
				CustomEnchantHandler.enchantItemStack(clickedItem, entry.getKey(), entry.getValue());

				p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
				p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);

				p.sendMessage("§aYou have successfully enchanted an item with " + entry.getKey().getName() + ".");
			} else {
				p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
				p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1, 1);

				p.sendMessage("§cUh oh! The book has crumbled into the dust.");
			}

			holdingItem.setAmount(holdingItem.getAmount() - 1);

			p.updateInventory();
		}

	}

}
