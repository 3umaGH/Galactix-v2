package bukkit.galactix.menu.list;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import bukkit.galactix.droptable.DropTable;
import bukkit.galactix.droptable.DropTables;
import bukkit.galactix.item.handlers.ItemHandler;
import bukkit.galactix.main.Main;
import bukkit.galactix.menu.Menu;
import bukkit.galactix.player.GalactixPlayer;
import bukkit.galactix.utils.Util;

public class RewardChestMenu extends Menu {
	private DropTable table;

	public RewardChestMenu(int tier, DropTable table) {
		super(Util.tierToColor(tier) + ItemHandler.tierToText(tier) + " Space Chest", 3);
		this.table = table;
	}

	@Override
	public void loadIcons(GalactixPlayer p) {

		for (int i = 10; i < 17; i++) {
			this.getInventory().setItem(i, table.rollRewards());
		}

		final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		final int taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(),
				new Runnable() {
					@Override
					public void run() {
						inventoryTick();
						p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1, 1.5F);
					}
				}, 0L, 2L);

		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			public void run() {
				Bukkit.getScheduler().cancelTask(taskID);

				final int second_taskID = scheduler.scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
					@Override
					public void run() {
						inventoryTick();
						p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.ENTITY_IRON_GOLEM_ATTACK, 1, 1.5F);
					}
				}, 0L, 4L);

				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
					public void run() {
						Bukkit.getScheduler().cancelTask(second_taskID);

						final int third_taskID = scheduler.scheduleSyncRepeatingTask(Main.getInstance(),
								new Runnable() {
									@Override
									public void run() {
										inventoryTick();
										p.getPlayer().playSound(p.getPlayer().getLocation(),
												Sound.ENTITY_IRON_GOLEM_ATTACK, 1, 1.5F);
									}
								}, 0L, 10L);

						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
							public void run() {
								Bukkit.getScheduler().cancelTask(third_taskID);

								p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1,
										2);
								p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
								p.getPlayer().getWorld().spawnEntity(p.getPlayer().getLocation(), EntityType.FIREWORK);

								ItemStack winningItem = getInventory().getItem(13);

								if (winningItem == null || winningItem.getType().equals(Material.AIR)) {
									return;
								}

								if (p.hasFullInventory()) {
									p.getPlayer().getWorld().dropItem(p.getPlayer().getLocation(), winningItem);
									p.getPlayer()
											.sendMessage("§cYou have full inventory, your reward has been dropped.");
								} else
									p.getPlayer().getInventory().addItem(winningItem);

							}
						}, 50);
					}
				}, 50);
			}
		}, 50);

		p.getPlayer().openInventory(this.getInventory());

	}

	private void inventoryTick() {
		int currentSlot = 0;

		for (int i = 0; i < 27; i++) {
			if (currentSlot <= 10 || currentSlot > 16) {
				this.getInventory().setItem(currentSlot,DropTables.RANDOM_BEAUTY_PANE_TABLE.getTable().rollRewards());
				currentSlot++;
			}
			else {
				this.getInventory().setItem(currentSlot - 1, this.getInventory().getItem(currentSlot));
				currentSlot++;
			}
		}

		this.getInventory().setItem(16, table.rollRewards());
		
		this.getInventory().setItem(4, new ItemStack(Material.REDSTONE_TORCH));
		this.getInventory().setItem(22, new ItemStack(Material.REDSTONE_TORCH));
	}

	@Override
	public void onInventoryClick(GalactixPlayer p, InventoryClickEvent e) {
		e.setCancelled(true);

	}

}
