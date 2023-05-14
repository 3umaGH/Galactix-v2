package bukkit.galactix.customenchant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import bukkit.galactix.player.PlayerHandler;

public class CustomEnchantListener implements Listener {

	/*
	 * @EventHandler private void PlayerInventoryClickEvent(InventoryClickEvent e) {
	 * 
	 * if(!(e.getWhoClicked() instanceof Player)) return;
	 * 
	 * Player p = (Player)e.getWhoClicked();
	 * 
	 * 
	 * if(!(e.getSlotType() == SlotType.ARMOR)) return;
	 * 
	 * ItemStack i = e.getCurrentItem(); HashMap<CustomEnchant, Integer>
	 * enchantments = CustomEnchantHandler.getItemStackEnchantments(i);
	 * 
	 * for (CustomEnchant ench : enchantments.keySet())
	 * ench.onWear(PlayerHandler.getGalactixPlayer(p), i, enchantments.get(ench));
	 * 
	 * 
	 * }
	 */

	@EventHandler
	private void onPlayerDeathEvent(PlayerDeathEvent e) {
		List<ItemStack> itemsToCheck = new ArrayList<ItemStack>();

		itemsToCheck.addAll(Arrays.asList(e.getEntity().getInventory().getArmorContents()));
		itemsToCheck.add(e.getEntity().getInventory().getItemInMainHand());

		for (ItemStack i : itemsToCheck) {

			if (i == null || i.getType().equals(Material.AIR) || i.getType().equals(Material.BOOK))
				continue;

			for (Entry<CustomEnchant, Integer> entry : CustomEnchantHandler.getItemStackEnchantments(i).entrySet())
				entry.getKey().onDeathWhileWearing(PlayerHandler.getGalactixPlayer(e.getEntity()), i, e,
						entry.getValue());
		}

	}

	@EventHandler
	private void onDamageWith(EntityDamageByEntityEvent e) {

		if (!(e.getDamager() instanceof Player))
			return;

		Player p = (Player) e.getDamager();

		List<ItemStack> itemsToCheck = new ArrayList<ItemStack>();

		itemsToCheck.addAll(Arrays.asList(p.getInventory().getArmorContents()));
		itemsToCheck.add(p.getInventory().getItemInMainHand());

		for (ItemStack i : itemsToCheck) {

			if (i == null || i.getType().equals(Material.AIR) || i.getType().equals(Material.BOOK))
				continue;

			for (Entry<CustomEnchant, Integer> entry : CustomEnchantHandler.getItemStackEnchantments(i).entrySet())
				entry.getKey().onDamageWith(PlayerHandler.getGalactixPlayer(p), i, e, entry.getValue());
		}

	}

	@EventHandler
	private void onTakeDamage(EntityDamageByEntityEvent e) {

		if (!(e.getEntity() instanceof Player))
			return;

		Player p = (Player) e.getEntity();

		List<ItemStack> itemsToCheck = new ArrayList<ItemStack>();

		itemsToCheck.addAll(Arrays.asList(p.getInventory().getArmorContents()));
		itemsToCheck.add(p.getInventory().getItemInMainHand());

		for (ItemStack i : itemsToCheck) {

			if (i == null || i.getType().equals(Material.AIR) || i.getType().equals(Material.BOOK))
				continue;

			for (Entry<CustomEnchant, Integer> entry : CustomEnchantHandler.getItemStackEnchantments(i).entrySet())
				entry.getKey().onTakeDamageWhileWearing(PlayerHandler.getGalactixPlayer(p), i, e, entry.getValue());
		}

	}

	@EventHandler
	private void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();

		List<ItemStack> itemsToCheck = new ArrayList<ItemStack>();

		itemsToCheck.add(p.getInventory().getItemInMainHand());
		itemsToCheck.addAll(Arrays.asList(p.getInventory().getArmorContents()));

		for (ItemStack i : itemsToCheck) {

			if (i == null || i.getType().equals(Material.AIR) || i.getType().equals(Material.BOOK))
				continue;

			for (Entry<CustomEnchant, Integer> entry : CustomEnchantHandler.getItemStackEnchantments(i).entrySet())
				entry.getKey().onBlockBreak(PlayerHandler.getGalactixPlayer(p), i, e, entry.getValue());
		}
	}

}
