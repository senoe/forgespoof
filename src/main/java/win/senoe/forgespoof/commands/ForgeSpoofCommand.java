package win.senoe.forgespoof.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import win.senoe.forgespoof.gui.GuiForgeSpoof;

import java.util.Collections;
import java.util.List;

public class ForgeSpoofCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "forgespoof";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("fs");
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/forgespoof";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiForgeSpoof());
        MinecraftForge.EVENT_BUS.unregister(this);
    }

}
