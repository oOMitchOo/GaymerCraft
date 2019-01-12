package oomitchoo.gaymercraft.block.modsupport.botania;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;
import oomitchoo.gaymercraft.helper.LogHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by oOMitchOo on 06.01.2019.
 * Sort into groups with same resistance, Material and SoundType
 * (can't override methods to make ONE block with different of those).
 * Each group with # (max 4) members, must be neighbours in index
 * and have meta 0 to (#-1)
 */
public enum EnumVariantSlabBotania implements IStringSerializable {
    // Slab0
    LIVINGWOOD(0, 0, 0, 2.0F, MapColor.WOOD, Material.WOOD, (10F/3F), SoundType.WOOD, "livingwood", new ModelResourceLocation("botania:livingwood0slab#half=bottom"), new ModelResourceLocation("botania:livingwood#variant=default")),
    LIVINGWOOD_PLANK(1, 1, 1, 2.0F, MapColor.WOOD, Material.WOOD, (10F/3F), SoundType.WOOD, "livingwood_planks", "livingwoodPlanks", new ModelResourceLocation("botania:livingwood1slab#half=bottom"), new ModelResourceLocation("botania:livingwood#variant=planks")),
    DREAMWOOD(2, 2, 4, 2.0F, MapColor.WOOD, Material.WOOD, (10F/3F), SoundType.WOOD, "dreamwood", new ModelResourceLocation("botania:dreamwood0slab#half=bottom"), new ModelResourceLocation("botania:dreamwood#variant=default")),
    DREAMWOOD_PLANK(3, 3, 5, 2.0F, MapColor.WOOD, Material.WOOD, (10F/3F), SoundType.WOOD, "dreamwood_planks", "dreamwoodPlanks", new ModelResourceLocation("botania:dreamwood1slab#half=bottom"), new ModelResourceLocation("botania:dreamwood#variant=planks")),
    // Slab1
    LIVINGROCK(4, 0, 2, 2.0F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "livingrock", new ModelResourceLocation("botania:livingrock0slab#half=bottom"), new ModelResourceLocation("botania:livingrock#variant=default")),
    LIVINGROCK_BRICK(5, 1, 3, 2.0F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "livingrock_brick", "linvingrockBrick", new ModelResourceLocation("botania:livingrock1slab#half=bottom"), new ModelResourceLocation("botania:livingrock#variant=brick")),
    YELLOW_PAVEMENT(6, 2, 41, 2.0F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "yellow_pavement", "yellowPavement", new ModelResourceLocation("botania:pavement4slab#half=bottom"), new ModelResourceLocation("botania:pavement#color=yellow")),
    GREEN_PAVEMENT(7, 3, 42, 2.0F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "green_pavement", "greenPavement", new ModelResourceLocation("botania:pavement5slab#half=bottom"), new ModelResourceLocation("botania:pavement#color=green")),
    // Slab2
    SMOKEY_QUARTZ(8, 0, 6, 0.8F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "smokey_quartz", "smokeyQuartz", new ModelResourceLocation("botania:quartzslabdarkhalf#half=bottom"), new ModelResourceLocation("botania:quartztypedark#variant=normal")),
    MANA_QUARTZ(9, 1, 7, 0.8F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "mana_quartz", "manaQuartz", new ModelResourceLocation("botania:quartzslabmanahalf#half=bottom"), new ModelResourceLocation("botania:quartztypemana#variant=normal")),
    BLAZE_QUARTZ(10, 2, 8, 0.8F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "blaze_quartz", "blazeQuartz", new ModelResourceLocation("botania:quartzslabblazehalf#half=bottom"), new ModelResourceLocation("botania:quartztypeblaze#variant=normal")),
    LAVENDER_QUARTZ(11, 3, 9, 0.8F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "lavender_quartz", "lavenderQuartz", new ModelResourceLocation("botania:quartzslablavenderhalf#half=bottom"), new ModelResourceLocation("botania:quartztypelavender#variant=normal")),
    // Slab3
    REDQUARTZ(12, 0, 10, 0.8F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "redquartz", new ModelResourceLocation("botania:quartzslabredhalf#half=bottom"), new ModelResourceLocation("botania:quartztypered#variant=normal")),
    ELVEN_QUARTZ(13, 1, 11, 0.8F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "elven_quartz", "elvenQuartz", new ModelResourceLocation("botania:quartzslabelfhalf#half=bottom"), new ModelResourceLocation("botania:quartztypeelf#variant=normal")),
    SUNNY_QUARTZ(14, 2, 12, 0.8F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "sunny_quartz", "sunnyQuartz", new ModelResourceLocation("botania:quartzslabsunnyhalf#half=bottom"), new ModelResourceLocation("botania:quartztypesunny#variant=normal")),
    SHIMMERROCK(15, 3, 43, 2.0F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "shimmerrock", new ModelResourceLocation("botania:shimmerrock0slab#half=bottom"), new ModelResourceLocation("botania:shimmerrock#normal")),
    // Slab4
    METAMORPHIC_FOREST_STONE(16, 0, 13, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "forest_stone", "forestStone", new ModelResourceLocation("botania:biomestonea0slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=forest")),
    METAMORPHIC_PLAINS_STONE(17, 1, 14, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "plains_stone", "plainsStone", new ModelResourceLocation("botania:biomestonea1slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=plains")),
    METAMORPHIC_MOUNTAIN_STONE(18, 2, 15, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "mountain_stone", "mountainStone", new ModelResourceLocation("botania:biomestonea2slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=mountain")),
    METAMORPHIC_FUNGAL_STONE(19, 3, 16, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "fungal_stone", "fungalStone", new ModelResourceLocation("botania:biomestonea3slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=fungal")),
    // Slab5
    METAMORPHIC_SWAMP_STONE(20, 0, 17, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "swamp_stone", "swampStone", new ModelResourceLocation("botania:biomestonea4slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=swamp")),
    METAMORPHIC_DESERT_STONE(21, 1, 18, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "desert_stone", "desertStone", new ModelResourceLocation("botania:biomestonea5slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=desert")),
    METAMORPHIC_TAIGA_STONE(22, 2, 19, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "taiga_stone", "taigaStone", new ModelResourceLocation("botania:biomestonea6slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=taiga")),
    METAMORPHIC_MESA_STONE(23, 3, 20, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "mesa_stone", "mesaStone", new ModelResourceLocation("botania:biomestonea7slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=mesa")),
    // Slab6
    METAMORPHIC_FOREST_COBBLESTONE(24, 0, 21, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "forest_cobblestone", "forestCobblestone", new ModelResourceLocation("botania:biomestonea8slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=forest_cobble")),
    METAMORPHIC_PLAINS_COBBLESTONE(25, 1, 22, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "plains_cobblestone", "plainsCobblestone", new ModelResourceLocation("botania:biomestonea9slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=plains_cobble")),
    METAMORPHIC_MOUNTAIN_COBBLESTONE(26, 2, 23, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "mountain_cobblestone", "mountainCobblestone", new ModelResourceLocation("botania:biomestonea10slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=mountain_cobble")),
    METAMORPHIC_FUNGAL_COBBLESTONE(27, 3, 24, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "fungal_cobblestone", "fungalCobblestone", new ModelResourceLocation("botania:biomestonea11slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=fungal_cobble")),
    // Slab7
    METAMORPHIC_SWAMP_COBBLESTONE(28, 0, 25, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "swamp_cobblestone", "swampCobblestone", new ModelResourceLocation("botania:biomestonea12slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=swamp_cobble")),
    METAMORPHIC_DESERT_COBBLESTONE(29, 1, 26, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "desert_cobblestone", "desertCobblestone", new ModelResourceLocation("botania:biomestonea13slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=desert_cobble")),
    METAMORPHIC_TAIGA_COBBLESTONE(30, 2, 27, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "taiga_cobblestone", "taigaCobblestone", new ModelResourceLocation("botania:biomestonea14slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=taiga_cobble")),
    METAMORPHIC_MESA_COBBLESTONE(31, 3, 28, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "mesa_cobblestone", "mesaCobblestone", new ModelResourceLocation("botania:biomestonea15slab#half=bottom"), new ModelResourceLocation("botania:biomestonea#variant=mesa_cobble")),
    // Slab8
    METAMORPHIC_FOREST_BRICK(32, 0, 29, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "forest_brick", "forestBrick", new ModelResourceLocation("botania:biomestoneb0slab#half=bottom"), new ModelResourceLocation("botania:biomestoneb#variant=forest")),
    METAMORPHIC_PLAINS_BRICK(33, 1, 30, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "plains_brick", "plainsBrick", new ModelResourceLocation("botania:biomestoneb1slab#half=bottom"), new ModelResourceLocation("botania:biomestoneb#variant=plains")),
    METAMORPHIC_MOUNTAIN_BRICK(34, 2, 31, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "mountain_brick", "mountainBrick", new ModelResourceLocation("botania:biomestoneb2slab#half=bottom"), new ModelResourceLocation("botania:biomestoneb#variant=mountain")),
    METAMORPHIC_FUNGAL_BRICK(35, 3, 32, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "fungal_brick", "fungalBrick", new ModelResourceLocation("botania:biomestoneb3slab#half=bottom"), new ModelResourceLocation("botania:biomestoneb#variant=fungal")),
    // Slab9
    METAMORPHIC_SWAMP_BRICK(36, 0, 33, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "swamp_brick", "swampBrick", new ModelResourceLocation("botania:biomestoneb4slab#half=bottom"), new ModelResourceLocation("botania:biomestoneb#variant=swamp")),
    METAMORPHIC_DESERT_BRICK(37, 1, 34, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "desert_brick", "desertBrick", new ModelResourceLocation("botania:biomestoneb5slab#half=bottom"), new ModelResourceLocation("botania:biomestoneb#variant=desert")),
    METAMORPHIC_TAIGA_BRICK(38, 2, 35, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "taiga_brick", "taigaBrick", new ModelResourceLocation("botania:biomestoneb6slab#half=bottom"), new ModelResourceLocation("botania:biomestoneb#variant=taiga")),
    METAMORPHIC_MESA_BRICK(39, 3, 36, 1.5F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "mesa_brick", "mesaBrick", new ModelResourceLocation("botania:biomestoneb7slab#half=bottom"), new ModelResourceLocation("botania:biomestoneb#variant=mesa")),
    // Slab10
    WHITE_PAVEMENT(40, 0, 37, 2.0F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "white_pavement", "whitePavement", new ModelResourceLocation("botania:pavement0slab#half=bottom"), new ModelResourceLocation("botania:pavement#color=white")),
    BLACK_PAVEMENT(41, 1, 38, 2.0F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "black_pavement", "blackPavement", new ModelResourceLocation("botania:pavement1slab#half=bottom"), new ModelResourceLocation("botania:pavement#color=black")),
    BLUE_PAVEMENT(42, 2, 39, 2.0F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "blue_pavement", "bluePavement", new ModelResourceLocation("botania:pavement2slab#half=bottom"), new ModelResourceLocation("botania:pavement#color=blue")),
    RED_PAVEMENT(43, 3, 40, 2.0F, MapColor.STONE, Material.ROCK, 10F, SoundType.STONE, "red_pavement", "redPavement", new ModelResourceLocation("botania:pavement3slab#half=bottom"), new ModelResourceLocation("botania:pavement#color=red")),
    // Slab11
    SHIMMERWOOD_PLANK(44, 0, 44, 2.0F, MapColor.WOOD, Material.WOOD, 10F, SoundType.WOOD, "shimmerwood_planks", "shimmerwoodPlanks", new ModelResourceLocation("botania:shimmerwoodplanks0slab#half=bottom"), new ModelResourceLocation("botania:shimmerwoodplanks#normal"));

    private static final EnumVariantSlabBotania[] INDEX_LOOKUP = new EnumVariantSlabBotania[values().length];
    private static final EnumVariantSlabBotania[] ORDER_LOOKUP = new EnumVariantSlabBotania[values().length];
    private final int index;
    private final int meta;
    private final int modelLoadingIndex;
    private final float hardness;
    private final MapColor mapColor;
    private final Material material;
    private final float resistance;
    private final SoundType soundType;
    @Nullable
    private ItemBlock itemBlock;
    private final String name;
    private final String unlocalizedName;
    private final ModelResourceLocation locationModelSlab;
    private final ModelResourceLocation locationModelDouble;

    EnumVariantSlabBotania(int index, int meta, int modelLoadingIndex, float hardness, MapColor mColor, Material material, float resistance, SoundType soundType, String name, ModelResourceLocation locationModelSlab, ModelResourceLocation locationModelDouble)
    {
        this(index, meta, modelLoadingIndex, hardness, mColor, material, resistance, soundType, name, name, locationModelSlab, locationModelDouble);
    }

    EnumVariantSlabBotania(int index, int meta, int modelLoadingIndex, float hardness, MapColor mColor, Material material, float resistance, SoundType soundType, String name, String unlName, ModelResourceLocation locationModelSlab, ModelResourceLocation locationModelDouble)
    {
        this.index = index;
        this.meta = meta;
        this.modelLoadingIndex = modelLoadingIndex;
        this.hardness = hardness;
        this.mapColor = mColor;
        this.material = material;
        this.resistance = resistance;
        this.soundType = soundType;
        this.name = name;
        this.unlocalizedName = unlName;
        this.locationModelSlab = locationModelSlab;
        this.locationModelDouble = locationModelDouble;
    }

    // I know... an enum is a collection of constants. That's why I only allow setting, when it is null. (so only once)
    public void setItemBlock(ItemBlock itemBlock) throws Exception {
        if (this.itemBlock == null)
            this.itemBlock = itemBlock;
        else throw new Exception("This EnumVariant " + this.name + " has already an ItemBlock: " + itemBlock.getUnlocalizedName());
    }

    @Nullable
    public ItemBlock getItemBlock() { return this.itemBlock; }

    public int getIndex() { return this.index; }

    public int getMetadata()
    {
        return this.meta;
    }

    public int getModelLoadingIndex() { return this.modelLoadingIndex; }

    public float getHardness() { return this.hardness; }

    @Nonnull
    public MapColor getMapColor()
    {
        return this.mapColor;
    }

    @Nonnull
    public Material getMaterial() { return this.material; }

    public float getResistance() { return this.resistance; }

    @Nonnull
    public SoundType getSoundType() { return this.soundType; }

    @Nonnull
    public String toString()
    {
        return this.name;
    }

    @Nonnull
    public ModelResourceLocation getLocationModelSlab() { return this.locationModelSlab; }

    @Nonnull
    public ModelResourceLocation getLocationModelDouble() { return this.locationModelDouble; }

    public static int getNumberOfVariantsWithMetaZero() {
        int number = 0;
        for (EnumVariantSlabBotania enumType : values()) {
            if (enumType.getMetadata() == 0)
                number++;
        }
        return number;
    }

    @Nonnull
    public static EnumVariantSlabBotania byIndex(int index)
    {
        if (index < 0 || index >= INDEX_LOOKUP.length)
        {
            index = 0;
        }

        return INDEX_LOOKUP[index];
    }

    public static int getLookupLength() { return INDEX_LOOKUP.length; }

    public static EnumVariantSlabBotania byModelOrderIndex(int index) {
        if (index < 0 || index >= ORDER_LOOKUP.length)
        {
            LogHelper.error("modelLoadingIndex " + index + " out of bound. Something went terribly wrong!");
            return ORDER_LOOKUP[0];
        }

        return ORDER_LOOKUP[index];
    }

    @Nullable
    public static EnumVariantSlabBotania byName(String name)
    {
        for (EnumVariantSlabBotania enumType : values()) {
            if (enumType.getName().equals(name))
                return enumType;
        }
        return null; // if not found, return null
    }

    @Nonnull
    public String getName()
    {
        return this.name;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    static
    {
        for (EnumVariantSlabBotania enumtype : values())
        {
            INDEX_LOOKUP[enumtype.getIndex()] = enumtype;
        }
    }

    static
    {
        for (int i = 0; i < values().length; i++) {
            for (EnumVariantSlabBotania enumtype : values())
            {
                if (enumtype.modelLoadingIndex == i)
                    ORDER_LOOKUP[i] = enumtype;
            }
            if (ORDER_LOOKUP[i] == null)
                LogHelper.error("Couldn't find a EnumVariantSlab with modelLoadingIndex " + i);
        }
    }
}