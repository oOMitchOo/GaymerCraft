package oomitchoo.gaymercraft.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import oomitchoo.gaymercraft.helper.handlers.ConfigHandler;
import oomitchoo.gaymercraft.reference.Reference;

/**
 * Created by oOMitchOo on 07.12.2018.
 */
public class GuiModConfig extends GuiConfig {
    public GuiModConfig(GuiScreen guiScreen) {
        super(guiScreen,
                new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                Reference.MOD_ID,
                true,
                true,
                GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }
}