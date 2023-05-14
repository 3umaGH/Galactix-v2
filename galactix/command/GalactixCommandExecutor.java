package bukkit.galactix.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import bukkit.galactix.logger.Logger;
import bukkit.galactix.logger.Logger.Log;

public class GalactixCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		GalactixCommand gCmd = GalactixCommandHandler.getCommand(label.toLowerCase());

		if (gCmd == null)
			return false;

		if (sender instanceof Player) {
			Player p = (Player) sender;
			
			if (gCmd.isConsoleOnly()) {
				p.sendMessage("§cCannot execute this command as player!");
				Logger.sendLog(Log.WARN,
						p.getName() + " attempted to execute console only command " + gCmd.getCommand() + "!");
				return false;
			}
			
			if (args.length < gCmd.getMinArgs() | args.length > gCmd.getMaxArgs()) {
				p.sendMessage("§cInvalid amount of arguments (min: " + gCmd.getMinArgs() + ", max: " + gCmd.getMaxArgs() + ")!");
				return false;
			}
			
			if (!p.hasPermission(gCmd.getPerm())) {
				p.sendMessage("§cNot enough permissions!");
				return false;
			}

			gCmd.execute(sender, args);

		}

		if (sender instanceof ConsoleCommandSender) {

			if (gCmd.isPlayerOnly()) {
				Logger.sendLog(Log.WARN, gCmd.getCommand() + " cannot be executed in console.");
				return false;
			}
			
			if (args.length < gCmd.getMinArgs() | args.length > gCmd.getMaxArgs()) {
				Logger.sendLog(Log.WARN, "§cInvalid amount of arguments (min: " + gCmd.getMinArgs() + ", max: " + gCmd.getMaxArgs() + ")!");
				return false;
			}

			gCmd.execute(sender, args);

		}

	

		return false;
	}
	
}