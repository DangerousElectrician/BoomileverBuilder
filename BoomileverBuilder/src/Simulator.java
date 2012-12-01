
import java.util.LinkedList;

public class Simulator {	
	
	private static double[][] node; //x, y, force , angle, extra
									//0  1	  2	     3		4 
	
	static final int X = 0;
	static final int Y = 1;
	static final int FORCE = 2;
	static final int ANGLE = 3;
	
	private static int [][] nodeTypeConnect; // all data the node stores in ints
//	private static int nodeEntry=0;
	
	private static LinkedList<LinkedList<Integer>> nodeLinkTypeConnect = new LinkedList<LinkedList<Integer>>(); 
	
	private static double[][] beam; //node1, node2, force vector component x, force vector component y, vector, extra
//	private static int beamEntry=0;
	
	private static LinkedList<double[]> nodeLink= new LinkedList<double[]>(); //x, y, force vector component x, force vector component y, type 
															   		  //0  1		2						3							4 
	private static LinkedList<Integer> specialNodes = new LinkedList<Integer>();
	
	private static LinkedList<double[]> beamLink= new LinkedList<double[]>();
	
	static final int TYPE_DELETED = -1;
	static final int TYPE_FREE_NODE = 0;
	static final int TYPE_FIXED_NODE = 1;
	static final int TYPE_FIXED_X_NODE = 2;
	static final int TYPE_FIXED_Y_NODE = 3;
	
	static int addNode(double newNodeX, double newNodeY){
		nodeLink.add(new double[] {newNodeX,newNodeY,0,0,0});
		LinkedList<Integer> cookieList = new LinkedList<Integer>();
		cookieList.add(new Integer(0));
		nodeLinkTypeConnect.add(cookieList);
		return (nodeLink.size()-1);
	}
	
	static void setNodeLinkType(int index, int type) { 
		try {
			LinkedList<Integer> cookieList = nodeLinkTypeConnect.get(index);
			cookieList.set(0, type);
			nodeLinkTypeConnect.set(index, cookieList);
		} catch (Exception e) { //does something funny on initialization and tries to read index -1
		}
	}
	
	static int getNodeLinkType(int index) {
		return nodeLinkTypeConnect.get(index).get(0);
	}
/*	
	private static boolean setNodeTypeConnect(int nodeIndex, int type) {
		try {
			nodeTypeConnect[nodeIndex][0]=type;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}
	
	type=-1 soft deleted
    type=0 regular node
    type=1 constant force node
    type=2 fixed x force
    type=3 fixed y force
 
	*/
	
	static void setNodeLinkForce(int index, double force, double angle) {
		double[] element = nodeLink.get(index);
		element[2] = force;
		element[3] = angle;
		nodeLink.set(index, element);
	}
	
/*	
	private static boolean setNodeForce(int nodeIndex, double xforce, double yforce) {
		try {
			if (nodeTypeConnect[nodeIndex][0]==0 || nodeTypeConnect[nodeIndex][0]==1) {
				nodeTypeConnect[nodeIndex][0]=1;
				node[nodeIndex][2]=xforce;
				node[nodeIndex][3]=yforce;
				return true;
			} else {
				return false;			
			}			
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	private static boolean setNodeForceX(int nodeIndex, double force) {
		try {
			if (nodeTypeConnect[nodeIndex][0]==0 || nodeTypeConnect[nodeIndex][0]==2) {
				nodeTypeConnect[nodeIndex][0]=2;
				node[nodeIndex][2]=force;
				node[nodeIndex][3]=0;
				return true;
			} else {
				return false;			
			}			
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	private static boolean setNodeForceY(int nodeIndex, double force) {
		try {
			if (nodeTypeConnect[nodeIndex][0]==0 || nodeTypeConnect[nodeIndex][0]==3) {
				nodeTypeConnect[nodeIndex][0]=3;
				node[nodeIndex][2]=0;
				node[nodeIndex][3]=force;
				return true;
			} else {
				return false;			
			}			
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	*/
	static int findNode(double x, double y, double searchRadius) {
		int i=0;
		for (double[] v : nodeLink) {
			if(Math.sqrt((Math.pow(v[0]-x, 2)+Math.pow(v[1]-y, 2)))<=searchRadius) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	static double getNodeX(int index) {
		return nodeLink.get(index)[0];
	}
	
	static double getNodeY(int index) {
		return nodeLink.get(index)[1];
	}
	
	static int addBeam(int node1, int node2) {
		if (isNotInList(node2, nodeLinkTypeConnect.get(node1))) {
			beamLink.add(new double[] {node1, node2, 0,0,0,0});
			LinkedList<Integer> cookieList = nodeLinkTypeConnect.get(node1);
			cookieList.add(new Integer(node2));
			nodeLinkTypeConnect.set(node1, cookieList);
			cookieList = nodeLinkTypeConnect.get(node2);
			cookieList.add(new Integer(node1));
			nodeLinkTypeConnect.set(node2, cookieList);
			return (beamLink.size()-1);			
		}
		return -1;
	}
	
	private static boolean isNotInList(int value, LinkedList<Integer> list) {
		for (Integer brownie : list) {
			if(brownie.intValue() == value) {
				return false;
			}
		}
		return true;
	}
	
	static int getNodeLinkSize() {
		return nodeLink.size();
	}
	
	static double getNodeLink(int index, int property) {
		return nodeLink.get(index)[property];
	}
	
	static double[] getNodeLinkArray(int index) {
		return nodeLink.get(index);
	}
	
	static double[] getBeamLink(int index) {
		return beamLink.get(index);
	}

	static int getBeamLinkSize() {
		return beamLink.size();
	}

	static void setNodeLink(int index, int property, double value) {
		double[] cookie = nodeLink.get(index);
		cookie[property] = value;
		nodeLink.set(index, cookie);
	}
	
	static void toArray() {
		beam=new double [beamLink.size()][];
		int i = 0; for (double[] v : beamLink) beam[i++] = v;
		node=new double [nodeLink.size()][];
		nodeTypeConnect=new int [nodeLink.size()][];
		
		for (int j = 0; j < node.length; j++) {
//			node[j] = new double[nodeLink.size()];
			node[j]=nodeLink.get(j);
			printArray(node[j]);
		}

		for (int j = 0; j < nodeTypeConnect.length; j++) {
			LinkedList<Integer> cookieList = nodeLinkTypeConnect.get(j);
			nodeTypeConnect[j] = new int [cookieList.size()];
			for (int k = 0; k < nodeTypeConnect[j].length; k++) {
				nodeTypeConnect[j][k] = -99;
			}
			for (int k = 0; k < nodeTypeConnect[j].length; k++) {
				int cookie = cookieList.get(k).intValue();
				if (isNotInArray(cookie, nodeTypeConnect[j])) {
					nodeTypeConnect[j][k] = cookieList.get(k).intValue();
				}				
			}
		}
	}
	
	private static boolean isNotInArray(int value ,int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (value == array[i]) {
				return false;
			}
		}
		return true;
	}

	static void toList() {
		nodeLink.clear();
		nodeLinkTypeConnect.clear();
		beamLink.clear();
		
		for (int i = 0; i < node.length; i++) {
			addNode(node[i][0], node[i][1]);
			setNodeLinkType(i, nodeTypeConnect[i][0]);
			setNodeLinkForce(i, node[i][2], node[i][3]);
		}
		for (int i = 0; i < beam.length; i++) {
			beamLink.add(beam[i]);
		}
	}
	
	static void deleteNode(int index) {
		setNodeLinkType(index, TYPE_DELETED);
	}
	
	static void simulate() {
		toArray();
		specialNodes.clear();
		for (int i = 0; i < nodeTypeConnect.length; i++) {
			if (nodeTypeConnect[i][0]!=0) {
				specialNodes.add(i);
			}
		}
		System.out.println("");
		for (Integer v : specialNodes) {
			System.out.print(v+": ");	
			for (int i = 0; i < nodeTypeConnect[v].length; i++) {
				System.out.print(nodeTypeConnect[v][i]+" ");
				
			}
			System.out.println("");
		}		
	}

	private static void printArray(double[] array) {
		/*for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println("");*/
	}
	
}