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
	
	public Grid(float w, float h, float scal, float startx, float starty){
		width = w;
		height = h;
		scale = scal;
		nheight = (int) (h/scal + .5);
		nwidth = (int) (w/scal + .5);
		map = new Coord[nheight][nwidth];
		R2Pos = new Coord(startx, starty);
		int[] intInit = intPos(startx, starty);
		for(int i = 0; i < nheight; i++){
			for(int j = 0; j < nwidth; j++){
				map[i][j] = new Coord((.5f)*scale + scale*((float)(j)), (.5f)*scale + scale*((float)(i)));
			}
		}
		map[intInit[0]][intInit[1]] = R2Pos;
	}
	
	public void addObstacles(ArrayList<int[]> obs){
		for(int[] o: obs){
			map[o[0]][o[1]].obst = true;
		}
	}

	private ArrayList<int[]> calcObst(ListObstacles obs){
		ArrayList<int[]> coordObst = new ArrayList<int[]>();
		float top;
		float bottom;
		float left;
		float right;
		float trackv;
		float trackh;
		for(Obstacle block: obs.obstacles){
			top = block.xpos + block.height*.5f;
			bottom = block.xpos - block.height*.5f;
			left = block.ypos - block.width*.5f;
			right = block.ypos + block.width*.5f;
			trackv = top;
			trackh = left;
			while(trackv <= bottom || trackh != right){
				coordObst.add(intPos(trackv, trackh));
				trackv += scale;
				if(trackv > bottom){
					trackv = top;
					trackh = right;
				}
			}
			coordObst.add(intPos(bottom, trackh));
			
		}
		return coordObst;
	}
	
	/** 
	 * @return the cells on the grid surrounding the cell (x, y)
	 */
	public ArrayList<Coord> adjCoords(int x, int y){
		ArrayList<Coord> adj = new ArrayList<Coord>();
		if(x + 1 < nheight){
			adj.add(map[x + 1][y]);
			if(y + 1 < nwidth){
				adj.add(map[x + 1][y + 1]);
			}
		}
		if(y + 1 < nwidth){
			adj.add(map[x][y + 1]);
		}
		if(x - 1 >= 0){
			adj.add(map[x - 1][y]);
			if(y + 1 < nwidth){
				adj.add(map[x - 1][y + 1]);
			}
			if(y - 1 >= 0){
				adj.add(map[x - 1][y - 1]);
			}
		}
		if(y - 1 >= 0){
			adj.add(map[x][y - 1]);
			if(x + 1 < nheight){
				adj.add(map[x + 1][y - 1]);
			}
		}
		return adj;
		
	}
	
	public int[] intPos(float x, float y){
		int[] pos = new int[2];
		pos[0] = (int) ((y - (.5f)*scale)/scale + .5);
		pos[1] = (int) ((x - (.5f)*scale)/scale + .5);
		return pos;
		
		//width = scale, height = scale
		//coordinates: width = array1stcoord*scale -> array1stcoord*scale + scale
		//height = array2ndcoord*scale -> array2ndcoord*scale + scale
	}
	
	public float distance(Coord a, Coord b){
		return (float) Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
	}
	
	private void updatePos(float x, float y){
		R2Pos = new Coord(x, y);
	}

	
}

