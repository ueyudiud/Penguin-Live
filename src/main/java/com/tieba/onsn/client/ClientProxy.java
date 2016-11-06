package com.tieba.onsn.client;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy
{
	private static String cachedID;
	private static String cachedPassword;

	/**
	 * Load local user information from save file.
	 */
	public static void loadLocalPlayerID()
	{
		File file = Minecraft.getMinecraft().mcDataDir;
		File file1 = new File(file, "live.cfg");
		Configuration config = new Configuration(file1);
		config.load();
		cachedID = config.get("user", "id", (String) null).getString();
		cachedPassword = config.get("uers", "password", (String) null).getString();
	}

	/**
	 * Save local user information to file.
	 * @param id
	 * @param password
	 */
	public static void saveLocalPlayerID(String id, String password)
	{
		File file = Minecraft.getMinecraft().mcDataDir;
		File file1 = new File(file, "live.cfg");
		Configuration configuration = new Configuration(file1);
		configuration.get("user", "id", (String) null).set(id);
		configuration.get("user", "password", (String) null).set(password);
		configuration.save();
	}
	
	public static String getCachedID()
	{
		return cachedID;
	}
	
	public static String getCachedPassword()
	{
		return cachedPassword;
	}
}