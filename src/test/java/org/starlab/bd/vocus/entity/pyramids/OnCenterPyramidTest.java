package org.starlab.bd.vocus.entity.pyramids;

import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.starlab.bd.vocus.util.Utility;

public class OnCenterPyramidTest {

	@BeforeClass
	public static void preload(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public static final String IMAGE_PATH = "src/test/resources/L_on_off.png";
	@Test
	public void testLoadOnCenterPyr() throws Exception {
		int sigma = 3;
		Mat srcImage = Utility.getSpliitedImage(Utility.getLabImage(Utility.loadImage(IMAGE_PATH))).get(0);
		Mat surroundInt = SurroundPyramid.getSurround(srcImage,sigma);
		Pyramid surroundPyramid = new Pyramid();
		surroundPyramid.getPyramids().add(surroundInt);
		SurroundPyramid pyr = new SurroundPyramid(srcImage, sigma);
		OnCenterPyramid onnPyr = new OnCenterPyramid(srcImage, pyr);
		System.out.println("Onn:\n" + onnPyr.getPyramids().get( onnPyr.getPyramids().size()-1).dump());
	}

}
