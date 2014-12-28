package com.SinistrousDexterous.Letsmod.creativetab;

import com.SinistrousDexterous.Letsmod.init.ModItems;
import com.SinistrousDexterous.Letsmod.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabLM
{
   public static final CreativeTabs tabLM = new CreativeTabs(Reference.MOD_ID)
   {
      @Override
      public Item getTabIconItem()
      {
         return ModItems.salt;
      }

      @Override
      public String getTranslatedTabLabel()
      {
         return "Letsmod mod";
      }
   };
}
