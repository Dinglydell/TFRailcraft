package dinglydell.tfsanity.player;

import java.util.Arrays;
import java.util.Random;

import dinglydell.tfsanity.api.ISanityEffect;
import dinglydell.tfsanity.api.ISanityEffectData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class StagedMessageEffect implements ISanityEffect<StagedMessageEffectData> {

	public Random rnd = new Random();
	public String[] initialMessages;
	public String[][] ongoingMessages;
	public String[] finalMessages;
	private String name;
	
	
	public StagedMessageEffect(String name, String[] initialMessages, String[][] ongoingMessages, String[] finalMessages) {
		this.name = name;
		this.initialMessages = initialMessages;
		this.ongoingMessages = ongoingMessages;
		this.finalMessages = finalMessages;
	}
	
	@Override
	public void saveNBTData(NBTTagCompound nbt, StagedMessageEffectData data) {
		nbt.setInteger("stage", data.stage);
		
	}

	@Override
	public ISanityEffectData loadNBTData(NBTTagCompound nbt, PlayerSanityDataExtendedProps psdep) {
		return new StagedMessageEffectData(this, psdep, nbt.getInteger("stage"));
	}

	@Override
	public boolean shouldActivate(PlayerSanityDataExtendedProps psdep, Random rnd) {
		if(rnd.nextDouble() * Math.min(20000, psdep.getInsanity()) > 4000) {
			return rnd.nextDouble() < 0.001;
		}
		return false;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ISanityEffectData activateEffect(PlayerSanityDataExtendedProps psdep, Random rnd) {
		sendRandomSpookyMessage(psdep.player, initialMessages);
		
		psdep.player.playSound("ambient.cave.cave", 1f, 1f);
		return new StagedMessageEffectData(this, psdep);
	}

	public void sendRandomSpookyMessage(EntityPlayer player, String[] possibleMessages) {
		ChatComponentText chat = new ChatComponentText(possibleMessages[rnd.nextInt(possibleMessages.length)]);
		chat.getChatStyle().setColor(EnumChatFormatting.DARK_AQUA).setItalic(true);
		player.addChatComponentMessage(chat);
		
	}


}
