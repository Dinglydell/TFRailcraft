package dinglydell.tfsanity.player;

import dinglydell.tfsanity.network.PacketSanity;

import java.util.ArrayList;
import java.util.List;

import dinglydell.tfsanity.TFSanity;
import dinglydell.tfsanity.api.ISanityEffect;
import dinglydell.tfsanity.api.ISanityEffectData;
import dinglydell.tfsanity.api.SanityEffectHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants.NBT;
public class PlayerSanityDataExtendedProps implements IExtendedEntityProperties {
	private static final String INSANITY = "insanity";
	public static final String SANITYDATA = "sanityData";
	private static final String SANITY_EFFECTS = "sanityEffects";
	private static final String SANITY_EFFECT_NAME = "name";
	public final EntityPlayer player;
	private int insanity;
	
	private List<ISanityEffectData> activeSanityEffects = new ArrayList<ISanityEffectData>();
	//the time at which the player last fell asleep
	private long sleepTime;
	
	public PlayerSanityDataExtendedProps(EntityPlayer player) {
		this.player = player;

	}
	
	@Override
	public void saveNBTData(NBTTagCompound nbt) {
		nbt.setInteger(INSANITY, insanity);
		
		NBTTagList effectsTag = new NBTTagList();
		for(ISanityEffectData effectData : activeSanityEffects) {
			NBTTagCompound effectTag = new NBTTagCompound();
			effectData.getEffect().saveNBTData(effectTag, effectData);
			effectTag.setString(SANITY_EFFECT_NAME, effectData.getEffect().getName());
			effectsTag.appendTag(effectTag);
		}
		
		nbt.setTag(SANITY_EFFECTS, effectsTag);
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt) {
		insanity= nbt.getInteger(INSANITY);
		NBTTagList effectsTag = nbt.getTagList(SANITY_EFFECTS, NBT.TAG_COMPOUND);
		for(int i = 0; i < effectsTag.tagCount(); i++) {
			NBTTagCompound effectTag = effectsTag.getCompoundTagAt(i);
			
			ISanityEffect effect = SanityEffectHandler.getInstance().getEffectByName(effectTag.getString(SANITY_EFFECT_NAME));
			activeSanityEffects.add(effect.loadNBTData(effectTag, this));
			
		}
	}

	@Override
	public void init(Entity entity, World world) {
		
		
	}
	
	public int getInsanity() {
		return insanity;
	}
	
	public void setInsanity(int newValue) {
		insanity = newValue;
	}
	
	public void addInsanity(int amount) {
		insanity += amount;
		if(insanity < 0) {
			insanity = 0;
		}
	}

	public static final PlayerSanityDataExtendedProps get(EntityPlayer player) {
		return (PlayerSanityDataExtendedProps) player
				.getExtendedProperties(SANITYDATA);
}

	public void sendPacket() {
		if (player instanceof EntityPlayerMP) {
			EntityPlayerMP emp = ((EntityPlayerMP) player);
			TFSanity.snw.sendTo(new PacketSanity(this), emp);
		}
	}

	public void setSleepTime(long worldTime) {
		sleepTime = worldTime;
		
	}
	public long getSleepTime() {
		return sleepTime;
	}

	public List<ISanityEffectData> getActiveSanityEffects() {
		return activeSanityEffects;
	}

	public void addActiveEffect(ISanityEffectData effect) {
		if(effect == null) {
			return;
		}
		activeSanityEffects.add(effect);
		
	}

	public boolean isEffectActive(ISanityEffect effect) {
		for(ISanityEffectData effectData : activeSanityEffects) {
			if(effectData.getEffect() == effect) {
				return true;
			}
		}
		return false;
	}

	public boolean isSleepBlocked() {
		for(ISanityEffectData effectData : activeSanityEffects) {
			if(effectData.shouldBlockSleep()) {
				return true;
			}
		}
		return false;
	}
}
