package org.d1p4k.screenshotonzoom.client;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface SpyglassUseCallback {
    Event<SpyglassUseCallback> EVENT = EventFactory.createArrayBacked(SpyglassUseCallback.class,
            (listeners) -> (world, player, sheep) -> {
                for (SpyglassUseCallback listener : listeners) {
                    ActionResult result = listener.interact(world, player, sheep);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult interact(World world, PlayerEntity player, Hand hand);
}