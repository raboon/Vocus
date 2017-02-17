package org.starlab.bd.vocus.filters;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public enum GaborFilter implements Filter {
	INSTANCE;
	
	int pos_kernel_size = 21;
	int pos_sigma = 5;
	int pos_lm = 50;
	int pos_th = 0;
	int pos_gamma = 0;
	int pos_psi = 90;
	double thetaDeg;
	List<Double> thetas ;
	
	public void init(List<Double> thetas){
		this.thetas = thetas;
	}

	@Override
	public Mat getFilteredImage(Mat inputImg) {
		
		double sigma = 1.4;
		double lambda = 40;
		double gamma = 1.0;
		double psiDeg = 0;

		double theta = thetaDeg * Math.PI / 180; // radian
		double psi = psiDeg * Math.PI / 180; // radian
		
		Size ksize = new Size(pos_kernel_size, pos_kernel_size);
		Mat kernel = Imgproc.getGaborKernel(ksize, sigma, theta, lambda, gamma);		
		Mat dest = new Mat(inputImg.rows(), inputImg.cols(), CvType.CV_8UC1);
		Imgproc.filter2D(inputImg, dest, inputImg.type(), kernel);
		//Utility.ShowImage(dest, "filterd_img gabor 0");
		return dest;

	}
	
	@Override	
	public List<Mat> getFilteredImageList(Mat inputImg)  {
		
		List<Mat> filteredImageList = new ArrayList<>();
		thetas.forEach(th -> {
			thetaDeg = th;
			filteredImageList.add(getFilteredImage(inputImg));
		});
		return filteredImageList;
	}

	
}
