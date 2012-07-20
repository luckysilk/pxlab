package de.pxlab.pxl.gui;

import java.awt.*;

import de.pxlab.gui.*;
import de.pxlab.pxl.*;

/**
 * Create an adjustment panel with Smith & Pokorny LMS cone activity sliders.
 * 
 * @author H. Irtel
 * @version 0.2.1
 */
/*
 * 
 * 03/10/00
 */
public class LMSAdjustmentSliders extends ColorAdjustmentSliders {
	/**
	 * Create an adjustment panel with Smith & Pokorny LMS cone activity
	 * sliders.
	 */
	public LMSAdjustmentSliders(ColorServer ct) {
		super(PxlColor.CS_LMS, ct);
		double[] m = PxlColor.systemColor(PxlColor.WHITE).transform(csType);
		int n = (int) (m[0] + 9.0) / 10;
		double mx = (double) (10 * n);
		AxisModel model1 = new LinearAxisModel(0.0, mx, 0.0);
		slider1 = new Slider(Slider.VERTICAL, model1, 5, 0);
		slider1.setTrackColor(Color.red);
		slider1.setPreferredSpacing(cellHeight, 240, cellHeight);
		slider1.setAxisListener(new ColorAdjustmentSliders.Slider1Handler());
		n = (int) (m[1] + 9.0) / 10;
		mx = (double) (10 * n);
		AxisModel model2 = new LinearAxisModel(0.0, mx, 0.0);
		slider2 = new Slider(Slider.VERTICAL, model2, 5, 0);
		slider2.setTrackColor(Color.green);
		slider2.setPreferredSpacing(cellHeight, 240, cellHeight);
		slider2.setAxisListener(new ColorAdjustmentSliders.Slider2Handler());
		n = (int) (m[2] + 9.0) / 10;
		mx = (double) (10 * n);
		AxisModel model3 = new LinearAxisModel(0.0, mx, 0.0);
		slider3 = new Slider(Slider.VERTICAL, model3, 5, 0);
		slider3.setTrackColor(Color.blue);
		slider3.setPreferredSpacing(cellHeight, 240, cellHeight);
		slider3.setAxisListener(new ColorAdjustmentSliders.Slider3Handler());
		// Add the sliders to this panel.
		createLayout(slider1, slider2, slider3);
	}
}
