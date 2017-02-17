package org.starlab.bd.vocus.channel;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.starlab.bd.vocus.entity.SourceImage;
import org.starlab.bd.vocus.entity.feature.IntensityFeature;
import org.starlab.bd.vocus.util.Utility;

public class IntensityChannelTest {
	public static final String IMAGE_PATH = "src/test/resources/on_off.png";
	IntensityChannel channel;
	SourceImage src = SourceImage.INSTANCE;
	@BeforeClass
	public static void preSetUp(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	@Before
	public void setUp() throws Exception{
		Mat onOff = Utility.loadImage(IMAGE_PATH);
		IntensityFeature iFeature = new IntensityFeature(Utility.getLabImage(onOff));
		iFeature.extractFeature();
		
		channel = new IntensityChannel(iFeature);		
		
		src.addObserver(channel);
		src.updateImage(onOff);
	}

	@Test
	public void testGenImagePyramids() throws Exception {
		int i = 0;		
		Utility.ShowImage(channel.getCenterSurroundPyramids().get(0).getPyramids().get(0), "center surround sigma 3");
		Utility.ShowImage(channel.getCenterSurroundPyramids().get(1).getPyramids().get(0), "center surround sigma 7");
		Utility.ShowImage(channel.getSurroundCenterPyramids().get(0).getPyramids().get(0), "surround center sigma 3");
		Utility.ShowImage(channel.getSurroundCenterPyramids().get(1).getPyramids().get(0), "surround center sigma 7");
		System.in.read();
	}


	@Test
	public void testExtractFeature() throws Exception {
		IntensityFeature iFeature = new IntensityFeature(Utility.getLabImage(Utility.loadImage(IMAGE_PATH)));
		iFeature.extractFeature();
		Utility.ShowImage(iFeature.getL(), "L");
		Thread.sleep(5000);
	}

}
