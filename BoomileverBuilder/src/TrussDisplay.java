import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class TrussDisplay extends JFrame{

	final static int RIGHT_OFFSET=20;
	final static int TOP_OFFSET=60;

	static int searchRadius = 8;

	static double scale=2;
	static int circleSize=4;
	static int circleCenter=circleSize/2;
	static int circleSelectSize=12;
	static int circleSelectCenter=circleSelectSize/2;

	public static int WindowWidth=100;
	public static int WindowHeight=100;

	public TrussDisplay(Truss truss, int x, int y, String caption) {
		super(caption);


		setContentPane(new DrawTruss(truss, caption));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(WindowWidth, WindowHeight);

		setLocation(x, y);

		setVisible(true);
	}

	class DrawTruss extends JPanel{

		Truss truss;
		String caption;
		public DrawTruss(Truss truss, String caption) {
			this.truss = truss;
			this.caption = caption;
		}

		@Override
		public void paintComponent(Graphics g) {
			for (Truss.Node node : truss.getNodeList()) {
				int x = gridX(node.x);
				int y = gridY(node.y);
				g.fillOval(x-circleCenter, y-circleCenter, circleSize, circleSize);
			}

			for (Truss.Beam beam : truss.getBeamList()) {
				g.drawLine(gridX(truss.getNodeList().get(beam.n1).x),
						gridY(truss.getNodeList().get(beam.n1).y),
						gridX(truss.getNodeList().get(beam.n2).x),
						gridY(truss.getNodeList().get(beam.n2).y));
			}
			g.drawString(caption, 0, WindowHeight-42);
		}
	}

	public static int gridY(double coordinate) {
		return (int)(WindowHeight-((coordinate*scale)+TOP_OFFSET));
	}

	public static int gridX(double coordinate) {
		return (int)((coordinate*scale)+RIGHT_OFFSET);
	}
}
