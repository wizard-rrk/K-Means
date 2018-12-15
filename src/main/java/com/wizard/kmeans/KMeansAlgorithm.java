package com.wizard.kmeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class KMeansAlgorithm {

	public static void main(String[] args) throws Exception {
		String fileLocation=null;
		int kCount=0;
		Reader r= null;
		if(args.length==2){
			fileLocation = args[0];
			kCount=Integer.parseInt(args[1]);
			r=new FileReader(new File(fileLocation));
		}else if(args.length==1){
			throw new Exception("Expected file name as First argument and k -count as second argument");
		}
		else{

			System.out.println("*************************************************************************************");
			System.out.println("INFO : NO data provided, required file location with data, and K count, since no data available, using sample input as reference");
			System.out.println("*************************************************************************************");
			kCount=4;
			r=new InputStreamReader(KMeansAlgorithm.class.getResourceAsStream("/kmeansTestData.txt"));
		}
		HashMap<String, Feature> dataMap =null;
		dataMap=readData(r);
		System.out.println("Final result "+new KMeansAlgorithm().execut(kCount, dataMap));
	}

	private static HashMap<String, Feature> readData(Reader file) throws IOException {
		HashMap<String, Feature>  dataMap=new  HashMap<String, Feature>();
		;
		try(BufferedReader br=new  BufferedReader(file)){


			String line=br.readLine();
			while(line!=null){

				String[] slist=line.split(";");
				dataMap.put(slist[0], new  Feature(Double.parseDouble(slist[1]),Double.parseDouble(slist[2])));
				line=br.readLine();
			}
		};
		// TODO Auto-generated method stub
		return dataMap;
	}

	public HashMap<String , Integer>  execut(int kCount, HashMap<String, Feature> dataMap) throws Exception {
		if(kCount<2){
			throw new Exception(" Clusters can't be less than  2 ");
		}
		if(dataMap==null || dataMap.size()<2){
			throw new Exception(" dataMap can't be null ");
		}
		MinMaxBean[] minMaxList=getMinMax(dataMap, Feature.featureCount);

		Cluster [] initialcluster=getCentroids(minMaxList, kCount);

		HashMap<String , Integer> result=calculateGroups(null,dataMap,initialcluster);


		return result;

	}


	private HashMap<String, Integer> calculateGroups(HashMap<String, Integer> previousResult,HashMap<String, Feature> dataMap, Cluster[] cluster) {
		// TODO Auto-generated method stub

		HashMap<String, Integer> currentResult=new HashMap<String, Integer>();


		for (java.util.Map.Entry<String, Feature> entry : dataMap.entrySet()) {
			List<Double> euclidionDistances=new  ArrayList<Double>();
			for (int i=0;i<cluster.length;i++){
				euclidionDistances.add(calculateDistance(cluster[i],entry.getValue()));

			}
			double min=Collections.min(euclidionDistances);
			int clusterId=euclidionDistances.indexOf(min);

			currentResult.put(entry.getKey(), clusterId);

		}
		boolean flag=compareResult(previousResult, currentResult);
		if( !flag){
			Cluster [] nextCluster=calculateNewClusters(currentResult, dataMap, cluster.length);

			return calculateGroups(currentResult,dataMap,nextCluster);

		}

		return currentResult;
	}

	private boolean compareResult(HashMap<String, Integer> previousResult, HashMap<String, Integer> currentResult) {

		if(previousResult == null){
			return false;
		}
		for (Map.Entry<String, Integer> entry: previousResult.entrySet()){
			if(entry.getValue()  != currentResult.get(entry.getKey())){
				return false;
			}
		}
		return true;
	}

	private Cluster[] calculateNewClusters(HashMap<String, Integer> previousResult,HashMap<String, Feature> dataMap, int kCount){

		Cluster[] cList=new Cluster[kCount];
		for(int i=0;i<kCount;i++){
			double xSum=0;
			double ySum=0;
			int dataCount=0;

			for (Map.Entry<String,Integer> entry:previousResult.entrySet()){
				if(i==entry.getValue()){
					Feature f=dataMap.get(entry.getKey());
					xSum+=f.getX();
					ySum+=f.getY();
					dataCount++;

				}
			}
			cList[i]=new Cluster((xSum/dataCount), (ySum/dataCount));
		}
		return cList;

	}

	private double calculateDistance(Cluster c,Feature f){

		double d=0;

		double x=c.getX()-f.getX();
		double y=c.getY()-f.getY();
		d=Math.sqrt((x*x)+(y*y));
		return d;

	}

	private Cluster[] getCentroids(MinMaxBean[] minMaxList, int kCount) {

		Cluster[]  CentroidList= new Cluster[kCount];

		for(int i=0;i<kCount;i++){
			double x = ThreadLocalRandom.current().nextDouble(minMaxList[0].min, minMaxList[0].max + 1);
			double y = ThreadLocalRandom.current().nextDouble(minMaxList[1].min, minMaxList[1].max + 1);
			CentroidList[i]=new Cluster(x,y);
		}



		return CentroidList;
	}

	private MinMaxBean[] getMinMax(HashMap<String, Feature> dataMap, Integer noOfDimentions) {
		MinMaxBean[] minMaxList= new MinMaxBean[Feature.featureCount] ;

		List<Double> d1 = new ArrayList<Double>();
		List<Double> d2 = new ArrayList<Double>();

		for (Feature value : dataMap.values()) {
			d1.add(value.getX());
			d2.add(value.getY());

		}

		minMaxList[0]=new MinMaxBean(Collections.min(d1), Collections.max(d1));
		minMaxList[1]=new MinMaxBean(Collections.min(d2), Collections.max(d2));


		return minMaxList;

	}

}
