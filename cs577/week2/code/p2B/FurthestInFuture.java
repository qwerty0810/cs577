import java.util.ArrayList;
import java.util.Scanner;

public class FurthestInFuture {

	private ArrayList<String> cache;
	private ArrayList<String> request;
	private int cacheSize = 0;
	private int pageFaults = 0;

	public FurthestInFuture(int numInCache, int numRequest) {
		this.cache = new ArrayList<String>(numInCache);
		this.request = new ArrayList<String>(numRequest);
	}

	public void setRequestSequence(String requestSequence) {
		String[] requestArray = requestSequence.trim().split(" ");
		this.request = new ArrayList<String>(Arrays.asList(requestArray));
	}

	public boolean isFault(String newRequest) {
		return !cache.contains(newRequest);
	}

	public int findFurthestPagePosition(String page) {
		int furthestPosition = -1;
		int position = cacheSize;
		while (position < request.size()) {
			if (request.get(position).equals(page)) {
				furthestPosition = position;
				break;
			}
			position++;
		}
		return furthestPosition;
	}

	public int findEvictedPagePosition() {
		int cacheID = 0;
		for (int i = 1; i < cacheSize; i++) {
			if (findFurthestPagePosition(cache.get(cacheID)) == -1) return cacheID;
			if (findFurthestPagePosition(cache.get(i)) == -1) return i;
			if (findFurthestPagePosition(cache.get(i)) > findFurthestPagePosition(cache.get(cacheID))) {
				cacheID = i;
			}
		}
		return cacheID;
	}

	public void getNewPageRequest() {
		if (cacheSize < cache.size()) {
			if (isFault(request.get(cacheSize))) {
				pageFaults++;
				cache.add(request.get(cacheSize));
				cacheSize++;
			}
			return;
		}

		if (isFault(request.get(cacheSize))) {
			pageFaults++;
			cache.set(findEvictedPagePosition(), request.get(cacheSize));
		}
		cacheSize++;
	}

	public int getNumFaults() {
		for (int i = 0; i < request.size(); i++) {
			getNewPageRequest();
		}
		return pageFaults;
	}

	public static void main(String[] args) {
		ArrayList<FurthestInFuture> instanceList = new ArrayList<FurthestInFuture>();
		Scanner scan = new Scanner(System.in);

		int numInstance = Integer.parseInt(scan.nextLine().trim());
		for (int id = 0; id < numInstance; id++) {
			int numInCache = Integer.parseInt(scan.nextLine().trim());
			int numRequest = Integer.parseInt(scan.nextLine().trim());
			FurthestInFuture instance = new FurthestInFuture(numInCache, numRequest);
			instance.setRequestSequence(scan.nextLine().trim());
			instanceList.add(instance);
		}

		for (FurthestInFuture instance : instanceList) {
			System.out.println(instance.getNumFaults());
		}

		scan.close();
	}
}
