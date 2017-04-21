package src;
import java.util.ArrayList;

public class Grid {
	private Coord[][] map;
	private int nheight;
	private double height;
	private int nwidth;
	private double width;
	private double scale;
	private Coord R2Pos;
	
	public Grid(double h, double w, double s){
		height = h;
		width = w;
		scale = s;
		nheight = (int) (h/s + .5);
		nwidth = (int) (w/s + .5);
		map = new Coord[nheight][nwidth];
		R2Pos = new Coord(0, 0);
	}
	
	private void addObstacles(Obstacles obs){
		
	}

	private void calcObst(Obstacles obs){
		//figure out what to return for this method
	}
	
	private void updatePos(float x, float y){
		R2Pos = new Coord(x, y);
	}
	
}

