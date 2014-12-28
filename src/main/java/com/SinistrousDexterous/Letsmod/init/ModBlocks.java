package com.SinistrousDexterous.Letsmod.init;

import com.SinistrousDexterous.Letsmod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import com.SinistrousDexterous.Letsmod.block.BlockLM;
import com.SinistrousDexterous.Letsmod.block.BlockSalt;


@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
   public static final BlockLM saltBlock = new BlockSalt();

   public static void init()
   {
      GameRegistry.registerBlock(saltBlock, "saltBlock");
   }

}
