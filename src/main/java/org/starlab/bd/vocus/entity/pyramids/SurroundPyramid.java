package org.starlab.bd.vocus.entity.pyramids;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
public class SurroundPyramid extends Pyramid {
	
	public SurroundPyramid(Mat srcImage,int sigma) {
		super(srcImage);
		if(srcImage.type() != CvType.CV_8UC1){
			throw new IllegalArgumentException("src image should be CV_8UC1");
		}
		List<Mat> surroundPyr = new ArrayList<Mat>();
		for (Mat image : getPyramids()) {
			surroundPyr.add(SurroundPyramid.getSurround(image,sigma));
		}
		
	}

	protected static Mat getIntegralImg(Mat image) {
		Mat integral = new Mat(image.size(), CvType.CV_64FC1);
		Imgproc.integral(image, integral,-1);
		return integral;
	}
	
	protected static Mat getSurround( Mat srcImage, int sigma){
		Mat integral = getIntegralImg(srcImage);
		Mat surround = Mat.zeros(integral.rows(),integral.cols(), CvType.CV_8UC1);
//		System.out.println("int = \n" + integral.dump()+"\n");
		for(int y = 0 ; y <integral.rows() ; y++){
			for(int x = 0; x < integral.cols() ; x++){
				surround.put(y, x, SurroundPyramid.F(integral, x-sigma+1,y-sigma+1,(2*sigma+1), (2*sigma+1)));
			}
		}
		int N = (int)Math.pow(2*sigma +1, 2) -1;
		surround = surround.submat(new Rect(0, 0, surround.width()-1, surround.height()-1));
//		System.out.println("Surrndint 1 = \n" + surround.dump()+"\n N="+N);
		Core.subtract(surround, srcImage, surround);
//		System.out.println("Surrndint 2 = \n" + surround.dump()+"\n");
		Mat sur = Mat.zeros(surround.size(), CvType.CV_64FC1);
		Core.divide(N,surround,sur);
//		System.out.println("Surrndint 3 = \n" + sur.dump()+"\n");
		return sur;
	}

	protected static double F(Mat integral, int x, int y, int w, int h) {
		return getIntMatVal(integral, x + w - 1 , y + h - 1) 
				- getIntMatVal(integral, x - 1, y + h - 1) 
				- getIntMatVal(integral, x + w - 1, y - 1) 
				+ getIntMatVal(integral, x - 1 , y- 1);
	}
	
	private static double getIntMatVal(Mat integralImg, int x, int y){
		if(x < 0){
			x = 0;
		}
		else if(x >= integralImg.width()){
			x = integralImg.width() - 1;
		}
			
		if(y < 0){
			y = 0;
		}else if( y >= integralImg.height() ){
			y = integralImg.height() -1;
		}
//		if(x< 0 || y < 0 || x >= Intergral.width() || y >= Intergral.height()){
//			return 0;
//		}
		
		return integralImg.get(y , x )[0];
	}	
	
}
