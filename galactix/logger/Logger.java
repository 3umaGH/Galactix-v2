package bukkit.galactix.logger;

import org.bukkit.Bukkit;

public class Logger {
	
	public static enum Log {
		INFO("§9"),WARN("§6"), FATAL("§4"), SEVERE("§c");
		
		private String color = "§f";
		Log(String color) {
			this.color = color;
		}
		
		public String getColor() {
			return color;
		}
	}
	
	public static void sendLog(Log type, String message) {
		Bukkit.getConsoleSender().sendMessage("§d[GALACTIX] " + type.getColor() + "[" + type.toString() + "] " + message);
	}

}
