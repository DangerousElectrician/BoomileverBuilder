import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class TrussInterpreter {


	public static void interpret(Truss truss, String trussString) {

		Scanner scan = new Scanner(trussString);

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

		 */

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
					break;
				}
				scan.nextDouble();
			} catch (InputMismatchException e) {
				char input = scan.next().charAt(0);
				switch (input) {

				case 'p':
					truss.solve();
					break;

				case 'q': //quit
					break loop;

				default:
					mode = input;
					break;
				}
			} catch(NoSuchElementException e) {
				break loop;
			}
		}
	}
}
