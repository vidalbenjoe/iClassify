package descisiondiscussflip;


import java.util.ArrayList;
import java.util.List;




public class TopicData {

  public static final List<Data> IMG_DESCRIPTIONS = new ArrayList<Data>();
  static {
    TopicData.IMG_DESCRIPTIONS.add(new TopicData.Data("Decision tree", "desctreeimg1.jpg",
    		"<b>Decision tree </b> builds classification or regression models in the form of a tree structure. It breaks down a dataset into smaller and smaller subsets while at the same time an associated decision tree is incrementally developed. The final result is a tree with decision nodes and leaf nodes. A decision node (e.g., Outlook) has two or more branches (e.g., Sunny, Overcast and Rainy). Leaf node (e.g., Play) represents a classification or decision. The topmost decision node in a tree which corresponds to the best predictor called root node. Decision trees can handle both categorical and numerical data. "
    		));
    TopicData.IMG_DESCRIPTIONS.add(new TopicData.Data("Entropy", "desctreeimg2.jpg",
                                                  "A decision tree is built top-down from a root node and involves partitioning the data into subsets that contain instances with similar values (homogenous). ID3 algorithm uses entropy to calculate the homogeneity of a sample. If the sample is completely homogeneous the entropy is zero and if the sample is an equally divided it has entropy of one."
                                                 ));
    TopicData.IMG_DESCRIPTIONS.add(new TopicData.Data("", "desctreeimg3.jpg",
                                                  "To build a decision tree, we need to calculate two types of entropy using frequency tables as follows:<br><b>a.)</b> Entropy using the frequency table of one attribute."
    											 	));
    TopicData.IMG_DESCRIPTIONS.add(new TopicData.Data("", "desctreeimg4.jpg",
                                                  "<b>b.)</b> Entropy using the frequency table of two attributes"
    												));
    TopicData.IMG_DESCRIPTIONS.add( new TopicData.Data("Information Gain", "",
                         "The <b>information gain</b> is based on the decrease in entropy after a dataset is split on an attribute. Constructing a decision tree is all about finding attribute that returns the highest information gain (i.e., the most homogeneous branches)."
        				));
    TopicData.IMG_DESCRIPTIONS.add(new TopicData.Data("Step 1:  ", "desctreeimg5.jpg",
                                                  "Calculate entropy of the target."
                                                 ));
    TopicData.IMG_DESCRIPTIONS.add(new TopicData.Data("Step 2", "desctreeimg6.jpg",
                                                  "The dataset is then split on the different attributes. The entropy for each branch is calculated. Then it is added proportionally, to get total entropy for the split. The resulting entropy is subtracted from the entropy before the split. The result is the Information Gain, or decrease in entropy. "
                                                ));
    TopicData.IMG_DESCRIPTIONS.add(new TopicData.Data("Step 3:", "desctreeimg7.jpg",
                                                  "Choose attribute with the largest information gain as the decision node."
                                                ));
    TopicData.IMG_DESCRIPTIONS.add(new TopicData.Data("Step 4a:", "desctreeimg8.jpg",
                                                  " A branch with entropy of 0 is a <b>leaf node.</b>"
                                                 	));
    TopicData.IMG_DESCRIPTIONS.add(new TopicData.Data("Step 4b:", "desctreeimg9.jpg",
            "A branch with entropy more than 0 needs further splitting."
           	));
    
    TopicData.IMG_DESCRIPTIONS.add(new TopicData.Data("Step 5:", "",
            "The ID3 algorithm is run recursively on the non-leaf branches, until all data is classified."
           	));
    TopicData.IMG_DESCRIPTIONS.add(new TopicData.Data("Decision Tree to Decision Rules", "desctreeimg10.jpg",
            "A <b>decision tree</b> can easily be transformed to a set of rules by <b>mapping</b> from the <b>root node</b> to the <b>leaf nodes one by one.</b>"
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


