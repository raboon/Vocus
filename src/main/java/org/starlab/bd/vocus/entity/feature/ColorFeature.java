package org.starlab.bd.vocus.entity.feature;

import java.awt.Point;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.starlab.bd.vocus.entity.SourceImage;
import org.starlab.bd.vocus.util.Utility;

import lombok.Getter;

public class ColorFeature extends AbstractFeature{

	public static final int V_MAX = 255;

	public static final int R_MAX = 255;
	public static final int G_MAX = 127;
	public static final int B_MAX = 0;
	public static final int Y_MAX = 255;

	public static final int R_MIN = 127;
	public static final int G_MIN = 0;
	public static final int B_MIN = 127;
	public static final int Y_MIN = 127;

	public static final Point POINT_R = new Point(R_MAX, R_MIN);
	public static final Point POINT_G = new Point(G_MIN, G_MAX);
	public static final Point POINT_B = new Point(B_MIN, B_MAX);
	public static final Point POINT_Y = new Point(Y_MIN, Y_MAX);
	
	protected List<Mat> splittedImage = null;
	protected Mat a,b;
	@Getter
	private Mat featuredImage ;
	
	public ColorFeature(Mat srcImage) {
		super(srcImage);
		splittedImage = Utility.getSpliitedImage(srcImage);
		featuredImage = new Mat(srcImage.size(), CvType.CV_8SC1);
		mapAB();
	}
	
	public void setFeature(ColorFeature f) {
		this.srcImage = f.srcImage;
		splittedImage = f.splittedImage;		
		featuredImage = new Mat(srcImage.size(), CvType.CV_8SC1);
		mapAB();
	}	
	
	protected void mapAB(){
		a = splittedImage.get(SourceImage.A_INDEX);
		b = splittedImage.get(SourceImage.B_INDEX);
		if(a.height() != b.height()){
			throw new IllegalArgumentException("Dimention missmatch between and b chanel. check height");
		}
		if(a.width() != b.width()){
			throw new IllegalArgumentException("Dimention missmatch between and b chanel. check width");
		}
	}

	@Override
	public void extractFeature() {
		// TODO Auto-generated method stub
		
	}

}
