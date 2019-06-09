package dinglydell.tfsanity.player;

import java.util.Random;

import dinglydell.tfsanity.api.ISanityEffect;
import dinglydell.tfsanity.api.ISanityEffectData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class StrangePresenceEffect implements ISanityEffect<StrangePresenceEffectData> {
	/** Possible messages that occur when this effect starts. */
	public static String[] intial_presence_messages = new String[] {"You feel a strange presence nearby...", "You can't shake the feeling that something is watching you...", "You sense a mysterious evil in the air...", "What was that? Something is nearby..."};
	
	/** Possible messages that occur while this effect is ongoing, stage by stage */
	public static String[][] ongoing_presence_messages = new String[][] {
		{"It's coming... You can feel it.", "You can still feel it. Something is there...", "You are sure of it - there is definitely something watching you...", "It's growing stronger..."},
		{"Closer and closer, it is almost upon you...", "You feel a great sense of fear envolop you...", "It follows you wherever you go..."},
		{"It's here! It's right behind you!", "The evil presence overwhelms you... You're not sure you can cope anymore...", "Run! Fight! Hide! Anything to escape it...", "It's here. It's so powerful... Is this the end?"}};
		/** Possble messages that can occur when this effect goes away. */
	public static String[] final_presence_messages = new String[] {"You don't feel a presence anymore. Could it be gone?", "You begin to relax as the presence goes away.", "The feeling gradually fades... You contemplate your peculiar experience."};
	public Random rnd = new Random();
	
	@Override
	public void saveNBTData(NBTTagCompound nbt, StrangePresenceEffectData data) {
		nbt.setInteger("stage", data.stage);
		
	}

	@Override
	public ISanityEffectData loadNBTData(NBTTagCompound nbt, PlayerSanityDataExtendedProps psdep) {
		return new StrangePresenceEffectData(this, psdep, nbt.getInteger("stage"));
	}

	@Override
	public boolean shouldActivate(PlayerSanityDataExtendedProps psdep, Random rnd) {
		if(rnd.nextDouble() * Math.min(20000, psdep.getInsanity()) > 4000) {
			return rnd.nextDouble() < 0.0001;
		}
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "strange_presence";
	}

	@Override
	public ISanityEffectData activateEffect(PlayerSanityDataExtendedProps psdep, Random rnd) {
		sendRandomSpookyMessage(psdep.player, intial_presence_messages);
		
		psdep.player.playSound("ambient.cave.cave", 1f, 1f);
		return new StrangePresenceEffectData(this, psdep);
	}

	public void sendRandomSpookyMessage(EntityPlayer player, String[] possibleMessages) {
		ChatComponentText chat = new ChatComponentText(possibleMessages[rnd.nextInt(possibleMessages.length)]);
		chat.getChatStyle().setColor(EnumChatFormatting.DARK_AQUA).setItalic(true);
		player.addChatComponentMessage(chat);
		
	}




}
