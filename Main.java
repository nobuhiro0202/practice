public class Main {
    public static void main(String[] args) {
      LinkedListNode head = new LinkedListNode(3);
      head.next = new LinkedListNode(5);
      head.next.next = new LinkedListNode(8);
      head.next.next.next = new LinkedListNode(5);
      head.next.next.next.next = new LinkedListNode(10);
      head.next.next.next.next = new LinkedListNode(2);
      head.next.next.next.next = new LinkedListNode(1);

      LinkedListNode a = head.partition(head, 5);
      a.printLinkedList();
    }
}
