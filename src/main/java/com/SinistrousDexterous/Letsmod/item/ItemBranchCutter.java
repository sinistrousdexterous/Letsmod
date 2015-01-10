package com.SinistrousDexterous.Letsmod.item;

import java.util.Set;

import com.SinistrousDexterous.Letsmod.creativetab.CreativeTabLM;

import com.SinistrousDexterous.Letsmod.proxy.CommonProxy;
import com.SinistrousDexterous.Letsmod.reference.Reference;

import com.google.common.collect.Sets;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ItemBranchCutter extends ItemLMTool
{
   @SuppressWarnings("rawtypes")
   public static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[]{});

   public ItemBranchCutter()
   {
      super(0.0f, ToolMaterial.WOOD, blocksEffectiveAgainst);
      this.setUnlocalizedName("branchcutter");
      this.setMaxStackSize(1);
      this.setMaxDamage(64);
   }

   @Override// can Harvest
   public boolean func_150897_b(Block block)
   {
      if (block.isLeaves(CommonProxy.getProxy().getWorld(), 0, 0, 0))
      {
         return true;
      }

      return false;
   }

   @Override //getDigSpeed
   public float func_150893_a(ItemStack item, Block block)
   {
      if (block.isLeaves(CommonProxy.getProxy().getWorld(), 0, 0, 0))
      {
         return efficiencyOnProperMaterial + 1;
      }

      return 1.0F;
   }

   //Break leaf block
   @SuppressWarnings({ "rawtypes", "unchecked" })
   @Override
   public boolean onBlockStartBreak(ItemStack item, int X, int Y, int Z, EntityPlayer player)
   {
      World world = player.worldObj;
      Block block = world.getBlock(X,Y,Z);
      int meta = world.getBlockMetadata(X, Y, Z);
      boolean validTarget = false;

      if (block.isLeaves(world, 0, 0, 0))
      {
         if (!world.isRemote)
         {
            //If the Forestry method didn't work, try the vanilla way.
            //Call it once here and it gets called again when it breaks.
            block.dropBlockAsItem(world, X, Y, Z, meta, 0);//drop saplings

            //Stick drop chance 100%
            if (world.rand.nextInt(1) == 0)
            {
               world.spawnEntityInWorld(new EntityItem(world, X + 0.5D, Y + 0.5D, Z + 0.5D, new ItemStack(Items.stick, 1, 0)));
            }
         }
         validTarget = true;
      }

      if (validTarget)
      {
         item.damageItem(1, player);
         if (item.stackSize == 0)
         {
            player.destroyCurrentEquippedItem();
         }
      }
      return false;
   }
}
