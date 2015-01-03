package com.SinistrousDexterous.Letsmod.init;

import com.SinistrousDexterous.Letsmod.item.ItemBranchCutter;
import com.SinistrousDexterous.Letsmod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import com.SinistrousDexterous.Letsmod.item.ItemLM;
import com.SinistrousDexterous.Letsmod.item.ItemSalt;
import net.minecraft.item.ItemTool;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
   public static final ItemLM   salt         = new ItemSalt();
   public static final ItemTool branchcutter = new ItemBranchCutter();

   public static void init()
   {
      GameRegistry.registerItem(salt, "salt");
      GameRegistry.registerItem(branchcutter, "branchcutter");
   }

}
