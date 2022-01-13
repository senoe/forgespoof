package win.senoe.forgespoof.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Keyboard;
import win.senoe.forgespoof.ForgeSpoof;
import win.senoe.forgespoof.gui.elements.GuiSlotModidList;

import java.io.IOException;

public class GuiForgeSpoof extends GuiScreen {

    private GuiTextField textField;
    private GuiSlotModidList modidList;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        FontRenderer fr = fontRendererObj;
        this.drawDefaultBackground();
        GlStateManager.pushMatrix();
        GlStateManager.scale(2, 2, 0);
        fr.drawStringWithShadow(ForgeSpoof.NAME, this.width / 4.0F - fr.getStringWidth(ForgeSpoof.NAME) / 2.0F, 10, 0xFFFFFF55);
        GlStateManager.scale(0.5, 0.5, 0);
        fr.drawStringWithShadow(ForgeSpoof.VERSION, this.width / 2.0F + fr.getStringWidth(ForgeSpoof.NAME), 17, -1);
        GlStateManager.popMatrix();
        textField.drawTextBox();
        modidList.drawScreen(mouseX, mouseY, partialTicks);
        Keyboard.enableRepeatEvents(textField.isFocused());
        if (mc.thePlayer.ticksExisted % 10 == 0) {
            textField.updateCursorCounter();
        }
        if (textField.getText().isEmpty() && !textField.isFocused()) {
            fr.drawStringWithShadow("Enter a mod id", this.width / 2.0F - 47, 53, 0xFF555555);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        textField = new GuiTextField(0, fontRendererObj, this.width / 2 - 50, 50, 100, 14);
        modidList = new GuiSlotModidList(this);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        textField.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        textField.textboxKeyTyped(typedChar, keyCode);
        if (keyCode == Keyboard.KEY_RETURN && this.textField.isFocused()) {
            addModid();
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        modidList.actionPerformed(button);
    }

    private void addModid() {
        if (!textField.getText().trim().isEmpty()) {
            ForgeSpoof.modIds.add(textField.getText());
            textField.setText("");
            ForgeSpoof.configHandler.save();
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
    
}
