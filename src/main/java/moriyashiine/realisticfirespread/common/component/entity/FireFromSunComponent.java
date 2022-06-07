/*
 * All Rights Reserved (c) 2022 MoriyaShiine
 */

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
		fireFromSun = tag.getBoolean("FireFromSun");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putBoolean("FireFromSun", fireFromSun);
	}

	@Override
	public void serverTick() {
		if (fireFromSun && obj.getFireTicks() <= 0) {
			fireFromSun = false;
		}
	}

	public boolean isFireFromSun() {
		return fireFromSun;
	}

	public void setFireFromSun(boolean fireFromSun) {
		this.fireFromSun = fireFromSun;
	}
}
