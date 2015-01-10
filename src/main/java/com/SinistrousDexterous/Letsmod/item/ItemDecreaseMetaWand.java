package com.SinistrousDexterous.Letsmod.item;

import com.google.common.collect.Sets;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import java.util.Random;
import java.util.Set;

public class ItemDecreaseMetaWand extends ItemLMTool
{
   public static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[]{});

   public ItemDecreaseMetaWand()
   {
      super(0.0f, ToolMaterial.WOOD, blocksEffectiveAgainst);
      this.setUnlocalizedName("metaWand");
      this.setMaxStackSize(1);
      this.setMaxDamage(64);
   }

   @Override
   public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
   {
      if(!world.isRemote)
      {
         boolean flag = false; //default decrease
         MovingObjectPosition target = getMovingObjectPositionFromPlayer(world, player, false);
         int meta= world.getBlockMetadata(target.blockX, target.blockY, target.blockZ);
         Block block = getBlock(target, world);

         if(!block.isAir(world, target.blockX, target.blockY, target.blockZ) && block.canHarvestBlock(player, meta))
         {
            if(flag)
               meta += 1;
            else
               meta -=1;

            world.setBlockMetadataWithNotify(target.blockX, target.blockY, target.blockZ, meta, 2);
            //Flag 1 will cause a block update. Flag 2 will send the change to clients (you almost always want this).
            // Flag 4 prevents the block from being re-rendered, if this is a client world. Flags can be added together.
            if(itemStack.attemptDamageItem(1, new Random()))
            {
               player.destroyCurrentEquippedItem();
            }
            else
            itemStack.damageItem(1, player);
         }
         else if(block.isAir(world, target.blockX, target.blockY, target.blockZ))
         {
            flag = !flag;
         }
      }
      return itemStack;
   }
}
