package org.starlab.bd.vocus.entity.pyramids;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class OffSurroundPyramid extends Pyramid{

	public OffSurroundPyramid(Mat srcImage , SurroundPyramid surroundPyramid) {
		super(srcImage);	
		loadOffSurroundPyr(this, surroundPyramid);		
	}
	
	public OffSurroundPyramid(Pyramid srcPyramid , SurroundPyramid surroundPyramid) {		
		loadOffSurroundPyr(srcPyramid, surroundPyramid);		
	}

	private void loadOffSurroundPyr(Pyramid srcPyramid, SurroundPyramid surroundPyramid) {
		List<Mat> resultedImagePyr = new ArrayList<Mat>();
		for(int i = 0 ; i< surroundPyramid.getPyramids().size() ; i++){
			if(srcPyramid.getPyramids().get(i).type() != CvType.CV_8UC1){
				throw new IllegalArgumentException("src image should be CV_8UC1");
			}
			Mat tempImage = new Mat(srcPyramid.getPyramids().get(i).size(),CvType.CV_8UC1);
			Core.subtract(srcPyramid.getPyramids().get(i), surroundPyramid.getPyramids().get(i), tempImage);
			resultedImagePyr.add(tempImage);
			
		}
	}

}
