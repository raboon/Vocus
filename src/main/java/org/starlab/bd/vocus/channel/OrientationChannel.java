package org.starlab.bd.vocus.channel;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.starlab.bd.vocus.entity.SourceImage;
import org.starlab.bd.vocus.entity.pyramids.GaborPyramid;
import org.starlab.bd.vocus.entity.pyramids.LaplacePyramid;

import lombok.Getter;
import lombok.Setter;


@Setter
public class OrientationChannel extends AbstractChannel {	
	private List<Integer> thetaDeg = new ArrayList<>();
	@Getter
	private List<List<Mat>> gaborPyramids = new ArrayList<>();;
	private LaplacePyramid laplacePyr = LaplacePyramid.INSTANCE;
	private GaborPyramid gaborPyrInstance = GaborPyramid.INSTANCE;
	
	public OrientationChannel(){
		super();		
		init();
		thetaDeg.add(0);
		thetaDeg.add(90);
		thetaDeg.add(180);
		thetaDeg.add(270);
	}
	
	@Override
	protected void genImagePyramids() {
		
		laplacePyr.loadLpcaePyramid(SourceImage.INSTANCE.getImageChannelLab().get(0));
		for(int deg : thetaDeg){
			gaborPyrInstance.loadGaborPyramid(laplacePyr, deg);
			gaborPyramids.add(gaborPyrInstance.getPyramids());
		}	
	}

	protected void calcConrastScaleMaps() {
		

	}

	protected void calcFeatureMaps() {
		// TODO Auto-generated method stub

	}

	protected void calcConspicuityMaps() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void init() {
		gaborPyramids.clear();	
		laplacePyr.getPyramids().clear();
		laplacePyr.getGasPyr().getPyramids().clear();
	}

	

}
