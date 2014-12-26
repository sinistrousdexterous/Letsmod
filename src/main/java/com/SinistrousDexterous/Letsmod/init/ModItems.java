package com.SinistrousDexterous.Letsmod.init;

import com.SinistrousDexterous.Letsmod.item.ItemLM;
import com.SinistrousDexterous.Letsmod.item.ItemSalt;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems
{
   public static final ItemLM salt = new ItemSalt();

   public static void init()
   {
      GameRegistry.registerItem(salt, "salt");
   }

}
