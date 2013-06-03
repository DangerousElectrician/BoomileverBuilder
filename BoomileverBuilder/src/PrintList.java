import java.text.DecimalFormat;
import java.util.LinkedList;


public class PrintList {

	/**Prints an array on a single line with each element delimited by a tab
	 * @param array
	 */
	public static void p(double[] array) {
		DecimalFormat df = new DecimalFormat("#.##");
		for (int i = 0; i < array.length; i++) {
			System.out.print(df.format(array[i])+"\t");
		}
		System.out.println();
	}

	/**Prints a multidimensional array with each sub-array delimited by a new line
	 * @param array
	 */
	public static void p(double[][] array) {
		for (int i = 0; i < array.length; i++) {
			p(array[i]);
		}
		System.out.println();
	}

	/**Prints a list on a single line with each element delimited by a tab
	 * @param list
	 */
	public static void dp(LinkedList<Double> list) {
		for (Double double1 : list) {
			System.out.print(double1 + "\t");
		}
		System.out.println();
	}

	/**Prints a multidimensional list with each sub-list delimited by a new line
	 * @param list
	 */
	public static void dp2(LinkedList<LinkedList<Double>> list) {
		for (LinkedList<Double> list2 : list) {
			dp(list2);
		}
		System.out.println();
	}


	/**Prints a list on a single line with each element delimited by a tab
	 * @param list
	 */
	public static void ip(LinkedList<Integer> list) {
		for (Integer integer : list) {
			System.out.print(integer + "\t");
		}
		System.out.println();
	}

	/**Prints a multidimensional list with each sub-list delimited by a new line
	 * @param list
	 */
	public static void ip2(LinkedList<LinkedList<Integer>> list) {
		for (LinkedList<Integer> list2 : list) {
			ip(list2);
		}
		System.out.println();
	}
}
