package bukkit.galactix.droptable;

import bukkit.galactix.item.Item;
import bukkit.galactix.logger.Logger;
import bukkit.galactix.logger.Logger.Log;

public enum DropTables {

	REWARD_CHEST_TIER1(true, new Reward(Item.TEST_SHOVEL_0.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_1.getId(), 1, 1, 200),
			new Reward(Item.TEST_SHOVEL_2.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_3.getId(), 1, 1, 200)),
	REWARD_CHEST_TIER2(true, new Reward(Item.TEST_SHOVEL_0.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_1.getId(), 1, 1, 200),
			new Reward(Item.TEST_SHOVEL_2.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_3.getId(), 1, 1, 200)),
	REWARD_CHEST_TIER3(true, new Reward(Item.TEST_SHOVEL_0.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_1.getId(), 1, 1, 200),
			new Reward(Item.TEST_SHOVEL_2.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_3.getId(), 1, 1, 200)),
	REWARD_CHEST_TIER4(true, new Reward(Item.TEST_SHOVEL_0.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_1.getId(), 1, 1, 200),
			new Reward(Item.TEST_SHOVEL_2.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_3.getId(), 1, 1, 200)),
	REWARD_CHEST_TIER5(true, new Reward(Item.TEST_SHOVEL_0.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_1.getId(), 1, 1, 200),
			new Reward(Item.TEST_SHOVEL_2.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_3.getId(), 1, 1, 200)),
	REWARD_CHEST_TIER6(true, new Reward(Item.TEST_SHOVEL_0.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_1.getId(), 1, 1, 200),
			new Reward(Item.TEST_SHOVEL_2.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_3.getId(), 1, 1, 200)),

	
	RANDOM_BEAUTY_PANE_TABLE(true, new Reward(Item.BEAUTY_PANE_BLUE.getId(), 1, 1, 10),
			new Reward(Item.BEAUTY_PANE_CYAN.getId(), 1, 1, 10), new Reward(Item.BEAUTY_PANE_GREEN.getId(), 1, 1, 10),
			new Reward(Item.BEAUTY_PANE_LIME.getId(), 1, 1, 10), new Reward(Item.BEAUTY_PANE_MAGNETA.getId(), 1, 1, 10),
			new Reward(Item.BEAUTY_PANE_ORANGE.getId(), 1, 1, 10), new Reward(Item.BEAUTY_PANE_PINK.getId(), 1, 1, 10),
			new Reward(Item.BEAUTY_PANE_PURPLE.getId(), 1, 1, 10), new Reward(Item.BEAUTY_PANE_RED.getId(), 1, 1, 10),
			new Reward(Item.BEAUTY_PANE_WHITE.getId(), 1, 1, 10), new Reward(Item.BEAUTY_PANE_YELLOW.getId(), 1, 1, 10)),

	LEGENDARY_SUPPLY_DROP(true, new Reward(Item.TEST_SHOVEL_0.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_1.getId(), 1, 1, 200),
			new Reward(Item.TEST_SHOVEL_2.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_3.getId(), 1, 1, 200)),

	NORMAL_SUPPLY_DROP(true, new Reward(Item.TEST_SHOVEL_0.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_1.getId(), 1, 1, 200),
			new Reward(Item.TEST_SHOVEL_2.getId(), 1, 1, 200), new Reward(Item.TEST_SHOVEL_3.getId(), 1, 1, 200));

	private DropTable table;

	DropTables(boolean alwaysDrop, Reward... rewards) {
		this.table = new DropTable().addRewards(alwaysDrop, rewards);
	}

	public DropTable getTable() {
		return this.table;
	}

	public static void initDropTables(){
		Logger.sendLog(Log.INFO, "initalized " + DropTables.values().length + " drop tables.");
	}

}
