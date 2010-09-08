package com.telekawaru.gloomroomtd.classes;

import com.stickycoding.rokon.Texture;

/**
 * TextTexture.java A texture specifically tailored for bitmap fonts
 * 
 * @author Richard
 */
public class QuietFontTexture extends Texture {

	protected char[] chars;

	/**
	 * Creates a FontTexture from an asset, assuming there is only 1 row
	 * 
	 * @param filename
	 *            path to a valid image file in assets
	 * @param characters
	 *            the string of characters
	 */
	public QuietFontTexture(String filename, String characters) {
		this(filename, characters, characters.length(), 1);
	}

	/**
	 * Creates a FontTexture from an asset, the character string must be passed
	 * 
	 * @param filename
	 *            path to a valid image file in assets
	 * @param characters
	 *            the string of characters
	 * @param columns
	 *            number of columns
	 * @param rows
	 *            number of rows
	 */
	public QuietFontTexture(String filename, String characters, int columns, int rows) {
		super(filename, columns, rows);
		this.chars = new char[characters.length()];
		for (int i = 0; i < characters.length(); i++)
			this.chars[i] = characters.charAt(i);
	}

	/**
	 * Returns the index of a given character
	 * 
	 * @param ch
	 *            character to find
	 * @return tile index, -1 if not found
	 */
	public int charPos(char ch) {
		for (int i = 0; i < this.chars.length; i++)
			if (this.chars[i] == ch) return i;
		return -1;
	}

	/**
	 * Returns the column position of a given character
	 * 
	 * @param ch
	 *            character to find
	 * @return column index, -1 if not found
	 */
	public int getCol(char ch) {
		return this.charPos(ch) % this.columns;
	}

	/**
	 * Returns the row position of a given character
	 * 
	 * @param ch
	 *            character to find
	 * @return row index, -1 if not found
	 */
	public int getRow(char ch) {
		int charPos = this.charPos(ch);
		return charPos - (charPos % this.columns) / this.columns;
	}

}
