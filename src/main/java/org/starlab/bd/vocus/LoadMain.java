package org.starlab.bd.vocus;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.starlab.bd.vocus.channel.ColorChannel;
import org.starlab.bd.vocus.channel.IntensityChannel;
import org.starlab.bd.vocus.channel.OrientationChannel;
import org.starlab.bd.vocus.entity.SourceImage;
import org.starlab.bd.vocus.entity.feature.BlueFeature;
import org.starlab.bd.vocus.entity.feature.ColorFeature;
import org.starlab.bd.vocus.entity.feature.GreenFeature;
import org.starlab.bd.vocus.entity.feature.IntensityFeature;
import org.starlab.bd.vocus.entity.feature.RedFeature;
import org.starlab.bd.vocus.entity.feature.YellowFeature;
import org.starlab.bd.vocus.util.Utility;

public class LoadMain {
	Mat srcImage = null;

	public static void main(String args[]) {
		try {
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			SourceImage src = SourceImage.INSTANCE;
			Mat onOff = Utility.loadImage("src/main/resources/on_off.png");
			Utility.ShowImage(onOff, "ON OFF");

			/** Observer List */
			
			OrientationChannel orientationChannel = new OrientationChannel();
			ColorFeature cf = new ColorFeature(onOff);
			ColorChannel<BlueFeature> blueMapper = new ColorChannel<BlueFeature>(new BlueFeature(onOff));
			ColorChannel<RedFeature> redMapper = new ColorChannel<RedFeature>(new RedFeature(onOff));
			ColorChannel<GreenFeature> greenyMapper = new ColorChannel<GreenFeature>(new GreenFeature(onOff));
			ColorChannel<YellowFeature> yelllowMapper = new ColorChannel<YellowFeature>(new YellowFeature(onOff));
			IntensityChannel intensityChannel = new IntensityChannel(new IntensityFeature(onOff));
			
			src.updateImage(onOff);
			src.addObserver(intensityChannel);
			src.addObserver(orientationChannel);
			src.addObserver(redMapper);
			src.addObserver(greenyMapper);
			src.addObserver(blueMapper);
			src.addObserver(yelllowMapper);
			
			/** check SourceImage status. if not finished wait, else take another image from buffer */
			/**get image from from queue*/
			src.updateImage(onOff);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
