package com.keaton.jsonissavage;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import org.json.simple.JSONObject;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;








public class Notification implements Listener {
	public static int PlayersOnline = 0;
	public static ArrayList<Player> playerList;
	
    public Notification(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);   
    }
   
 

	   
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

       
        PlayersOnline = Bukkit.getOnlinePlayers().length;
        System.out.println("Player joined! Hola amigo! Players online: " + PlayersOnline);
        
        for (Player p2 : Bukkit.getOnlinePlayers()) {
            Notification.playerList.add(p2);
        }
        
         try {
     				Notification.update();
     			} catch (IOException e1) {
     				// TODO Auto-generated catch block
     				e1.printStackTrace();
     				
     			}
                  
         
        }
               
           
       

   
        @EventHandler
        public void onPlayerQuit(PlayerQuitEvent e) {
        
        	Bukkit.getServer().getScheduler().runTaskLater(Main.getPlugin(), new Runnable()
        	{
        	@Override
			public void run()
        	{
        		  PlayersOnline = Bukkit.getOnlinePlayers().length;
                  System.out.println("Player left! Adios amigo! Players online: " + PlayersOnline);
            
                  for (Player p2 : Bukkit.getOnlinePlayers()) {
                      Notification.playerList.add(p2);
                  }
                  
                  try {
      				Notification.update();
      			} catch (IOException e1) {
      				// TODO Auto-generated catch block
      				
      				e1.printStackTrace();
      				
      			}
        	}
        	}, 0L);
        	
        
           
             

    }
    
        @SuppressWarnings("unchecked")
		public static void update() throws IOException{
        
    		
    		ArrayList<Player> players = Notification.playerList;
    
    		
    		
  
    		
    		JSONObject obj = new JSONObject();
      		for ( Player p : players ) {
   		     obj.put("player", p.getName());
   		     /// And so on...
      		}
 
    		obj.put("players", Notification.PlayersOnline);
    		
    /**		JSONArray servicesJSON = new JSONArray();
    	        ArrayList<Player> playerList = Notification.playerList;
    	        for(int i=0; i< playerList.size(); i++)
    	        {
    	            servicesJSON.add(playerList.get(i)); // use the toMap method here.
    	        }
    	        obj.put("player", servicesJSON);**/
    		
    	        
    		File fileOld = new File("stats.json");
    		fileOld.delete();
    		
    		try (FileWriter file = new FileWriter("stats.json")) {
    			
    			
    			file.write(obj.toJSONString());
    			System.out.println("Successfully Copied JSON Object to File...");
    			System.out.println("\nJSON Object: " + obj);
    		}
        }

	        
	   
}