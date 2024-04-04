package ub.cse.algo;

import java.nio.file.Paths;
import java.util.*;

public class Solution {

    private Info info;
    private Graph graph;
    private ArrayList<Client> clients;
    private ArrayList<Integer> bandwidths;

    /**
     * Basic Constructor
     *
     * @param info: data parsed from input file
     */
    public Solution(Info info) {
        this.info = info;
        this.graph = info.graph;
        this.clients = info.clients;
        this.bandwidths = info.bandwidths;
    }

    /**
     * Method that returns the calculated 
     * SolutionObject as found by your algorithm
     *
     * @return SolutionObject containing the paths, priorities and bandwidths
     */


    public SolutionObject outputPaths() {

        SolutionObject sol = new SolutionObject();

        HashMap<Integer, ArrayList<Integer>> shortestpaths= Traversals.bfsPaths(this.graph,this.clients);


        ArrayList<Client> sorted_clients= new ArrayList<>();

        ArrayList<Client> copy= this.clients;
        for (int j=0; j<this.clients.size();j++) {
            Client smallalpha = null;
            float alpha=Float.MAX_VALUE;
            for (int i = 0; i < this.clients.size(); i++) {
                    if (this.clients.get(i).alpha < alpha) {
                        smallalpha = this.clients.get(i);
                        alpha= smallalpha.alpha;
                    }
            }

            //System.out.println(smallalpha.payment);
            sorted_clients.add(smallalpha);
            this.clients.remove(smallalpha);
        }
        this.clients=copy;

        for( Client client: sorted_clients ){
           float maxdelay=  client.alpha * (shortestpaths.get(client.id).size()-1);

           System.out.println(maxdelay);
           break;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(graph.contentProvider);
        HashMap<Integer,ArrayList<ArrayList<Integer>>> list_of_paths= new HashMap<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            //System.out.println(node);
            int bandwidth = this.bandwidths.get(node);
            ArrayList<Integer> path= new ArrayList<>();
            path.add(node);
            list_of_paths.put(node,new ArrayList<>());
            for (int neighbor : graph.get(node)) {
                int neighborbandwidith= this.bandwidths.get(neighbor);
                path.add(neighbor);
                if(this.clients.contains(neighbor)){
                    list_of_paths.get(node).add(path);
                }
            }
           // System.out.println(bandwidth);


            break;

        }

        ArrayList<ArrayList<Integer>> paths= new ArrayList<>();



        /* TODO: Your solution goes here */
        return sol;
    }
}
