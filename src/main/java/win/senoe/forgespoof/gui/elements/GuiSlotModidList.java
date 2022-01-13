package win.senoe.forgespoof.gui.elements;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.GuiScrollingList;
import win.senoe.forgespoof.ForgeSpoof;
import win.senoe.forgespoof.gui.GuiForgeSpoof;

public class GuiSlotModidList extends GuiScrollingList {

    private final GuiForgeSpoof parent;

    public GuiSlotModidList(GuiForgeSpoof parent) {
        super(parent.mc, 110, parent.height, 80, (int) (parent.height / 1.5), parent.width / 2 - 55, 15, parent.width, parent.height);
        this.parent = parent;
    }

    @Override
    protected void drawSlot(int slotIdx, int right, int top, int height, Tessellator tessellator) {
        String modid;
        try {
            modid = ForgeSpoof.modIds.get(slotIdx);
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        FontRenderer fr = parent.mc.fontRendererObj;
        fr.drawString(fr.trimStringToWidth(modid, listWidth - 10), this.left + 3, top + 2, -1, true);
        fr.drawString("x", right - fr.getCharWidth('x') - 1, top + 1.5F, 0xFFFF5555, true);
    }

    @Override
    protected void elementClicked(int index, boolean doubleClick) {
        ForgeSpoof.modIds.remove(index);
        ForgeSpoof.configHandler.save();
    }

    @Override
    protected int getSize() {
        return ForgeSpoof.modIds.size();
    }

    @Override
    protected int getContentHeight() {
        return (this.getSize()) * 35 + 1;
    }

    @Override
    protected boolean isSelected(int index) {
        return false;
    }

    @Override
    protected void drawBackground() {

    }

}
