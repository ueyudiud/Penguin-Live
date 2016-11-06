package com.tieba.onsn.client.gui;

import java.io.IOException;

public class ChatSender implements Runnable
{
	public static void sendChat(String chattingElements, String selectSnapshot) throws IOException
	{
		new Thread(new ChatSender()).start();
	}

	@Override
	public void run()
	{
		try
		{
			//FIXME
		}
		catch (Exception exception)
		{
			
		}
	}
}