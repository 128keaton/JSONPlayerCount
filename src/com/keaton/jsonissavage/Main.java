package com.keaton.jsonissavage;


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
    }
   
       
   
    @Override
    public void onDisable() {
       
        }
    public static Main getInstance() {
        return instance;
        }
   
   

}

 