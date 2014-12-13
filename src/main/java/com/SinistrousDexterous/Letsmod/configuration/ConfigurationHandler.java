package com.SinistrousDexterous.Letsmod.configuration;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler
{
   public static void init(File configFile)
   {
      Configuration configuration = new Configuration(configFile);
      boolean configValue=false;
      try
      {
         configuration.load();
         //read in configuration
         configValue = configuration.get(Configuration.CATEGORY_GENERAL,"configValue",true,"This is an example config value")
                        .getBoolean(true);
      }
      catch(Exception e)
      {
         //log exception
      }
      finally
      {
         configuration.save();
         //save configuration
      }
   }
}
