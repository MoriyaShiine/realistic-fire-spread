package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.accessor.IsFireFromSunAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements IsFireFromSunAccessor {
	@Shadow
	public abstract int getFireTicks();
	
	private boolean isFireFromSun = false;
	
	@Override
	public boolean getIsFireFromSun() {
		return isFireFromSun;
	}
	
	@Override
	public void setIsFireFromSun(boolean isFireFromSun) {
		this.isFireFromSun = isFireFromSun;
	}
	
	@Inject(method = "tick", at = @At("HEAD"))
	private void setFireTicks(CallbackInfo callbackInfo) {
		if (getFireTicks() <= 0 && getIsFireFromSun()) {
			setIsFireFromSun(false);
		}
	}
	
	@Inject(method = "fromTag", at = @At("HEAD"))
	private void fromTag(CompoundTag tag, CallbackInfo callbackInfo) {
		isFireFromSun = tag.getBoolean("IsFireFromSun");
	}
	
	@Inject(method = "toTag", at = @At("HEAD"))
	private void toTag(CompoundTag tag, CallbackInfoReturnable<CompoundTag> callbackInfo) {
		tag.putBoolean("IsFireFromSun", isFireFromSun);
	}
}
