package bukkit.galactix.command.list;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bukkit.galactix.command.GalactixCommand;
import bukkit.galactix.customenchant.CustomEnchant;
import bukkit.galactix.customenchant.CustomEnchantments;
import bukkit.galactix.customenchant.handlers.CustomEnchantHandler;
import bukkit.galactix.item.Item;
import bukkit.galactix.item.handlers.ItemHandler;
import bukkit.galactix.item.list.EnchantBook;
import bukkit.galactix.logger.Logger;
import bukkit.galactix.logger.Logger.Log;
import bukkit.galactix.menu.list.ItemListMenu;
import bukkit.galactix.player.GalactixPlayer;
import bukkit.galactix.player.PlayerHandler;
import bukkit.galactix.utils.Util;

public class AdminCommand extends GalactixCommand {
	private static int adminToolID = 1337;
	
	
	public AdminCommand(String command) {
		super(command);
	}
	
	private void printHelp(Player p) {
		p.sendMessage("---------- §9[ GALACTIX ADMIN HELP ]§f ----------");
		p.sendMessage("§e/admin help §f- will show all available admin subcommands.");
		p.sendMessage("§e/admin tool §f- will give you an admin tool.");
		p.sendMessage("§e/admin item [id] [amount] §7[player]§f - will give you or a player an item with specified id.");
		p.sendMessage("§e/admin itemlist [page] §f- will open inventory with all scripted items.");
		p.sendMessage("admin enchant <ench name> <level>, admin removeenchant <name>, admin getenchants, admin createbook <ench> <lvl> <success rate>");
	}

	@Override
	public void execute(CommandSender s, String[] args) {
		Player p = (Player)s;
		GalactixPlayer gP = PlayerHandler.getGalactixPlayer(p);
		
		if(args[0].equalsIgnoreCase("help")) {
			printHelp(p);
			
			return;
		}
		
		// DEBUG COMMANDS
		if(args[0].equalsIgnoreCase("enchant")) {
			CustomEnchant ench = CustomEnchantments.getEnchantByName(args[1]);
			
			if(ench != null) 
				CustomEnchantHandler.enchantItemStack(p.getInventory().getItemInMainHand(), ench, Integer.parseInt(args[2]));
			else
				p.sendMessage("Enchantment " + args[1] + " not found.");
			
			return;
		}
		
		if(args[0].equalsIgnoreCase("createbook")) {
			CustomEnchant ench = CustomEnchantments.getEnchantByName(args[1]);
			
			if(ench != null) 
				p.getInventory().addItem(EnchantBook.getItemStack(ench, Integer.parseInt(args[2]), Integer.parseInt(args[3])));
			else
				p.sendMessage("Enchantment " + args[1] + " not found.");
			
			return;
		}
		
		if(args[0].equalsIgnoreCase("removeenchant")) {
			CustomEnchant ench = CustomEnchantments.getEnchantByName(args[1]);
			
			if(ench != null) 
				CustomEnchantHandler.removeEnchantment(p.getInventory().getItemInMainHand(), ench);
			else
				p.sendMessage("Enchantment " + args[1] + " not found.");
			
			return;
		}
		
		if(args[0].equalsIgnoreCase("getenchants")) {
			HashMap<CustomEnchant, Integer> enchants = CustomEnchantHandler.getItemStackEnchantments(p.getInventory().getItemInMainHand());
			
			
			p.sendMessage("Item in hand has following enchantments: ");
			
			for(CustomEnchant e : enchants.keySet()) 
				p.sendMessage(e.getName() + " : " + enchants.get(e));
			
			
			return;
		}
		
		//DEBUG COMMANDS END
		
		if(args[0].equalsIgnoreCase("itemlist")) {
			new ItemListMenu().openMenu(gP);;
			return;
		}
		
		if(args[0].equalsIgnoreCase("tool")) {
			ItemStack adminTool = new ItemStack(Item.getItemById(adminToolID).getItemStack());
			
			if(gP.hasFullInventory()) {
				p.sendMessage("§cYou have a full inventory, buddy.");
				return;
			}
			
			p.getInventory().addItem(adminTool);
			p.sendMessage("§aWhoosh! Dont abuse dem powerz, ok?");
			
			Logger.sendLog(Log.INFO, "Player " + p.getName() + " has gave himself an " + adminTool.getItemMeta().getDisplayName() + ".");
			
			return;
		}
		
		if(args[0].equalsIgnoreCase("item")) {
			//args1 item, args2 amnt args3player
			
			if(args.length <= 2 | args.length > 4) {
				p.sendMessage("§cUsage: /admin item [id] [amount])");
				return;
			}
			
			
			if(!Util.isNumeric(args[1])) {
				p.sendMessage("§cPlease specify an item ID. (/admin item [id] [amount])");
				return;
			}
			
			if(!Util.isNumeric(args[2])) {
				p.sendMessage("§cPlease specify an item amount. (/admin item [id] [amount])");
				return;
			}
			
			int itemID = Integer.parseInt(args[1]);
			int amnt = Integer.parseInt(args[2]);
			
			if(amnt <= 0 | amnt > 64) {
				p.sendMessage("§cItem amount cannot be less than 1 or more than 64.");
				return;
			}
			
			Item itm = Item.getItemById(itemID);
			
			if(itm == null) {
				p.sendMessage("§cItem with ID " + itemID + " does not exist.");
				return;
			}
			
			ItemStack itemStack = new ItemStack(itm.getItemStack());
			itemStack.setAmount(amnt);
			
			GalactixPlayer playerGiveTo;
			
			if(args.length == 4) {
				Player giveTo = Bukkit.getPlayer(args[3]);
					
				if(giveTo == null) {
					p.sendMessage("§c" + args[3] + " is not found.");
					return;
				}
				
				
				if(!giveTo.isOnline()) {
					p.sendMessage("§c" + args[3] + " is offline.");
					return;
				}
				
				playerGiveTo = PlayerHandler.getGalactixPlayer(giveTo);
				
			} else 
				playerGiveTo = gP;
					
			if(playerGiveTo.hasFullInventory()) {
				playerGiveTo.getPlayer().sendMessage("§c" + gP.getPlayer().getName() + " has a full inventory.");
				return;
			}
			
			playerGiveTo.getPlayer().getInventory().addItem(itemStack);
			
			p.sendMessage("Gave " + itemStack.getItemMeta().getDisplayName() + "§f x §e" + amnt + "§f to §e" + playerGiveTo.getPlayer().getName());
			Logger.sendLog(Log.INFO, "Gave " + itemStack.getItemMeta().getDisplayName() + "§f x §e" + amnt + "§f to §e" + playerGiveTo.getPlayer().getName());
			
			return;
		}
		
		printHelp(p);
		
	}

	@Override
	public boolean isConsoleOnly() {
		return false;
	}

	@Override
	public boolean isPlayerOnly() {
		return true;
	}

	@Override
	public int getMinArgs() {
		return 1;
	}

	@Override
	public int getMaxArgs() {
		return 5;
	}

	@Override
	public String getPerm() {
		return "galactix.admin";
	}


	
}
