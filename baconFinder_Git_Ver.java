package algo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import algo.AdjListGraph;
import algo.Graph;
import algo.GraphTuple;


public class baconFinder_Git_Ver {
	private Map<String, List<String>> g = new HashMap<String, List<String>>();
	private Map<String, List<String>> tmp = new HashMap<String, List<String>>();
	public Map<String, String> edgeMap = new HashMap<String, String>();
	AdjListGraph Gra = new AdjListGraph();
	
	public void reader(String rfile) {
	    File file = new File(rfile);
	    try (FileReader fileReader = new FileReader(file);
	         BufferedReader bufferedReader = new BufferedReader(fileReader)) {

	        String movie = null;
	        List<String> actors = null;
	        String line;
	        
	        while ((line = bufferedReader.readLine()) != null) {
	            if (line.startsWith("M: ")) {
	                // start of a new movie
	                if (movie != null) {
	                    // store the previous movie and its actors
	                    tmp.put(movie, actors);
	                }
	                // create a new list of actors for the new movie
	                movie = line.substring(3);
	                actors = new ArrayList<>();
	            } else {
	                // add an actor to the current movie
	                actors.add(line);
	            }
	        }
	        // store the last movie and its actors
	        if (movie != null) {
	            tmp.put(movie, actors);
	        }
	        // assign the temporary map to the main map
	        g = tmp;
	        // print the map
	        for (Map.Entry<String, List<String>> entry : g.entrySet()) {
	            System.out.println(entry.getKey() + ": " + entry.getValue());
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void builder(Map<String, List<String>> g) {
		    for (String key : g.keySet()) {		//for each movie
		        List<String> hereActors = g.get(key);		//list of actors in movie
		        for(int i = 0; i < hereActors.size()-1; i++) {	//first actor
		        	for(int j = i+1; j < hereActors.size(); j++) {		//second actor
		        		String n1 = hereActors.get(i);
		      		    String n2 = hereActors.get(j);
		        		if(!Gra.containsNode(hereActors.get(j))) {	//needs to be if already exists
		        			Gra.addNode(hereActors.get(j));
		        		}
		        		if(!Gra.containsNode(hereActors.get(i))) {	//needs to be if already exists
		        			Gra.addNode(hereActors.get(i));
		        		}
		        		if(hereActors.get(j) != hereActors.get(i)) {
		      		     Gra.addEdge(hereActors.get(i), hereActors.get(j));
		      		     Gra.addEdge(hereActors.get(j), hereActors.get(i));

		      		     String n3 = n1 + "__" + n2;
		      		     String n4 = n2 + "__" + n1;
		      		     edgeMap.put(n3, key);
		      		     edgeMap.put(n4, key);
		        		}
		        	}        	
		        }      
		    }
	}
	public int finder(String name) {
		int dist = -1;
		String startName = "Kevin Bacon";
		if(!Gra.containsNode(name)) {
				return dist;
		}
		//System.out.println(name);
		Map<String, GraphTuple> results = Gra.bfs(startName);
		//System.out.println("---------------------> " + results.get("Tom Cruise"));
		//System.out.println("---------------------> " + results.get("Kevin Bacon"));
		//System.out.println(results);
		//System.out.println(results.size());
		GraphTuple s = results.get(name);
		//System.out.println(s);
		dist = s.d;
		String h = s.p;
		String currName = name;
		String newName;
		String pathMovie;
		System.out.println("");
		System.out.print(name + " has a Bacon number of " + dist + ". ");
		if(name.equals("Kevin Bacon")) {
			System.out.println("That IS Kevin!");
		}
		else{
			System.out.println("");
		}
		
		while(!currName.equals("Kevin Bacon")) {
			//System.out.println(currName);
			newName = results.get(currName).p;
			pathMovie = edgeMap.get(newName + "__" + currName);
			System.out.println("    " + currName + " was in " + pathMovie + " with " + newName + "!");
			
			//System.out.println(newName);
			//System.out.println(pathMovie);
			currName = newName;
		}
		System.out.println("");
		return dist;
		
	}
	public void run(String dbFile) {
		reader(dbFile);
		builder(g);
		int val = 0;
		Scanner obj = new Scanner(System.in);		//input collector
		System.out.println("Welcome to the Oracle of Bacon");
		
		int loopVar = 0;
		while(loopVar != -1) {
			System.out.println("Who would you like to find? (First and Last name)");
			String in = obj.nextLine();//input			
			if(in.isEmpty()) {
				loopVar = -1;
				System.out.println("Thank you for using this program");
			}
			else {
				String name = in;
				//String nameLast = obj.next();//input
		
				if(name.equals("Kevin Bacon")) {
					//System.out.println("That is Kevin");
				}
				val = finder(name);	
				if(val == -1) {
					System.out.println("");
					System.out.println( name + " has a bacon number of infinity!");
					System.out.println("");

				}
				if(val != -1 && val !=0) {
					//System.out.println(name + " has a Bacon number of " + val);
					//System.out.println("queue"); //have a queue with the path and print that to show path
				}
				
			}
			
		}	
		obj.close();	//close scanner
	}	
	
	
	public static void main(String[] args) {
		File f = new File("C:\\Users\\TyCof\\Algo\\BaconAssignment4\\bin\\test.txt");
		baconFinder_Git_Ver driver = new baconFinder_Git_Ver();
		driver.run(f.getAbsolutePath());
		
	}

	
}
