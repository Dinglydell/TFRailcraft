package dinglydell.tfsanity.player;

import java.util.Random;

import dinglydell.tfsanity.api.ISanityEffect;
import dinglydell.tfsanity.api.ISanityEffectData;
import net.minecraft.nbt.NBTTagCompound;

public class StrangeSoundsEffect implements ISanityEffect {
	String[] possibleSounds = new String[] {"mob.ghast.scream", "portal.portal", "portal.travel", "mob.ghast.moan", "portal.trigger", "mob.wither.idle", "random.explode"};
	@Override
	public void saveNBTData(NBTTagCompound nbt, ISanityEffectData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISanityEffectData loadNBTData(NBTTagCompound nbt, PlayerSanityDataExtendedProps psdep) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean shouldActivate(PlayerSanityDataExtendedProps psdep, Random rnd) {
		if(psdep.getInsanity() < 6000) {
			return false;
		}
		if(psdep.getInsanity() < 10000) {
			return rnd.nextDouble() < 0.01;
 		}
		if(psdep.getInsanity() < 15000) {
			return rnd.nextDouble() < 0.1;
		}
		return rnd.nextDouble() < 0.05;
	}

	@Override
	public String getName() {
		return "strange_sounds";
	}

	@Override
	public ISanityEffectData activateEffect(PlayerSanityDataExtendedProps psdep, Random rnd) {
		psdep.player.playSound(possibleSounds[rnd.nextInt(possibleSounds.length)], 1f, 1f);
		return null;
	}

}
