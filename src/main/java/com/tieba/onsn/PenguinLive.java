package com.tieba.onsn;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "pl", name = "Penguin Live", version = "0.1.1", clientSideOnly = true)
public class PenguinLive
{
	@Instance
	public PenguinLive instance;

	public PenguinLive()
	{
		instance = this;
	}
	
	@SubscribeEvent
	public void onLoad(FMLPreInitializationEvent event)
	{
		
	}
}