import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BipartiteMaxMatching {

	private int numNodesA;
	private int numNodesB;
	private List<List<Integer>> adjList;

	private BipartiteMaxMatching(int numNodesA, int numNodesB, int numEdges) {
		this.numNodesA = numNodesA;
		this.numNodesB = numNodesB;
		this.adjList = new ArrayList<>(numNodesA);
		for (int i = 0; i < numNodesA; i++) {
			this.adjList.add(new ArrayList<>());
		}
	}

	private static BipartiteMaxMatching[] parseInput() {
		Scanner input = new Scanner(System.in);

		int numInstances = input.nextInt();
		BipartiteMaxMatching[] instances = new BipartiteMaxMatching[numInstances];

		for (int numInstance = 0; numInstance < numInstances; numInstance++) {
			int numNodesA = input.nextInt();
			int numNodesB = input.nextInt();
			int numEdges = input.nextInt();
			instances[numInstance] = new BipartiteMaxMatching(numNodesA, numNodesB, numEdges);
			input.nextLine();

			while (numEdges > 0) {
				int nodeA = input.nextInt();
				int nodeB = input.nextInt();

				instances[numInstance].addEdge(nodeA, nodeB);
				numEdges--;
			}
		}
		input.close();
		return instances;
	}

	private void addEdge(int nodeA, int nodeB) {
		this.adjList.get(nodeA - 1).add(nodeB - 1);
	}

	private int maxMatching() {
		int[] matches = new int[this.numNodesB];
		int count = 0;
		for (int i = 0; i < this.numNodesA; i++) {
			int[] visited = new int[this.numNodesB];
			if (isMatch(visited, matches, i)) {
				count++;
			}
		}
		return count;
	}

	private boolean isMatch(int[] visited, int[] matches, int i) {
		for (int j : this.adjList.get(i)) {
			if (visited[j] == 0) {
				visited[j] = 1;
				if (matches[j] == 0 || isMatch(visited, matches, matches[j])) {
					matches[j] = i;
					return true;
				}
			}
		}
		return false;
	}

	private char isPerfectMatch() {
		for (int i = 0; i < this.numNodesA; i++) {
			for (int j : this.adjList.get(i)) {
				if (this.adjList.get(i).size() < this.numNodesB) {
					return 'N';
				}
			}
		}
		return 'Y';
	}

	public static void main(String[] args) {
		try {
			BipartiteMaxMatching[] instances = parseInput();
			for (BipartiteMaxMatching m : instances) {
				System.out.println(m.maxMatching() + " " + m.isPerfectMatch());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
