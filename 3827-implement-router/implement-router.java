import java.util.*;

class Router {
    private int memoryLimit;
    private Deque<int[]> queue; // stores packets as {source, destination, timestamp}
    private Set<String> packetSet; // for duplicate detection
    private Map<Integer, ArrayList<Integer>> destMap; // destination -> sorted list of timestamps

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.queue = new LinkedList<>();
        this.packetSet = new HashSet<>();
        this.destMap = new HashMap<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        String key = source + "#" + destination + "#" + timestamp;

        // check duplicate
        if (packetSet.contains(key)) {
            return false;
        }

        // evict oldest if memory full
        if (queue.size() >= memoryLimit) {
            int[] old = queue.pollFirst();
            String oldKey = old[0] + "#" + old[1] + "#" + old[2];
            packetSet.remove(oldKey);

            ArrayList<Integer> tsList = destMap.get(old[1]);
            int idx = Collections.binarySearch(tsList, old[2]);
            if (idx >= 0) {
                tsList.remove(idx);
            }
            if (tsList.isEmpty()) {
                destMap.remove(old[1]);
            }
        }

        // add new packet
        int[] packet = new int[]{source, destination, timestamp};
        queue.offerLast(packet);
        packetSet.add(key);

        destMap.putIfAbsent(destination, new ArrayList<>());
        destMap.get(destination).add(timestamp); // always increasing
        return true;
    }

    public int[] forwardPacket() {
        if (queue.isEmpty()) {
            return new int[]{}; // empty
        }
        int[] packet = queue.pollFirst();
        String key = packet[0] + "#" + packet[1] + "#" + packet[2];
        packetSet.remove(key);

        ArrayList<Integer> tsList = destMap.get(packet[1]);
        int idx = Collections.binarySearch(tsList, packet[2]);
        if (idx >= 0) {
            tsList.remove(idx);
        }
        if (tsList.isEmpty()) {
            destMap.remove(packet[1]);
        }
        return packet;
    }

    public int getCount(int destination, int startTime, int endTime) {
        if (!destMap.containsKey(destination)) {
            return 0;
        }
        ArrayList<Integer> tsList = destMap.get(destination);

        int left = lowerBound(tsList, startTime);
        int right = upperBound(tsList, endTime);

        return right - left;
    }

    // find first index >= target
    private int lowerBound(ArrayList<Integer> list, int target) {
        int lo = 0, hi = list.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (list.get(mid) < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    // find first index > target
    private int upperBound(ArrayList<Integer> list, int target) {
        int lo = 0, hi = list.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (list.get(mid) <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
