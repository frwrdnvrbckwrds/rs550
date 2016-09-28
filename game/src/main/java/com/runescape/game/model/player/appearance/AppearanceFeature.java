package com.runescape.game.model.player.appearance;

public class AppearanceFeature {
	
	int styleIndex;
	int colorIndex;
	
	int[] styles;
	int[] colors;
	
	AppearanceFeature(int[] styles, int[] colors, int defaultStyleIndex, int defaultColorIndex) {
		this.styles = styles;
		this.colors = colors;
		styleIndex = defaultStyleIndex;
		colorIndex = defaultColorIndex;
	}
	
	public int getStyle() {
		return styles[styleIndex];
	}
	
	public int getColor() {
		return colors[colorIndex];
	}
	
	public void modifyStyle(int offset) {
		styleIndex += offset;
		if (styleIndex < 0) {
			styleIndex += styles.length;
		} else if (styleIndex > styles.length) {
			styleIndex -= styles.length;
		}
	}
	
	public void modifyColor(int offset) {
		colorIndex += offset;
		if (colorIndex < 0) {
			colorIndex += colors.length;
		} else if (styleIndex > colors.length) {
			colorIndex -= colors.length;
		}
	}
	
	public void randomizeStyle() {
		styleIndex = (int) (Math.random() * styles.length);
	}
	
	public void randomizeColor() {
		colorIndex = (int) (Math.random() * colors.length);
	}
	
	public int getStyleIndex() {
		return styleIndex;
	}
	
	public void setStyleIndex(int styleIndex) {
		this.styleIndex = styleIndex;
	}
	
	public int getColorIndex() {
		return colorIndex;
	}
	
	public void setColorIndex(int colorIndex) {
		this.colorIndex = colorIndex;
	}

}