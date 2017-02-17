package org.starlab.bd.vocus.filters;

import java.util.List;

import org.opencv.core.Mat;

public interface Filter {
 public Mat getFilteredImage(Mat srcImage);
 public List<Mat> getFilteredImageList(Mat srcImageList);
}
