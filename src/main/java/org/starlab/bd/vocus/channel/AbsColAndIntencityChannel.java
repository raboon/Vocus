package org.starlab.bd.vocus.channel;

import java.awt.IllegalComponentStateException;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.starlab.bd.vocus.entity.feature.AbstractFeature;
import org.starlab.bd.vocus.entity.pyramids.OffSurroundPyramid;
import org.starlab.bd.vocus.entity.pyramids.OnCenterPyramid;
import org.starlab.bd.vocus.entity.pyramids.Pyramid;
import org.starlab.bd.vocus.entity.pyramids.SurroundPyramid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbsColAndIntencityChannel<F extends AbstractFeature> extends AbstractChannel {
	private List<Integer> sigmas;
	protected List<SurroundPyramid> surroundPyramids;
	protected List<OnCenterPyramid> centerSurroundPyramids;
	protected List<OffSurroundPyramid> surroundCenterPyramids;
	
	private Mat image;
			
	public AbsColAndIntencityChannel(){
		sigmas = new ArrayList<>();
		sigmas.add(3);		
		sigmas.add(7);
		surroundPyramids = new ArrayList<>();
		centerSurroundPyramids = new ArrayList<>();
		surroundCenterPyramids = new ArrayList<>();
	}
	
	protected void init() throws NullPointerException{
		surroundPyramids.clear();
		centerSurroundPyramids.clear();
		surroundCenterPyramids.clear();		
	}
	
	@Override
	protected void genImagePyramids()throws NullPointerException{
		init();
		if(image.type() != CvType.CV_8UC1){
			throw new IllegalComponentStateException("Image type should be CV_8UC1. but it was :"+ image.type());
		}
		pyramid = new Pyramid(image);		
	}
	
	@Override
	protected void calcConrastScaleMaps()throws NullPointerException {
		for(int sigma : sigmas){			
			SurroundPyramid surroundPyr = new SurroundPyramid(image, sigma);
			surroundPyramids.add(surroundPyr);
			centerSurroundPyramids.add(new OnCenterPyramid(image, surroundPyr));
			surroundCenterPyramids.add(new OffSurroundPyramid(image, surroundPyr));
		}	
	}

	@Override
	protected void calcFeatureMaps()throws NullPointerException {
		
		
	}

	@Override
	protected void calcConspicuityMaps()throws NullPointerException {
		// TODO Auto-generated method stub
		
	}
	
}
