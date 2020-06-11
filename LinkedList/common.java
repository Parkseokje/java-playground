package LinkedList;

class SimpleLinkedList {
    Node header;

    class Reference {
        public int count;
    }

    static class Node {
        int data;
        Node next = null;
    }

    SimpleLinkedList() {
        header = new Node();
    }

    Node get(int nth) {
        Node n = header;
        int counter = 0;

        while (n.next != null) {
            if (counter == nth)
                return n;
            else {
                n = n.next;
                counter++;
            }
        }

        return null;
    }

    void append(int d) {
        Node n = header;
        Node end = new Node();
        end.data = d;

        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    void delete(int d) {
        Node n = header;

        while (n.next != null) {
            if (n.next.data == d) {
                n.next = n.next.next;
            } else {
                n = n.next;
            }
        }
    }

    boolean deleteNode(Node n) {
        if (n == null || n.next == null) {
            return false;
        }
        Node next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }

    void retrieve() {
        Node n = header.next;

        while (n.next != null) {
            System.out.print(n.data + " -> ");
            n = n.next;
        }
        System.out.println(n.data);
    }

    void removeDuplicates() {
        Node n = header;

        while (n != null && n.next != null) {
            Node r = n; // r은 n의 값과 n 이후 노드들의 값들을 비교하기 위한 변수이다.

            while (r.next != null) {
                if (n.data == r.next.data) {
                    r.next = r.next.next;
                } else {
                    r = r.next;
                }
            }

            n = n.next;
        }
    }

    // 1,2,3,4 에서 뒤에서 두번 째값을 찾아라
    // 뒤에서 두번 째값은 3
    // total이 N이라고 했을 때 뒤에서 K번 째값은 N - k + 1 (ex. 4 - 2 + 1 = 3)
    Node KthToLast(Node first, int k) {
        Node n = first;
        int total = 1;

        while (n.next != null) {
            total++;
            n = n.next;
        }

        n = first;
        for (int i = 1; i < total - k + 1; i++) {
            n = n.next;
        }
        return n;
    }

    Node KthToLastRecursive(Node n, int k, Reference r) {
        if (n == null) {
            return null;
        }

        Node found = KthToLastRecursive(n.next, k, r);
        r.count++;
        if (r.count == k) {
            return n;
            // System.out.println(k + "th to last node is " + n.data);
        }

        return found;
    }

    // Complexity: time -> O(n), space -> O(1)
    Node KthToLastPointer(Node n, int k) {
        Node p1 = n;
        Node p2 = n;

        for (int i = 0; i < k; i++) {
            if (p1 == null)
                return null;
            p1 = p1.next;
        }

        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }
}