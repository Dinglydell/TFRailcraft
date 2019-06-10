package dinglydell.TFRailcraft;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.bioxx.tfc.Items.Tools.ItemCustomBucket;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCFluids;
import com.bioxx.tfc.api.TFCItems;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dinglydell.TFRailcraft.item.TFRItems;
import mods.railcraft.common.fluids.Fluids;
import mods.railcraft.common.fluids.RailcraftFluids;
import mods.railcraft.common.items.RailcraftItem;
import mods.railcraft.common.util.crafting.CokeOvenCraftingManager;
import mods.railcraft.common.util.crafting.RollingMachineCraftingManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

@Mod(modid = TFRailcraft.MODID, version = TFRailcraft.VERSION, dependencies="required-after:terrafirmacraft")//;required-after:railcraft")
public class TFRailcraft
{
    public static final String MODID = "TFRailcraft";
    public static final String VERSION = "1.0";
    
    @EventHandler
	public void preInit(FMLPreInitializationEvent event) {
    	replaceWater();
    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	registerItems();
    	addRCRecipes();
    }
	private void addRCRecipes() {
		// logs -> charcoal
		CokeOvenCraftingManager.getInstance().addRecipe(new ItemStack(TFCItems.logs), false, false, new ItemStack(TFCItems.coal, 1, 1), new FluidStack(Fluids.CREOSOTE.get(), 250), 1800);
		// coal -> coke. maybe you want coke for steam engines?
		//CokeOvenCraftingManager.getInstance().addRecipe(new ItemStack(TFCItems.coal, 1, 0), true, false, new ItemStack(mods.railcraft.common.items., 1, 1), new FluidStack(Fluids.CREOSOTE.get(), 250), 1800);
		
		
		RollingMachineCraftingManager.getInstance().addRecipe(RailcraftItem.rail.getStack(8, 4), new Object[] { "s s", "s s", "s s", 's', TFCItems.blackSteelIngot});
		
		RollingMachineCraftingManager.getInstance().addRecipe(RailcraftItem.rail.getStack(8, 1), new Object[] { "trg", "trg", "trg", 't', RailcraftItem.rail.getStack(1, 0), 'r', Items.redstone, 'g', TFCItems.goldIngot});

		RollingMachineCraftingManager.getInstance().addRecipe(RailcraftItem.rail.getStack(8, 3), new Object[] { "trg", "trg", "trg", 't', RailcraftItem.rail.getStack(1, 4), 'r', Items.redstone, 'g', TFCItems.goldIngot});
		
	}
	private void registerItems() {
		TFRItems.woodenBucketCreosote = new ItemStack(new ItemCustomBucket(
				Blocks.air).setUnlocalizedName("woodenBucketCreosote"), 1);
		GameRegistry.registerItem(TFRItems.woodenBucketCreosote.getItem(),
				"woodenBucketCreosote");
		
		FluidContainerRegistry.registerFluidContainer(Fluids.CREOSOTE.get(),
				TFRItems.woodenBucketCreosote,
				new ItemStack(TFCItems.woodenBucketEmpty));
		
	}
	
	private void replaceWater() {

		try { // this, as you may well have noticed, is a bit of a hack
			Field water = FluidRegistry.class.getDeclaredField("WATER");
			finalField(water);
			water.set(null, TFCFluids.FRESHWATER);

			Field tag = Fluids.class.getDeclaredField("tag");
			tag.setAccessible(true);
			finalField(tag);
			tag.set(Fluids.WATER, "freshwater");

			Field waterBlock = Blocks.class.getDeclaredField("water");
			finalField(waterBlock);
			waterBlock.set(null, TFCBlocks.freshWaterStationary);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	private void finalField(Field field) throws Exception {
		Field modifiers = Field.class.getDeclaredField("modifiers");
		modifiers.setAccessible(true);
		modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);

	}
}
    
  
