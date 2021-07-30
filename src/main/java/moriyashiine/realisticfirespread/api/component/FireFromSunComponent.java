package moriyashiine.realisticfirespread.api.component;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import moriyashiine.realisticfirespread.common.registry.RSFComponents;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;

import java.util.Optional;

public class FireFromSunComponent implements ComponentV3, ServerTickingComponent {
	private final Entity entity;
	private boolean fireFromSun = false;
	
	public FireFromSunComponent(Entity entity) {
		this.entity = entity;
	}
	
	public boolean isFireFromSun() {
		return fireFromSun;
	}
	
	public void setFireFromSun(boolean fireFromSun) {
		this.fireFromSun = fireFromSun;
	}
	
	@Override
	public void readFromNbt(NbtCompound tag) {
		setFireFromSun(tag.getBoolean("FireFromSun"));
	}
	
	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putBoolean("FireFromSun", isFireFromSun());
	}
	
	@Override
	public void serverTick() {
		if (entity.getFireTicks() <= 0 && isFireFromSun()) {
			setFireFromSun(false);
		}
	}
	
	public static FireFromSunComponent get(Entity entity) {
		return RSFComponents.FIRE_FROM_SUN_COMPONENT.get(entity);
	}
	
	@SuppressWarnings("unused")
	public static Optional<FireFromSunComponent> maybeGet(Entity entity) {
		return RSFComponents.FIRE_FROM_SUN_COMPONENT.maybeGet(entity);
	}
}
