import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GraphIO {
	
	static public void readFile(Graph g, String filename) throws IOException {
		try {
			
			File file = new File(filename);
			Scanner scn = new Scanner(file);
			int id0, x, y, id1, id2, weight;
			int numNodes = scn.nextInt();
			
			if(scn.hasNext() == true) {
				for(int i = 0; i < numNodes; i++) {
					id0 = scn.nextInt();
					x = scn.nextInt();
					y = scn.nextInt();
					g.addNode(id0, x, y);	
				}
				while(scn.hasNext() == true) {
					id1 = scn.nextInt();
					id2 = scn.nextInt();
					weight = scn.nextInt();
					g.addEdge(id1, id2, weight);
				}
			}
			scn.close();
		}
		catch(Exception e) {
			throw new IOException();
		}
		
		
	}
}
