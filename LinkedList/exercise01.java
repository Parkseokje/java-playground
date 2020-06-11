package LinkedList;

/**
 * 단방향 Linked List 중간노드 삭제 - 삭제할 노드는 다음 노드의 포인터만 가지고 있음
 */
public class exercise01 {
    public static void main(String args[]) {
        SimpleLinkedList ll = new SimpleLinkedList();
        ll.append(1);
        ll.append(2);
        ll.append(3);
        ll.append(4);
        // System.out.println(ll.get(1).data);

        ll.deleteNode(ll.get(3));
        ll.retrieve();
    }
}