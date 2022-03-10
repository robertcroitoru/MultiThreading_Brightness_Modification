import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;

import java.util.Hashtable;

public class ImagineaMea extends BufferedImage implements Interfata {// clasa care contine proprietatile imaginii, cu metodele ei aferente

	int width; // latimea
	int height; //lungimea
	int imageType; //tipul

	public ImagineaMea(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied,
			Hashtable<?, ?> properties) {
		super(cm, raster, isRasterPremultiplied, properties);
	} //hastable - mapeaza cheie cu valaorea

	public ImagineaMea(int width, int height, int imageType, IndexColorModel cm) {
		super(width, height, imageType, cm);
	}

	public ImagineaMea(int width, int height, int imageType) { 
		super(width, height, imageType);
	}

	public void setImageType(int imageType) { //setam tipul imaginii
		this.imageType = imageType;
	}

	public void setWidth(int width) { // setam latimea
		this.width = width;
	}

	public void setHeight(int height) { //setam inaltime
		this.height = height;
	}

	@Override
	public void setRGB(int x, int y, int rgb) { //setam rgb
		// TODO Auto-generated method stub
		super.setRGB(x, y, rgb);

	}

	@Override
	public int getHeight() { // luam inaltimea de la imagine
		return height;
	}

	@Override
	public int getWidth() { //luam latimea
		return width;
	}

	public int getImageType() {  //luam tipul imaginii 
		return imageType;
	}

}
