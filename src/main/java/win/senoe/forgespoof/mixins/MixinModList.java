package win.senoe.forgespoof.mixins;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import win.senoe.forgespoof.ForgeSpoof;

import java.util.List;
import java.util.Map;

@Mixin(FMLHandshakeMessage.ModList.class)
public class MixinModList extends FMLHandshakeMessage {

    @Shadow(remap = false)
    private Map<String, String> modTags;

    /**
     * @author __fastcall
     * @see net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage
     */
    @Inject(method = "<init>(Ljava/util/List;)V", at = @At("RETURN"), remap = false)
    private void removeIds(List<ModContainer> modList, CallbackInfo ci) {
        if (!Minecraft.getMinecraft().isSingleplayer() && !ForgeSpoof.modIds.isEmpty()) {
            ForgeSpoof.LOGGER.info("Removing modids...");
            this.modTags.keySet().removeIf(key -> ForgeSpoof.modIds.stream().anyMatch(key::equalsIgnoreCase));
        }
    }

}
