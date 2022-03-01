package moriyashiine.realisticfirespread.common.component.entity;

import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;

public class FireFromSunComponent implements ServerTickingComponent {
	private final Entity obj;
	private boolean fireFromSun = false;

	public FireFromSunComponent(Entity obj) {
		this.obj = obj;
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

	public boolean isFireFromSun() {
		return fireFromSun;
	}

	public void setFireFromSun(boolean fireFromSun) {
		this.fireFromSun = fireFromSun;
	}
}
