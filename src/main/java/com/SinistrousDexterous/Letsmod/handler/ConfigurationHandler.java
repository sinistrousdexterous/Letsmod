package com.SinistrousDexterous.Letsmod.handler;

import java.io.File;

import com.SinistrousDexterous.Letsmod.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;


public class ConfigurationHandler
{
   public static Configuration configuration;
   public static boolean testValue = false;
   public static void init(File configFile)
   {
      if (configuration == null)
      {
         configuration = new Configuration(configFile);
         loadConfiguration();
      }
   }
   @SubscribeEvent
   public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
   {
      if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
      {
         loadConfiguration();
         //resync config
      }
   }
   private static void loadConfiguration()
   {
      testValue = configuration.getBoolean("configValue", Configuration.CATEGORY_GENERAL, false, "example Test value");
      if(configuration.hasChanged())
      {
         configuration.save();
      }
   }
}
