package org.starlab.bd.vocus.entity.pyramids;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import lombok.Getter;

@Getter
public class GaborPyramid extends Pyramid {

	private static int pos_kernel_size = 21;
	private static double sigma = 1.4;
	private static double lambda = 40;
	private static double gamma = 1.0;
	private List<Mat> gaborImageList;
	private static Size ksize;
	public static final GaborPyramid INSTANCE = new GaborPyramid();
	
	private GaborPyramid(){
		
	}

	public void loadGaborPyramid(LaplacePyramid laplacePyr,int thetaDeg) {
		
		gaborImageList = new ArrayList<>();
		ksize = new Size(pos_kernel_size, pos_kernel_size);
		try {
			for (Mat src : laplacePyr.getPyramids()) {
				Mat dest = GaborPyramid.getGaborImage(src,thetaDeg);
				gaborImageList.add(dest);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		setImageList(gaborImageList);
	}

	public static Mat getGaborImage(Mat src,double thetaDeg) {
		double theta = thetaDeg * Math.PI / 180; // radian
		// double psi = psiDeg * Math.PI / 180; // radian
		
		Mat kernel = Imgproc.getGaborKernel(ksize, sigma, theta, lambda, gamma);
		Mat dest = new Mat(src.size(),src.type());;
		Imgproc.filter2D(src, dest, src.type(), kernel);
		return dest;
	}
}
