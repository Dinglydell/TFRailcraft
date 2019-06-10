package dinglydell.TFRailcraft;

import com.bioxx.tfc.Items.Tools.ItemCustomBucket;
import com.bioxx.tfc.api.TFCItems;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dinglydell.TFRailcraft.item.TFRItems;
import mods.railcraft.common.fluids.Fluids;
import mods.railcraft.common.fluids.RailcraftFluids;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

@Mod(modid = TFRailcraft.MODID, version = TFRailcraft.VERSION, dependencies="required-after:terrafirmacraft")//;required-after:railcraft")
public class TFRailcraft
{
    public static final String MODID = "TFRailcraft";
    public static final String VERSION = "1.0";
    
    @EventHandler
	public void preInit(FMLPreInitializationEvent event) {

    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	registerItems();
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
}
    
  
