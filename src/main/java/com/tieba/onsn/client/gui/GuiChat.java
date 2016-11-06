package com.tieba.onsn.client.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The chat GUI.
 * @author ueyudiud
 *
 */
@SideOnly(Side.CLIENT)
public class GuiChat extends GuiScreen
{
	private static final ResourceLocation BACKGROUND = null;//TODO
	private GuiTextBox textBox = new GuiTextBox(this);
	private String selectSnapshot;

	public GuiChat()
	{
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);//Render buttons.
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		super.actionPerformed(button);
		switch (button.id)
		{
		case 2 :
			ChatSender.sendChat(textBox.chattingElements, selectSnapshot);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		buttonList.add(new GuiButton(0, -1, -1, "Send"));//Send message to Tieba.
		buttonList.add(new GuiButton(1, -1, -1, "Clear"));//Clear text box.
		buttonList.add(new GuiButton(2, -1, -1, "Login Configuration"));//Login Configuration.
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return true;
	}
}