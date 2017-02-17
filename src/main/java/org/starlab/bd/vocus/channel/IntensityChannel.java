package org.starlab.bd.vocus.channel;

import org.starlab.bd.vocus.entity.feature.IntensityFeature;
import org.starlab.bd.vocus.util.Utility;

public class IntensityChannel extends AbsColAndIntencityChannel<IntensityFeature> {
	IntensityFeature feature ;
	
	public IntensityChannel(IntensityFeature f){
		 feature = f;
	}
	
	@Override
	protected void init() {
		super.init();
		feature.setSrcImage(getSrcimg().getImageChannelLab().get(0));			
		feature.extractFeature();
		setImage(feature.getL());
		Utility.ShowImage(feature.getL(), "Source L");
	}	
	
}
