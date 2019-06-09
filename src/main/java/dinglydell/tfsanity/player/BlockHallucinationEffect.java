package dinglydell.tfsanity.player;

import java.util.Random;

import dinglydell.tfsanity.TFSanity;
import dinglydell.tfsanity.api.ISanityEffect;
import dinglydell.tfsanity.api.ISanityEffectData;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.ChatComponentText;

public class BlockHallucinationEffect implements ISanityEffect {
	public static final int SPAWN_RADIUS = 8;
	private Block block;
	private float chanceMultiplier; 
	
	public BlockHallucinationEffect(Block block, float chanceMultiplier) {
		this.block = block;
		this.chanceMultiplier = chanceMultiplier;
	}
	
	
	
	@Override
	public void saveNBTData(NBTTagCompound nbt, ISanityEffectData data) {
		
		
	}

	@Override
	public ISanityEffectData loadNBTData(NBTTagCompound nbt, PlayerSanityDataExtendedProps psdep) {
		return null;
	}

	@Override
	public boolean shouldActivate(PlayerSanityDataExtendedProps psdep, Random rnd) {
		if(psdep.getInsanity() < 10000) {
			return false;
		}
		if(psdep.getInsanity() < 15000) {
			return rnd.nextDouble() < 0.001 * this.chanceMultiplier;
 		}
		if(psdep.getInsanity() < 20000) {
			return rnd.nextDouble() < 0.01 * this.chanceMultiplier;
		}
		return rnd.nextDouble() < 0.005 * this.chanceMultiplier;
	}

	@Override
	public String getName() {
		return "block_hallucination";
	}

	@Override
	public ISanityEffectData activateEffect(PlayerSanityDataExtendedProps psdep, Random rnd) {
		if(psdep.player.worldObj.isRemote) {
			return null;
		}
		//psdep.player.addChatComponentMessage(new ChatComponentText("hi!"));
		EntityPlayerMP playerMP = (EntityPlayerMP)psdep.player;
		int x = (int)psdep.player.posX + rnd.nextInt(SPAWN_RADIUS*2 ) - SPAWN_RADIUS;
		int y = (int)psdep.player.posY + 1;
		int z = (int)psdep.player.posZ + rnd.nextInt(SPAWN_RADIUS*2 ) - SPAWN_RADIUS;
		// keep going down until you find a solid block
		while(!playerMP.worldObj.getBlock(x, y, z).isOpaqueCube()) {
			y--;
		}
		
		S23PacketBlockChange packet = new S23PacketBlockChange(x, y,  z, psdep.player.worldObj);
		packet.field_148883_d = block;
		playerMP.playerNetServerHandler.sendPacket(packet);
		return null;
	}

}
