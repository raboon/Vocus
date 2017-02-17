package org.starlab.bd.vocus.entity.feature;

import java.awt.geom.Point2D;

import org.opencv.core.Mat;
import org.opencv.core.Point;

public class BlueFeature extends ColorFeature{

	public BlueFeature(Mat srcImage) {
		super(srcImage);
	}	
	

	@Override
	public void extractFeature() {		
		for (int y = 0; y < a.height(); y++) {
			for (int x = 0; x < a.width(); x++) {
				Point p = new Point();

				p.x = (int) a.get(y, x)[0];
				p.y = (int) b.get(y, x)[0];
				int val = (int) (V_MAX - Point2D.distance(p.x, p.y, POINT_G.getX(), POINT_G.getY()));
				getFeaturedImage().put(y, x, val);
			}
		} 
		
	}

}
