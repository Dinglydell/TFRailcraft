package dinglydell.tfsanity.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import dinglydell.tfsanity.player.PlayerSanityDataExtendedProps;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class PacketSanityHandler implements IMessageHandler<PacketSanity, IMessage> {

	@Override
	public IMessage onMessage(PacketSanity message, MessageContext ctx) {
		EntityPlayer player = (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer
				: ctx.getServerHandler().playerEntity);
		PlayerSanityDataExtendedProps ptdep = PlayerSanityDataExtendedProps
				.get(player);
		ptdep.loadNBTData(message.data);
		return null;
	}

}
