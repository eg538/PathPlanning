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
	public ArrayList<Obstacle> obstacles;

	public Grid(float w, float h, float scal, float startx, float starty) {
		width = w;
		height = h;
		scale = scal;
		nheight = (int) (h / scal + .5);
		nwidth = (int) (w / scal + .5);
		map = new Coord[nheight][nwidth];
		R2Pos = new Coord(startx, starty);
		obstacles = new ArrayList<Obstacle>();
		int[] intInit = intPos(startx, starty);
		for (int i = 0; i < nheight; i++) {
			for (int j = 0; j < nwidth; j++) {
				map[i][j] = new Coord((.5f) * scale + scale * ((float) (j)), (.5f) * scale + scale * ((float) (i)));
			}
		}
		map[intInit[0]][intInit[1]] = R2Pos;
	}

	public void addObstacle(Obstacle obst) {
		obstacles.add(obst);
		float top;
		float bottom;
		float left;
		float right;
		int[] coordsOne;
		int[] coordsTwo;
		top = obst.ypos - obst.height * .5f - (13f/scale);
		if(top < 0){
			top = 0;
		}
		bottom = obst.ypos + obst.height * .5f + (13f/scale);
		if(bottom > height){
			bottom = height;
		}
		left = obst.xpos - obst.width * .5f - (13f/scale);
		if(left < 0){
			left = 0;
		}
		right = obst.xpos + obst.width * .5f + (13f/scale);
		if(right > width){
			right = width;
		}
		coordsOne = intPos(left, top);
		coordsTwo = intPos(right, bottom);
		for (int t = coordsOne[0]; t <= coordsTwo[0]; t++) {
			for(int u = coordsOne[1]; u <= coordsTwo[1]; u++){
				map[t][u].obst = true;
			}
		}
	}
	/**
	 * @return the cells on the grid surrounding the cell (x, y)
	 */
	public ArrayList<Coord> adjCoords(int x, int y) {
		ArrayList<Coord> adj = new ArrayList<Coord>();
		if (x + 1 < nheight) {
			adj.add(map[x + 1][y]);
			if (y + 1 < nwidth) {
				adj.add(map[x + 1][y + 1]);
			}
		}
		if (y + 1 < nwidth) {
			adj.add(map[x][y + 1]);
		}
		if (x - 1 >= 0) {
			adj.add(map[x - 1][y]);
			if (y + 1 < nwidth) {
				adj.add(map[x - 1][y + 1]);
			}
			if (y - 1 >= 0) {
				adj.add(map[x - 1][y - 1]);
			}
		}
		if (y - 1 >= 0) {
			adj.add(map[x][y - 1]);
			if (x + 1 < nheight) {
				adj.add(map[x + 1][y - 1]);
			}
		}
		return adj;

	}

	public int[] intPos(float x, float y) {
		int[] pos = new int[2];
		pos[0] = (int) ((y - (.5f) * scale) / scale + .5);
		pos[1] = (int) ((x - (.5f) * scale) / scale + .5);
		return pos;

		// width = scale, height = scale
		// coordinates: width = array1stcoord*scale -> array1stcoord*scale +
		// scale
		// height = array2ndcoord*scale -> array2ndcoord*scale + scale
	}

	public float distance(Coord a, Coord b) {
		return (float) Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
	}

	private void updatePos(float x, float y) {
		R2Pos = new Coord(x, y);
	}

}
