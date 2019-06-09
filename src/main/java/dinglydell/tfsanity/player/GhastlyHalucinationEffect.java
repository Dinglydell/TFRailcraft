package dinglydell.tfsanity.player;

import java.util.Random;

import dinglydell.tfsanity.api.ISanityEffect;
import dinglydell.tfsanity.api.ISanityEffectData;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

public class GhastlyHalucinationEffect implements ISanityEffect {

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
		if(psdep.getInsanity() < 10000) {
			return false;
		}
		if(psdep.getInsanity() < 15000) {
			return rnd.nextDouble() < 0.01;
 		}
		if(psdep.getInsanity() < 20000) {
			return rnd.nextDouble() < 0.1;
		}
		return rnd.nextDouble() < 0.05;
	}

	@Override
	public String getName() {
		return "ghastly_hallucinations";
	}

	@Override
	public ISanityEffectData activateEffect(PlayerSanityDataExtendedProps psdep, Random rnd) {
		psdep.player.addChatComponentMessage(new ChatComponentText("hi"));
		return new GhastlyHalucinationEffectData(this, psdep.player);
	}

}
