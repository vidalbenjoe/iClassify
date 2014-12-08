package knearestdiscussflip;


import java.util.ArrayList;
import java.util.List;




public class KNearestData {

  public static final List<Data> IMG_DESCRIPTIONS = new ArrayList<Data>();
  static {
    KNearestData.IMG_DESCRIPTIONS.add(new KNearestData.Data("K Nearest Neighbors - Classification", "",
    		"<b>K nearest neighbors</b> is a simple algorithm that stores all available cases and classifies new cases based on a similarity measure (e.g., distance functions). KNN has been used in <b>statistical estimation</b> and <b>pattern recognition </b>already in the beginning of 1970's as a non-parametric technique.  "
    		));
    KNearestData.IMG_DESCRIPTIONS.add(new KNearestData.Data("Algorithm", "knnimg1.jpg",
                                                  "A case is classified by a majority vote of its neighbors, with the case being assigned to the class most common amongst its K nearest neighbors measured by a distance function. If K = 1, then the case is simply assigned to the class of its nearest neighbor. "
                                                 ));
    KNearestData.IMG_DESCRIPTIONS.add(new KNearestData.Data("", "knnimg2.jpg",
                                                  "It should also be noted that all three distance measures are only valid for continuous variables. In the instance of categorical variables the Hamming distance must be used. It also brings up the issue of standardization of the numerical variables between 0 and 1 when there is a mixture of numerical and categorical variables in the dataset."
    											 	));
    KNearestData.IMG_DESCRIPTIONS.add(new KNearestData.Data("", "",
                                                  "Choosing the optimal value for K is best done by first inspecting the data. In general, a <b>large K value is more precise</b> as it reduces the overall noise but there is no guarantee. Cross-validation is another way to retrospectively determine a good K value by using an independent dataset to validate the K value. Historically, the optimal K for most datasets has been between 3-10. That produces much better results than 1NN. "
    												));
    KNearestData.IMG_DESCRIPTIONS.add( new KNearestData.Data("Example:", "knnimg3.jpg",
                         "Consider the following data concerning credit default. Age and Loan are two numerical variables (predictors) and Default is the target."
        				));
    KNearestData.IMG_DESCRIPTIONS.add(new KNearestData.Data("Step 1:  ", "knnimg4.jpg",
                                                  "<b>D = Sqrt[(48-33)^2 + (142000-150000)^2] = 8000.01  >> Default=Y</b><br><br>We can now use the training set to classify an unknown case (Age=48 and Loan=$142,000) using Euclidean distance. If K=1 then the nearest neighbor is the last case in the training set with Default=Y.<br><br><br>With K=3, there are two Default=Y and one Default=N out of three closest neighbors. The prediction for the unknown case is again Default=Y."
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
