# entropy-triangle-weka-package
A set of information-theoretic tools for the assesment of classifiers in Weka.

This package is an implementation for Weka of the work of this papers:

* [Valverde-Albacete, F. J., & Peláez-Moreno, C. (2014).
 100% classification accuracy considered harmful:
 The normalized information transfer factor explains the accuracy paradox.
 PLoS ONE 9(1).](http://dx.doi.org/10.1371/journal.pone.0084217)
 
* [Valverde-Albacete, F. J., & Peláez-Moreno, C. (2010).
 Two information-theoretic tools to assess the performance of multi-class classifiers.
 Pattern Recognition Letters, Volume 31, Issue 12, 1 September 2010, Pages 1665-1671.](http://dx.doi.org/10.1016/j.patrec.2010.05.017)

## BUILD

To build the project from source use ant from this directory. 
It's necessary to specify the project build file (build_package.xml) and the weka.jar as library. 
Optionally you can set the build command, make_package is the default one.

### Requirements
- Java 1.7 or later
- Weka 3.7.12

```bash
$ ant -f ./build_package.xml -lib <path-to>/weka.jar make_package
```

## INSTALL

To install the package on Weka use the Weka Package Manager GUI
or use the package manager from the command line as follows:

# Add weka.jar to claspath
$ export CLASSPATH=${CLASSPATH}:<path-to>/weka.jar

$ java weka.core.WekaPackageManager -install-package EntropyTriangle.zip
