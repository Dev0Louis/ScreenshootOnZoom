package org.d1p4k.screenshotonzoom.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.item.Item;
import net.minecraft.item.SpyglassItem;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.lwjgl.glfw.GLFW;

import javax.swing.text.JTextComponent;

@Environment(EnvType.CLIENT)
public class ScreenshotOnZoomClient implements ClientModInitializer {

    private MinecraftClient client;
    private static KeyBinding screenshotKey;
    private boolean temp = false;

    @Override
    public void onInitializeClient() {
        // Add a Listener that will be called when the players zooms with a Spyglass.
        // This will take a screenshot of the current viewport.

        client = MinecraftClient.getInstance();
        screenshotKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.example.screenshot",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_U,
                "key.category.misc"
        ));

        SpyglassUseCallback.EVENT.register((world, player, hand) -> {
            if(player.isSneaking()){
                if(temp){
                    takeScreenshot();
                }
                temp = !temp;
            }

            return ActionResult.PASS;
        });



    }

    private void takeScreenshot() {
        ScreenshotRecorder.saveScreenshot(this.client.runDirectory, this.client.getFramebuffer(), message -> this.client.execute(() -> this.client.inGameHud.getChatHud().addMessage((Text)message)));
    }
}
