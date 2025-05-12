/*Design a data structure that can efficiently manage data packets in a network router. Each data packet consists of the following attributes:

source: A unique identifier for the machine that generated the packet.
destination: A unique identifier for the target machine.
timestamp: The time at which the packet arrived at the router.
Implement the Router class:

Router(int memoryLimit): Initializes the Router object with a fixed memory limit.

memoryLimit is the maximum number of packets the router can store at any given time.
If adding a new packet would exceed this limit, the oldest packet must be removed to free up space.
bool addPacket(int source, int destination, int timestamp): Adds a packet with the given attributes to the router.

A packet is considered a duplicate if another packet with the same source, destination, and timestamp already exists in the router.
Return true if the packet is successfully added (i.e., it is not a duplicate); otherwise return false.
int[] forwardPacket(): Forwards the next packet in FIFO (First In First Out) order.

Remove the packet from storage.
Return the packet as an array [source, destination, timestamp].
If there are no packets to forward, return an empty array.
int getCount(int destination, int startTime, int endTime):

Returns the number of packets currently stored in the router (i.e., not yet forwarded) that have the specified destination and have timestamps in the inclusive range [startTime, endTime].
Note that queries for addPacket will be made in increasing order of timestamp. */
import java.util.*;

public class Router {
    private static class Packet {
        int source, destination, timestamp;
        Packet(int source, int destination, int timestamp) {
            this.source = source;
            this.destination = destination;
            this.timestamp = timestamp;
        }

        String getKey() {
            return source + "#" + destination + "#" + timestamp;
        }
    }

    private int memoryLimit;
    private Deque<Packet> queue;
    private Set<String> seenPackets;

    // Destination → List of timestamps (in insertion order)
    private Map<Integer, ArrayList<Integer>> destMap;

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.queue = new ArrayDeque<>();
        this.seenPackets = new HashSet<>();
        this.destMap = new HashMap<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        Packet packet = new Packet(source, destination, timestamp);
        String key = packet.getKey();

        if (seenPackets.contains(key)) return false;

        if (queue.size() >= memoryLimit) {
            Packet removed = queue.poll();
            seenPackets.remove(removed.getKey());
            ArrayList<Integer> timestamps = destMap.get(removed.destination);
            // Remove only one occurrence
            int index = Collections.binarySearch(timestamps, removed.timestamp);
            if (index >= 0) timestamps.remove(index);
        }

        queue.offer(packet);
        seenPackets.add(key);
        destMap.putIfAbsent(destination, new ArrayList<>());
        destMap.get(destination).add(timestamp);
        return true;
    }

    public int[] forwardPacket() {
        if (queue.isEmpty()) return new int[0];

        Packet packet = queue.poll();
        seenPackets.remove(packet.getKey());

        ArrayList<Integer> timestamps = destMap.get(packet.destination);
        int index = Collections.binarySearch(timestamps, packet.timestamp);
        if (index >= 0) timestamps.remove(index);

        return new int[]{packet.source, packet.destination, packet.timestamp};
    }

    public int getCount(int destination, int startTime, int endTime) {
        ArrayList<Integer> timestamps = destMap.get(destination);
        if (timestamps == null || timestamps.isEmpty()) return 0;

        // Find lower bound index (first ≥ startTime)
        int left = lowerBound(timestamps, startTime);
        // Find upper bound index (first > endTime)
        int right = upperBound(timestamps, endTime);

        return right - left;
    }

    // First index where val ≥ target
    private int lowerBound(List<Integer> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) >= target) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    // First index where val > target
    private int upperBound(List<Integer> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) > target) high = mid;
            else low = mid + 1;
        }
        return low;
    }
}

