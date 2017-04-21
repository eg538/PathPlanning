package src;

import java.util.PriorityQueue;

public class Dijkstra implements PathPlanning {
	private Coord goal;
	
	public Dijkstra(float x, float y){
		goal = new Coord(x, y);
	}
	
	@Override
	public Path calcPath(Grid g) {
		Path newMoves = new Path();
		PriorityQueue<Coord> queue = new PriorityQueue<Coord>();
		return newMoves;
	}
}
