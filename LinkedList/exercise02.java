package LinkedList;

class Storage {
    int carry = 0;
    SimpleLinkedList.Node result = null;
}

// Linked List Digit 합산
// 숫자 419를 의미하는 링크드리스트 9->1->4와
// 숫자 346을 의미하는 링크드리스트 6->4->3가 있을 때,
// 419+346의 합인 765를 링크드리스트 5->6->7를 반환하라.
public class exercise02 {
    public static void main(String[] args) {
        SimpleLinkedList l1 = new SimpleLinkedList();
        l1.append(9);
        l1.append(1);
        l1.retrieve();

        SimpleLinkedList l2 = new SimpleLinkedList();
        l2.append(1);
        l2.append(1);
        l2.retrieve();

        SimpleLinkedList.Node n = sumListsReverse(l1.get(1), l2.get(1));
        while (n.next != null) {
            System.out.print(n.data + " -> ");
            n = n.next;
        }
        System.out.println(n.data);
    }

    static int getListLength(SimpleLinkedList.Node n) {
        int total = 0;

        while (n != null) {
            total++;
            n = n.next;
        }

        return total;
    }

    static SimpleLinkedList.Node insertBefore(SimpleLinkedList.Node n, int data) {
        SimpleLinkedList.Node before = new SimpleLinkedList.Node();
        before.data = data;

        if (n != null)
            before.next = n;

        return before;
    }

    static SimpleLinkedList.Node LPadList(SimpleLinkedList.Node n, int length) {
        for (int i = 0; i < length; i++) {
            n = insertBefore(n, 0);
        }
        return n;
    }

    static Storage addLists(SimpleLinkedList.Node l1, SimpleLinkedList.Node l2) {
        if (l1 == null && l2 == null) {
            Storage storage = new Storage();
            return storage;
        }

        Storage storage = addLists(l1.next, l2.next);
        int value = storage.carry + l1.data + l2.data;
        int data = value % 10;
        storage.result = insertBefore(storage.result, data);
        storage.carry = value / 10;

        // System.out.println(l1.data + ", " + l2.data + ", " + storage.carry);

        return storage;
    }

    static SimpleLinkedList.Node sumLists(SimpleLinkedList.Node l1, SimpleLinkedList.Node l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        SimpleLinkedList.Node result = new SimpleLinkedList.Node();
        int value = carry;

        if (l1 != null)
            value += l1.data;
        if (l2 != null)
            value += l2.data;

        result.data = value % 10;

        if (l1 != null || l2 != null) {
            SimpleLinkedList.Node next = new SimpleLinkedList.Node();
            next = sumLists(l1 == null ? null : l1.next, l2 == null ? null : l2.next, value / 10);
            result.next = next;
        }

        return result;
    }

    static SimpleLinkedList.Node sumListsReverse(SimpleLinkedList.Node l1, SimpleLinkedList.Node l2) {
        int len1 = getListLength(l1);
        int len2 = getListLength(l2);

        if (len1 < len2) {
            l1 = LPadList(l1, len2 - len1);
        } else {
            l2 = LPadList(l2, len1 - len2);
        }

        Storage storage = addLists(l1, l2);
        if (storage.carry == 0) {
            return storage.result;
        } else {
            storage.result = insertBefore(storage.result, storage.carry);
            return storage.result;
        }
    }
}