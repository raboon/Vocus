package org.starlab.bd.vocus.entity.pyramids;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaplacePyramid extends Pyramid {
	private GaussianPyramid gasPyr = GaussianPyramid.INSTANCE;
	public static final LaplacePyramid INSTANCE = new LaplacePyramid();
	public LaplacePyramid() {
		
	}	

	public void loadLpcaePyramid(Mat srcImg) {
		List<Mat> laplacePyr = new ArrayList<>();
		gasPyr.loadGaussPyr(srcImg);
		this.getDownPyramid(srcImg, 0);
		for (int i = getPyramids().size()-1; i >1; i--) {
			Mat tempImage = gasPyr.getPyramids().get(i);
			Mat nextImage = new Mat(gasPyr.getPyramids().get(i-1).size(),tempImage.type());
			Imgproc.pyrUp(tempImage, nextImage, gasPyr.getPyramids().get(i-1).size());
			org.opencv.core.Core.subtract(getPyramids().get(i-1), nextImage, nextImage);
			laplacePyr.add(nextImage);
		}
		
		getPyramids().clear();
		
		for(int i = laplacePyr.size()-1;i>=0;i--){
			getPyramids().add(laplacePyr.get(i));
		}
	}

}
