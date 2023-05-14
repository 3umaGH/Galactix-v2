package bukkit.galactix.droptable;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import bukkit.galactix.utils.Util;

public class DropTable {
	private List<Reward> rewards = new ArrayList<Reward>();
	private boolean noEmptyDrops;

	public DropTable() {}

	public DropTable addRewards(boolean noEmptyDrops, Reward... rewards) {
		this.noEmptyDrops = noEmptyDrops;
		
		for(Reward reward : rewards)
			this.rewards.add(reward);
		
		return this;
	}

	public ItemStack rollRewards() {
		ItemStack rewardItemStack = roll();

		if (noEmptyDrops)
			while (rewardItemStack.getType().equals(Material.AIR))
				rewardItemStack = roll();
		else
			return roll();

		return rewardItemStack;
	}

	private ItemStack roll() {
		ItemStack rewardItemStack = new ItemStack(Material.AIR);

		for (Reward reward : rewards) { // 256, 1
			if ((Util.generateRandomBetween(1, reward.getChance()) == reward.getChance())) {
				
				if(reward.getItemStack().getType().equals(Material.AIR))
					break;
				
				rewardItemStack = reward.getItemStack();
				break;
			}
		}

		return rewardItemStack;
	}
	
}
