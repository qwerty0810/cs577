import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class IntervalScheduling {
	private static int arrangeJob(List<String> instance) {
		int[][] time = new int[instance.size()][2];
		for (int i = 0; i < instance.size(); i++) {
			String[] job = instance.get(i).split(" ");
			time[i][0] = Integer.parseInt(job[0]);
			time[i][1] = Integer.parseInt(job[1]);
		}


		Arrays.sort(time, Comparator.comparingInt(entry -> entry[1]));

		int count = 1;
		int idx = 1;
		int countInIdx = 0;
		while (idx < time.length) {
			if (time[countInIdx][1] <= time[idx][0]) {
				count++;
				countInIdx = idx;
			}
			idx++;
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numInstance = Integer.parseInt(scan.nextLine().trim());
		List<List<String>> instancesList = new ArrayList<>(numInstance);
		for (int i = 1; i <= numInstance; i++) {
			int numJobs = Integer.parseInt(scan.nextLine().trim());
			List<String> instance = new ArrayList<>(numJobs);
			for (int jobId = 0; jobId < numJobs; jobId++) {
				String jobString = scan.nextLine().trim();
				instance.add(jobString);
			}
			instancesList.add(instance);
		}
		for (List<String> instance : instancesList) {
			int countNum = arrangeJob(instance);
			System.out.println(countNum);
		}
	}
}
