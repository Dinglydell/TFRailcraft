package dinglydell.tfsanity.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dinglydell.tfsanity.player.PlayerSanityDataExtendedProps;

public class SanityEffectHandler {
	private static final SanityEffectHandler INSTANCE = new SanityEffectHandler();
	public static final SanityEffectHandler getInstance()
	{
		return INSTANCE;
	}
	
	private Map<String, ISanityEffect> potentialEffects = new HashMap<String, ISanityEffect>();
	private Random rnd = new Random();
	
	private SanityEffectHandler() {
		
	}
	
	public void registerSanityEffect(ISanityEffect effect) {
		potentialEffects.put(effect.getName(), effect);
	}

	public void handleEffectsTick(PlayerSanityDataExtendedProps psdep) {
		List<ISanityEffectData> activeEffects = psdep.getActiveSanityEffects();
		for(int i = 0; i < activeEffects.size(); i++) {
			ISanityEffectData effectData = activeEffects.get(i);
			if(!effectData.handleTick(rnd )) {
				activeEffects.remove(i--);
			}
		}
	}
	
	/** Checks whether each ISanityEffect should be activated and activates those that should */
	public void handleActivationCheck(PlayerSanityDataExtendedProps psdep) {
		Random rnd = new Random();
		for(ISanityEffect effect : potentialEffects.values()) {
			if(!psdep.isEffectActive(effect) && effect.shouldActivate(psdep, rnd)) {
				psdep.addActiveEffect(effect.activateEffect(psdep, rnd));
			}
		}
	}

	public ISanityEffect getEffectByName(String name) {
		return potentialEffects.get(name);
	}
}
