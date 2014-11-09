package bayesdiscussflip;

import java.util.ArrayList;
import java.util.List;

public class NativeBayesData {

  public static final List<Data> IMG_DESCRIPTIONS = new ArrayList<Data>();
  static {
    NativeBayesData.IMG_DESCRIPTIONS.add(new NativeBayesData.Data("Naive Bayesian", "",
    		"The <b>Naive Bayesian </b>classifier is based on Bayes theorem with independence assumptions between predictors. A Naive Bayesian model is easy to build, with no complicated iterative parameter estimation which makes it particularly <b>useful for very large datasets</b>. Despite its simplicity, the Naive Bayesian classifier often does surprisingly well and is widely used because it often outperforms more sophisticated classification methods. "
    		));
    NativeBayesData.IMG_DESCRIPTIONS.add(new NativeBayesData.Data("Algorithm", "bayesimg1.jpg",
                                                  "<b>Bayes theorem</b> provides a way of calculating the posterior probability, P(c|x), from P(c), P(x), and P(x|c). Naive Bayes classifier assume that the effect of the value of a predictor (x) on a given class (c) is independent of the values of other predictors. This assumption is called class <b>conditional independence.</b><br>"
                                                 ));
    NativeBayesData.IMG_DESCRIPTIONS.add(new NativeBayesData.Data("Example", "bayesimg2.jpg",
                                                  "The <b>posterior probability</b> can be calculated by first, constructing a frequency table for each attribute against the target. Then, transforming the frequency tables to likelihood tables and finally use the Naive Bayesian equation to calculate the posterior probability for each class. The class with the highest posterior probability is the <b>outcome of prediction.</b> "
    											 	));
    NativeBayesData.IMG_DESCRIPTIONS.add(new NativeBayesData.Data("The zero-frequency problem", "",
                                                  "Add 1 to the count for every attribute value-class combination (Laplace estimator) when an attribute value (Outlook=Overcast) doesn�t occur with every class value (Play Golf=no). "
    												));
    NativeBayesData.IMG_DESCRIPTIONS.add( new NativeBayesData.Data("Numerical Predictors", "bayesimg3.jpg",
                         "Numerical variables need to be transformed to their categorical counterparts (binning) before constructing their frequency tables. The other option we have is using the distribution of the numerical variable to have a good guess of the frequency. For example, one common practice is to assume normal distributions for numerical variables.<br>The probability density function for the normal distribution is defined by two parameters (mean and standard deviation)."
        				));
    NativeBayesData.IMG_DESCRIPTIONS.add(new NativeBayesData.Data("Predictors Contribution", "bayesimg4.jpg",
                                                  "<b>Kononenko's information </b>gain as a sum of information contributed by each attribute can offer an explanation on how values of the predictors influence the class probability."
                                                 ));
    NativeBayesData.IMG_DESCRIPTIONS.add(new NativeBayesData.Data("", "bayesimg5.jpg",
            "The <b>contribution of predictors</b> can also be visualized by plotting nomograms. Nomogram plots log odds ratios for each value of each predictor. Lengths of the lines correspond to spans of odds ratios, suggesting importance of the related predictor. It also shows impacts of individual values of the predictor."
           ));

  }
  public static final class Data {

    public final String title;
    public final String imageFilename;
    public final String description;
    
    private Data(String title, String imageFilename, String description
                 ) {
      this.title = title;
      this.imageFilename = imageFilename;
      this.description = description;
      
    }
  }
}
