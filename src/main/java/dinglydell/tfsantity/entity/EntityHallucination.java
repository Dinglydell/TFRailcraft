package dinglydell.tfsantity.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityHallucination extends Entity {

	private EntityPlayer player;

	public EntityHallucination(World world, EntityPlayer player) {
		super(world);
		this.player = player;
	}

	@Override
	protected void entityInit() {
		
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		
	}

}
