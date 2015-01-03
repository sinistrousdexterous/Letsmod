package com.SinistrousDexterous.Letsmod.item;

import com.SinistrousDexterous.Letsmod.creativetab.CreativeTabLM;

import com.SinistrousDexterous.Letsmod.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemLM extends Item
{
   public ItemLM()
   {
      super();
      this.setCreativeTab(CreativeTabLM.tabLM);
   }

   @Override
   public String getUnlocalizedName()
   {
      return String.format("item.%s%s", Reference.RESOURCE_PREFIX, this.getUnwrappedName(super.getUnlocalizedName()));
   }

   @Override
   public String getUnlocalizedName(ItemStack itemStack)
   {
      return String.format("item.%s%s", Reference.RESOURCE_PREFIX, this.getUnwrappedName(super.getUnlocalizedName()));
   }

   protected static String getUnwrappedName(String unlocalizedName)
   {
      return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
   }
   @Override
   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister iconRegister)
   {
      itemIcon = iconRegister.registerIcon(this.getUnwrappedName(this.getUnlocalizedName()));
   }
}
