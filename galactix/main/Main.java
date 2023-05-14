package bukkit.galactix.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import bukkit.galactix.command.GalactixCommandHandler;
import bukkit.galactix.command.list.AdminCommand;
import bukkit.galactix.command.list.ConsoleCommand;
import bukkit.galactix.customenchant.handlers.CustomEnchantHandler;
import bukkit.galactix.customenchant.listeners.CustomEnchantListener;
import bukkit.galactix.droptable.DropTables;
import bukkit.galactix.item.handlers.ItemHandler;
import bukkit.galactix.item.listeners.EnchantmentBookListener;
import bukkit.galactix.item.listeners.ItemListener;
import bukkit.galactix.menu.MenuListener;
import bukkit.galactix.player.PlayerHandler;
import bukkit.galactix.supplycrate.SupplyCrateHandler;
import bukkit.galactix.supplycrate.SupplyCrateListener;

/*
 * -= Permissions =-
 * - galactix.admin -  OPs permissions
 */

public class Main extends JavaPlugin implements Listener {
	private static double VERSION = 0.1;

	private static Plugin instance;

	@Override
	public void onEnable() {
		instance = this;

		GalactixCommandHandler.loadCommand(new AdminCommand("admin"));
		GalactixCommandHandler.loadCommand(new ConsoleCommand("console"));

		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new PlayerHandler(), this);
		getServer().getPluginManager().registerEvents(new ItemListener(), this);
		getServer().getPluginManager().registerEvents(new MenuListener(), this);
		getServer().getPluginManager().registerEvents(new SupplyCrateListener(), this);
		getServer().getPluginManager().registerEvents(new CustomEnchantListener(), this);
		getServer().getPluginManager().registerEvents(new EnchantmentBookListener(), this);

		CustomEnchantHandler.initEnchantments();
		ItemHandler.initItems();
		DropTables.initDropTables();
		SupplyCrateHandler.initDropLocations();
		PlayerHandler.loadPlayers();
		

		Bukkit.getConsoleSender()
				.sendMessage("§a[GALACTIX] Galactix §ev" + VERSION + "§a has been successfully initalized! ");
	}

	@Override
	public void onDisable() {

		MenuListener.closeAllInventories();
		PlayerHandler.unloadPlayers();
		SupplyCrateHandler.removeChests();

		Bukkit.getConsoleSender().sendMessage("§c[GALACTIX] Galactix §ev" + VERSION + "§c has been disabled! ");
	}

	public static Plugin getInstance() {
		return instance;
	}

}
