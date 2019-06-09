package dinglydell.tfsanity.player;

import java.util.Random;

import dinglydell.tfsanity.api.ISanityEffect;
import dinglydell.tfsanity.api.ISanityEffectData;

public class FullBlockHallucinationEffectData implements ISanityEffectData {

	@Override
	public ISanityEffect getEffect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean handleTick(Random rnd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldBlockSleep() {
		// TODO Auto-generated method stub
		return false;
	}

}
