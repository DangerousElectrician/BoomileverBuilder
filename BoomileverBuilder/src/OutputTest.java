
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;


@SuppressWarnings("serial")
public class OutputTest extends Panel
	
	implements MouseListener{
	
	final static int RIGHT_OFFSET=20;
	final static int TOP_OFFSET=60;
	
	static int searchRadius = 8;
	
	static double scale=2;
	static int circleSize=8;
	static int circleCenter=circleSize/2;
	
	static int WindowWidth=1300;
	static int WindowHeight=700;
		
	static int mode=1;
	
	static int selectNode=-1;
	
	Stroke drawingStroke = new BasicStroke(2);

	public void paint(Graphics g) {
		addMouseListener(this);
		Graphics2D graph = (Graphics2D)g;
		graph.setStroke(drawingStroke);
		
		for (int i = 0; i < Simulator.getBeamLinkSize(); i++) {
			double[] currentBeam = Simulator.getBeamLink(i);
			int x1=gridX(Simulator.getNodeX((int) currentBeam[0]));
			int y1=gridY(Simulator.getNodeY((int) currentBeam[0]));
			int x2=gridX(Simulator.getNodeX((int) currentBeam[1]));
			int y2=gridY(Simulator.getNodeY((int) currentBeam[1]));
			
			graph.setPaint(Color.orange);
			graph.drawLine(x1, y1, x2, y2);
		}
		
		for (int i = 0; i < Simulator.getNodeLinkSize(); i++) {
			int nodeType = Simulator.getNodeLinkType(i);
			if (nodeType!=-1) {
				int x=gridX(Simulator.getNodeLink(i, Simulator.X))-circleCenter;
				int y=gridY(Simulator.getNodeLink(i, Simulator.Y))-circleCenter;				
				if (i== selectNode) {
					graph.setPaint(Color.red);
				} else if (nodeType == Simulator.TYPE_FIXED_NODE){
					graph.setPaint(Color.black);
				} else if (nodeType == Simulator.TYPE_FIXED_X_NODE) {
					graph.setPaint(Color.cyan);
				} else if (nodeType == Simulator.TYPE_FIXED_Y_NODE) {
					graph.setPaint(Color.green);
				} else {
					graph.setPaint(Color.blue);
				}
				graph.drawString(Integer.toString(i), x+6, y);
				graph.fillOval(x, y, circleSize, circleSize);
			}			
		}		
	}
	
	public static int gridY(double coordinate) {
		return (int)(WindowHeight-((coordinate*scale)+TOP_OFFSET));
	}
	
	public static int gridX(double coordinate) {
		return (int)((coordinate*scale)+RIGHT_OFFSET);
	}

	public static void main(String[] args) {
		
		Panel frame = new OutputTest();
		/*frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				 System.exit(0);
			}
		});*/
//		frame.setTitle("OutputTest");
		frame.setSize(WindowWidth, WindowHeight);
		frame.setVisible(true);
		ControlWindow.startControlGUI();

		Scanner scan = new Scanner(System.in);
		while(true) {
			switch (scan.nextInt()) {
			case 0:
				/*Simulator.toArray();
				for (int i = 0; i < Simulator.node.length; i++) {
					 System.out.println("array  "+Simulator.node[i][0]+ " "+ Simulator.node[i][1] + " " + Simulator.nodeType[i]);
				}*/
				break;
				
			case 1:
				mode=1;
				break;
				
			case 2:
				mode=2;
				break;
				
			case -1:
				System.exit(0); //emergency exit just in case
				break;

			default:
				// System.out.println("invalid");
				ControlWindow.print("Invalid mode");
			}
		}
	}
	
	long prevTime=0;
	int[] beamNode= {-1,-1};
	int beamNodeIndex=0;
	
	@Override
	public void mousePressed(MouseEvent e) {

		if (prevTime<=e.getWhen()) {
			double x=(e.getX()-RIGHT_OFFSET)/scale;
			double y=((WindowHeight-TOP_OFFSET-e.getY())/scale);
			if (e.getButton()==MouseEvent.BUTTON1) {
				
				if (mode==1) {
					if (Simulator.findNode(x, y, searchRadius)==-1) {
						int nodeIndex = Simulator.addNode(x, y);
						ControlWindow.print("Added node "+ nodeIndex +" at " +x +", "+y);
						selectNodePrint(nodeIndex, false);
					} else {
						int foo=Simulator.findNode(x, y, searchRadius);
						if (Simulator.getNodeLinkType(foo) != -1) {
							selectNodePrint(foo, true);
						} else {
							int nodeIndex = Simulator.addNode(x, y);
							ControlWindow.print("Added node "+ nodeIndex +" at " +x +", "+y);
							selectNodePrint(nodeIndex, false);
						}
						
					}
					
				} else if(mode==2){
					
					int foo=Simulator.findNode(x, y, searchRadius);
					if (foo>-1) {
						if (Simulator.getNodeLinkType(foo) != -1) {
							if (beamNodeIndex==0) {
								selectNodePrint(foo, true);
								beamNode[beamNodeIndex++]=foo;
							} else if (beamNodeIndex==1) {
								selectNodePrint(foo, true);
								beamNode[beamNodeIndex]=foo;
								addBeamPrint(beamNode[0], beamNode[1]);
								beamNodeIndex=1;
								beamNode[0]=foo;
								selectNodePrint(foo, true);
							}
						}						
					}
				}
				
			} else if(e.getButton()==MouseEvent.BUTTON3) {
				
				if (mode==2) {
					int foo=Simulator.findNode(x, y, searchRadius);
					if (foo>-1) {
						if (beamNode[0]==-1) {
							selectNodePrint(foo, true);
							beamNode[0]=foo;
							beamNodeIndex=1;
						} else {
							addBeamPrint(beamNode[0], foo);
						}						
					} else {
						selectNode=-1;
						beamNode[0]=-1;
						beamNodeIndex=0;
					}
				}
			}
			
			repaint();
			prevTime=e.getWhen()+100;
			
		} 
	}
	
	public void addBeamPrint(int node1, int node2) {
		if(node1!=node2) {
			int beamCookie = Simulator.addBeam(node1, node2);
			if (beamCookie==-99) {
				ControlWindow.print("Created beam from node " + node1 + " to node " + node2);				
			}
		}
	}
	
	public static void selectNodePrint(int node, boolean print) {
		if (node!=selectNode) {
			selectNode=node;
			double[] foo = Simulator.getNodeLinkArray(node);
			ControlWindow.setSpinNode(node);
			ControlWindow.setSpinX(foo[0]);
			ControlWindow.setSpinY(foo[1]);
			ControlWindow.setSpinForceX(foo[2]);
			ControlWindow.setSpinForceY(foo[3]);
			ControlWindow.setMovementSelect(Simulator.getNodeLinkType(node));
			if (print) {
				ControlWindow.print("Selected node " + node + " at "+foo[0] + ", " +foo[1]);
			}
		}		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}