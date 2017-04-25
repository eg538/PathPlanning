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
	
	public Grid(float w, float h, float s, float startx, float starty){
		width = w;
		height = h;
		scale = s;
		nheight = (int) (h/s + .5);
		nwidth = (int) (w/s + .5);
		map = new Coord[nheight][nwidth];
		R2Pos = new Coord(startx, starty);
		int[] intInit = intPos(startx, starty);
		for(int i = 0; i < nheight; i++){
			for(int j = 0; j < nwidth; j++){
				map[i][j] = new Coord(((float)(.5))*scale + scale*((float)(j)), ((float)(.5))*scale + scale*((float)(i)));
			}
		}
		map[intInit[0]][intInit[1]] = R2Pos;
	}
	
	/*public Grid(float w, float h, float s, Coord start){
		width = w;
		height = h;
		scale = s;
		nheight = (int) (h/s + .5);
		nwidth = (int) (w/s + .5);
		map = new Coord[nheight][nwidth];
		R2Pos = start;
		for(int i = 0; i < nheight; i++){
			for(int j = 0; j < nwidth; j++){
				map[i][j] = new Coord(.5f*scale + scale*((float)(j)), .5f*scale + scale*((float)(i)));
			}
		}
	}*/
	
	public void addObstacles(ArrayList<float[]> obs){
		int[] obstnum;
		for(float[] o: obs){
			obstnum = intPos(o[0], o[1]);
			map[obstnum[0]][obstnum[1]].obst = true;
		}
	}

	private void calcObst(Obstacles obs){
		//figure out what to return for this method
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
		}
		return adj;
		
	}
	
	public int[] intPos(float x, float y){
		int[] pos = new int[2];
		pos[0] = (int) ((y - scale/2)/scale);
		pos[1] = (int) ((x - scale/2)/scale);
		return pos;
	}
	
	public float distance(Coord a, Coord b){
		return (float) Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
	}
	
	private void updatePos(float x, float y){
		R2Pos = new Coord(x, y);
	}

	
}

