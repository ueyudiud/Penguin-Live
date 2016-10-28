package com.tieba.onsn.client.gui;

import com.tieba.onsn.client.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiChatLogin extends GuiScreen
{
	//TODO
	private GuiTextField fieldName = new GuiTextField(0, Minecraft.getMinecraft().fontRendererObj, -1, -1, -1, -1);
	private GuiTextField fieldPassword = new GuiTextField(0, Minecraft.getMinecraft().fontRendererObj, -1, -1, -1, -1);

	public GuiChatLogin()
	{
		fieldName.setText(ClientProxy.getCachedID());
		fieldPassword.setText(ClientProxy.getCachedPassword());
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return true;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		fieldName.drawTextBox();
		fieldPassword.drawTextBox();
	}

	@Override
	public void initGui()
	{
		super.initGui();
		buttonList.add(new GuiButton(0, -1, -1, "Login"));
		buttonList.add(new GuiButton(1, -1, -1, "Cancel"));
	}
}