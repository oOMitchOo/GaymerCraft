package oomitchoo.gaymercraft.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.IConfigElement;
import oomitchoo.gaymercraft.helper.handlers.ConfigHandler;
import oomitchoo.gaymercraft.reference.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oOMitchOo on 07.12.2018.
 */
public class GuiModConfig extends GuiConfig {
    public GuiModConfig(GuiScreen guiScreen) {
        super(guiScreen,
                getConfigElements(),
                Reference.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }

    private static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        list.add(new DummyCategoryElement("Colored Water", "gc.configgui.ctgy.coloredWater", ColoredWaterEntry.class));
        list.add(new DummyCategoryElement("Unicorn", "gc.configgui.ctgy.unicorn", UnicornEntry.class));
        return list;
    }

    public static class ColoredWaterEntry extends GuiConfigEntries.CategoryEntry
    {
        public ColoredWaterEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
        {
            super(owningScreen, owningEntryList, prop);
        }

        @Override
        protected GuiScreen buildChildScreen()
        {
            // This GuiConfig object specifies the configID of the object and as such will force-save when it is closed. The parent
            // GuiConfig object's entryList will also be refreshed to reflect the changes.
            return new GuiConfig(this.owningScreen,
                    (new ConfigElement(ConfigHandler.config.getCategory(Reference.Config.CATEGORY_COLORED_WATER))).getChildElements(),
                    this.owningScreen.modID, Reference.Config.CATEGORY_COLORED_WATER, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
                    this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
                    GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
        }
    }

    public static class UnicornEntry extends GuiConfigEntries.CategoryEntry
    {
        public UnicornEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
        {
            super(owningScreen, owningEntryList, prop);
        }

        @Override
        protected GuiScreen buildChildScreen()
        {
            // This GuiConfig object specifies the configID of the object and as such will force-save when it is closed. The parent
            // GuiConfig object's entryList will also be refreshed to reflect the changes.
            return new GuiConfig(this.owningScreen,
                    (new ConfigElement(ConfigHandler.config.getCategory(Reference.Config.CATEGORY_UNICORN))).getChildElements(),
                    this.owningScreen.modID, Reference.Config.CATEGORY_UNICORN, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
                    this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
                    GuiConfig.getAbridgedConfigPath(ForgeModContainer.getConfig().toString()));
        }
    }
}