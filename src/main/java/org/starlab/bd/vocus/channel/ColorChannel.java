package org.starlab.bd.vocus.channel;

import org.starlab.bd.vocus.entity.feature.ColorFeature;

public class ColorChannel<F extends ColorFeature> extends AbsColAndIntencityChannel<F> {
 
	F feature ;
	
	public ColorChannel(F feature){
		this.feature = feature ;
	}
	
	protected void init(ColorFeature newFeature) {
		super.init();
		feature.setFeature(newFeature);		
	}

}
