---
permalink: /programmatic-use/MyExperiment.html
layout: page
title: "MyExperiment.java"
tag: documentation
---

```java
import javax.swing.JFrame;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.evaluation.Evaluation;
import weka.etplugin.EntropyTrianglePanel;


public class MyExperiment {

 public static void main(String[] args) {

  JFrame frame = new JFrame();
  EntropyTrianglePanel et = new EntropyTrianglePanel();

  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.add(et);
  frame.setVisible(true);
  frame.pack();

  try {
    DataSource source = new DataSource("./datasets/segment-challenge.arff");
    Instances train = source.getDataSet();
    Instances test = DataSource.read("./datasets/segment-test.arff");
    train.setClassIndex(train.numAttributes() - 1);
    test.setClassIndex(test.numAttributes() - 1);

    Evaluation eval = new Evaluation(train);
    weka.classifiers.rules.ZeroR zr = new weka.classifiers.rules.ZeroR();
    zr.buildClassifier(train);
    eval.evaluateModel(zr, test);

    et.addData(eval, zr, test.relationName(), null);

    eval = new Evaluation(train);
    weka.classifiers.rules.OneR oner = new weka.classifiers.rules.OneR();
    oner.buildClassifier(train);
    eval.evaluateModel(oner, test);
    et.addData(eval, oner, test.relationName(), null);

    eval = new Evaluation(train);
    weka.classifiers.bayes.NaiveBayes nb = new weka.classifiers.bayes.NaiveBayes();
    nb.buildClassifier(train);
    eval.evaluateModel(nb, test);
    et.addData(eval, nb, test.relationName(), null);

    weka.classifiers.trees.J48 j48 = new weka.classifiers.trees.J48();
    j48.buildClassifier(train);
    eval = new Evaluation(train);
    eval.evaluateModel(j48, test);
    et.addData(eval, j48, test.relationName(), null);

  } catch (Exception e) {
    System.out.println("Error on main");
    e.printStackTrace();
  }
 }
}
```
