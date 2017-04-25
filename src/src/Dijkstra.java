package src;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra implements PathPlanning {
	private float goalx;
	private float goaly;
	private Coord init;
	
	public Dijkstra(float x, float y){
		goalx = x;
		goaly = y;
	}
	
	@Override
	public Path calcPath(Grid g) {
		int[] goalInt = g.intPos(goalx, goaly);
		Coord goal = g.map[goalInt[0]][goalInt[1]];
		CompareCoords comp = new CompareCoords();
		PriorityQueue<Coord> queue = new PriorityQueue<Coord>(1, comp);
		init = g.R2Pos;
		init.dist = 0;
		queue.add(g.R2Pos);
		Coord head = null;
		ArrayList<Coord> neighbors;
		int[] intCoords;
		while(!queue.peek().equals(goal)){
			head = queue.poll();
			intCoords = g.intPos(head.x, head.y);
			neighbors = g.adjCoords(intCoords[0], intCoords[1]);
			for(Coord c: neighbors){
				if(c.obst){
					c.dist = Float.POSITIVE_INFINITY;
					queue.add(c);
				}else if(c.dist == -1){
					c.dist = g.distance(c, head) + head.dist;
					queue.add(c);
				}else if(!c.track){
					c.dist = Math.min(c.dist, c.dist + head.dist);
				}
			}
			head.track = true;
		}
		head = queue.poll();
		return givePath(g, head);
	}
	
	public Path givePath(Grid g, Coord end){
		Path moves = new Path();
		moves.path.add(end);
		int[] intCoords = g.intPos(end.x, end.y);
		ArrayList<Coord> neighbors = g.adjCoords(intCoords[0], intCoords[1]);
		Coord current = end;
		current.track = false;
		while(!neighbors.contains(init)){
			for (Coord c: neighbors){
				if(c.track && Math.abs(c.dist - Math.abs(current.dist - g.distance(c, current))) < .000001){
					moves.path.add(c);
					c.track = false;
					current = c;
					break;
				}
			}
			intCoords = g.intPos(current.x, current.y);
			neighbors = g.adjCoords(intCoords[0], intCoords[1]);
			current.track = false;
		}
		moves.path.add(init);
		return moves;
	}
}
