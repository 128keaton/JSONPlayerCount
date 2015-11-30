package com.keaton.jsonissavage;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;










public class Notification implements Listener {
	public static int PlayersOnline = 0;
	public static ArrayList<String> playerList = new ArrayList<String>();
	
    public Notification(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);   
    }
   
 

	   
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

       
        PlayersOnline = Bukkit.getOnlinePlayers().length;
        System.out.println("Player joined! Hola amigo! Players online: " + PlayersOnline);
        
        for(Player allPlayers : Bukkit.getOnlinePlayers()) {
        	
        	System.out.println("Players: " + allPlayers.getName() + "\n");
        	playerList.add(allPlayers.getName());
        	
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
            
                  for(Player allPlayers : Bukkit.getOnlinePlayers()) {
                  
                  	System.out.println("Players: " + allPlayers.getName() + "\n");
                  	playerList.add(allPlayers.getName());
                  	
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
    
  
		public static void update() throws IOException{
        
        	
    		
    
        	JsonFactory jfactory = new JsonFactory();
  
    		

    		



    			File fileOld = new File("stats.json");
        		fileOld.delete();
        		
        		 DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
        		 Date date = new Date();
        		 
        		 
    			@SuppressWarnings("deprecation")
			JsonGenerator jGenerator = jfactory.createJsonGenerator(new File("stats.json"), JsonEncoding.UTF8);
    			jGenerator.writeStartObject(); // {

    			jGenerator.writeStringField("last_updated", dateFormat.format(date)); 
    			jGenerator.writeNumberField("players", Notification.PlayersOnline); // "age" : 29

    			jGenerator.writeFieldName("list");
    			jGenerator.writeStartArray();
    			
    			for(Player allPlayers : Bukkit.getOnlinePlayers()){

                    jGenerator.writeStartObject();
                    jGenerator.writeStringField("name", allPlayers.getName() );
                    jGenerator.writeEndObject();

                 }
    			 jGenerator.writeEndArray();
                  for(Player allPlayers : Bukkit.getOnlinePlayers()){
                	  
                	  System.out.println("Players: " + allPlayers.getName() + "\n");
                     }
                  
		
    		
    		

    			jGenerator.close();
    	        
    	
    		
    	
    			
    			
    		
    			System.out.println("Successfully Copied JSON Object to File...");
    			System.out.println("\nJSON Object: " + jGenerator.toString());
    		
        }

	        
	   
}