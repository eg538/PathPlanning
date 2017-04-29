package src;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GridTest {
	Grid g;
	
	@Before
	public void prep(){
		g = new Grid(10f, 8f, (float) .45, 0.25f, 0.25f);
		for(int i = 0; i < g.map.length; i++){
			for(int j = 0; j < g.map[0].length; j++){
				//System.out.print("(" + g.map[i][j].x + ", " + g.map[i][j].y + ")");
				int[] intCoords = g.intPos(g.map[i][j].x, g.map[i][j].y);
				System.out.print("[" + intCoords[0] + ", " + intCoords[1] + "] ");
			}
			System.out.println();
		}
	}
	
	@Test
	public void testCoordinates(){
		assertTrue(g.map[0][1].x == .75);
		assertTrue(g.map[0][0].y == .25);
	}
	
	@Test
	public void testIntPos() {
		assertEquals(g.intPos(.025f, .02f)[0], 0);
		assertEquals(g.intPos(.04f, .005f)[1], 0);
	}
	
	@Test
	public void testNeigh(){
		ArrayList<Coord> children = g.adjCoords(0, 1);
		int [] coordInts= new int[2]; 
		for(Coord c: children){
			coordInts = g.intPos(c.x, c.y);
			System.out.println(coordInts[0] + ", " +coordInts[1]);
		}
	}

}
