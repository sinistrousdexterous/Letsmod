package com.SinistrousDexterous.Letsmod.item;

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
   }

   @Override
   public String getUnlocalizedName()
   {
      return String.format("item.%s%s", Reference.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
   }

   @Override
   public String getUnlocalizedName(ItemStack itemStack)
   {
      return String.format("item.%s%s", Reference.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister iconRegister)
   {
      itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
   }

   protected String getUnwrappedUnlocalizedName(String unlocalizedName)
   {
      return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
   }
}
