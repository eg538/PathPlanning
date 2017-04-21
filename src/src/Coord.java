package src;

public class Coord{
		float x;
		float y;
		boolean obst;
		
		public Coord(float xCoords, float yCoords){
			x = xCoords;
			y = yCoords;
			obst = false;
		}
		
		public Coord(float xCoords, float yCoords, float dist){
			x = xCoords;
			y = yCoords;
			obst = false;
		}
		
		public void setObst(boolean val){
			obst = val;
		}
	}