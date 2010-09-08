package com.telekawaru.gloomroomtd.classes;

import com.stickycoding.rokon.*;

public class TextOut {

	private static TexAtlas atlas;
	private static QuietFontTexture whiteText, whitePunc;
	private static GameSprite tSprite;
	private static float wPTW, wPTH, wTTW, wTTH;
	private static boolean initComplete = false;
	private static String textChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz()%$+-[]?# ";
	private static String puncChars = ".,:;'!";

	public static float getWidth(String string) {
		float cWidth = 0f;
		for (int i = 0; i < string.length(); i++)
			if (textChars.indexOf(string.charAt(i)) > -1)
				cWidth += wTTW;
			else if (puncChars.indexOf(string.charAt(i)) > -1) cWidth += wPTW;
		return cWidth;
	}

	public static void init() {
		atlas = new TexAtlas(2, 256, 256);
		atlas.insert(whiteText = new QuietFontTexture("text_white.png", textChars, 12, 7));
		atlas.insert(whitePunc = new QuietFontTexture("punc_white.png", puncChars));
		atlas.complete();
		wPTH = whitePunc.getTileHeight();
		wPTW = whitePunc.getTileWidth();
		wTTH = whiteText.getTileHeight();
		wTTW = whiteText.getTileWidth();
		initComplete = true;
	}

	public static boolean isPunc(char tChar) {
		return puncChars.contains(String.valueOf(tChar));
	}

	public static boolean isText(char tChar) {
		return textChars.contains(String.valueOf(tChar));
	}

	public static void write(String string, float x, float y, Layer layer) {
		if (initComplete == false) init();
		float xCoord = x;
		int pPos, tPos;
		for (int i = 0; i < string.length(); i++) {
			char cCh = string.charAt(i);
			if ((pPos = puncChars.indexOf(cCh)) > -1) {
				tSprite = new GameSprite(xCoord, y, wPTW, wPTH, whitePunc, pPos);
				xCoord += wPTW;
			} else if ((tPos = textChars.indexOf(cCh)) > -1) {
				tSprite = new GameSprite(xCoord, y, wTTW, wTTH, whiteText, tPos);
				xCoord += wTTW;
			}
			layer.add(tSprite);
		}
	}

	public static void write(String string, float x, float y, Layer layer, boolean isCentered) {
		if (initComplete == false) init();
		if (isCentered == true) write(string, x - (getWidth(string) / 2), y, layer);
		else
			write(string, x, y, layer);
	}
}
