package dinglydell.tfsanity;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import dinglydell.tfsanity.api.SanityEffectHandler;
import dinglydell.tfsanity.command.CommandSanity;
import dinglydell.tfsanity.event.PlayerEventHandler;
import dinglydell.tfsanity.event.SanityEventHandler;
import dinglydell.tfsanity.network.PacketSanity;
import dinglydell.tfsanity.network.PacketSanityHandler;
import dinglydell.tfsanity.player.BlockHallucinationEffect;
import dinglydell.tfsanity.player.GhastlyHalucinationEffect;
import dinglydell.tfsanity.player.StagedMessageEffect;
import dinglydell.tfsanity.player.StrangePresenceEffect;
import dinglydell.tfsanity.player.StrangeSoundsEffect;

@Mod(modid = TFSanity.MODID, version = TFSanity.VERSION)//, dependencies="required-after:terrafirmacraft")
public class TFSanity
{
    public static final String MODID = "TFSanity";
    public static final String VERSION = "1.0";
    
    public static SimpleNetworkWrapper snw;
    @EventHandler
	public void preInit(FMLPreInitializationEvent event) {

    	registerPacketHandlers();
    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	registerEventHandlers();
    	registerSanityEffects();
    }
    
    private void registerSanityEffects() {
		SanityEffectHandler seh = SanityEffectHandler.getInstance();
		//seh.registerSanityEffect(new StrangePresenceEffect());
		seh.registerSanityEffect(new StagedMessageEffect("strange_presence", StrangePresenceEffect.intial_presence_messages, StrangePresenceEffect.ongoing_presence_messages, StrangePresenceEffect.final_presence_messages));
		seh.registerSanityEffect(new StrangeSoundsEffect());
		seh.registerSanityEffect(new BlockHallucinationEffect(Blocks.netherrack, 1));
		seh.registerSanityEffect(new BlockHallucinationEffect(Blocks.soul_sand, 0.5f));
		seh.registerSanityEffect(new BlockHallucinationEffect(Blocks.end_stone, 0.5f));
		seh.registerSanityEffect(new BlockHallucinationEffect(Blocks.sponge, 0.1f));
	//	seh.registerSanityEffect(new GhastlyHalucinationEffect());
		
		
	}
	private void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
		FMLCommonHandler.instance().bus().register(new SanityEventHandler());

    }
    
    private void registerPacketHandlers() {
		snw = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
		snw.registerMessage(PacketSanityHandler.class,
				PacketSanity.class,
				0,
				Side.CLIENT);
    }
    
    @EventHandler
	public void serverLoad(FMLServerStartingEvent event) {

		event.registerServerCommand(new CommandSanity());
}
}
