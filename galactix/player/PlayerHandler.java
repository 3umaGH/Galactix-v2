package bukkit.galactix.player;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import bukkit.galactix.logger.Logger;
import bukkit.galactix.logger.Logger.Log;

public class PlayerHandler implements Listener {
	private static HashMap<UUID, GalactixPlayer> loadedPlayers = new HashMap<UUID, GalactixPlayer>();

	@EventHandler
	private void onPlayerJoin(PlayerJoinEvent e) {
		loadPlayer(e.getPlayer());
	}

	@EventHandler
	private void onPlayerDisconnect(PlayerQuitEvent e) {
		unloadPlayer(e.getPlayer());
	}

	private static void loadPlayer(Player p) {
		GalactixPlayer gP = new GalactixPlayer(p);
		loadedPlayers.put(p.getUniqueId(), gP);

		gP.sendTitle(5, 20, 5, "", "&aPlayer data has been successfully loaded.");
		Logger.sendLog(Log.INFO, "Player " + p.getName() + " has been successfully loaded.");
	}

	private static void unloadPlayer(Player p) {
		GalactixPlayer gP = getGalactixPlayer(p);

		if (gP == null)
			Logger.sendLog(Log.WARN, "Trying to unload " + p.getName() + ", but he is not loaded.");
		else {
			loadedPlayers.remove(p.getUniqueId());
			Logger.sendLog(Log.INFO, "Player " + p.getName() + " has been successfully unloaded.");
		}

	}

	public static GalactixPlayer getGalactixPlayer(Player p) {
		GalactixPlayer gP = loadedPlayers.get(p.getUniqueId());
		return gP;
	}

	public static void loadPlayers() {
		for (Player p : Bukkit.getOnlinePlayers())
			loadPlayer(p);
	}

	public static void unloadPlayers() {
		for (Player p : Bukkit.getOnlinePlayers())
			unloadPlayer(p);
	}

}
