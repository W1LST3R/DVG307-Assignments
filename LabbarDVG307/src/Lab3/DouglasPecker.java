package Lab3;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import se.hig.dvg307.lib.gui.ObjectRenderer;
import se.hig.dvg307.lib.gui.ViewerWindow;
import se.hig.dvg307.lib.types.geomtypes.*;

public class DouglasPecker {
	ObjectRenderer renderer = new ObjectRenderer ();
	private double [][] data;
	private ArrayList <Vertex2D> vertices;
	
	public DouglasPecker(String path) {
		try {
			data = loadList(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static double[][] loadList(String path) throws FileNotFoundException,IOException {

		  ArrayList<Double[]> list = new ArrayList<Double[]>();
		 
		  BufferedReader in = new BufferedReader(new FileReader(path));
		  String l;
		  String[] elementBuffer;
		  Double[] valueBuffer;
		  while((l = in.readLine()) != null) {

			  elementBuffer = l.trim().split(" ");
			  valueBuffer = new Double[2];
			  valueBuffer[0] =  Double.parseDouble(elementBuffer[0]);
			  valueBuffer[1] = Double.parseDouble(elementBuffer[1]);
			 
			  list.add(valueBuffer);
		  }
		  in.close();
		  
		  // returnList is 2D array, with size equal to amount of rows to be read from the file.
		  // Each row has structure [x1,y1,x2,y2].
		  double[][] returnList = new double[list.size()][2];
		  Double[] data;
		 
		  for(int i = 0; i < list.size(); i++) {
			  data = list.get(i);
			  returnList[i][0] = data[0];
			  returnList[i][1] = data[1];
		  }
		  return returnList;
	}
	
	public void drawVertices() {
		vertices = new ArrayList <Vertex2D> ();
		int i = 0;
		while(i < data.length) {
			vertices.add(new Vertex2D (data[i][0], data[i][1]));
			i++;
		}
		i=0;

        // Skapa ett antal punkter:
        ArrayList<Point2D> points = new ArrayList<> ();
        for (Vertex2D v : vertices)
            points.add (new Point2D (v));

        // Skapa en linje:
        LineString2D ls = new LineString2D ();
        while(i < vertices.size()-1) {
        	ls.appendVertex(vertices.get(i));
        	ls.appendVertex(vertices.get(i+1));
			i++;
		}

        // Initiera ObjectRenderer:
        renderer = new ObjectRenderer ();
        // En enhet i objektkoordinater motsvarar 45 pixlar
        // i fönsterkoordinater (både åt x- och y-håll):
        renderer.setScaleFactor (45, 45);

        // Bestäm att punkter ska vara röda:
        renderer.setColor (new Color (255, 0, 0));
        // och lägg till punkterna till ObjectRenderer:
        for (Point2D p : points)
           renderer.addObject (p);
           
        // Bestäm att linjedraget ska vara grönt:
        renderer.setColor (new Color (0, 0, 255));
        // och lägg till linjedraget till ObjectRenderer:
        renderer.addObject (ls);
	}
	
	public void drawVertices(ArrayList<Integer> al) {
		ArrayList <Vertex2D> vert = new ArrayList <Vertex2D> ();
		int i = 0;
		while(i < al.size()) {
			vert.add(new Vertex2D (vertices.get(al.get(i)).x(), vertices.get(al.get(i)).y()));
			i++;
		}
		i=0;

        // Skapa ett antal punkter:
        ArrayList<Point2D> points = new ArrayList<> ();
        for (Vertex2D v : vert)
            points.add (new Point2D (v));

        // Skapa en linje:
        LineString2D ls = new LineString2D ();
        while(i < vert.size()-1) {
        	ls.appendVertex(vert.get(i));
        	ls.appendVertex(vert.get(i+1));
			i++;
		}

        // Initiera ObjectRenderer:
        
        // En enhet i objektkoordinater motsvarar 45 pixlar
        // i fönsterkoordinater (både åt x- och y-håll):
        renderer.setScaleFactor (45, 45);

        // Bestäm att punkter ska vara röda:
        renderer.setColor (new Color (0, 0, 0));
        // och lägg till punkterna till ObjectRenderer:
        for (Point2D p : points)
           renderer.addObject (p);
           
        // Bestäm att linjedraget ska vara grönt:
        renderer.setColor (new Color (255, 0, 0));
        // och lägg till linjedraget till ObjectRenderer:
        renderer.addObject (ls);


        // Slutligen: Visa fönstret för användaren:
        new ViewerWindow (renderer, "Efter Dougals pecker");
	}
	
	public int getStart() {
		return 0;
	}
	
	public int getEnd() {
		return data.length-1;
	}
	
	public ArrayList<Integer> dpAlgorithm(ArrayList<Integer> line,int start, int end,double thresh){
		//Här sparas resultatet i en array för att minska anrop till maxDistance och därför spara på mine
		double[] infoArr = maxDistance(start, end);
		//infoArr[1] == distansen från linjen till punkten som ligger längst ifrån
		//infoArr[0] == index för punkten som ligger längst ifrån
		//Om distancen är mindre än gränsvärdet retunerar den arrayListen. Alltså basfallet
		if(infoArr[1] < thresh){
			return line;
		}else{
			//Annars så delar den linjen i 2 delar, Divide and conquer. Den delas från start punkt till max punkt och max punkt till slut punkt
			dpAlgorithm(line,start,(int)infoArr[0],thresh);
			line.add((int)infoArr[0]);
			return dpAlgorithm(line,(int)infoArr[0],end,thresh);
		}
	}
	
	public double[] maxDistance(int start, int end){
		double maxDist = 0;
		int pos = 0;
		//Loopar igenom alla punkter mellan start och slut punkt för att hitta punkten som ligger längst ifrån
		for(int i =start+1;i<end;i++) {
			double dist = distance(start, end, i);
			if(dist>maxDist) {
				maxDist = dist;
				pos=i;
			}
		}
		//Retunerar distansen och indexet för punkten
		double[] arr = new double[] {pos,maxDist};
		return arr;
	}
	
	public double distance(int start, int end,int point){
		return Math.abs(((vertices.get(end).x()-vertices.get(start).x())*(vertices.get(point).y()-vertices.get(start).y()))
				-((vertices.get(point).x()-vertices.get(start).x())*(vertices.get(end).y()-vertices.get(start).y())))/
				Math.sqrt(Math.pow(vertices.get(end).x()- vertices.get(start).x(),2)+Math.pow(vertices.get(end).y()- vertices.get(start).y(),2));
	}
	
}
