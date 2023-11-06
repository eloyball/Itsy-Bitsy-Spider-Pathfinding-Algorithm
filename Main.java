import java.io.*;
import java.util.*;

public class Main {
	private static String output = "";
	
	public static void main(String[] args) {
		Graph maze = new Graph();
		int[] mazeProps = new int[3];		// 0: Rows; 1: Columns; 2: Levels
		int[] startCoords = new int[3];		// 0: X, 1: Y, 2: Z
		int[] endCoords = new int [3];
		
		// Read File & Construct Graph
		try {
			 File inputFile = new File("input.txt");
			 Scanner scan = new Scanner(inputFile);
			 
			 System.out.println("Input file opened.");
			 
			 int i = 0;
			 int lineNum = 0;
			 while(scan.hasNextLine()) {
				 String inputStr = scan.nextLine();
				 String data[] = inputStr.split(" ");
				 
				 lineNum++;
				 switch(lineNum) {
				 	case 1:		// Create Graph
				 		mazeProps[0] = Integer.parseInt(data[1]);
				 		mazeProps[1] = Integer.parseInt(data[2]);
				 		mazeProps[2] = Integer.parseInt(data[0]);
				 		
				 		int maxV = mazeProps[0] * mazeProps[1] * mazeProps[2];
				 		for(int j = 0; j < maxV; j++) {
				 			int x = j % mazeProps[1];
				 			int z = j / (mazeProps[0] * mazeProps[1]);
				 			int y = (j - (mazeProps[0] * mazeProps[1] * z)) / mazeProps[1];
				 			
				 			maze.addVertex(x, y, z);
				 		}
				 		continue;
				 	case 2:
				 		startCoords[0] = Integer.parseInt(data[1]);
				 		startCoords[1] = Integer.parseInt(data[2]);
				 		startCoords[2] = Integer.parseInt(data[0]);
				 		continue;
				 	case 3:
				 		endCoords[0] = Integer.parseInt(data[1]);
				 		endCoords[1] = Integer.parseInt(data[2]);
				 		endCoords[2] = Integer.parseInt(data[0]);
				 		continue;
				 }
				 
				 // Assign Edges to Corresponding Vertices
				 for(int j = 0; j < data.length; j++) {
					 int[] binData = translateData(data[j]);
					 for(int k = 0; k < binData.length; k++) {
						 if(binData[k] == 0) {
							 continue;
						 }
						 
						 Vertex currV = maze.getVertexById(i);
						 Vertex targetV;
						 switch(k) {
						 	case 0:		//North
						 		targetV = maze.getVertexByCoords(currV.x, currV.y - 1, currV.z);
						 		currV.addEdge(targetV, 'N');
						 		break;
						 	case 1:		//East
						 		targetV = maze.getVertexByCoords(currV.x + 1, currV.y, currV.z);
						 		currV.addEdge(targetV, 'E');
						 		break;
						 	case 2:		//South
						 		targetV = maze.getVertexByCoords(currV.x, currV.y + 1, currV.z);
						 		currV.addEdge(targetV, 'S');
						 		break;
						 	case 3:		//West
						 		targetV = maze.getVertexByCoords(currV.x - 1, currV.y, currV.z);
						 		currV.addEdge(targetV, 'W');
						 		break;
						 	case 4:		//Up
						 		targetV = maze.getVertexByCoords(currV.x, currV.y, currV.z + 1);
						 		currV.addEdge(targetV, 'U');
						 		break;
						 	case 5:		//Down
						 		targetV = maze.getVertexByCoords(currV.x, currV.y, currV.z - 1);
						 		currV.addEdge(targetV, 'D');
						 		break;
						 }
					 }
					 i++;
				 }
			 }
			 
			 scan.close();
			 
			 System.out.println("Successfully finished reading input file!");
			 
			 // Debugging
			 //System.out.println("maze proportions: x=" + mazeProps[0] + ", y=" + mazeProps[1] + ", z=" + mazeProps[2]);
			 //System.out.println("start coordinates: x=" + startCoords[0] + ", y=" + startCoords[1] + ", z=" + startCoords[2]);
			 //System.out.println("end coordinates: x=" + endCoords[0] + ", y=" + endCoords[1] + ", z=" + endCoords[2]);
			 
			 //maze.print(mazeProps[0], mazeProps[1], mazeProps[2]);
			 //maze.printEdges();
		 } catch (FileNotFoundException e){
			 e.printStackTrace();
		 }
		
		// Finish Reading File
		Vertex startVertex = maze.getVertexByCoords(startCoords[0], startCoords[1], startCoords[2]);
		Vertex endVertex = maze.getVertexByCoords(endCoords[0], endCoords[1], endCoords[2]);
		
		dfs(startVertex, endVertex);
		
		System.out.println("Success! Solution found! Result:");
		System.out.println(output);
		
		// Write output to new file
		try {
			File outputFile = new File("output.txt");
		    if (outputFile.createNewFile()) {
		    	System.out.println("Output file created.");
		    } else {
		        System.out.println("Output file already exists.");
		    }
		    
		    FileWriter myWriter = new FileWriter("output.txt");
		    myWriter.write(output);
		    myWriter.close();
		    System.out.println("Successfully wrote to the output file!");
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	// Translate a string into an integer array
	public static int[] translateData(String in) {
		String s[] = in.split("");
		int out[] = new int[s.length];
		
		for(int i = 0; i < s.length; i++) {
			out[i] = Integer.parseInt(s[i]);
		}
		
		return out;
	}
	
	// Depth-First Search
	public static boolean dfs(Vertex vertex, Vertex goal) {		
		ArrayList<Edge> moves = vertex.getEdges();
		
		for(int i = 0; i < moves.size(); i++) {
			Vertex nextV = moves.get(i).getEnd();
			
			if(nextV == goal) {
				output += moves.get(i).getLabel();
				return true;
			}
			
			if(!nextV.isVisited()) {
				nextV.setVisited(true);
				
				output += moves.get(i).getLabel() + " ";
				if(dfs(nextV, goal)) {
					return true;
				}
			}
		}
		
		output = output.substring(0, output.length() - 2);
		return false;
	}
	
	// Iterative approach for DFS (was unable to get it working)
	/*public static String dfs(Vertex startV, Vertex goalV) {
		String out = "";
		Stack<Vertex> stack = new Stack<Vertex>();
		stack.push(startV);
		
		while(!stack.isEmpty()) {
			Vertex v = stack.pop();
			
			ArrayList<Edge> moves = v.getEdges();
			int possibleMoves = 0;
			
			for(int i = 0; i < moves.size(); i++) {
				Vertex nextV = moves.get(i).getEnd();
				
				if(!nextV.isVisited()) {
					possibleMoves++;
					nextV.setVisited(true);
					stack.push(nextV);
					out += moves.get(i).getLabel();
					
					if(nextV == goalV) {
						return out;
					}
				}
			}
			
			if(possibleMoves == 0) {
				out = out.substring(0, out.length() - 1);
			}
		}
		
		return "Exit not found.";
	}*/
}