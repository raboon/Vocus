package org.starlab.bd.vocus.entity.feature;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.starlab.bd.vocus.util.Utility;

import lombok.Getter;

public class IntensityFeature extends AbstractFeature {

	@Getter
	protected Mat l;
	
	public IntensityFeature(Mat srcImage) {
		super(srcImage);
	}

	@Override
	public void extractFeature() {
		if(srcImage.type() == CvType.CV_8UC3){
			l = Utility.getSpliitedImage(srcImage).get(0);
		}else if(srcImage.type() == CvType.CV_8UC1){
			l = srcImage;
		}else{
			throw new IllegalArgumentException("Image type is not CV_8SC3 or CV_8SC1, type found:"+srcImage.type());
		}
	}
	
	public void setFeature(ColorFeature f) {
		this.l = f.splittedImage.get(0);
	}

}
