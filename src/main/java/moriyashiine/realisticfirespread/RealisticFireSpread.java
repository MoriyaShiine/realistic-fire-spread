package moriyashiine.realisticfirespread;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

@Mod(modid = "realisticfirespread", name = "Realistic Fire Spread", version = "1.0.0")
public class RealisticFireSpread
{
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void worldTick(WorldTickEvent event)
	{
		World world = event.world;
		
		if(world.getTotalWorldTime() % 20 == 0)
		{
			if(!world.isRemote && world.getGameRules().getBoolean("doFireTick"))
			{
				for(Entity entity : world.loadedEntityList)
				{
					if(entity.isBurning())
					{
						BlockPos pos = entity.getPosition();
						Random rand = world.rand;
						int x = MathHelper.getInt(rand, -1, 1);
						int y = MathHelper.getInt(rand, -1, 1);
						int z = MathHelper.getInt(rand, -1, 1);
						pos = pos.add(x, y, z);
						
						if(world.getBlockState(pos).getBlock() == Blocks.AIR)
						{
							for(EnumFacing face : EnumFacing.values())
							{
								boolean flammable = Blocks.FIRE.canCatchFire(world, pos.offset(face));
								
								if(flammable)
								{
									world.setBlockState(pos, Blocks.FIRE.getDefaultState());
									break;
								}
							}
						}
					}
				}
			}
		}
	}
}
