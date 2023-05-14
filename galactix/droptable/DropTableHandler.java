package bukkit.galactix.droptable;

import java.util.HashMap;

import bukkit.galactix.logger.Logger;
import bukkit.galactix.logger.Logger.Log;

public class DropTableHandler {
	private static HashMap<Integer, DropTable> dropTables = new HashMap<Integer, DropTable>();
	
	/* DROP TABLES
	 * Random stained glass panes - 1338
	 * 
	 * 
	 * 
	 */
	
	public static void initDropTables() {
	
		loadDropTable(0, new DropTable().addRewards(true, 
				new Reward(6,1,1,2320),
				new Reward(5,1,1,1160),
				new Reward(4,1,1,580),
				new Reward(3,1,1,240),
				new Reward(2,1,1,120),
				new Reward(2,1,1,60),
				new Reward(1,1,1,50),
				new Reward(0,1,1,30)
				));
		
		loadDropTable(1337, new DropTable().addRewards(true, 
				new Reward(1340,1,1,10),
				new Reward(1341,1,1,10),
				new Reward(1342,1,1,10),
				new Reward(1343,1,1,10),
				new Reward(1344,1,1,10),
				new Reward(1345,1,1,10),
				new Reward(1346,1,1,10),
				new Reward(1347,1,1,10),
				new Reward(1348,1,1,10),
				new Reward(1349,1,1,10),
				new Reward(1350,1,1,10)
				));

		
		Logger.sendLog(Log.INFO, "initalized " + dropTables.size() + " drop tables.");
	}
	
	private static void loadDropTable(int id, DropTable table) {
		dropTables.put(id, table);
	}

	public static HashMap<Integer, DropTable> getDropTables() {
		return dropTables;
	}
	
	public static DropTable getDropTableById(int id) {
		return dropTables.get(id);
	}
	

}
