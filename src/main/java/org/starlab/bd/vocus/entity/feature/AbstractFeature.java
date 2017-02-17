package org.starlab.bd.vocus.entity.feature;

import org.opencv.core.Mat;

import lombok.Setter;

public abstract class AbstractFeature implements Feature{
	
	@Setter
	protected Mat srcImage;
	 
	public AbstractFeature(Mat srcImage){
		this.srcImage = srcImage;
	}	
	
}
