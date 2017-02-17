package org.starlab.bd.vocus.entity;

import java.util.List;
import java.util.Observable;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.starlab.bd.vocus.util.Utility;

import lombok.Getter;


public class SourceImage extends Observable {
	private Mat rgbImgSource;
	@Getter
	private List<Mat> imageChannelLab; 
	@Getter
	Size size;
	
	public static final int L_INDEX = 0;
	public static final int A_INDEX = 1;
	public static final int B_INDEX = 2;
	
	public Status status;
	public static final SourceImage INSTANCE = new SourceImage();
	private SourceImage() {		
	}

	public void updateImage(Mat src) {
		INSTANCE.status = Status.INIT;
		if (src.type() != CvType.CV_8UC3) {
			throw new IllegalArgumentException("Image is not CV_8UC3");
		}
		INSTANCE.rgbImgSource = Utility.getLabImage(src);
		size = rgbImgSource.size();
		INSTANCE.imageChannelLab = Utility.getSpliitedImage(rgbImgSource);		
		setChanged();
		notifyObservers();
	}
}
