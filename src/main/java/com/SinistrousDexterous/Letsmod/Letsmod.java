package com.SinistrousDexterous.Letsmod;

import com.SinistrousDexterous.Letsmod.handler.ConfigurationHandler;
import com.SinistrousDexterous.Letsmod.proxy.IProxy;
import com.SinistrousDexterous.Letsmod.reference.Reference;
import com.SinistrousDexterous.Letsmod.utility.LogHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Letsmod
{
   @Mod.Instance(Reference.MOD_ID)
   public static Letsmod instance;

   @SidedProxy(clientSide = Reference.CLIENTPROXY,serverSide = Reference.SERVERPROXY)
   public static IProxy proxy;

   @Mod.EventHandler
   public void Init(FMLPreInitializationEvent event)
   {
      ConfigurationHandler.init(event.getSuggestedConfigurationFile());
      LogHelper.info("Pre-Initialisation Complete");
   }
   @Mod.EventHandler
   public void Init(FMLInitializationEvent event)
   {
      LogHelper.info("Initialisation Complete");
   }
   @Mod.EventHandler
   public void Init(FMLPostInitializationEvent event)
   {
      LogHelper.info("Post-Initialisation Complete");
   }
}
