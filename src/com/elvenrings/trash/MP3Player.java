package com.elvenrings.trash;

import java.io.File;
import java.util.Scanner;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;

public class MP3Player
{

	public static void main(String[] args) throws Exception
	{
		File file = new File("/Users/iliasusalifu/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/MUSIC02.MP3");
		//File file = new File("/Users/iliasusalifu/Documents/Migrate/Migrate/Phone/Music1/uktop40/05 - Summertime Sadness (Cedric Gervais Vocal Down Mix) - Lana Del Rey.mp3");
		if(file.canRead())
		{
			System.out.println("Can read file.");
			MediaLocator mrl = new MediaLocator(file.toURI().toURL());
			Player player = Manager.createPlayer(mrl);
			player.start();
			System.out.println("Playing file.");
			Scanner sc = new Scanner(System.in);
			
			do
			{
				String s = sc.nextLine();
				if(s.equalsIgnoreCase("stop"))
					
				{
					System.exit(0);
				}
				
			}while(true);
		}
		else
		{
			System.out.println("Cannot read file.");
		}
	}

}
