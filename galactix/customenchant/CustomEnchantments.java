package bukkit.galactix.customenchant;

import java.util.HashMap;

import bukkit.galactix.customenchant.list.DebugEnchantment;

public enum CustomEnchantments {
	DebugEnch(0, new DebugEnchantment());
	
	private int id;
	private CustomEnchant e;
	
	private static HashMap<Integer, CustomEnchant> enchantmentIDs = new HashMap<Integer, CustomEnchant>();
	private static HashMap<String, CustomEnchant> enchantmentList = new HashMap<String, CustomEnchant>();
	
	CustomEnchantments(int id, CustomEnchant e){
		this.id = id;
		this.e = e;
		e.setID(id);
	}
	
	static {
		for (CustomEnchantments ench : CustomEnchantments.values()) {
			enchantmentList.put(ench.e.getName().toLowerCase(),ench.e);
			enchantmentIDs.put(ench.id, ench.e);
		}
	}
	
	public static CustomEnchant getEnchantByName(String name) {
		return enchantmentList.get(name.toLowerCase());
	}

	public static CustomEnchant getEnchantById(Integer i) {
		return enchantmentIDs.get(i);
	}

	public static HashMap<Integer, CustomEnchant> getEnchantmentIDs() {
		return enchantmentIDs;
	}

	public static HashMap<String, CustomEnchant> getEnchantmentList() {
		return enchantmentList;
	}
	
	public CustomEnchant getEnchantment() {
		return this.e;
	}
	
	public int getId() {
		return this.id;
	}
	
	
}
