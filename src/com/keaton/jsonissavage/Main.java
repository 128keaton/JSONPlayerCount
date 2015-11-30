package com.keaton.jsonissavage;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin {
public static Main instance;
public static Permission perms = null;

private static Plugin plugin; 

  public static Plugin getPlugin() {
    return plugin;
  }
  

    @Override
    public void onEnable() {
        new Notification(this);
        plugin = this;
        Main.instance = this;
        try {
            final File[] libs = new File[] {
            		new File(getDataFolder(), "jackson-core-2.6.3.jar")};
            for (final File lib : libs) {
                if (!lib.exists()) {
                    JarUtils.extractFromJar(lib.getName(),
                            lib.getAbsolutePath());
                }
            }
            for (final File lib : libs) {
                if (!lib.exists()) {
                    getLogger().warning(
                            "There was a critical error loading My plugin! Could not find lib: "
                                    + lib.getName());
                    Bukkit.getServer().getPluginManager().disablePlugin(this);
                    return;
                }
                addClassPath(JarUtils.getJarUrl(lib));
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        Notification.PlayersOnline = Bukkit.getOnlinePlayers().length;

        for(Player allPlayers : Bukkit.getOnlinePlayers()) {
        	
        	System.out.println("Players: " + allPlayers.getName() + "\n");
        	Notification.playerList.add(allPlayers.getName());
        	
        	}
         try {
     				Notification.update();
     			} catch (IOException e1) {
     				// TODO Auto-generated catch block
     				e1.printStackTrace();
     				
     			}
                  
         
        }
    
        
    
    private void addClassPath(final URL url) throws IOException {
        final URLClassLoader sysloader = (URLClassLoader) ClassLoader
                .getSystemClassLoader();
        final Class<URLClassLoader> sysclass = URLClassLoader.class;
        try {
            final Method method = sysclass.getDeclaredMethod("addURL",
                    new Class[] { URL.class });
            method.setAccessible(true);
            method.invoke(sysloader, new Object[] { url });
        } catch (final Throwable t) {
            t.printStackTrace();
            throw new IOException("Error adding " + url
                    + " to system classloader");
        }
    }
    
   
       
   
    @Override
    public void onDisable() {
    	File fileOld = new File("stats.json");
		fileOld.delete();
		
       
        }
    public static Main getInstance() {
        return instance;
        }
   
   

}

 