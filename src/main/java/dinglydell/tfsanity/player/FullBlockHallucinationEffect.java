package dinglydell.tfsanity.player;

import java.util.Random;

import dinglydell.tfsanity.api.ISanityEffect;
import dinglydell.tfsanity.api.ISanityEffectData;
import dinglydell.tfsanity.event.SanityEventHandler;
import net.minecraft.nbt.NBTTagCompound;

public class FullBlockHallucinationEffect implements ISanityEffect<FullBlockHallucinationEffectData> {

	@Override
	public void saveNBTData(NBTTagCompound nbt, FullBlockHallucinationEffectData data) {
		
	}

	@Override
	public ISanityEffectData loadNBTData(NBTTagCompound nbt, PlayerSanityDataExtendedProps psdep) {
		return null;
	}

	@Override
	public boolean shouldActivate(PlayerSanityDataExtendedProps psdep, Random rnd) {
		if(psdep.getInsanity() < 20000) {
			return false;
		}
		if(psdep.getInsanity() < 25000) {
			return rnd.nextDouble() < 0.001;
 		}
		if(psdep.getInsanity() < 30000) {
			return rnd.nextDouble() < 0.01;
		}
		return rnd.nextDouble() < 0.005;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISanityEffectData activateEffect(PlayerSanityDataExtendedProps psdep, Random rnd) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
