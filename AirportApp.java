//
// Name: Chokboonanun, Saharat
// Project: 5
// Due: 5/14/2021
// Course: cs-2400-03-sp21
//
// Description:
// Creating a airport app that uses graph ADT to connect each node and data. 
//

import java.util.*;
import java.io.*;

public class AirportApp {
    public static void main(String[] args) throws RuntimeException{
        System.out.println("Airports by Saharat Chokboonanun\n");

        DirectedGraph<String> graph = new  DirectedGraph<>();
        DirectedGraph<String> graph2 = new  DirectedGraph<>();

        Scanner input = new Scanner(System.in); 

        String cities = "airports.csv";
        String roads = "distances.csv";
        String response;
        boolean exit = false;

        File file1 = new File(cities);
        File file2= new File(roads);
       
        try{
            Scanner scanner = new Scanner(file1);

            while (scanner.hasNextLine()) {
                String tmp= scanner.nextLine();
                String[] commas = tmp.split(",", 2); 

                graph.addVertex(commas[0]);
                graph.addVertex(commas[1]);
                graph.addEdge(commas[0],commas[1]);      
            }

            scanner.close();

        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try{
            Scanner scanner = new Scanner(file2);

            while (scanner.hasNextLine()) {
                String tmp= scanner.nextLine();
                String[] commas = tmp.split(",", 3); 

                if(!graph2.haveVertex(commas[0])){
                    graph2.addVertex(commas[0]);
                }
                
                if(!graph2.haveVertex(commas[1])){
                    graph2.addVertex(commas[1]);
                }
            
                graph2.addEdge(commas[0],commas[1],Double.parseDouble(commas[2])); 
            }

            scanner.close();

        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        do{
            System.out.println("Command ? (H to open menu)");
            response=input.next();
            response=response.toUpperCase().trim();
            String first; 
            String second; 
            String error = "ERROR AREA CODE!";
            int third;

            if(response.length()==1){
                switch(response.charAt(0)){
                        
                    case 'Q':
                        System.out.print("City code: ");
                        response = input.next().toUpperCase().trim();
                        graph.getNeighbor(response);                        
                        break;
                    case 'I':
                        System.out.println("City codes and distance");
                        System.out.print("First code: ");
                        first = input.next().toUpperCase().trim();
                        System.out.print("Second code: ");
                        second = input.next().toUpperCase().trim();
                        System.out.print("distance: ");
                        third = input.nextInt();

                        if(graph.haveVertex(first) && graph.haveVertex(second) && graph2.addEdge(first, second, third)){
                            System.out.print("You have inserted a connection from ");
                            graph.getNeighbor(first);
                            System.out.print(" to ");
                            graph.getNeighbor(second);
                            System.out.println("with a distance of " + third);
                        }else{
                            System.out.println(error);
                        }
                        break;
                    case 'H': 
                        System.out.println("Q Query the airport information by entering the airport code.");
                        System.out.println("D Find the minimum distance between two airports.");
                        System.out.println("I Insert a connection by entering two airport codes and distance.");
                        System.out.println("R Remove an existing connection by entering two airport codes.");
                        System.out.println("H Display this message.");
                        System.out.println("E Exit.");
                        break;
                    case 'D':
                        StackInterface<String> bestRoute = new LinkedStack<>();

                        System.out.println("City codes");
                        System.out.print("First code: ");
                        first = input.next().toUpperCase().trim();
                        System.out.print("Second code: ");
                        second = input.next().toUpperCase().trim();

                        if(graph.haveVertex(first) && graph.haveVertex(second)){
                            double distance = graph2.getCheapestPath(first, second, bestRoute);
                            int termial = graph2.getShortestPath(first, second, bestRoute);
                                
                            System.out.println("The shortest route from " + first + " to " +  second + " is " + distance + " miles long.");
                            System.out.println("With " + termial + " termial.");
                        }else{
                            System.out.println(error);
                        }
                        break;
                    case 'R':
                        System.out.println("City codes");
                        System.out.print("First code: ");
                        first = input.next().toUpperCase().trim();
                        System.out.print("Second code: ");
                        second = input.next().toUpperCase().trim();

                        if(graph.haveVertex(first) && graph.haveVertex(second)){
                            graph2.removeEdge(first, second);
                            System.out.println("The connection from " + first + " to " +  second + " is removed.");
                            
                        }else{
                            System.out.println(error);
                        }
                           
                        break;
                    case 'E':
                        exit = true;
                        System.out.println("Program exiting.");
                        break;
                    default:       
                }
                System.out.println("");
            }  
           }while(!exit); 

           input.close();

    }
}