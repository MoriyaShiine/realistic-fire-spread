package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.accessor.IsFireFromSunAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSkeletonEntity.class)
public abstract class AbstractSkeletonEntityMixin extends Entity implements IsFireFromSunAccessor {
	public AbstractSkeletonEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}
	
	@Inject(method = "tickMovement", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, ordinal = 0, target = "Lnet/minecraft/entity/mob/AbstractSkeletonEntity;setOnFireFor(I)V"))
	private void tickMovement(CallbackInfo callbackInfo) {
		if (!isOnFire()) {
			setIsFireFromSun(true);
		}
	}
}
