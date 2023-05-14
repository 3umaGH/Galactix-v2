package bukkit.galactix.command;

import org.bukkit.command.CommandSender;

public abstract class GalactixCommand {

	private String command;

	public GalactixCommand(String command) {
		this.command = command;
	}

	public abstract void execute(CommandSender s, String[] args);

	public abstract boolean isConsoleOnly();

	public abstract boolean isPlayerOnly();

	public abstract int getMinArgs();

	public abstract int getMaxArgs();

	public abstract String getPerm();

	public String getCommand() {
		return command;
	}

}
