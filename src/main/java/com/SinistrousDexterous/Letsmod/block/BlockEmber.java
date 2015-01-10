package com.SinistrousDexterous.Letsmod.block;

import com.SinistrousDexterous.Letsmod.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockEmber extends BlockLM
{
   @SideOnly(Side.CLIENT)
   private IIcon blockTop;
   @SideOnly(Side.CLIENT)
   private IIcon blockBottom;
   @SideOnly(Side.CLIENT)
   private IIcon blockInner;

   public BlockEmber()
   {
      super(Material.fire);
      this.setTickRandomly(true);
      this.setBlockName("emberBlock");
      this.setBlockTextureName("emberBlock");
   }

   /**
    * Updates the blocks bounds based on its current state. Args: world, x, y, z
    */
   public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int X, int Y, int Z)
   {
      int meta = blockAccess.getBlockMetadata(X, Y, Z);
      float pix=0.0625F; // 1/16
      float pixAfterUsing = (float) meta / 16.0F;
                           //X   , Y  , Z
      this.setBlockBounds( pix, 0.0F,pix, //Start
                            1.0F - pix, 1.0F - pixAfterUsing, 1.0F - pix);//End
   }

   /**
    * Sets the block's bounds for rendering it as an item
    */
   public void setBlockBoundsForItemRender()
   {
      float pix=0.0625F; // 1/16
      this.setBlockBounds(pix, 0.0F, pix,
                          1.0F - pix, 1.0F, 1.0F - pix);
   }

   /**
    * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
    * cleared to be reused)
    */
   public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int X, int Y, int Z)
   {
      int meta = world.getBlockMetadata(X, Y, Z);
      float pix=0.0625F; // 1/16
      float pixAfterUsing = (float)meta / 16.0F;
      return AxisAlignedBB.getBoundingBox
            (
            (double)((float)(X) + pix), (double)Y , (double)((float)(Z) + pix),
            (double)((float)(X + 1) - pix)   , (double)((float)Y + 1.0F - pixAfterUsing), (double)((float)(Z + 1) - pix)
            );
   }

   /**
    * Returns the bounding box of the wired rectangular prism to render.
    */
   @SideOnly(Side.CLIENT)
   public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int X, int Y, int Z)
   {
      int meta = world.getBlockMetadata(X, Y, Z);
      float pix=0.0625F; // 1/16
      float pixAfterUsing = (float)meta / 16.0F;
      return AxisAlignedBB.getBoundingBox
            (
            (double)((float)(X) + pix), (double)Y, (double)((float)(Z) + pix),
            (double)((float)(X + 1) - pix), (double)((float)(Y) + 1 -pixAfterUsing), (double)((float)(Z + 1) - pix)
            );
   }

   /**
    * Gets the block's texture. Args: side, meta
    */
   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta)
   {
      return side == 0 ? this.blockBottom :
              (side >1 && side < 6 ? this.blockIcon :
              (meta > 0 && side == 1 ? this.blockInner : this.blockTop));
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister iconRegister)
   {
      this.blockIcon = iconRegister.registerIcon(this.formattedTextureName() + "_side");
      this.blockInner = iconRegister.registerIcon(this.formattedTextureName() + "_inner");
      this.blockTop = iconRegister.registerIcon(this.formattedTextureName() + "_top");
      this.blockBottom = iconRegister.registerIcon(this.formattedTextureName() + "bottom");
   }
   protected String formattedTextureName()
   {
      return String.format("%s%s", Reference.RESOURCE_PREFIX, this.getTextureName());
   }

   /**
    * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
    */
   public boolean renderAsNormalBlock()
   {
      return false;
   }

   /**
    * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
    * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
    */
   public boolean isOpaqueCube()
   {
      return false;
   }

   /**
    * Called upon block activation (right click on the block.)
    */
   public boolean onBlockActivated(World world, int X, int Y, int Z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
   {
      this.attemptUseBlock(world, X, Y, Z, player);
      return true;
   }

   /**
    * Called when a player hits the block. Args: world, x, y, z, player
    */
   public void onBlockClicked(World world, int X, int Y, int Z, EntityPlayer player)
   {
      this.attemptUseBlock(world, X, Y, Z, player);
   }

   private void attemptUseBlock(World world, int X, int Y, int Z, EntityPlayer player)
   {
         int meta = world.getBlockMetadata(X, Y, Z) + 1;//update cake meta after eating

         if (meta >= 16)
         {
            world.setBlockToAir(X, Y, Z);
         }
         else
         {
            world.setBlockMetadataWithNotify(X, Y, Z, meta, 2);//2 as flag?
         }
   }

   /**
    * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
    */
   public boolean canPlaceBlockAt(World world, int X, int Y, int Z)
   {
      return !super.canPlaceBlockAt(world, X, Y, Z) ? false : this.canBlockStay(world, X, Y, Z);
   }

   /**
    * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
    * their own) Args: x, y, z, neighbor Block
    */
   public void onNeighborBlockChange(World world, int X, int Y, int Z, Block block)
   {
      if (!this.canBlockStay(world, X, Y, Z))
      {
         int meta = world.getBlockMetadata(X, Y, Z);
         for(int i=Y; i>0; i--)
         {
            if(this.canBlockStay(world, X, i, Z))
            {
               world.setBlockMetadataWithNotify(X, Y, Z, meta, 2);
            }
         }
         world.setBlockToAir(X, Y, Z);
      }
   }

   /**
    * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
    */
   public boolean canBlockStay(World world, int X, int Y, int Z)
   {
      return world.getBlock(X, Y - 1, Z).getMaterial().isSolid();
   }

   /**
    * Returns the quantity of items to drop on block destruction.
    */
   public int quantityDropped(Random rand)
   {
      return 0;
   }

   public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
   {
      return null;
   }

   /**
    * Gets an item for the block being called on. Args: world, x, y, z
    */
   @SideOnly(Side.CLIENT)
   public Item getItem(World world, int X, int Y, int Z)
   {
      return Items.cake;
   }
}