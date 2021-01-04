package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.accessor.IsFireFromSunAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements IsFireFromSunAccessor {
	private boolean isFireFromSun = false;
	
	@Shadow
	public abstract int getFireTicks();
	
	@Shadow
	public World world;
	
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
		if (!world.isClient && getFireTicks() <= 0 && getIsFireFromSun()) {
			setIsFireFromSun(false);
		}
	}
	
	@Inject(method = "onStruckByLightning", at = @At("HEAD"))
	private void onStruckByLightning(ServerWorld world, LightningEntity lightning, CallbackInfo callbackInfo) {
		if (!world.isClient) {
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
