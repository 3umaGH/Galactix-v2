package bukkit.galactix.command.list;

import org.bukkit.command.CommandSender;

import bukkit.galactix.command.GalactixCommand;
import bukkit.galactix.logger.Logger;
import bukkit.galactix.logger.Logger.Log;

public class ConsoleCommand extends GalactixCommand {

	public ConsoleCommand(String command) {
		super(command);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(CommandSender s, String[] args) {
		Logger.sendLog(Log.INFO, "yay!");
	}

	@Override
	public boolean isConsoleOnly() {
		return true;
	}

	@Override
	public boolean isPlayerOnly() {
		return false;
	}

	@Override
	public int getMinArgs() {
		return 0;
	}

	@Override
	public int getMaxArgs() {
		return 0;
	}

	@Override
	public String getPerm() {
		return "galactix.admin";
	}


	
}
