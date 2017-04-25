package src;

public class Coord{
		float x;
		float y;
		boolean obst;
		float dist;
		boolean track;
		
		public Coord(float xCoords, float yCoords){
			x = xCoords;
			y = yCoords;
			obst = false;
			dist = -1;
			track = false;
		}
		
		public Coord(float xCoords, float yCoords, float dist){
			x = xCoords;
			y = yCoords;
			obst = false;
			dist = -1;
			track = false;
		}
		
		public void setObst(boolean val){
			obst = val;
		}
		
		public String toString(){
			return "Coordinates: " + x + ", " + y + "\n" + "Distance: " + dist;
		}
		
		public boolean equals(Coord compare){
			return x == compare.x && y == compare.y;
		}
	}