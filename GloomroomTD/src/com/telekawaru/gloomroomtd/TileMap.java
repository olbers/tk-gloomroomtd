package com.telekawaru.gloomroomtd;

import java.io.IOException;
import java.io.InputStream;
import com.stickycoding.rokon.Debug;
import com.stickycoding.rokon.tileengine.RectangularLayer;
import com.stickycoding.rokon.tileengine.TiledSprite;

public class TileMap {
	private int[][] tileMap;
	private int mapWidth, mapHeight;

	public TileMap(int width, int height) {
		this.mapWidth = width;
		this.mapHeight = height;
		this.tileMap = new int[this.mapHeight][this.mapWidth];
		String fileData = new String();
		try {
			int cCh;
			InputStream ioS = MainActivity.mAct.getAssets().open("mapdata/map01.txt");
			while ((cCh = ioS.read()) > -1)
				fileData += (char) cCh;
			ioS.close();
			Debug.print("Read " + Integer.toString(fileData.length()) + " bytes");
		} catch (IOException exception) {
			Debug.print("Unable to load map");
		}
		if (fileData.length() > 0) {
			String[] fileLines = fileData.replace("\r", "").split("\n");
			for (int o = 0; o < fileLines.length; o++) {
				String[] lineCols = fileLines[o].split(",");
				if (o == 0) if ((fileLines.length != this.mapHeight) || (lineCols.length != this.mapWidth)) {
					this.mapWidth = lineCols.length;
					this.mapHeight = fileLines.length;
					this.tileMap = new int[this.mapHeight][this.mapWidth];
				}
				for (int i = 0; i < lineCols.length; i++)
					try {
						this.tileMap[o][i] = Integer.valueOf(lineCols[i]);
					} catch (Exception exception) {
						Debug.print(exception.getMessage());
					}
			}
		}
	}

	public void generateGraphics(RectangularLayer layer) {
		layer.clear();
		for (int o = 0; o < this.mapHeight; o++)
			for (int i = 0; i < this.mapWidth; i++) {
				TiledSprite mapTile = new TiledSprite(layer, i, o, Textures.map01.getTileWidth(), Textures.map01.getTileHeight());
				mapTile.setTextureTile(Textures.map01, this.tileMap[o][i]);
				layer.add(mapTile);
			}
	}

	public int getTileIndex(float x, float y) {
		int tX = (int) (x / Textures.map01.getTileWidth());
		int tY = (int) (y / Textures.map01.getTileHeight());
		return this.tileMap[tY][tX];
	}

}
