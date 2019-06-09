package dinglydell.tfsanity.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import dinglydell.tfsanity.player.PlayerSanityDataExtendedProps;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class PacketSanity implements IMessage {

	NBTTagCompound data;

	public PacketSanity() {
	}

	public PacketSanity(PlayerSanityDataExtendedProps psdep) {
		data = new NBTTagCompound();
		psdep.saveNBTData(data);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		data = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, data);
}

}
