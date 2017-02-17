package org.starlab.bd.vocus.channel;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.starlab.bd.vocus.entity.SourceImage;
import org.starlab.bd.vocus.util.Utility;

public class OrientationChannelTest {
	List<Mat> imageList;

	public static final String IMAGE_PATH = "src/test/resources/on_off.png";
	private OrientationChannel ochannel ;
	SourceImage src = SourceImage.INSTANCE;
	@BeforeClass
	public static void preSetUp(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	@Before
	public void setUp() throws Exception{
		
		Mat onOff = Utility.loadImage(IMAGE_PATH);
		ochannel = new OrientationChannel();		
		
		src.addObserver(ochannel);
		src.updateImage(onOff);
	}

	@Test
	public void testGenImagePyramids() throws Exception {
		int i = 0;		
		
//		ochannel.genImagePyramids();
		for(List<Mat> gpyr : ochannel.getGaborPyramids()){
			System.out.println("Size:"+ gpyr.size());
			Utility.ShowImage(gpyr.get(0), i+"");
			i+=90;
		};
		
		System.in.read();
	}

}
