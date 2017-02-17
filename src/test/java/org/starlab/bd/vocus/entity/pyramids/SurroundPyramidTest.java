package org.starlab.bd.vocus.entity.pyramids;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.starlab.bd.vocus.entity.feature.IntensityFeature;
import org.starlab.bd.vocus.util.Utility;

public class SurroundPyramidTest {
	public static final String IMAGE_PATH = "src/test/resources/on_off.png";
	SurroundPyramid surPyr;
	IntensityFeature iFeature;
	@BeforeClass
	public static void preSetUp(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	@Before
	public void setUp() throws Exception{
//		Mat onOff = Utility.loadImage(IMAGE_PATH);
//		iFeature = new IntensityFeature(Utility.getLabImage(onOff));
//		iFeature.extractFeature();
//		surPyr = new SurroundPyramid(iFeature.getL(), 3);	
		
	}

	@Test
	public void testSurroundPyramid() throws Exception {
		
		Mat intImage = SurroundPyramid.getIntegralImg(iFeature.getL());
		
		Utility.ShowImage(intImage, "intImage");
		Thread.sleep(500000);
	}	
	
	@Test
	public void getCompareSurroundManualWithNormalStructure(){
		Mat image = Mat.ones(25,25, CvType.CV_8UC1);
		
		
		Mat surround = Mat.ones(image.rows(),image.cols(), CvType.CV_8UC1);
		image.put(image.rows()/2, image.cols()/2, 10);
		System.out.println("img = \n" + image.dump()+"\n");
		int sigma = 7;
		for(int y=0; y< image.rows();y++){
			for(int x = 0; x< image.cols(); x++){
				surround.put(y, x, getNeighbourSum(new Point(y,x),(sigma/2),image)/(Math.pow(sigma, 2) - 1));
			}
		}
		
		System.out.println("sur = \n" + surround.dump()+"\n");
		Mat onn1 = Mat.zeros(image.size(), CvType.CV_64FC1);
		Core.subtract(image, surround, onn1);
		System.out.println("Onn1:\n"+ onn1.dump());
		
		
		Mat surConvIMg = getNeighbourByConvolution(sigma,image);	
		Core.divide((Math.pow(sigma, 2) - 1), surConvIMg, surConvIMg);
		//System.out.println("sur = \n" + surConvIMg.dump()+"\n");
		Mat onn2 = Mat.zeros(image.size(), CvType.CV_64FC1);
		Core.subtract(image, surConvIMg, onn2);
		System.out.println("Onn2:\n"+ onn2.dump());
		
		sigma = 3;
			
//		Mat intImage = SurroundPyramid.getIntegralImg(image);
//		Mat surroundInt1 = Mat.zeros(intImage.rows(),intImage.cols(), CvType.CV_8UC1);
//		System.out.println("int = \n" + intImage.dump()+"\n");
//		for(int y = 0 ; y <image.rows() ; y++){
//			for(int x = 0; x < image.cols() ; x++){
//				surroundInt1.put(y, x, SurroundPyramid.F(intImage, x-sigma+1,y-sigma+1,(2*sigma+1), (2*sigma+1)));
//			}
//		}
//		
//		surroundInt1 = surroundInt1.submat(new Rect(0, 0, surroundInt1.width()-1, surroundInt1.height()-1));
		Mat surroundInt = SurroundPyramid.getSurround(image,sigma);	
		Mat onn = Mat.zeros(image.size(), CvType.CV_64FC1);
		Core.subtract(image, surroundInt, onn);
		System.out.println("Onn:\n"+ onn.dump());
		
	}
	
	public int getNeighbourSum(Point p, int sigma, Mat image){
		int sum = 0;
		for(int y = (int)(p.y - sigma) ; y <= p.y + sigma && y < image.rows() ; y++){
			for(int x = (int)(p.x - sigma) ; x <= p.x + sigma && x < image.cols() ; x++){
				if((y == p.y && x == p.x) || y <0 || x < 0){
					continue;
				}else{
					sum += image.get(y, x)[0];
				}
			}
		}
		return sum ;
	}
	
	public Mat getNeighbourByConvolution(int sigma, Mat image){
		Point anchor = new Point( -1, -1 );
		int  delta = 0;
		int  ddepth = -1;		
		Mat kernel = Mat.ones(sigma, sigma, CvType.CV_32FC1);
		kernel.put(sigma/2, sigma/2, 0);
		Mat surImg = new Mat(image.size(),image.type());
		Imgproc.filter2D(image, surImg, ddepth, kernel,anchor,delta, Core.BORDER_CONSTANT  );
		return surImg;
	}
}
