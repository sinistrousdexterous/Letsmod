package com.SinistrousDexterous.Letsmod.item;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.*;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.util.Set;

public class ItemLMTool extends ItemLM
{

      private Set BlocksEffectiveAgainst;
      protected float efficiencyOnProperMaterial = 4.0F;
      /** Damage versus entities. */
      private float damageVsEntity;
      /** The material this tool is made from. */
      protected Item.ToolMaterial toolMaterial;
      private static final String __OBFID = "CL_00000019";

      protected ItemLMTool(float baseDamage, Item.ToolMaterial material, Set BlocksEffectiveAgainst)
      {
         this.toolMaterial = material;
         this.BlocksEffectiveAgainst = BlocksEffectiveAgainst;
         this.maxStackSize = 1;
         this.setMaxDamage(material.getMaxUses());
         this.efficiencyOnProperMaterial = material.getEfficiencyOnProperMaterial();
         this.damageVsEntity = baseDamage + material.getDamageVsEntity();
         this.setCreativeTab(CreativeTabs.tabTools);
         /*
         if (this instanceof ItemPickaxe)
         {
            toolClass = "pickaxe";
         }
         else if (this instanceof ItemAxe)
         {
            toolClass = "axe";
         }
         else if (this instanceof ItemSpade)
         {
            toolClass = "shovel";
         }
         */
      }

      public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
      {
         return this.BlocksEffectiveAgainst.contains(p_150893_2_) ? this.efficiencyOnProperMaterial : 1.0F;
      }

      /**
       * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
       * the damage on the stack.
       */
      public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
      {
         p_77644_1_.damageItem(2, p_77644_3_);
         return true;
      }

      public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
      {
         if ((double)p_150894_3_.getBlockHardness(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D)
         {
            p_150894_1_.damageItem(1, p_150894_7_);
         }

         return true;
      }

      /**
       * Returns True is the item is renderer in full 3D when hold.
       */
      @SideOnly(Side.CLIENT)
      public boolean isFull3D()
      {
         return true;
      }

      public Item.ToolMaterial func_150913_i()
      {
         return this.toolMaterial;
      }

      /**
       * Return the enchantability factor of the item, most of the time is based on material.
       */
      public int getItemEnchantability()
      {
         return this.toolMaterial.getEnchantability();
      }

      /**
       * Return the name for this tool's material.
       */
      public String getToolMaterialName()
      {
         return this.toolMaterial.toString();
      }

      /**
       * Return whether this item is repairable in an anvil.
       */
      public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_)
      {
         ItemStack mat = this.toolMaterial.getRepairItemStack();
         if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, p_82789_2_, false)) return true;
         return super.getIsRepairable(p_82789_1_, p_82789_2_);
      }

      /**
       * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
       */
      public Multimap getItemAttributeModifiers()
      {
         Multimap multimap = super.getItemAttributeModifiers();
         multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", (double)this.damageVsEntity, 0));
         return multimap;
      }

      /*===================================== FORGE START =================================*/
      private String toolClass;
      @Override
      public int getHarvestLevel(ItemStack stack, String toolClass)
      {
         int level = super.getHarvestLevel(stack, toolClass);
         if (level == -1 && toolClass != null && toolClass.equals(this.toolClass))
         {
            return this.toolMaterial.getHarvestLevel();
         }
         else
         {
            return level;
         }
      }

      @Override
      public Set<String> getToolClasses(ItemStack stack)
      {
         return toolClass != null ? ImmutableSet.of(toolClass) : super.getToolClasses(stack);
      }

      @Override
      public float getDigSpeed(ItemStack stack, Block block, int meta)
      {
         if (ForgeHooks.isToolEffective(stack, block, meta))
         {
            return efficiencyOnProperMaterial;
         }
         return super.getDigSpeed(stack, block, meta);
      }
    /*===================================== FORGE END =================================*/

   public Block getBlock(MovingObjectPosition target, World world)
   {
      if (target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
      {
         int x = target.blockX;
         int y = target.blockY;
         int z = target.blockZ;
         Block block = world.getBlock(x, y, z);

         return block;
      }
      return null;
   }
}
