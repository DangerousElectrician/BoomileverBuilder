import java.util.Scanner;


public class TrussSim {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Truss truss = new Truss();

		/*double[][] truss= {
			{0.5547,	0,	0,	0,	1,	0,	0,	0,	1,	0,	0},
			{0.8321,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0},
			{-0.5547,	1,	0,	0,	0,	0.5547,	0,	0,	0,	0,	0},
			{-0.8321,	0,	0,	0,	0,	-0.8321,	0,	0,	0,	0,	0},
			{0,	-1,	0.5547,	0,	0,	0,	-0.5547,	0,	0,	0,	0},
			{0,	0,	-0.8321,	0,	0,	0,	-0.8321,	0,	0,	0,	0},
			{0,	0,	0,	1,	-1,	-0.5547,	0.5547,	0,	0,	0,	0},
			{0,	0,	0,	0,	0,	0.8321,	0.8321,	0,	0,	0,	4},
			{0,	0,	-0.5547,	-1,	0,	0,	0,	0,	0,	0,	0},
			{0,	0,	0.8321,	0,	0,	0,	0,	0,	0,	1,	0}};


		PrintList.p(truss);
		System.out.println();
		PrintList.p(GaussianElimination.solve(truss));*/

		/*
		  n 0 0 1 0 .5 1.73205080757 b 0 1 1 2 2 0 f 0 x 1 l 2 5.2 -1.55 p q
		  n 0 0 1 0 0 1 1 1 b 0 1 1 2 2 0 1 3 3 2 f 0 x 1 l 2 5 0 p q
		  n 0 0 1 0 0 1 b 0 1 1 2 2 0 f 0 x 1 l 2 5 0 p q
		  solution
B:0-1	B:1-2	B:2-0	Nx:0	Ny:0	Ny:1
5		-7.0711	5		-5		-5		5
		  n 0 0 1 0 0 1 b 0 1 1 2 2 0 f 0 x 1 l 2 5 3.14159 p q
		  n 0 0 1 0 2 1 b 0 1 1 2 2 0 f 0 x 1 l 2 1 0 p q
		  solution
F:0-1	F:1-2	F:2-0	Nx:0	Ny:0	Ny:1
-1	-1.4142	2.2361	-1	-1	1

		 */

		System.out.println("Press 'h' for help");
		char mode = 'n';

		loop:
		while (true) {
			try {
				switch (mode) {
				case 'n': //add node
					while (scan.hasNextDouble()) {
						truss.addNode(scan.nextDouble(), scan.nextDouble());
					}
					break;

				case 'b': // add beam
					while (scan.hasNextInt()) {
						truss.addBeam(scan.nextInt(), scan.nextInt());
					}
					break;

				case 'l': //add load to node
					while (scan.hasNextInt()) {
						truss.setNodeProperties(scan.nextInt(), Truss.LOADED, scan.nextDouble(), scan.nextDouble());
					}
					break;

				case 'f': //fixed node
					while (scan.hasNextInt()) {
						truss.setNodeProperties(scan.nextInt(), Truss.FIXED);
					}
					break;

				case 'x': //rolling x node
					while (scan.hasNextInt()) {
						truss.setNodeProperties(scan.nextInt(), Truss.ROLLING_X);
					}
					break;

				case 'y': //rolling y node
					while (scan.hasNextInt()) {
						truss.setNodeProperties(scan.nextInt(), Truss.ROLLING_Y);
					}
					break;

				default:
					if (mode != 'h') System.out.println("Invalid command, press 'h' for help");
					else System.out.println(help());
					break;
				}
				scan.nextDouble();
			} catch (Exception e) {
				char input = scan.next().charAt(0);
				switch (input) {

				case 'p':
					System.out.println("Node:");
					for (Truss.Node node : truss.getNodeList()) {
						System.out.print(node);
						System.out.println();
					}
					System.out.println("Beam:");
					for (Truss.Beam beam : truss.getBeamList()) {
						System.out.println(beam);
					}
					System.out.println("Node prop:");
					for (Truss.Properties prop: truss.getNodeProperties()) {
						System.out.println(prop);
					}
					/*System.out.println("matrix");
					double[][] an = truss.toArray();
					PrintList.p(an);
					System.out.println("\nsolved");
					PrintList.p(GaussianElimination.reduce(an));*/

					System.out.println("\nsolution");
					for (Truss.Beam beam : truss.getBeamList()) {
						System.out.print("F:"+beam.n1+"-"+beam.n2+"\t");
					}
					for (Truss.Properties prop : truss.getNodeProperties()) {
						if (prop.type == Truss.FIXED) {
							System.out.print("Nx:"+prop.node+"\tNy:"+prop.node+"\t");
						} else if (prop.type == Truss.ROLLING_X) {
							System.out.print("Ny:"+prop.node);
						} else if (prop.type == Truss.ROLLING_Y) {
							System.out.print("Nx:"+prop.node);
						}
					}
					System.out.println();
//					PrintList.p(GaussianElimination.solution(an));
					PrintList.p(truss.solve());
					break;

				case 'q': //quit
					break loop;

				default:
					mode = input;
					break;
				}
			}
		}
	}

	public static String help() {
		//TODO write instructions
		return "Help goes here";
	}
}
