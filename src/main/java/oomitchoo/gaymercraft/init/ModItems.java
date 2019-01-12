package oomitchoo.gaymercraft.init;

import net.minecraft.item.Item;
import oomitchoo.gaymercraft.item.ItemRainbowStar;
import oomitchoo.gaymercraft.item.debug.DebugTool;

/**
 * Created by oOMitchOo on 21.11.2018.
 */

public final class ModItems {
    public static final Item RAINBOW_STAR = new ItemRainbowStar("rainbowStar", "rainbow_star");

    // ============================ TESTING / DEBUGGING HELPER ============================
    public static final Item DEBUG_TOOL = new DebugTool("debugTool", "debug_tool");
}
