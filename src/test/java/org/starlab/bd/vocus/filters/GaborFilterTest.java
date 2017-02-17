package org.starlab.bd.vocus.filters;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.starlab.bd.vocus.entity.pyramids.GaborPyramid;
import org.starlab.bd.vocus.util.Utility;

public class GaborFilterTest {	
	Mat onOff;
	
	@Before
	public void setUp() throws Exception{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		onOff = Utility.loadImage("src/main/resources/on_off.png");
	}

	@Test
	public void testGabor() {	   
		try {			
			
			showGaborImage(0);
			showGaborImage(90);
			showGaborImage(180);
			showGaborImage(270);
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Imgproc.filter2D(kernel, dest, CvType.CV_32F, kernel);
	}

	private void showGaborImage(double deg) throws IOException {
		Mat dest = GaborPyramid.getGaborImage(onOff, deg);
		 Utility.ShowImage(dest,"filterd_img gabor "+ deg);
		
			
		
	}
	
}