package moriyashiine.realisticfirespread.api.component;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import moriyashiine.realisticfirespread.common.registry.RSFComponents;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;

import java.util.Optional;

public class FireFromSunComponent implements ComponentV3, ServerTickingComponent {
	private final Entity obj;
	private boolean fireFromSun = false;
	
	public FireFromSunComponent(Entity obj) {
		this.obj = obj;
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
		if (obj.getFireTicks() <= 0 && isFireFromSun()) {
			setFireFromSun(false);
		}
	}
	
	public static FireFromSunComponent get(Entity obj) {
		return RSFComponents.FIRE_FROM_SUN_COMPONENT.get(obj);
	}
	
	@SuppressWarnings("unused")
	public static Optional<FireFromSunComponent> maybeGet(Entity obj) {
		return RSFComponents.FIRE_FROM_SUN_COMPONENT.maybeGet(obj);
	}
}
