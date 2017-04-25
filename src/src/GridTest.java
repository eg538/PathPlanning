package src;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GridTest {
	Grid g;
	
	@Before
	public void prep(){
		g = new Grid(4, 5, (float) .5, 0.25f, 0.25f);
		for(int i = 0; i < g.map.length; i++){
			for(int j = 0; j < g.map[0].length; j++){
				System.out.print("(" + g.map[i][j].x + ", " + g.map[i][j].y + ")" + " [" + i + ", " + j +"]    ");
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
		assertEquals(g.intPos((float).25, (float).25)[0], 0);
		assertEquals(g.intPos((float)1.25, (float).25)[1], 2);
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
