package bukkit.galactix.chat;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;


public class ChatHandler {

	public static void sendGlobalMessage(String msg) {
		for (Player allP : Bukkit.getOnlinePlayers()) allP.sendMessage(msg);
	}
	
    public static void sendWorldMessage(String msg, World world){
			for(Player allP : Bukkit.getOnlinePlayers()) 
				if(allP.getWorld().equals(world)) allP.sendMessage(msg);
    }
    
    public static void sendGlobalMessageExcept(Player p, String msg){
			for(Player allP : Bukkit.getOnlinePlayers()){
				if(!allP.equals(p))
					allP.sendMessage(msg);
			}
    }

}
