package dinglydell.tfsanity.command;

import java.util.ArrayList;
import java.util.List;

import dinglydell.tfsanity.player.PlayerSanityDataExtendedProps;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class CommandSanity implements ICommand {

	private static List<String> aliases = new ArrayList<String>();
	static {
		aliases.add("tfs");
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		return "tfsanity";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "/tfs";
	}

	@Override
	public List getCommandAliases() {
		return aliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		World world = sender.getEntityWorld();
		EntityPlayer player = (EntityPlayer) sender;
		if (!world.isRemote) {
			PlayerSanityDataExtendedProps psdep = PlayerSanityDataExtendedProps
					.get(player);
			sender.addChatMessage(new ChatComponentText(Integer.toString(psdep.getInsanity())));
		}
		
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return sender instanceof EntityPlayer;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		// TODO Auto-generated method stub
		return false;
	}

}
