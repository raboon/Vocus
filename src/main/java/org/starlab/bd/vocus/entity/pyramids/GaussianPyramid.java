package org.starlab.bd.vocus.entity.pyramids;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class GaussianPyramid extends Pyramid {
	Size ksize = new Size(5, 5);
	public static final GaussianPyramid INSTANCE = new GaussianPyramid();
	private GaussianPyramid() {
		
	}
	public void loadGaussPyr(Mat srcImage) {
		super.getDownPyramid(srcImage, 0);
		List<Mat> gaussianPyramid = new ArrayList<>();
		for (Mat image : getPyramids()) {
			org.opencv.imgproc.Imgproc.GaussianBlur(image, image, ksize, 0, 0, org.opencv.core.Core.BORDER_DEFAULT);
			Size s = new Size(image.cols() / 2, image.rows() / 2);
			Imgproc.pyrDown(image, image, s);
			gaussianPyramid.add(image);
		}
		setImageList(gaussianPyramid);
	}	
	
}
