---
permalink: /how-to-use/
layout: page
title: "How to use"
#description: ""
group: navigation
tag: documentation
weight: 2.1
---

{% include JB/setup %}

The Entropy Triangle package for Weka includes a visualization plugin and new evaluation metrics.
The package can be used from the Weka GUIs and [programmatically]({{site.baseurl}}/programmatic-use/).

## The Entropy Triangle visualization plugin

The Entropy Triangle is accessible from the Weka Explorer and the Weka GUI Chooser window.

When called from the Weka GUI Chooser window, will be displayed an empty plot.
This is the form to open the visualization to [load a file](#load-file) with data of another session.

![]({{ site.baseurl }}/assets/img/weka_ExtensionsET.png)

### Add data from the Explorer

The data of a classification evaluation performed on the Weka Explorer can be added to the Entropy Triangle via the context menu of the **Result list** panel of the **Clasify** tab. For that, select on the list the result you want to add and *right-click* to open the context menu. Then, move the cursor to **Plugins** to open the visualization plugins sub-menu and click **Entropy Triangle**.

![]({{ site.baseurl }}/assets/img/weka-visualizationPlugin_small.png)

The data will be added to the existing window of the Entropy Triangle. If you do not have the Entropy Triangle opened, a clean new plot will be generated.

### Changing the colorbar metric

### Split mode

### Minimum increment from entropy line

### Remove data

To remove a point from the plot, *right-click* on it, to open the point context menu, and select **delete**.

![]({{ site.baseurl }}/assets/img/*****.png)

To erase all the data close the window. A clean window will be loaded when opening again the visualization.

### Save to a file

The Entropy Triangle lets you save the evaluation information of the plotted classification process. The data is saved in the Weka instances format, similar that the Weka Experimenter files. Where, each classification performed is an instance, and the dataset, classifier, evaluation metrics, and timestamp information are the attributes.

When the Entropy Triangle has data plotted, the upper-right button is titled **Save data**. You can choose a Weka instances file format: arff, xrff, and the binary bsi. CSV, JSON.

* arff, or its xml variant xrff.
* CSV
* JSON
*


### Load file
