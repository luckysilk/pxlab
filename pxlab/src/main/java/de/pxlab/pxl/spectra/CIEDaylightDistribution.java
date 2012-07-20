package de.pxlab.pxl.spectra;

/**
 * A factory class which can create CIE daylight spectra generated by 3 basis
 * functions according to the CIE method.
 * 
 * @author H. Irtel
 * @version 0.1.1
 */
public class CIEDaylightDistribution {
	/**
	 * These data are based on Wyszecki & Stiles (1982), page 762. 5 nm steps
	 * have been interpolated linearly from 10 nm data.
	 */
	/* Error at S0, 440 nm corrected (H. Irtel, 05/11/00) */
	private static final float[] CIE_S0 = { 61.50F, 65.15F, 68.80F, 66.10F,
			63.40F, 64.60F, 65.80F, 80.30F, 94.80F, 99.80F, 104.80F, 105.35F,
			105.90F, 101.35F, 96.80F, 105.35F, 113.90F, 119.75F, 125.60F,
			125.55F, 125.50F, 123.40F, 121.30F, 121.30F, 121.30F, 117.40F,
			113.50F, 113.50F, 113.50F, 112.15F, 110.80F, 108.65F, 106.50F,
			107.65F, 108.80F, 107.05F, 105.30F, 104.85F, 104.40F, 102.20F,
			100.00F, 98.00F, 96.00F, 95.55F, 95.10F, 92.10F, 89.10F, 89.80F,
			90.50F, 90.40F, 90.30F, 89.35F, 88.40F, 86.20F, 84.00F, 84.55F,
			85.10F, 83.50F, 81.90F, 82.25F, 82.60F, 83.75F, 84.90F, 83.10F,
			81.30F, 76.60F, 71.90F, 73.10F, 74.30F, 75.35F, 76.40F, 69.85F,
			63.30F, 67.50F, 71.70F, 74.35F, 77.00F };
	/**
	 * These data are based on Wyszecki & Stiles (1982), page 762. 5 nm steps
	 * have been interpolated linearly from 10 nm data.
	 */
	private static final float[] CIE_S1 = { 38.00F, 40.20F, 42.40F, 40.45F,
			38.50F, 36.75F, 35.00F, 39.20F, 43.40F, 44.85F, 46.30F, 45.10F,
			43.90F, 40.50F, 37.10F, 36.90F, 36.70F, 36.30F, 35.90F, 34.25F,
			32.60F, 30.25F, 27.90F, 26.10F, 24.30F, 22.20F, 20.10F, 18.15F,
			16.20F, 14.70F, 13.20F, 10.90F, 8.60F, 7.30F, 6.00F, 5.10F, 4.20F,
			3.05F, 1.90F, 0.95F, 0.00F, -0.80F, -1.60F, -2.55F, -3.50F, -3.50F,
			-3.50F, -4.65F, -5.80F, -6.50F, -7.20F, -7.90F, -8.60F, -9.05F,
			-9.50F, -10.20F, -10.90F, -10.80F, -10.70F, -11.35F, -12.00F,
			-13.00F, -14.00F, -13.80F, -13.60F, -12.80F, -12.00F, -12.65F,
			-13.30F, -13.10F, -12.90F, -11.75F, -10.60F, -11.10F, -11.60F,
			-11.90F, -12.20F };
	/**
	 * These data are based on Wyszecki & Stiles (1982), page 762. 5 nm steps
	 * have been interpolated linearly from 10 nm data.
	 */
	private static final float[] CIE_S2 = { 5.30F, 5.70F, 6.10F, 4.55F, 3.00F,
			2.10F, 1.20F, 0.05F, -1.10F, -0.80F, -0.50F, -0.60F, -0.70F,
			-0.95F, -1.20F, -1.90F, -2.60F, -2.75F, -2.90F, -2.85F, -2.80F,
			-2.70F, -2.60F, -2.60F, -2.60F, -2.20F, -1.80F, -1.65F, -1.50F,
			-1.40F, -1.30F, -1.25F, -1.20F, -1.10F, -1.00F, -0.75F, -0.50F,
			-0.40F, -0.30F, -0.15F, 0.00F, 0.10F, 0.20F, 0.35F, 0.50F, 1.25F,
			2.00F, 2.60F, 3.20F, 3.65F, 4.10F, 4.40F, 4.70F, 4.90F, 5.10F,
			5.90F, 6.70F, 7.00F, 7.30F, 7.95F, 8.60F, 9.20F, 9.80F, 10.00F,
			10.20F, 9.25F, 8.30F, 8.95F, 9.60F, 9.05F, 8.50F, 7.75F, 7.00F,
			7.30F, 7.60F, 7.80F, 8.00F };
	private static final int _first = 360;
	private static final int _last = 740;
	private static final int _step = 5;
	private static final int _size = 77;

	/*
	 * Returns a CIE daylight spectrum for the given color temperature with
	 * wavelength range from 380 to 720 nm at 5 nm steps.
	 * 
	 * @param st the CIE daylight specification string.
	 */
	protected static SpectralDistribution instance(String st) {
		return (instance(380, 720, 5, temperature(st)));
	}

	/*
	 * Returns a CIE daylight spectrum for the given color temperature with
	 * wavelength range from 380 to 720 nm at 5 nm steps.
	 * 
	 * @param t the color temperature for the requested daylight distribution.
	 */
	protected static SpectralDistribution instance(double t) {
		return (instance(380, 720, 5, t));
	}

	/*
	 * Returns a CIE daylight spectrum with the given wavelength range for the
	 * given color temperature. These calculations are from Wyszecki & Stiles
	 * (1982), pages 145, 146.
	 */
	protected static SpectralDistribution instance(int first, int last,
			int step, double T) {
		double T2, T3, x, y, M1, M2;
		int start = 0;
		int limit = _size;
		int dstep = 1;
		if (T < 1000.0)
			T *= 100.0;
		// System.out.println("Daylight from " + first + " to " + last + " by "
		// + step + " nm for " + T + " K");
		if (step != _step) {
			if (step != 2 * _step) {
				throw new RuntimeException(
						"Only daylight spectra with steps of 5 or 10 nm are currently supported.");
			} else {
				dstep = 2;
			}
		}
		if ((first < _first) || (last > _last)) {
			throw new RuntimeException(
					"Only daylight spectra from 360 to 740 nm are currently supported.");
		}
		if ((first % step) != 0) {
			throw new RuntimeException(
					"Wavelength ranges must be limited by integer multiples of the wavelength step size.");
		}
		if (first > _first) {
			// index of first element used
			start = (first - _first) / _step;
		}
		if (last < _last) {
			// 1 + index of last element used
			limit -= ((_last - last) / _step);
		}
		T2 = T * T;
		T3 = T2 * T;
		if (T < 7000.0) {
			x = -4.6070 * (1000000000.0 / T3) + 2.9678 * (1000000.0 / T2)
					+ 0.09911 * (1000.0 / T) + 0.244063;
		} else {
			x = -2.0064 * (1000000000.0 / T3) + 1.9018 * (1000000.0 / T2)
					+ 0.24748 * (1000.0 / T) + 0.237040;
		}
		y = -3.000 * x * x + 2.870 * x - 0.275;
		M1 = (-1.3515 - 1.7703 * x + 5.9114 * y)
				/ (0.0241 + 0.2562 * x - 0.7341 * y);
		M2 = (0.0300 - 31.4424 * x + 30.0717 * y)
				/ (0.0241 + 0.2562 * x - 0.7341 * y);
		int n = (last - first) / step + 1;
		float[] data = new float[n];
		// System.out.println("Using indices from " + start + " to " + limit +
		// " by " + dstep);
		int j = 0;
		for (int i = start; i < limit; i += dstep) {
			// System.out.print(j + ": ");
			data[j++] = (float) (CIE_S0[i] + M1 * CIE_S1[i] + M2 * CIE_S2[i]);
			// System.out.println(data[j-1]);
		}
		return (new SpectralDistribution(first, last, step, data));
	}

	private static double temperature(String n) {
		double t = 0.0;
		try {
			t = Double.valueOf(n.substring(1)).doubleValue();
		} catch (NumberFormatException e) {
			new de.pxlab.pxl.NonFatalError("Invalid daylight specification: "
					+ n + "\nReplaced by D6500.");
			t = 6500.0;
		}
		return (t);
	}
}