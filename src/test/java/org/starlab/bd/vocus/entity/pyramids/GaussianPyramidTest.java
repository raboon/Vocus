package org.starlab.bd.vocus.entity.pyramids;

import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.starlab.bd.vocus.util.Utility;

public class GaussianPyramidTest {
	public static final String IMAGE_PATH = "src/test/resources/on_off.png";
	
	@Before
	public void setUp(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	@Test
	public void testLoadGaussPyr() throws Exception {
		GaussianPyramid gpyr = GaussianPyramid.INSTANCE;
		gpyr.loadGaussPyr(Utility.getSpliitedImage(Utility.getLabImage(Utility.loadImage(IMAGE_PATH))).get(0));
		for(int i =0;i<gpyr.getPyramids().size();i++){
			Utility.ShowImage(gpyr.getPyramids().get(i), "L"+i);
		}
		Thread.sleep(50000);
	}

}
