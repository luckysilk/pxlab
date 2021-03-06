package de.pxlab.pxl.display;

import java.awt.*;
import de.pxlab.pxl.*;
import de.pxlab.pxl.spectra.SpectralLightSource;
import de.pxlab.pxl.spectra.SpectralLightFilter;

/**
 * This is a surface simulation of a center and surround display illuminated by
 * two different light sources.
 * 
 * @author H. Irtel
 * @version 0.2.0
 */
public class MetamericSurfaces extends SpectralColorDisplay {
	public ExPar Illuminant = new ExPar(SPECTRUM, new ExParValue("C"),
			"Illuminant spectrum");
	public ExPar IlluminantLuminance = new ExPar(0.0, 200.0, new ExParValue(
			100.0), "Luminance of the illuminant");
	public ExPar Background = new ExPar(SPECTRUM, new ExParValue("N 4/"),
			"Background reflectance");
	public ExPar Center1 = new ExPar(SPECTRUM, new ExParValue("MG1"),
			"Center 1 reflectance");
	public ExPar Center2 = new ExPar(SPECTRUM, new ExParValue("MG2"),
			"Center 2 reflectance");
	public ExPar CenterSize = new ExPar(PROPORT, new ExParValue(0.33),
			"Center square size");

	/** Cunstructor creating the title of the display. */
	public MetamericSurfaces() {
		setTitleAndTopic("Two Metameric Surfaces", SPECTRAL_COLOR_DSP | DEMO);
	}
	private ExPar c1, c2, c3, c4;
	private int s1, s2, s3, s4;

	protected int create() {
		SpectralLightSource ls = getLightSource(this.Illuminant,
				this.IlluminantLuminance);
		SpectralLightFilter bg = getLightFilter(this.Background);
		SpectralLightFilter ct1 = getLightFilter(this.Center1);
		SpectralLightFilter ct2 = getLightFilter(this.Center2);
		c1 = enterLight("Left background", ls, bg);
		c2 = enterLight("Right background", ls, bg);
		c3 = enterLight("Left center", ls, ct1);
		c4 = enterLight("Right center", ls, ct2);
		s1 = enterDisplayElement(new Bar(c1));
		s2 = enterDisplayElement(new Bar(c2));
		s3 = enterDisplayElement(new Bar(c3));
		s4 = enterDisplayElement(new Bar(c4));
		return (s4);
	}

	protected void computeGeometry() {
		double p = CenterSize.getDouble();
		Rectangle r1 = firstSquareOfTwo(width, height, true);
		Rectangle r2 = secondSquareOfTwo(width, height, true);
		getDisplayElement(s1).setRect(r1);
		getDisplayElement(s2).setRect(r2);
		getDisplayElement(s3).setRect(innerRect(r1, p));
		getDisplayElement(s4).setRect(innerRect(r2, p));
	}
}
