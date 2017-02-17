package org.starlab.bd.vocus.channel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.starlab.bd.vocus.util.Utility;

public class LoadMainTest {

	@Before
	public void setUp(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	@Test
	public void testShowImage_LoadMainTest(){
		Mat image;
		try {
			image = Utility.loadImage("src/test/resources/on_off.png");
			assertEquals(true, Utility.ShowImage(image, "ON OFF"));
		} catch (Exception e) {		
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testGetBlackAndWhiteImage_LoadMainTest(){
		Mat image;
		try {
			image = Utility.loadImage("src/test/resources/on_off.png");
			assertEquals(true, Utility.ShowImage(Utility.getBlackAndWhiteImage(image), "Black And White"));
		} catch (Exception e) {		
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testGetLabImage_LoadMainTest(){
		Mat image;
		try {
			image = Utility.loadImage("src/test/resources/on_off.png");
			assertEquals(true, Utility.ShowImage(Utility.getLabImage(image), "Black And White"));
		} catch (Exception e) {		
			fail(e.getMessage());
		}
		
	}
}
