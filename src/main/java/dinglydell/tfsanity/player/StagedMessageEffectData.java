package dinglydell.tfsanity.player;

import java.util.Random;

import dinglydell.tfsanity.api.ISanityEffect;
import dinglydell.tfsanity.api.ISanityEffectData;

public class StagedMessageEffectData implements ISanityEffectData {

	private StagedMessageEffect effect;
	private PlayerSanityDataExtendedProps psdep;
	public int stage;
	private int cooldown = 0;

	public StagedMessageEffectData(StagedMessageEffect effect, PlayerSanityDataExtendedProps psdep) {
		this(effect, psdep, 0);
	}

	public StagedMessageEffectData(StagedMessageEffect effect, PlayerSanityDataExtendedProps psdep, int stage) {
		this.effect = effect;
		this.stage = stage;
		this.psdep = psdep;
	}

	@Override
	public ISanityEffect getEffect() {
		return effect;
	}

	@Override
	public boolean handleTick(Random rnd) {
		if(cooldown-- > 0) {
			return true;
		}
		cooldown = 40;
		// chance (out of 1,000) of a message occuring:
		int chance = Math.min(50, stage + psdep.getInsanity() / 1000);
		
		if(effect.rnd.nextInt(1000) < chance) {
			effect.sendRandomSpookyMessage(psdep.player, effect.ongoingMessages[stage]);
			// check if go on to next stage
			if(stage+1 < effect.ongoingMessages.length && rnd.nextInt(effect.ongoingMessages[stage].length/2) == 0) {
				stage++;
				onChangeStage();
			}
		}
		
		//should it end? chance out of 10,000
		int endChance = 100 * stage + 50000 / psdep.getInsanity();
		if(effect.rnd.nextInt(10000) < endChance) {
			effect.sendRandomSpookyMessage(psdep.player, effect.finalMessages);
			//lose 1000 insanity from the experience
			psdep.addInsanity(-1000);
			return false;
		}
		return true;
	}

	protected void onChangeStage() {
		//psdep.player.playSound("ambient.cave.cave", 1f, 1f);
		
	}

	@Override
	public boolean shouldBlockSleep() {
		return true;
	}

}
