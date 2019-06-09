package dinglydell.tfsanity.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import dinglydell.tfsanity.api.SanityEffectHandler;
import dinglydell.tfsanity.player.PlayerSanityDataExtendedProps;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class SanityEventHandler {
	private static final int UPDATE_TICKS = 100;
	private static final int CONFINEMENT_MULTIPLIER = 1;
	/** minimum light level before insanity is induced by the darkness */
	private static final int DARKNESS_THRESHOLD = 5;
	private static final int PASSIVE_CHANGE = -5;
	/** The minimum sun brightness (from 0 to 1) for the sun to have an effect on sanity */
	private static final float SUN_BRIGHTNESS_THRESHOLD = 0.8f;
	/** The effect the sun has on sanity */
	private static final int SUN_EFFECT = -5;
	/** The effect being able to directly see the sky has on sanity */
	private static final int SKY_EFFECT = -5;
	private int updateTime;
	
	@SubscribeEvent
	public void onTick(PlayerTickEvent  event) {
		if (event.phase==Phase.START && !event.player.isPlayerSleeping()) { 
			PlayerSanityDataExtendedProps psdep = PlayerSanityDataExtendedProps
					.get(event.player);
			SanityEffectHandler.getInstance().handleEffectsTick(psdep);
			updateTime--;
			if(updateTime < 0) {
				updateTime = UPDATE_TICKS;
				
				
				//passive decrease of 1 insanity/second
				psdep.addInsanity(PASSIVE_CHANGE);
				
				//check if confined and increase insanity if so
				checkConfinement(psdep);
				
				//darkness causes insanity, artificial light is neutral, sunlight is healing
				checkLighting(psdep);
				
				
				//checks whether the current level of insanity is enough to activate this effect
				SanityEffectHandler.getInstance().handleActivationCheck(psdep);
				
			}
			
				
			
		}
	}


	private void checkLighting( PlayerSanityDataExtendedProps psdep) {
		int lightLevel = psdep.player.worldObj.getFullBlockLightValue((int)psdep.player.posX , (int)psdep.player.posY, (int)psdep.player.posZ);
		if(lightLevel < DARKNESS_THRESHOLD) {
			psdep.addInsanity(DARKNESS_THRESHOLD - lightLevel);
		}
		
		if(psdep.player.worldObj.canBlockSeeTheSky((int)psdep.player.posX, (int)psdep.player.posY, (int)psdep.player.posZ)) {
			psdep.addInsanity(SKY_EFFECT);
			if(psdep.player.worldObj.getSunBrightness(1f) > SUN_BRIGHTNESS_THRESHOLD ) {
				psdep.addInsanity(SUN_EFFECT); 
			}
		}
		
	}

	private void checkConfinement(PlayerSanityDataExtendedProps psdep) {
		// check for low ceilings
		checkDirection(ForgeDirection.UP, psdep.player.worldObj, (int)psdep.player.posX, (int)psdep.player.posY+1, (int)psdep.player.posZ, psdep, 2);
		
		ForgeDirection[] horizontals =  new ForgeDirection[] { ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST };
		for(ForgeDirection dir : horizontals) {
			checkDirection(dir, psdep.player.worldObj, (int)psdep.player.posX, (int)psdep.player.posY, (int)psdep.player.posZ, psdep, 5);
			checkDirection(dir, psdep.player.worldObj, (int)psdep.player.posX, (int)psdep.player.posY+1, (int)psdep.player.posZ, psdep, 5);
		}
	}

	private void checkDirection(ForgeDirection dir, World world, int x, int y, int z, PlayerSanityDataExtendedProps psdep, int numBlocks) {
		for(int i = 1; i <= numBlocks; i++) {
			Block b = world.getBlock(x + dir.offsetX * i, y + dir.offsetY * i, z + dir.offsetZ * i);
			if(b.isOpaqueCube()) {
				psdep.addInsanity((numBlocks + 1 - i) * CONFINEMENT_MULTIPLIER);
				return;
			}
		}
		
	}
}
