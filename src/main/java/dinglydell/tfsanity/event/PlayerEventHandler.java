package dinglydell.tfsanity.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import dinglydell.tfsanity.player.PlayerSanityDataExtendedProps;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.EnumStatus;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;

public class PlayerEventHandler {
	
	/** Sanity is changed by number of ticks slept multiplied by this */
	private static final float SLEEP_MULTIPLIER = 0.2f;
	/** Sleep longer than this has no effect */
	private static final int MAX_SLEEP = 10000;
	@SubscribeEvent
	public void playerClone(PlayerEvent.Clone clone) {
		PlayerSanityDataExtendedProps psdepNew = PlayerSanityDataExtendedProps
				.get(clone.entityPlayer);
		PlayerSanityDataExtendedProps psdepOld = PlayerSanityDataExtendedProps
				.get(clone.original);
		psdepNew.setInsanity(psdepOld.getInsanity());

	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {

		if (event.entity instanceof EntityPlayer) {

			event.entity
					.registerExtendedProperties(PlayerSanityDataExtendedProps.SANITYDATA,
							new PlayerSanityDataExtendedProps(
									(EntityPlayer) event.entity));
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPlayerMP && !event.world.isRemote) {
			PlayerSanityDataExtendedProps psdep = PlayerSanityDataExtendedProps
					.get((EntityPlayer) event.entity);
			psdep.sendPacket();
		}
	}
	
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event) {
		if (event.entity instanceof EntityPlayerMP && !event.entity.worldObj.isRemote) {
			PlayerSanityDataExtendedProps psdep = PlayerSanityDataExtendedProps
					.get((EntityPlayer) event.entity);
			//gain some insanity on death
			psdep.addInsanity(1000);
		}
	}
	@SubscribeEvent
	public void onPlayerSleep(PlayerSleepInBedEvent event) {
		PlayerSanityDataExtendedProps psdep = PlayerSanityDataExtendedProps
				.get((EntityPlayer) event.entity);
		if(psdep.isSleepBlocked()) {
			event.result = EnumStatus.OTHER_PROBLEM;
			event.entityPlayer.addChatComponentMessage(new ChatComponentText("You don't feel like sleeping right now..."));
		}
		psdep.setSleepTime(event.entity.worldObj.getWorldTime());
	}
	@SubscribeEvent
	public void onPlayerWake(PlayerWakeUpEvent event) {
		PlayerSanityDataExtendedProps psdep = PlayerSanityDataExtendedProps
				.get((EntityPlayer) event.entity);
		
		psdep.addInsanity((int)(SLEEP_MULTIPLIER * Math.min(MAX_SLEEP, psdep.getSleepTime() - event.entity.worldObj.getWorldTime())));
	}
	
	
}
