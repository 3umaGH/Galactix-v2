package bukkit.galactix.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;


public class Util {
	private static Random randomGenerator = new Random();
	

	public static boolean isNumeric(String s) {
		return s.matches("\\d+");
	}

	public static String colorize(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	public static int generateRandom(int nextInt) {
		return randomGenerator.nextInt(nextInt);
	}

	public static int generateRandomBetween(int min, int max) {
		return randomGenerator.nextInt(max - min + 1) + min;
	}
	
	public static String arabicToRoman(int number){
		switch (number){
			case 1: return "I";
			case 2: return "II";
			case 3: return "III";
			case 4: return "IV";
			case 5: return "V";
			case 6: return "VI";
			case 7: return "VII";
			case 8: return "VIII";
			case 9: return "IX";
			case 10: return "X";
			default: return String.valueOf(number);
		}
	}
	
	public static int romanToArabic(String number){
		switch (number){
			case "I": return 1;
			case "II": return 2;
			case "III": return 3;
			case "IV": return 4;
			case "V": return 5;
			case "VI": return 6;
			case "VII": return 7;
			case "VIII": return 8;
			case "IX": return 9;
			case "X": return 10;
			default: return Integer.valueOf(number);
		}
	}
	
	public static String tierToColor(int tier){
		switch (tier){
		case 1: return "§7";
		case 2: return "§e";
		case 3: return "§9";
		case 4: return "§5";
		case 5: return "§6";
		case 6: return "§d";
		default: return "§7";
		}
	}
	
    public static boolean isInBorder(Location center, Location notCenter, int range) {
    	int x = center.getBlockX(), z = center.getBlockZ();
    	int x1 = notCenter.getBlockX(), z1 = notCenter.getBlockZ();
    	 
    	if (x1 >= (x + range) || z1 >= (z + range) || x1 <= (x - range) || z1 <= (z - range) || range <= (center.getY() - notCenter.getY()) || range <= (notCenter.getY() - center.getY())) 
    		return false;
    	
    	if(!center.getWorld().getName().equals(notCenter.getWorld().getName()))
    		return false;
    	
    	return true;
    }
    
    public static List<Entity> getNearbyEntities(Location where, int range) {
    	List<Entity> found = new ArrayList<Entity>();
    	 
    	for (Entity entity : where.getWorld().getEntities())
    		if (isInBorder(where, entity.getLocation(), range)) 
    			found.add(entity);
    		
    	
    	return found;
    }
    
    public static Location calculatedirectionTowards(Location loc1, Location loc2){
    	Location loc = loc1;
    	
    	Double vX = Double.valueOf(loc2.getX() - loc1.getX());
        Double vY = Double.valueOf(loc2.getY() - loc1.getY());
        Double vZ = Double.valueOf(loc2.getZ() - loc1.getZ());
        Vector vectorToTarget = new Vector(vX.doubleValue(), vY.doubleValue(), vZ.doubleValue());
        
        loc.setDirection(vectorToTarget);
        
        return loc;
    }
    
    public static boolean countChance(int chance){
    	return (generateRandom(100) < chance);
    }
    
    public static boolean compareLocationLength(Location loc1, Location loc2, int radius){
    	return loc1.distance(loc2) < radius;
    }

}
