package dinglydell.tfsanity.player;

import java.util.Random;

import cpw.mods.fml.common.network.NetworkRegistry;
import dinglydell.tfsanity.api.ISanityEffect;
import dinglydell.tfsanity.api.ISanityEffectData;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GhastlyHalucinationEffectData implements ISanityEffectData {

	private GhastlyHalucinationEffect effect;
	private EntityPlayer player;
	private EntityGhast entity;

	public GhastlyHalucinationEffectData(GhastlyHalucinationEffect effect, EntityPlayer player) {
		this.effect = effect;
		this.player = player;
		this.entity = new EntityGhast(player.worldObj);
		
		this.entity.posX = player.posX;
		this.entity.posY = player.posY;
		this.entity.posZ = player.posZ;
		if(player.isClientWorld()) {
			return;
		}
		EntityPlayerMP playerMP = (EntityPlayerMP)player;
		S0FPacketSpawnMob packet = new S0FPacketSpawnMob(entity);
		S18PacketEntityTeleport packetEntity = new S18PacketEntityTeleport(entity);
		playerMP.playerNetServerHandler.sendPacket(packet);
		playerMP.playerNetServerHandler.sendPacket(packetEntity);
		//player.worldObj.spawnEntityInWorld(entity);
		
		//this.entity = new EntityGhast(player.worldObj);
		//this.entity.onUpdate();
	}
	
	@Override
	public ISanityEffect getEffect() {
		return effect;
	}

	@Override
	public boolean handleTick(Random rnd) {
		
		return false;
	}

	@Override
	public boolean shouldBlockSleep() {
		return false;
	}

}
