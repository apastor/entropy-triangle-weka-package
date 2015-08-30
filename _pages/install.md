---
permalink: /install/
layout: page
title: "Install"
description: ""
group: navigation
weight: 1
---

{% include JB/setup %}

To install the Entropy Triangle package on Weka you can use the Weka Package Manager. If you are in a Unix environment you can do it faster with the [command line](#command-line).

**The [EntropyTriangle.zip package]({{ site.latest-package-url }}) is cross-platform**.
You can install it on Weka independently of you operating system.
The only **requirements** are that you have **Java** (if you already have Weka running you have it),
and a recent release of the development version of **[Weka](http://www.cs.waikato.ac.nz/~ml/weka/) (>=3.7.8)**


### Package Manager GUI

1. In the Weka GUI Chooser, the main Weka window that opens on the program startup, go to the **Tools** menu and open the **Package manager**

    ![]({{ site.baseurl }}/assets/img/weka-openPackageManager.png)

2. On the top-right corner of the Package manager window click the **File/URL** button.

    ![]({{ site.baseurl }}/assets/img/weka-PackageManager.png)

3. Select the [EntropyTriangle.zip]({{ site.latest-package-url }}) file with one of the following methods:

        - Click the **Browse...** button to select an already downloaded file.

        - Paste in the text box the URL to the package zip file from the [*release* section](https://github.com/apastor/entropy-triangle-weka-package/releases/latest) of the project github.

    ![]({{ site.baseurl }}/assets/img/weka-fileWindow.png)

4. **Restart Weka** to get the plugin loaded.

### Command line
```bash
# Add weka.jar to classpath
$ export CLASSPATH=${CLASSPATH}:<path-to>/weka.jar

$ java weka.core.WekaPackageManager -install-package <path or url to>/EntropyTriangle.zip
```

---
## BUILD
<br>
To build the package from source you can use [Apache Ant](http://ant.apache.org/).
Run the following command from the project root directory:

```bash
$ ant -f ./build_package.xml -lib <path-to>/weka.jar make_package
```
You have to specify the project build file with the option `-f` and the weka jar file as library (option `-lib`).
Optionally, you can set the build command, `make_package` is the default one.
