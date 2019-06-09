package dinglydell.tfsanity.api;

import java.util.Random;

import dinglydell.tfsanity.player.PlayerSanityDataExtendedProps;
import net.minecraft.nbt.NBTTagCompound;

//TODO better handle instantaneous effects. Seems wrong to force them to implement saving/loading NBTs
/** An effect that can occur due to your sanity levels. One instance of each type exists */
public interface ISanityEffect<T extends ISanityEffectData> {
	public void saveNBTData(NBTTagCompound nbt, T data);
	
	public ISanityEffectData loadNBTData(NBTTagCompound nbt, PlayerSanityDataExtendedProps psdep);


	
	/** Determines whether this effect should activate */
	public boolean shouldActivate(PlayerSanityDataExtendedProps psdep, Random rnd);

	public String getName();
	
	/** Activates the effect and returns a data object. Return null if the effect is instantaneous  */
	public ISanityEffectData activateEffect(PlayerSanityDataExtendedProps psdep, Random rnd);

	

}
