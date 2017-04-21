package src;
import java.util.ArrayList;

public class Grid {
	public Coord[][] map;
	public int nheight;
	public float height;
	public int nwidth;
	public float width;
	public float scale;
	public Coord R2Pos;
	
	public Grid(float w, float h, float s){
		width = w;
		height = h;
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

