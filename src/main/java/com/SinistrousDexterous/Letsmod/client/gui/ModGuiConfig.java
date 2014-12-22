package com.SinistrousDexterous.Letsmod.client.gui;


import com.SinistrousDexterous.Letsmod.handler.ConfigurationHandler;
import com.SinistrousDexterous.Letsmod.reference.Reference;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ModGuiConfig extends GuiConfig
{
   public ModGuiConfig(GuiScreen guiScreen)
   {
      super(guiScreen,
            new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
            Reference.MOD_ID,
            false,//configID,
            false,// allRequireWorldRestart,
            GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString())); // allRequireMcRestart, title);
   }
}
