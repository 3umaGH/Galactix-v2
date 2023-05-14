package bukkit.galactix.scripteditem.items;

import org.bukkit.inventory.ItemStack;

import bukkit.galactix.scripteditem.Item;
import bukkit.galactix.scripteditem.ScriptedItem;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import bukkit.galactix.scripteditem.Item;

public class Items {
	
	public static enum CustomItem {
		ADMIN_TOOL(new AdminTool(1));
		
		private static final HashMap<String, CustomItem> itemList = new HashMap<String, CustomItem>();
		private static final HashMap<Integer, CustomItem> itemIDs = new HashMap<Integer, CustomItem>();
		
		private ItemStack i;
		private Item item;
		private int ID;
		
		private CustomItem(int ID, String name, String description, int tier, boolean showTier, Material item, short data){
			this.ID = ID;
			this.item = new Item(ID, name, description, tier, showTier, item, data);
		}
		
		private CustomItem(Item item) {
			this.ID = item.getId();
			this.item = item;
		}
		
	    static {
	        for (CustomItem i : CustomItem.values()) {
	        	itemList.put(i.item.getItemStack().getItemMeta().getDisplayName(), i);
	        	itemIDs.put(i.ID, i);
	        	
	        }
	    }
	    
	    public Item getItem() {
	    	return this.item;
	    }
	    
		public static Item getItemByStack(ItemStack i) {
			Item item = itemList.get(i.getItemMeta().getDisplayName()).item;

			if (item != null && item.getItemStack().getType().equals(i.getType()))
				return item;
			else
				return null;
		}

		public static Item getItemById(int id) {
			return itemIDs.get(id).item;
		}

		public static ScriptedItem getScriptedItem(ItemStack i) {
			Item item = getItemByStack(i);

			if (item != null && item.isScriptedItem())
				return (ScriptedItem) item;
			else
				return null;

		}
		
		public static HashMap<String, CustomItem> getItemList() {
			return itemList;
		}

		public static HashMap<Integer, CustomItem> getItemIDs() {
			return itemIDs;
		}
		
	}


}
