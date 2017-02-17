package org.starlab.bd.vocus.util;

import java.awt.Graphics;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Utility {

	public static boolean ShowImage(Mat frame, String screenName) {
		MatOfByte buffer = new MatOfByte();
		Imgcodecs.imencode(".png", frame, buffer);
		try {
			System.out.println("frame = \n" + frame.dump()+"\n");
			Image image = ImageIO.read(new ByteArrayInputStream(buffer.toArray()));
			JPanel panel = new JPanel() {
				private static final long serialVersionUID = 1L;

				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(image, 0, 0, null); // see javadoc for more info
													// on the parameters
				}
			};
			JFrame viewBoard = new JFrame(screenName);
			viewBoard.setBounds(0, 0, (int) frame.size().width, (int) frame.size().height);
			JFrame.setDefaultLookAndFeelDecorated(true);
			viewBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			viewBoard.add(panel);
			viewBoard.setVisible(true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static Mat loadImage(String imageFileName) throws Exception {

		return Imgcodecs.imread(imageFileName);
	}

	public static Mat getBlackAndWhiteImage(Mat frame) {
		Mat dstFrame = new Mat(frame.size(), CvType.CV_8UC1);
		Imgproc.cvtColor(frame, dstFrame, Imgproc.COLOR_BGR2GRAY);

		return dstFrame;
	}

	public static Mat getLabImage(Mat frame) {
		Mat dstFrame = new Mat(frame.size(),frame.type());
		Imgproc.cvtColor(frame, dstFrame, Imgproc.COLOR_RGB2Lab);

		return dstFrame;
	}

	public static List<Mat> getUpnPyramid(Mat srcImage) {
		List<Mat> pyramids = new ArrayList<Mat>();
		Mat tempImage = srcImage;
		pyramids.add(tempImage);

		for (int i = 1; i <= 4; i++) {
			Size s = new Size(tempImage.cols() * 2, tempImage.rows() * 2);
			Imgproc.pyrUp(tempImage, tempImage, s);
			pyramids.add(tempImage);
		}

		return pyramids;
	}
	
	public static List<Mat> getSpliitedImage(Mat srcImage){
		if(srcImage.type() != CvType.CV_8UC3){
			throw new IllegalArgumentException("Source iamge is not 3 chanel");
		}
		List<Mat> images = new ArrayList<>();
		org.opencv.core.Core.split(srcImage, images);
		return images;
	}
}
