/**

 L.C 146. LRU Cache

 Approach: Doublly linked list and hash map


 Time Complexity: O(1) - get, put
 Space Complexity: O(1) - get, put
 */

class LRUCache {

    class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head, tail;


    private void addToHead(Node node) {

        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;

    }


    private void removeNode(Node node) {

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    int capacity;
    HashMap<Integer, Node> map;
    public LRUCache(int capacity) {

        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;

        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {

        if(!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        removeNode(node);
        addToHead(node);

        return node.value;
    }

    public void put(int key, int value) {

        if(map.containsKey(key)) {

            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            node.value = value;
            return;

        }

        if(this.capacity == map.size()) {

            Node tailPrev = this.tail.prev;
            removeNode(tailPrev);
            map.remove(tailPrev.key); // the tail previous key
        }
        Node newNode = new Node(key,value);
        map.put(key, newNode);
        addToHead(newNode);

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
