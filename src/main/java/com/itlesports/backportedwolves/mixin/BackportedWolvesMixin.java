package com.itlesports.backportedwolves.mixin;

import btw.BTWAddon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BTWAddon.class)
public class BackportedWolvesMixin {
	@Inject(at = @At("RETURN"), method = "<init>")
	private void init(CallbackInfo info) {
		System.out.println("You're Running With Backported Wolves!");
	}
}
