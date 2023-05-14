package bukkit.galactix.player;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bukkit.galactix.utils.Title;

public class GalactixPlayer {
	private Player p;
	private UUID uuid;

	public GalactixPlayer(Player p) {
		this.p = p;
		this.uuid = p.getUniqueId();
	}

	public boolean hasFullInventory() {
		return p.getInventory().firstEmpty() == -1;
	}

	public void removeOneFromHand() {
		p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);

	}

	public void sendTitle(Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
		Title.sendTitle(p, fadeIn, stay, fadeOut, title, subtitle);
	}

	public Player getPlayer() {
		return p;
	}

}
