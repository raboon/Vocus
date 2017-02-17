package org.starlab.bd.vocus.channel;

import java.util.Observable;
import java.util.Observer;

import org.starlab.bd.vocus.entity.SourceImage;
import org.starlab.bd.vocus.entity.pyramids.Pyramid;

import lombok.Getter;

@Getter
public abstract class AbstractChannel implements Observer{
	protected SourceImage srcimg ;
	protected Pyramid pyramid;
	
	protected abstract void genImagePyramids()throws NullPointerException;

	protected abstract void calcConrastScaleMaps()throws NullPointerException;

	protected abstract void calcFeatureMaps()throws NullPointerException;

	/**
	 * calculate feature specific saliancy
	 * @throws NullPointerException
	 */
	protected abstract void calcConspicuityMaps()throws NullPointerException;
	
	private void calcFeatureMap()throws NullPointerException{
		init();
		genImagePyramids();
		calcConrastScaleMaps();
		calcFeatureMaps();
		calcConspicuityMaps();
	}
	
	protected abstract void init()throws NullPointerException;
	
	@Override
	public void update(Observable o, Object arg) {		
		if (o.getClass() == SourceImage.class) {
			srcimg = (SourceImage)o;
			calcFeatureMap();
		}
	}
	
}
