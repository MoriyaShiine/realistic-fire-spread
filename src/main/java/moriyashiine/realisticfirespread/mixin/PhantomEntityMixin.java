package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.common.registry.ModComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PhantomEntity.class)
public abstract class PhantomEntityMixin extends Entity {
	public PhantomEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/PhantomEntity;setOnFireFor(I)V"))
	private void realisticfirespread$setFireFromSun(CallbackInfo ci) {
		if (!world.isClient && !isOnFire()) {
			ModComponents.FIRE_FROM_SUN_COMPONENT.get(this).setFireFromSun(true);
		}
	}
}
