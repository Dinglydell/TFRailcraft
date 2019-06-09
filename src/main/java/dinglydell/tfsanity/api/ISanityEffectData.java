package dinglydell.tfsanity.api;

import java.util.Random;

/** Handles the data for an ISanityEffect. There's one instance of this for every time an ISanityEffect occurs */
public interface ISanityEffectData {
	public ISanityEffect getEffect();
	
	/** Handles a sanity tick - used for processing the effect
	 * @param effectData 

	@return True if the effect should stay active, false if not */
	public boolean handleTick(Random rnd);
	
	/** Determines whether this sanity effect should block sleep. */
	public boolean shouldBlockSleep();
	
}
