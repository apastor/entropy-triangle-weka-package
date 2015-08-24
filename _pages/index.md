---
permalink: "/"
title: "Overview"
layout: default
description: "package features"
---

{% include JB/setup %}

<h1 style="text-align:center;">Entropy Triangle Weka package</h1>

<p style="text-align:center;">A set of information-theoretic tools for the assessment of classifiers in Weka.</p>

![EntropyTriangle_overview]({{ site.baseurl }}/assets/img/ETplugin_overview.png)

---

# Features

* **New evaluation metrics** for the standard output of Weka.
  * Entropy Modulated Accuracy (EMA)

    $$ EMA = \frac{1}{k_{X|Y}} = \frac{1}{2^{H_{P_{X|Y}}}} $$


  * Normalized Information Transfer factor (NIT factor).

    $$ NIT_{factor} = \frac{\mu_{XY}}{k} $$


* Optional **advanced information-theoretic metrics**.
  * Class perplexity
  * Remaining perplexity
  * Information Transfer factor
  * Variation of information


* **Visualization Plugin** for an fast and easy, but complete and reliable comparison of classifiers performance.

    The Entropy Triangle is based on a balance equation of entropies.
    Represents in a ternary plot normalized values of the variation of information, the mutual information, and the increment of entropy from the uniform distribution. This let you compare different scenarios at a glance without loss of information.

  * Panel with **colorbar** (or labels) for the chosen metric (or classifier/dataset information)
  * **Tooltips** with detailed information hovering graph elements
  * Delete elements of the graph with right-click menu
  * **Save and load graph data** in arff format
  * **Export data to csv or json**
  * **Print the graph** to various file formats with *Ctrl+Shft+Alt+Left Mouse Click*


---

## Theoretical background
<br>
This package is an implementation of the work of these papers:

* [Valverde-Albacete, F. J., & Peláez-Moreno, C. (2014). 100% classification accuracy considered harmful: The normalized information transfer factor explains the accuracy paradox. PLoS ONE 9(1).](http://dx.doi.org/10.1371/journal.pone.0084217)

* [Valverde-Albacete, F. J., & Peláez-Moreno, C. (2010). Two information-theoretic tools to assess the performance of multi-class classifiers. Pattern Recognition Letters, Volume 31, Issue 12, 1 September 2010, Pages 1665-1671.](http://dx.doi.org/10.1016/j.patrec.2010.05.017)
