package src;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra implements PathPlanning {
	private float goalx;
	private float goaly;
	private Coord goal;
	private Coord init;
	
	public Dijkstra(float x, float y){
		goalx = x;
		goaly = y;
	}
	
	@Override
	public Path calcPath(Grid g) {
		int[] goalInt = g.intPos(goalx, goaly);
		try{
			goal = g.map[goalInt[0]][goalInt[1]];
		}catch(Exception E){
			goal = g.R2Pos;
		}
		CompareCoords comp = new CompareCoords();
		PriorityQueue<Coord> queue = new PriorityQueue<Coord>(comp);
		g.R2Pos.dist = 0;
		init = g.R2Pos;
		queue.add(g.R2Pos);
		Coord head = null;
		ArrayList<Coord> neighbors;
		int[] intCoords;
		while(!queue.peek().equals(goal)){
			head = queue.poll();
			intCoords = g.intPos(head.x, head.y);
			neighbors = g.adjCoords(intCoords[0], intCoords[1]);
			for(Coord c: neighbors){
				if(c.dist == -1){
					if(c.obst){
						c.dist = Float.POSITIVE_INFINITY;
					}else{
						c.dist = g.distance(c, head) + head.dist;
					}
					queue.add(c);
				}else{
					c.dist = Math.min(c.dist, c.dist + head.dist);
				}
			}
			head.track = true;
		}
		head = queue.poll();
		return givePath(g);
	}
	
	public Path givePath(Grid g){
		Path moves = new Path();
		moves.path.add(goal);
		int[] intCoords = g.intPos(goal.x, goal.y);
		ArrayList<Coord> neighbors = g.adjCoords(intCoords[0], intCoords[1]);
		Coord current = goal;
		current.track = false;
		while(!current.equals(init) && !neighbors.contains(init)){
			for (Coord c: neighbors){
				if(c.track && Math.abs(c.dist - Math.abs(current.dist - g.distance(c, current))) < .00001){
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
	
	public float[] goal(){
		float[] gl = new float[2];
		gl[0] = goalx;
		gl[1] = goaly;
		return gl;
	}
}
