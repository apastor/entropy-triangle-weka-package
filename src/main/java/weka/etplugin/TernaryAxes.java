/*
 *   This file is part of entropy-triangle-weka-package.
 *   
 *   Copyright (C) 2015  Antonio Pastor
 *   
 *   This program is free software: you can redistribute it
 *   and/or modify it under the terms of the GNU General Public License as
 *   published by the Free Software Foundation, either version 3 of the License,
 *   or (at your option) any later version.
 *   
 *   entropy-triangle-weka-package is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *   
 *   You should have received a copy of the GNU General Public License
 *   along with entropy-triangle-weka-package.
 *   If not, see <http://www.gnu.org/licenses/>.
 */

package weka.etplugin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.border.EmptyBorder;

final class TernaryAxes extends PlotElement {

	public static Font FONT_TICK = new Font("Arial", Font.PLAIN, 6);
	public static Font FONT_AXES = new Font("Arial", Font.PLAIN, 10);
	
	public static double FONT_SCALE_FACTOR = 1.0/200; // It must be between 0 and 1
	
	public static double TICK_SIZE = 0.02; // Axes tick size (Normalized to 1)
	
	Path2D axes = new Path2D.Double(Path2D.WIND_EVEN_ODD, 3);
	
	private String deltaH = new String("\u0394" + "H'");
	private String vI = new String("VI'");
	private String mI = new String("MI'");
	
	private double tick_dist = 0.1;
	private double tickLabels_dist = 0.1;
	
	public TernaryAxes (){
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		axes.moveTo(0, 0);
		axes.lineTo(1, 0);
		axes.lineTo(TernaryPlot.COS60, TernaryPlot.SIN60);
		axes.closePath();
	}
	
	public TernaryAxes (double tick_dist, double tickLabels_dist){
		this();
		setTick_dist(tick_dist);
		setTickLabels_dist(tickLabels_dist);
	}
	
	/**
	 * @return the tick_dist
	 */
	protected double getTick_dist() {
		return tick_dist;
	}

	/**
	 * @return the tickLabels_dist
	 */
	protected double getTickLabels_dist() {
		return tickLabels_dist;
	}

	/**
	 * @param tick_dist the tick_dist to set
	 */
	protected void setTick_dist(double tick_dist) {
		if(tick_dist<=1 && tick_dist >= 0 && tick_dist%0.05==0)
			this.tick_dist = tick_dist;
	}

	/**
	 * @param tickLabels_dist the tickLabels_dist to set
	 */
	protected void setTickLabels_dist(double tickLabels_dist) {
		if(tickLabels_dist<=1 && tickLabels_dist >= 0 && tickLabels_dist%0.05==0)
			this.tickLabels_dist = tickLabels_dist;
	}

	protected void paintComponent(Graphics g){
		Graphics2D g2D =(Graphics2D) g;
		
		AffineTransform aff_font = AffineTransform.getScaleInstance(FONT_SCALE_FACTOR, -FONT_SCALE_FACTOR); // FONT_SCALE_FACTOR (0,1)
		AffineTransform aff_rot60CW = (AffineTransform) aff_font.clone();
		AffineTransform aff_rot60ACW = (AffineTransform) aff_font.clone();

		aff_rot60CW.rotate(Math.toRadians(60));// rotation clockwise
		aff_rot60ACW.rotate(Math.toRadians(-60)); // rotation anticlockwise

		Rectangle2D sw;
		double sw_x, sw_y;
		
		g2D.setColor(Color.BLACK);
		g2D.setStroke(TernaryPlot.BS_NORMAL);
		
		g2D.draw(axes);
		
//		draw tick lines
		if (tick_dist!=0){
			for (double i=tick_dist; i<0.99; i+=tick_dist) {
				g2D.draw(new Line2D.Double(i-TICK_SIZE*TernaryPlot.COS60, -TICK_SIZE*TernaryPlot.SIN60, i, 0)); // DeltaH axis tick lines
				g2D.draw(new Line2D.Double(1-i*TernaryPlot.COS60, i*TernaryPlot.SIN60, 1-i*TernaryPlot.COS60+TICK_SIZE, i*TernaryPlot.SIN60)); // MI axis tick lines
				g2D.draw(new Line2D.Double((1-i-TICK_SIZE)*TernaryPlot.COS60, (1-i+TICK_SIZE)*TernaryPlot.SIN60, (1-i)*TernaryPlot.COS60, (1-i)*TernaryPlot.SIN60)); // VI axis tick lines
			}
		}

		if(tickLabels_dist!=0){
			g2D.setFont(FONT_TICK.deriveFont(aff_font));
			sw = new TextLayout (String.format("%.1f",0.1), g2D.getFont(), g2D.getFontMetrics().getFontRenderContext()).getBounds();
			sw_x = sw.getWidth();
			sw_y = sw.getHeight();
			//			System.out.println(sw_x);

			// draw tick labels	
			for (double i=tickLabels_dist ; i<0.99; i+=tickLabels_dist) {
				g2D.setFont(FONT_TICK.deriveFont(aff_rot60ACW)); // Rotated font for deltaH tick labels
				g2D.drawString(String.format("%.1f",i), (float) (i-(2*TICK_SIZE+sw_x)*TernaryPlot.COS60+sw_y*TernaryPlot.SIN60/2), (float) ((-2*TICK_SIZE-sw_x)*TernaryPlot.SIN60));
				//
				g2D.setFont(FONT_TICK.deriveFont(aff_rot60CW)); // Rotated font for VI tick labels
				g2D.drawString(String.format("%.1f",i), (float) ((1-i-2*TICK_SIZE-sw_x)*TernaryPlot.COS60-sw_y*TernaryPlot.SIN60/2), (float) ((1-i+2*TICK_SIZE+sw_x)*TernaryPlot.SIN60));
				//
				g2D.setFont(FONT_TICK.deriveFont(aff_font)); // Set scaled straight font for MI tick labels
				g2D.drawString(String.format("%.1f",i), (float) (1-i*TernaryPlot.COS60+2*TICK_SIZE), (float) (i*TernaryPlot.SIN60-sw_y/2));
			}

			// draw 1 tick labels
			g2D.setFont(FONT_TICK.deriveFont(aff_rot60ACW)); // Rotated font for deltaH tick labels
			g2D.drawString(String.format("%d",1), (float) (1-(2*TICK_SIZE+sw_x)*TernaryPlot.COS60+sw_y*TernaryPlot.SIN60/2), (float) ((-2*TICK_SIZE-sw_x)*TernaryPlot.SIN60));
			//
			g2D.setFont(FONT_TICK.deriveFont(aff_rot60CW)); // Rotated font for VI tick labels
			g2D.drawString(String.format("%d",1), (float) ((-2*TICK_SIZE-sw_x)*TernaryPlot.COS60-sw_y*TernaryPlot.SIN60/2), (float) ((2*TICK_SIZE+sw_x)*TernaryPlot.SIN60));
			//
			g2D.setFont(FONT_TICK.deriveFont(aff_font)); // Set scaled straight font for MI tick labels
			g2D.drawString(String.format("%d",1), (float) (TernaryPlot.COS60+2*TICK_SIZE), (float) (TernaryPlot.SIN60-sw_y/2));
		}
		

		g2D.setFont(FONT_AXES.deriveFont(aff_font));
		sw = new TextLayout (deltaH, g2D.getFont(), g2D.getFontMetrics().getFontRenderContext()).getBounds();
		sw_x = sw.getWidth();
		sw_y = sw.getHeight();
		//			System.out.println(sw);
		g2D.drawString(deltaH, (float) (0.5*(1-sw_x)), (float) (-sw_y-7*TICK_SIZE));

		g2D.setFont(FONT_AXES.deriveFont(aff_rot60ACW));
		sw = new TextLayout (vI, g2D.getFont(), g2D.getFontMetrics().getFontRenderContext()).getBounds();
		sw_x = sw.getWidth();
		sw_y = sw.getHeight();
		//			System.out.println(sw);
		g2D.drawString(vI, (float) (0.25-5*TICK_SIZE/TernaryPlot.COS60), (float) ((0.5+5*TICK_SIZE)*TernaryPlot.SIN60));

		g2D.setFont(FONT_AXES.deriveFont(aff_rot60CW));
		sw = new TextLayout (vI, g2D.getFont(), g2D.getFontMetrics().getFontRenderContext()).getBounds();
		sw_x = sw.getWidth();
		sw_y = sw.getHeight();
		//			System.out.println(sw);
		g2D.drawString(mI, (float) (0.75+5*TICK_SIZE/TernaryPlot.COS60-sw_y/2), (float) ((0.5+sw_y+5*TICK_SIZE)*TernaryPlot.SIN60));
	}

	@Override
	public boolean isTopElement() {
		return false;
	}

	@Override
	public int ElementType() {
		return PlotElement.AXES_ELEMENT;
	}
}
