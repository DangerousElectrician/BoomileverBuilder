import java.util.LinkedList;


public class Truss {

	private final LinkedList<Node> nodeList;
	private final LinkedList<Properties> nodeProp;
	private final LinkedList<Beam> beamList;
	private final LinkedList<Beam> beamHashList;

	private final int signCon = -1;

	public Truss() {
		nodeList = new LinkedList<Node>();
		nodeProp = new LinkedList<Truss.Properties>();
		beamList = new LinkedList<Truss.Beam>();
		beamHashList = new LinkedList<Truss.Beam>();
	}

	public Truss(Truss truss) {
		this.nodeList = new LinkedList<Truss.Node>(truss.getNodeList());
		this.nodeProp = new LinkedList<Truss.Properties>(truss.getNodeProperties());
		this.beamList = new LinkedList<Truss.Beam>(truss.getBeamList());
		this.beamHashList = new LinkedList<Beam>(truss.getBeamHashList());
	}

	public void addNode(double x, double y) {
		nodeList.add(new Node(x,y,nodeList.size()));
	}

	public void addBeam(int node1, int node2) {
		int i = 0;
		int hash = hashBeam(node1, node2);
		for (Beam hashBeam : beamHashList) {
			if (hashBeam.hash < hash) {
				break;
			} else if (hashBeam.hash == hash) {
				i = -1;
				break;
			}
			i++;
		}
		if (i>-1) {
			Beam beam = new Beam(node1, node2, 0, beamList.size());
			beamList.add(beam);
			beamHashList.add(i, beam);
		}
	}

	public LinkedList<Node> getNodeList() {
		return nodeList;
	}

	public LinkedList<Beam> getBeamList() {
		return beamList;
	}

	public LinkedList<Beam> getBeamHashList() {
		return beamHashList;
	}

	public static int REGULAR = 0;
	public static int FIXED = 1;
	public static int ROLLING_X = 2;
	public static int ROLLING_Y = 3;
	public static int LOADED= 4;

	public void setNodeProperties(int node, int type) {
		nodeProp.add(new Properties(node, type));
	}

	public void setNodeProperties(int node, int type, double force, double angle) {
		nodeProp.add(new Properties(node, type, force, angle));
	}

	public LinkedList<Properties> getNodeProperties(){
		return nodeProp;
	}

	private double[][] toArray() {
		int xRoll = 0;
		int yRoll = 0;
		int Fixed = 0;
		for (Properties prop : nodeProp) {
			if(prop.type == Truss.FIXED)	Fixed++;
			else if(prop.type == Truss.ROLLING_X) xRoll++;
			else if(prop.type == Truss.ROLLING_Y) yRoll++;
		}
		double[][] matrix = new double[nodeList.size()*2][beamList.size() + xRoll + yRoll + (Fixed*2) + 1];
		int i = 0;
		for (Beam beam : beamList) {
			matrix[(beam.n1*2)][i]=beam.getCos(nodeList);
			matrix[(beam.n1*2)+1][i]=beam.getSin(nodeList);
			matrix[(beam.n2*2)][i]=-1*beam.getCos(nodeList);
			matrix[(beam.n2*2)+1][i]=-1*beam.getSin(nodeList);
			i++;
		}
		for (Properties prop : nodeProp) {
			if (prop.type == Truss.LOADED) {
				matrix[prop.node*2][matrix[0].length-1] = Math.cos(prop.angle)*prop.force;
				matrix[(prop.node*2)+1][matrix[0].length-1] = Math.sin(prop.angle)*prop.force;
			} else if (prop.type == Truss.FIXED) {
				matrix[prop.node*2][i++] = signCon;
				matrix[(prop.node*2)+1][i++] = signCon;
			} else if (prop.type == Truss.ROLLING_X) {
				matrix[prop.node*2][i] = 0;
				matrix[(prop.node*2)+1][i++] = signCon;
			} else if (prop.type == Truss.ROLLING_Y) {
				matrix[prop.node*2][i] = signCon;
				matrix[(prop.node*2)+1][i++] = 0;
			}
		}
		return matrix;
	}

	public double[] solve() {
		double[] solution = GaussianElimination.solution(toArray());
		int i = 0;
		for (Beam beam : beamList) {
			beam.f = solution[i++];
		}
		return solution;
	}

	public class Node{
		public double x;
		public double y;
		public int id;

		public Node(double x, double y, int id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}

		@Override
		public String toString() {
			return id+"\t"+x+"\t"+y;
		}

	}

	public class Beam {
		public int hash;
		public int index;
		public int n1;
		public int n2;
		public double f;

		public Beam(int n1, int n2, double f, int id) {
			this.n1 = (n1>n2) ? n1 : n2;
			this.n2 = (n1>n2) ? n2 : n1;
			this.f = f;
			this.index = id;
			this.hash = hash();
		}

		private double xDif(LinkedList<Node> nodeList) {
			return nodeList.get(n2).x-nodeList.get(n1).x;
		}

		private double yDif(LinkedList<Node> nodeList) {
			return nodeList.get(n2).y-nodeList.get(n1).y;
		}

		public double getLength(LinkedList<Node> nodeList) {
			double xDif = this.xDif(nodeList);
			xDif *= xDif;
			double yDif = this.yDif(nodeList);
			yDif *= yDif;
			return Math.sqrt(xDif+yDif);
		}

		public double getCos(LinkedList<Node> nodeList) {
			return (xDif(nodeList)/getLength(nodeList))*signCon;
		}

		public double getSin(LinkedList<Node> nodeList) {
			return (yDif(nodeList)/getLength(nodeList))*signCon;
		}

		@Override
		public String toString() {
			return index+"\t"+n1+"\t"+n2+"\t"+f;
		}

		private int hash() {
			return (n1+1)*(n2+1);
		}

	}

	public static int hashBeam(int n1, int n2) {
		return (n1+1)*(n2+1);
	}

	public class Properties {
		public int node;
		public int type;
		public double angle = 0; //angle
		public double force = 0; //force

		public Properties (int node, int type, double force, double angle) { //all radians
			this.node = node;
			this.type = type;
			this.angle = angle;
			this.force = force;
		}

		public Properties (int node, int type) {
			this.node = node;
			this.type = type;
		}

		@Override
		public String toString() {
			return node + " t:" +type + " " + angle +" " + force;
		}
	}
}
