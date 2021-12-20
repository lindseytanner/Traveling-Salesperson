PROJECT TITLE: Traveling Salesperson Problem
PURPOSE OF PROJECT: Implement two greedy heuristics in the attempt to find
an optimal solution to the Traveling Salesperson Problem
DATE: December 13, 2021
AUTHOR: Lindsey Tanner
ALGORITHM DESCRIPTIONS: 
insertNearest()
    This algorithm is O(N^2)
    1. Checks if the size is zero and if it is, it adds the point to the end of the list
    2. Checks if size is greater than one, then there is a nested for loop that iterates 
        through all the points to see which point in the list the new point is closest to.
    3. Now, it adds the point after the point it is closest.
insertSmallest()
        This algorithm is O(N^2)
        1. Basically, my algorithm overall goes through the list and finds the distance between two points (a b)
            before the point is added. Then, the point(p) is added between the two(a p b)  in the list and finds the distance from point a to p and point b to p and adds them.
            Then, it finds the difference of the added together new distance and the old distance. Then it iterates through the list to find where the difference is the smallest
            this is because it is trying to find where the total distance will increase the least.
        This algorithm finds where the total distance increases the smallest. 
        Basically, you are putting the point in the list where the total distance will increase the least.
            increases the least amount.