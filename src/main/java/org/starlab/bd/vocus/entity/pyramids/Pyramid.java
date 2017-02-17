package org.starlab.bd.vocus.entity.pyramids;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Pyramid{	

	
	private List<Mat> imageList = new ArrayList<Mat>();
	private int offset = 0;	
	
	public Pyramid(){
		
	}
	
	public Pyramid(Mat srcImage){
		validate(srcImage);
		getDownPyramid(srcImage, offset);
	}
	
	protected void validate(Mat srcImage) {
		if(srcImage == null){
			throw new IllegalArgumentException("If source image is not assign");
		}else if(srcImage.type() != CvType.CV_8UC1){
			throw new IllegalArgumentException("Image is not CV_8UC1");
		}
	}
	
	public List<Mat> getDownPyramid(Mat srcImage, int offset) {
		imageList.clear();
		Mat tempImage = srcImage;
		imageList.add(tempImage);

		for (int i = 1; i < 5; i++) {
			Size s = new Size(tempImage.cols() / 2, tempImage.rows() / 2);
			Mat newImage = new Mat(tempImage.size(),tempImage.type());
			Imgproc.pyrDown(tempImage, newImage, s);
			if (i < offset)
				continue;
			tempImage = newImage;
			imageList.add(tempImage);
		}

		return imageList;
	}

	/**
	 * @return the scaleImagePyramids
	 */
	public List<Mat> getPyramids() {
		return imageList;
	}
	
	public void setImageList(List<Mat> newPyramid) {
		imageList.clear();
		imageList.addAll(newPyramid);
	}

	
	
}
