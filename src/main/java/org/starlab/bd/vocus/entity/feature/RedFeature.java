package org.starlab.bd.vocus.entity.feature;

import java.awt.geom.Point2D;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.starlab.bd.vocus.entity.SourceImage;

public class RedFeature extends ColorFeature {

	public RedFeature(Mat srcImage) {
		super(srcImage);
	}

	@Override
	public void extractFeature() {
		for (int y = 0; y < a.height(); y++) {
			for (int x = 0; x < a.width(); x++) {
				Point p = new Point();

				p.x = (int) a.get(y, x)[SourceImage.L_INDEX];
				p.y = (int) b.get(y, x)[SourceImage.L_INDEX];
				int val = (int) (V_MAX - Point2D.distance(p.x, p.y, POINT_R.getX(), POINT_R.getY()));
				getFeaturedImage().put(y, x, val);
			}
		}
	}

}
