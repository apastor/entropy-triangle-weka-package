/*
 *   This file is part of entropy-triangle-weka-package.
 *   
 *   entropy-triangle-weka-package is free software: you can redistribute it
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

/*
 *    TestTriangle.java
 *    Copyright (C) 2014-2015 Antonio A. Pastor Valles
 */

package weka.etplugin;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class TestTriangle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final JFrame frame = new JFrame();
		final EntropyTrianglePanel et = new EntropyTrianglePanel();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
//				System.out.println("Created GUI on EDT? "+
//						SwingUtilities.isEventDispatchThread());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(et);
				frame.setVisible(true);
				frame.pack();
			}
		});
		try {
			DataSource source = new DataSource("./datasets/segment-challenge.arff");
			Instances train = source.getDataSet();
			Instances test = DataSource.read("./datasets/segment-test.arff");
			train.setClassIndex(train.numAttributes() - 1);
			test.setClassIndex(test.numAttributes() - 1);
			weka.classifiers.trees.J48 j48 = new weka.classifiers.trees.J48();
			j48.buildClassifier(train);
			Evaluation eval = new Evaluation(train);
//			System.out.println(test.toString());
			eval.evaluateModel(j48, test);
//			System.out.println(eval.toSummaryString(true));
			et.addData(eval, j48, test.relationName(), null);;
			
//			System.out.println(eval.numInstances());
			eval = new Evaluation(train);
			weka.classifiers.bayes.NaiveBayes nb = new weka.classifiers.bayes.NaiveBayes();
			nb.buildClassifier(train);
			eval.evaluateModel(nb, test);
			et.addData(eval, nb, test.relationName(), null);
//			System.out.println(eval.numInstances());
			eval = new Evaluation(train);
			weka.classifiers.rules.ZeroR zr = new weka.classifiers.rules.ZeroR();
			zr.buildClassifier(train);
			eval.evaluateModel(zr, test);
			et.addData(eval, zr, test.relationName(), null);
			
			eval = new Evaluation(train);
			weka.classifiers.rules.OneR or = new weka.classifiers.rules.OneR();
			or.buildClassifier(train);
			eval.evaluateModel(or, test);
			et.addData(eval, or, test.relationName(), null);
			
		} catch (Exception e) {
			System.out.println("Error on main");
			e.printStackTrace();
		}
//		
	}
}
