import java.util.*;
import java.awt.Graphics;

/**
 * This class is a specialized Linked List of Points that represents a
 * Tour of locations attempting to solve the Traveling Salesperson Problem
 * 
 * @author Lindsey Tanner
 * @version Dec. 6 2021
 */

public class Tour implements TourInterface
{
    // instance variables
    int size;
    private ListNode node; 
    Point pointy;
    double distance;
    LinkedList<Point> list;
    // constructor
    public Tour()
    {
        pointy = new Point(0.0, 0.0);
        node = new ListNode(pointy);
        distance = 0;
        size = 0;
        list = new LinkedList<Point>();
    }

    //return the number of points (nodes) in the list   
    public int size()
    {
        return list.size();
    }

    // append Point p to the end of the list
    public void add(Point p)
    {
        list.add(p);
        size++;
    } 

    // print every node in the list 
    public void print()
    {   
        for(int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
    }

    // draw the tour using the given graphics context
    public void draw(Graphics g)
    {
        //makes circles at each of the points
        for(int i = 0; i < list.size(); i++)
        {
            Point newPointy = list.get(i);
            int x = (int) newPointy.getX();
            int y = (int) newPointy.getY();
            g.fillOval(x-2, y-2, 5, 5);
        }
        for(int j = 0; j < list.size() - 1; j++)
        {
            //point at j position
            Point newPointy = list.get(j);
            //gets x and y positions at the point
            int fromx = (int) newPointy.getX();
            int fromy = (int) newPointy.getY();
            //point at j + 1 position
            Point newPointer = list.get(j + 1);
            //gets x and y positions at the point
            int tox = (int) newPointer.getX();
            int toy = (int) newPointer.getY();
            //draws the line from the first point to next
            g.drawLine(fromx, fromy, tox, toy);   
        }
        //checks if size is zero(if it is nothing happens)
        if(size != 0)
        {
            //point at zero position
            Point zero = list.get(0);
            //gets x and  y positions at the point
            int fromzerox = (int) zero.getX();
            int fromzeroy = (int) zero.getY();
            //point at last position
            Point newPointerzero = list.get(list.size() - 1);
            //gets x and y positions at the point
            int newPointerzerox = (int) newPointerzero.getX();
            int newPointerzeroy = (int) newPointerzero.getY(); 
            //draws the line from the first point to next
            g.drawLine(fromzerox, fromzeroy, newPointerzerox, newPointerzeroy);
        }
    }

    //calculate the distance of the Tour, but summing up the distance between adjacent points
    //NOTE p.distance(p2) gives the distance where p and p2 are of type Point
    public double distance()
    {
        double sum = 0.0;
        //for loop to iterate through all the points to calculate distance between them
        for(int i = 0; i < list.size() - 1; i++)
        {
            //first point
            Point newPointy = list.get(i);
            //second point
            Point newPointer = list.get(i + 1);
            //calculate distance betwn points
            double distance = newPointy.distance(newPointer);
            //adds to sum
            sum = sum + distance;
        }
        //this checks to see if size is zero because i got an error if it wasnt
        if(size != 0)
        {
            //gets the point at the zero position
            Point zero = list.get(0);
            //gets point at the end
            Point newPointerzero = list.get(list.size() - 1);
            //finds the distance between the two
            double newDistance = zero.distance(newPointerzero);
            //adds them
            sum = sum + newDistance;
        }
        //returns the sum(total distance)
        return sum;
    }

    // add Point p to the list according to the NearestNeighbor heuristic
    public void insertNearest(Point p)
    {  
        //checks if size is zero since there is nothing to compare otherwise
        if(size == 0)
        {
            //adds p to the list
            list.add(p);
            //increases the size
            size++;
        }
        //makes sure that size is greater than 1, unnecessary but makes me feel safe that there will be no index out of bounds
        if(size >= 1)
        {
            //gets the point in the 0 position of list
            Point pt = list.get(0);
            //a simple for loop to iterate through the list
            for(int i = 1; i < list.size(); i++)
            {
                //checks to see if the distance is closer to this one or the other one
                if(p.distance(pt) > p.distance(list.get(i)))
                {
                    //gets the point if it is closer to it
                    pt = list.get(i); 
                } 
            }
            //adds the point to the index of point + 1 
            list.add(list.indexOf(pt) + 1, p);
            //increaes the size by 1
            size++;
        }
    }
    // add Point p to the list according to the InsertSmallest heuristic
    public void insertSmallest(Point p)
    { 
        //index variable for where the new point will go
        int index = 0;
        //a help variable to re-initalize later to compare distances from one point to another
        double help;
        //the new distance with the point added
        double newdistance;
        //the old distance without the point added
        double olddistance;
        //the difference between the new distance and the old distance
        double difference;
        //if size is 0 or 1, there is nothing to compare so we just add p to the end of the list
        if(size == 0 || size == 1)
        {
            list.add(p);
            size++;
            return;
        }
        //sets point 3 to the first thing in the list
        Point pt3 = list.get(0);
        //sets point 4 to the last thing in the list
        Point pt4 = list.get(size - 1);
        //front back is the distance from pt4 to pt3
        double frontback = pt4.distance(pt3);
        //the distance from p to pt3 plus the distance from p to pt4
        double back = p.distance(pt3) + p.distance(pt4);
        //the difference between p to pt3 plus the distance from p to pt4
        help = back - frontback;
        //sets the index to size
        index = size;
        //a simple for loop to iterate through the list to compare the distances to see which one is the smallest increase to find where the optimal place is to put p
        for(int i = 0; i < size - 1; i++)
        {
            //the point at index i
            Point pt1 = list.get(i);
            //the point at index i + 1
            Point pt2 = list.get(i + 1);
            //the distance of the points before p is added between them
            olddistance = pt2.distance(pt1);
            //the distance from p to i and from p to i + 1
            newdistance = p.distance(pt1) + p.distance(pt2);
            //finding what the difference is between the distance with p in it and the distance without p in it (p in - p out)
            difference = newdistance - olddistance;
            //finds where the difference is the least, so whichever place in the array that causes the tour to increase the least
            if(difference < help)
            {
                //new difference is the comparison to see if it will stay being the "least" difference(whichever difference is less stays the 
                //distance variable to be the comparison)
                help = difference;
                //sets the index to where the difference is lesser
                index = i + 1;
            }
        }
        //adds the point to the place where the difference is the least, so the distance increases the least at that place
        list.add(index, p);
        //increases the size
        size++;
    }
    // This is a private inner class, which is a separate class within a class.
    private class ListNode
    {
        private Point data;
        private ListNode next;
        public ListNode(Point p, ListNode n)
        {
            this.data = p;
            this.next = n;
        }

        public ListNode(Point p)
        {
            this(p, null);
        }        
    }

}
