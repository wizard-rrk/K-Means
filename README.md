# K-Means
Demonstration of KMeans Algorithm in java
K -> number of gruops/clusters
Means -> mathematical mean of given set of data


# What is K_Means...!!!!
K_Means is an unsupervised learning algorithm used for grouping/clustering a set of unlabelled data (which doesnt contain information about it) in a given number of groups.
Clustering is done based on the features of each data.

# Why K-Means
Grouping large set of data is difficult, and is even more difficult when we have more than one parameter to consider while grouping.
K-Means algorithm is way to arrive to those groups/clusters.

# Where can we use it
it can be used in any case where we need to group large set of unlabelled data into a given number of Groups/Clusters.
These datas can have multiple festures/dimentions.                                                                                        
Simple use cases can be :                                                                                                                 
-> grouping game players based on their shots and survival to determine the strike-rate.                                                  
-> grouping employess based on throughtput and the working hours to determine their performance 

# Lets take a simple example to expling how it works;                                                                               
Suppose we have data for players of a game, say with features like shots and accuracy.

Note: we will represent data in form of points on graph.                                                                                     
   Step 1: Select number of Clusters into which we want to group data. Let it be 2. Hence k=2.
  
   step 2: Determine 2 (as K=2) Random Centroid based on max and min value of each feature/Dimention.

   step 3: Calcutale distance of each centroid from one of the points.                                                                                Point will belong to Centroid with minimum distance.                                                                      
   
   Step 4: Repeat Step 3 for each point.                                                                                                      Finally we will get all the datas under 2 Clusters.
   
   Step 5: Store the result.
   
   Step 6: Compare the stored(if any) result with current result.
   
      (i) If the the clusters shows no change in set of data. This is considered to be the final result.
      (ii) else move on to the Step 7.
   
   Now this may be the final grouping which can be accurate but to decide so we will use the Means algorithm.
   
  Step 7: Calculate mean of each cluster. This is going to be the new Centroid for next round of Iteration.
  
  Step 8: Repeat Step 3, Step 4 Step 5 and Step 6.
  
  
