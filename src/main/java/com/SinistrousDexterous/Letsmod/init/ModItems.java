package com.SinistrousDexterous.Letsmod.init;

import com.SinistrousDexterous.Letsmod.item.*;
import com.SinistrousDexterous.Letsmod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
   public static final ItemLM salt             = new ItemSalt();
   public static final ItemLM branchcutter     = new ItemBranchCutter();
   public static final ItemLM metaWand = new ItemDecreaseMetaWand();

   public static void init()
   {
      GameRegistry.registerItem(salt, "salt");
      GameRegistry.registerItem(branchcutter, "branchcutter");
      GameRegistry.registerItem(metaWand, "metaWand");
   }

}
