import java.util.LinkedList;


public class TrussTest {

	public static void main(String[] args) {

		Truss truss = new Truss();
		TrussGA ga = new TrussGA();
		LinkedList<TrussGA.TrussChromo> viableTrussGene = new LinkedList<TrussGA.TrussChromo>();
		LinkedList<Truss> viableTruss = new LinkedList<Truss>();

		/*System.out.println("n 0 0 1 0 0 1 b 0 1 1 2 2 0 f 2 y 0 l 1 5 -1.570796326794897 p");
		System.out.println(ga.fitness("n 0 0 1 0 0 1 b 0 1 1 2 2 0 f 2 y 0 l 1 5 -1.570796326794897 p", truss));
		System.out.println("n 0 0 0 14 43 0 f 1 y 0 l 2 15 -1.570796326794897 b 0 1 1 2 2 0 p");
		truss = new Truss();
		System.out.println(ga.fitness("n 0 0 0 14 43 0 f 1 y 0 l 2 15 -1.570796326794897 b 0 1 1 2 2 0 p", truss));
		System.out.println();*/

		for (int i = 0; i < 54; i++) {
			while (true) {
				TrussGA.TrussChromo tgene = ga.generateTruss();
//				System.out.println(trussString);
				truss = new Truss();
				tgene.fitness = ga.fitness(tgene, truss);
//				System.out.println(tgene.fitness);
				if (tgene.fitness < 200) {
					viableTrussGene.add(tgene);
					viableTruss.add(truss);
					break;
				}
			}
		}
		System.out.println();
//		PrintWriter out = null;
//		try {
//			out = new PrintWriter(new FileWriter("C:\\Users\\Lou\\Desktop\\truss.txt"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		int index = 0;
		for (TrussGA.TrussChromo trussFitness : viableTrussGene) {
			System.out.println("index: "+index+++"\t"+trussFitness);
//			out.println(trussFitness);
		}
//		out.close();

		int x = 0;
		int y = 0;
		index = 0;
		for (Truss trussDisp : viableTruss) {
			new TrussDisplay(trussDisp, x, y, index+": "+ viableTrussGene.get(index++).fitness);
			if (x < 1000) x+=TrussDisplay.WindowWidth+30;
			else {
				x = 0;
				y+=TrussDisplay.WindowHeight;
			}
		}
	}
}
