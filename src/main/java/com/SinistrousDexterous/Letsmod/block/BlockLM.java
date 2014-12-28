package com.SinistrousDexterous.Letsmod.block;

import com.SinistrousDexterous.Letsmod.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;


public class BlockLM extends Block
{
   public BlockLM(Material material)
   {
      super(material);
   }
   public BlockLM()
   {
      this(Material.rock);
   }

   @Override
   public String getUnlocalizedName()
   {
      return String.format("tile.%s%s", Reference.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister iconRegister)
   {
      blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
   }

   protected String getUnwrappedUnlocalizedName(String unlocalizedName)
   {
      return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
   }

}
