package src;

import java.util.Comparator;

public class CompareCoords implements Comparator<Coord>{

	@Override
	public int compare(Coord o1, Coord o2) {
		if(o1.dist < o2.dist){
			return -1;
		}
		if(o1.dist == o2.dist){
			return 0;
		}
		return 1;
	}

}
