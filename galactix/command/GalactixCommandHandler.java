package bukkit.galactix.command;

import java.util.HashMap;

import org.bukkit.Bukkit;

import bukkit.galactix.logger.Logger;
import bukkit.galactix.logger.Logger.Log;

public class GalactixCommandHandler {
	private static HashMap<String, GalactixCommand> commands = new HashMap<String, GalactixCommand>();
	
	public static void loadCommand(GalactixCommand com) {
		Bukkit.getPluginCommand(com.getCommand()).setExecutor(new GalactixCommandExecutor());
		commands.put(com.getCommand(), com);
		
		Logger.sendLog(Log.INFO, "Command " + com.getCommand() + " has been initalized.");
	}
	
	public static GalactixCommand getCommand(String command) {
		return commands.get(command.toLowerCase());
	}

}
