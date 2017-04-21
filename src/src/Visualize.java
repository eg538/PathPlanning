package src;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Visualize {
	public static void main(String[] args) {
		JFrame window = new JFrame("Path Planner");
		VisualizationCanvas canvas = new VisualizationCanvas();
		window.add(canvas);
		window.pack();
		
		Grid g = new Grid(50.f, 100.0f, 1.0f);
		Coord position = new Coord(25.0f, 50.0f);
		
		canvas.setGrid(g);
		canvas.setPosition(position);
		canvas.repaint();
		
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class VisualizationCanvas extends Canvas {
	private static final long serialVersionUID = 3069601185478921231L;
	private Grid grid = null;
	private Path path = null;
	private Coord position = null;
	
	public VisualizationCanvas() {
		super();
		
		setSize(800, 800);
	}
	
	public void setGrid(Grid g) {
		grid = g;
	}
	
	public void setPath(Path p) {
		path = p;
	}
	
	public void setPosition(Coord c) {
		position = c;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if (grid != null) {
			float scale = Math.max(getWidth(), getHeight()) / Math.max(grid.width, grid.height);
			
			g.setColor(Color.BLACK);
			for (float x = 0; x <= grid.width + grid.scale / 2.0; x += grid.scale) {
				g.drawLine((int) (x * scale), 0, (int) (x * scale), (int) (grid.height * scale));
			}
			for (float y = 0; y <= grid.height + grid.scale / 2.0; y += grid.scale) {
				g.drawLine(0, (int) (y * scale), (int) (grid.width * scale), (int) (y * scale));
			}
			
			if (path != null) {
				g.setColor(Color.RED);
				for (int i = 1; i < path.path.size(); i++) {
					g.drawLine(
							(int) (path.path.get(i-1).x * scale),
							(int) (path.path.get(i-1).y * scale),
							(int) (path.path.get(i).x * scale),
							(int) (path.path.get(i).y * scale));
				}
			}
			
			if (position != null) {
				g.setColor(Color.BLUE);
				g.drawArc((int) (position.x * scale), (int) (position.y * scale),
						(int) (grid.scale * 0.25f * scale), (int) (grid.scale * 0.25f * scale),
						0, 360);
			}
		}
	}
}
