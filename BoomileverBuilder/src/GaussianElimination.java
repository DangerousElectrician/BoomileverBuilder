import java.util.LinkedList;


public class GaussianElimination {

	static LinkedList<Integer> usedRows;
	static LinkedList<LinkedList<Integer>> rowsWithColumn;

	static double[][] arr;



	/**Solves a linear system
	 * Does not check whether or not system is solvable
	 * @param matrix	Augmented matrix
	 * @return			Reduced row echelon form
	 */
	public static double[][] reduce(double[][] matrix) {
		arr = matrix;
		usedRows = new LinkedList<Integer>();
		rowsWithColumn = new LinkedList<LinkedList<Integer>>();
		//System.out.println("original");
		//PrintList.p(arr);
		rowsWithColumn=rowsWithColumn();
		mopLists();
		//System.out.println("rearrange");
		for (int i = 0; i < rowsWithColumn.size(); i++) { //if a row qualifies, remove all other possibilities
//			System.out.println("3debug: "+rowsWithColumn.get(i).size());
			if (rowsWithColumn.get(i).size()>1) { //rowsWithColumn.get(i).size()!=1
				usedRows.add(rowsWithColumn.get(i).get(0));
				while (rowsWithColumn.get(i).size() != 1) rowsWithColumn.get(i).removeLast();
				mopLists();
			}
		}
		arrange();
		//PrintList.p(arr);

		//System.out.println();
		for (int i = 0; i < arr.length; i++) {
			//PrintList.p(arr);
			double lead = arr[i][i];
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j]/=lead;
			}
			//PrintList.p(arr);
			for (int j = 0; j < arr.length; j++) {
				if (!(i==j)) {
					arr[j] = subtractArray(arr[j], arr[i], i);
				}
			}
		}

		//System.out.println("done\n");
		//PrintList.p(arr);
		return arr;
	}

	public static double[] solution(double[][] matrix) {
		int length = matrix.length;
		double[] solution = new double[length];
		double[][] reduced = reduce(matrix);
		for (int i = 0; i < solution.length; i++) {
			solution[i] = reduced[i][length];
		}
		return solution;
	}

	private static double[] subtractArray(double[] array1, double[] array2, int leadIndex) {
		double lead = array1[leadIndex];
		for (int i = 0; i < array1.length; i++) {
			array1[i]-=array2[i]*lead;
		}
		return array1;
	}

	private static void arrange() {
		double[][] newarr = new double[arr.length][arr[0].length];
		for (int i = 0; i < rowsWithColumn.size(); i++) {

//			System.out.println("2debug: "+rowsWithColumn.get(0).size());
			newarr[i] = arr[rowsWithColumn.get(i).get(0)];
		}
		arr = newarr;
	}

	private static void mopLists() { //removing usedRows from rowsWithColumn
		boolean done = true;
		while(done) {
			done = false;
			for (int i = 0; i < rowsWithColumn.size(); i++) {
				if (rowsWithColumn.get(i).size()==1) {
					if (isInList(usedRows, rowsWithColumn.get(i).get(0)) == -1) {
						usedRows.add(rowsWithColumn.get(i).get(0));
						done = true;
					}
				} else {
					for (Integer rowNum : usedRows) {
						int matchIndex = isInList(rowsWithColumn.get(i), rowNum);
						if (matchIndex!=-1) {
							rowsWithColumn.get(i).remove(matchIndex);
							done = true;
						}
					}
				}
			}
			//PrintList.ip2(rowsWithColumn);
		}
	}

	private static LinkedList<LinkedList<Integer>> rowsWithColumn() { //get a list of rows with a nonzero number in a column
		LinkedList<LinkedList<Integer>> result = new LinkedList<LinkedList<Integer>>();

		for (int i = 0; i < arr[0].length-1; i++) {
			LinkedList<Integer> colList = new LinkedList<Integer>();
			for (int j = 0; j < arr.length; j++) {
				if(arr[j][i]!=0) {
					colList.add(j);
				}
			}
			result.add(colList);
		}
//		System.out.println("debug: "+result.get(0).size());
		return result;
	}

	/**Searches list for val and returns index of val if found
	 * @param list	List to be checked
	 * @param val	Value to find
	 * @return		Index of value or -1 if none found
	 */
	private static int isInList(LinkedList<Integer> list, int val) {
		int index = 0;
		for (Integer integer : list) {
			if (val == integer) {
				return index;
			}
			index++;
		}
		return -1;
	}
}
