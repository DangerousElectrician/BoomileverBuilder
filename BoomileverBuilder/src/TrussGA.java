import java.util.LinkedList;
import java.util.Random;


public class TrussGA {

	public String required = "n 0 0 0 14 43 0 f 1 y 0 l 2 147.09 -1.570796326794897 "; //15kg load
	private static Random rand = new Random(19);

	public double fitness(TrussChromo trussChromo, Truss truss) {
		try {
			TrussInterpreter.interpret(truss, trussChromo.toString());
			trussChromo.beamString = truss.cleanBeamString();
			LinkedList<Truss.Node> nodeList = truss.getNodeList();
			if((nodeList.size()*2) != (truss.getBeamList().size() + 3)) return Double.MAX_VALUE;
			double totalLength = 0;
			//double totalTension = 0;
			//double totalCompression = 0;
			for(Truss.Beam beam : truss.getBeamList()) {
				if (Double.isNaN(beam.f) | Double.isInfinite(beam.f)) return Double.MAX_VALUE;
				if (beam.f>500) return Double.MAX_VALUE; //totalTension += beam.f;  //balsa breaking tension
				else if (beam.f<-1*500) return Double.MAX_VALUE;//totalCompression -= beam.f; //balsa breaking compression
				if(Math.abs(beam.f) > 1470) { //method of joints sometimes gives results for indeterminate case
					return Double.MAX_VALUE;
				}
				totalLength += beam.getLength(nodeList);
			}
			double fitness = totalLength; // (totalLength*totalLength+(totalTension+totalCompression))/(totalTension-totalCompression);
			if (fitness>0) { //check for compression. if more compression than tension, the truss fails
				return fitness;
			}
		} catch (Exception e) {
			// fail any truss that does throws an exception
		}
		return Double.MAX_VALUE;
	}

	public TrussGA.TrussChromo generateTruss() {
		int nodeCnt = 2;
		String nodeString = "";
		String beamString = ""; //0 1 1 2 2 0
		for (int i = 0; i < rand.nextInt(31)+1; i++) {
			nodeString = nodeString.concat(rand.nextInt(46)+" ");
			nodeString = nodeString.concat(rand.nextInt(16)+" ");
			nodeCnt++;
		}

		for (int n1 = 0; n1 < nodeCnt+1; n1++) {
			for (int i = 0; i < 2*(rand.nextInt(7)); i++) {
				int n2;
				do {
					n2 = rand.nextInt(nodeCnt+1);
				} while (n1 == n2);

				beamString = beamString.concat(n1+" ");
				beamString = beamString.concat(n2+" ");
			}
		}
		return new TrussChromo(nodeString, beamString, 0);
	}

	public class TrussChromo {
		public String nodeString;
		public String beamString;
		public double fitness;

		public TrussChromo(String nodeString, String beamString, double fitness) {
			this.nodeString = nodeString;
			this.beamString= beamString;
			this.fitness = fitness;
		}

		@Override
		public String toString() {
			return required+" n "+nodeString+" b "+beamString+" p ";
		}
	}
}
