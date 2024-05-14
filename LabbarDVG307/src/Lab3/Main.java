package Lab3;

import java.util.ArrayList;

public class Main
{
    public static void main (String[] args)
    {
    	DouglasPecker dp = new DouglasPecker("H:\\Algoritmer DVG307\\Lab3\\dp_testpoints.txt");

    	dp.drawVertices();
    	ArrayList<Integer> al = new ArrayList<>();
    	int start = dp.getStart();
    	int end = dp.getEnd();
    	al.add(start);
    	dp.dpAlgorithm(al, start,end, 0.4);
    	al.add(end);
    	dp.drawVertices(al);
    	
    }
}