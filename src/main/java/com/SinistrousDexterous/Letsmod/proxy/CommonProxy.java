package com.SinistrousDexterous.Letsmod.proxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;

public class CommonProxy implements IProxy
{
   protected static CommonProxy proxyInstance = null;

   public static void setInstance(CommonProxy newProxy)
   {
      proxyInstance = newProxy;
   }

   public static CommonProxy getProxy()
   {
      if (proxyInstance == null)
      {
         proxyInstance = new CommonProxy();
      }

      return proxyInstance;
   }

   public void initializeSounds(){}
   public void initializeRenderers(){}

   public World getWorld()
   {
      return null;
   }

   public static boolean runningOnServer()
   {
      boolean server = false;

      try
      {
         server = serverCheck();
      }
      catch (NoSuchMethodError e)
      {
         server = false;
      }

      return server;
   }

   @SideOnly(Side.SERVER)
   public static boolean serverCheck()
   {
      return true;
   }
}
