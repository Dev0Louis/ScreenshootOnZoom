package org.d1p4k.screenshotonzoom.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpyglassItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.d1p4k.screenshotonzoom.client.SpyglassUseCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpyglassItem.class)
public class SpyglassItemMixin {

    @Inject(method = "use", at = @At(value = "HEAD"), cancellable = true)
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ActionResult result = SpyglassUseCallback.EVENT.invoker().interact(world, user, hand);

        if(result == ActionResult.FAIL){
            return;
        }

    }
}
