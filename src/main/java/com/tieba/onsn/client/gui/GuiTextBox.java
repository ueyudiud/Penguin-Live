package com.tieba.onsn.client.gui;

import net.minecraft.client.gui.Gui;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTextBox extends Gui
{
	public String chattingElements;
	
	public GuiTextBox(GuiChat chat)
	{
	}
}