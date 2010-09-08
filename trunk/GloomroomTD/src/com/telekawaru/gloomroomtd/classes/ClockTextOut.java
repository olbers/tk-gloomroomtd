package com.telekawaru.gloomroomtd.classes;

import com.stickycoding.rokon.*;

public class ClockTextOut {

	private static TexAtlas atlas;
	private static GameSprite tSprite;
	private static boolean initComplete = false;
	private static String[] fontChars = new String[3];
	private static QuietFontTexture[] fontTextures = new QuietFontTexture[3];

	public static void init() {
		atlas = new TexAtlas(100, 2048, 2048);
		fontChars[0] = new String("01234567890ap");
		fontChars[1] = new String(":");
		fontChars[2] = new String("m");
		for (int i = 0; i < fontChars.length; i++) {
			Debug.print("Inserting Texture: clock_font" + Integer.toString(i) + ".png");
			atlas.insert(fontTextures[i] = new QuietFontTexture("clock_font" + Integer.toString(i) + ".png", fontChars[i], fontChars[i].length(), 1));
		}
		atlas.complete();
		initComplete = true;
	}

	public static void write(String string, float x, float y, Layer layer) {
		if (initComplete == false) init();
		float xCoord = x;
		int tPos;
		boolean fMatch = false;
		for (int i = 0; i < string.length(); i++) {
			char cCh = string.charAt(i);
			fMatch = false;
			Debug.print("Looking for char: " + cCh);
			for (int fi = 0; fi < fontChars.length; fi++) {
				Debug.print("Checking in " + Integer.toString(fi) + " [" + fontChars[fi] + "]");
				if ((tPos = fontChars[fi].indexOf(cCh)) > -1) {
					Debug.print("found it");
					fMatch = true;
					tSprite = new GameSprite(xCoord, y, fontTextures[fi].getTileWidth(), fontTextures[fi].getTileHeight(), fontTextures[fi], tPos);
					xCoord += fontTextures[fi].getTileWidth();
					break;
				} else {
					Debug.print("didn't find it...");
				}
			}
			if (fMatch == true) layer.add(tSprite);
		}
	}
}
