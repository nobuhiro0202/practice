import java.util.HashSet;

public class LinkedListNode {
    int data;
    LinkedListNode next;

    public LinkedListNode() {}

    public LinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }

    private void setNext(LinkedListNode node) {
        this.next = node;
    }

    public int length(LinkedListNode node) {
        int length = 0;
        LinkedListNode current = node;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    public LinkedListNode padList(LinkedListNode l, int padding) {
        LinkedListNode head = l;
        for (int i = 0; i < padding; i++) head = insertBefore(head, 0);
        return head;
    }

    public LinkedListNode insertBefore(LinkedListNode l, int data) {
        LinkedListNode node = new LinkedListNode(data);
        if (l != null) node.next = l;
        return node;
    }

    public void deleteDups() {
        HashSet<Integer> set = new HashSet<Integer>();
        LinkedListNode prev = null;
        LinkedListNode n = this;
        
        while (n != null) {
            if (set.contains(n.data)) prev.next = n.next; 
            else {
                set.add(n.data);
                prev = n;
            }
            n = n.next;
        }
    }

    public void printLinkedList() {
        LinkedListNode current = this;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    /**
    private class Index {
        public int value = 0;
    }

    LinkedListNode kthToLast(LinkedListNode head, int k) {
        Index idx = new Index();
        return kthToLast(head, k, idx);
    }

    LinkedListNode kthToLast(LinkedListNode head, int k, Index idx) {
        if (head == null) return null;
        LinkedListNode node = kthToLast(head.next, k, idx);
        idx.value++;
        if (idx.value == k) return head;
        return node;
    }
    */

    LinkedListNode nthToLast(LinkedListNode head, int k) {
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;
        for (int i = 0; i < k; i++) {
            if (p1 == null) return null;
            p1 = p1.next;
        }

        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    boolean deleteNode(LinkedListNode n) {
        if (n == null || n.next == null) return false;
        LinkedListNode next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }

    LinkedListNode partition(LinkedListNode node, int x) {
        LinkedListNode beforeStart = null;
        LinkedListNode beforeEnd = null;
        LinkedListNode afterStart = null;
        LinkedListNode afterEnd = null;

        while (node != null) {
            LinkedListNode next = node.next;
            node.next = null;
            if (node.data < x) {
                if (beforeStart == null) {
                    beforeStart = node;
                    beforeEnd = beforeStart;
                } else {
                    beforeEnd.next = node;
                    beforeEnd = node;
                }
            } else {
                if (afterStart == null) {
                    afterStart = node;
                    afterEnd = afterStart;
                } else {
                    afterEnd.next = node;
                    afterEnd = node;
                }
            }
            node = next;
        }

        if (beforeStart == null) return afterStart;

        beforeEnd.next = afterStart;
        return beforeStart;
    }

    LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) return null;
        
        LinkedListNode result = new LinkedListNode();

        int value = carry;
        if (l1 != null) value += l1.data;
        if (l2 != null) value += l2.data;
        result.data = value % 10;

        if (l1 != null || l2 != null) {
            LinkedListNode more = addLists(l1 == null ? null : l1.next, l2 == null ? null : l2.next, value >= 10 ? 1 : 0);
            result.setNext(more);
        }
        return result;
    }

    public class PartialSum {
        public LinkedListNode sum = null;
        public int carry = 0;
    }

    LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2) {
        int len1 = length(l1);
        int len2 = length(l1);
        if (len1 < len2) l1 = padList(l1, len2 - len1);
        if (len1 > len2) l2 = padList(l2, len1 - len2);

        PartialSum sum = addListsHelper(l1, l2);

        if (sum.carry == 0) return sum.sum;
        else {
            LinkedListNode result = insertBefore(sum.sum, sum.carry);
            return result;
        }
    }

    private PartialSum addListsHelper(LinkedListNode l1, LinkedListNode l2) {
        if (l1 == null && l2 == null) {
            PartialSum sum = new PartialSum();
            return sum;
        }
        PartialSum sum = addListsHelper(l1, l2);

        int val = sum.carry + l1.data + l2.data;

        LinkedListNode full_result = insertBefore(sum.sum, val % 10);
        sum.sum = full_result;
        sum.carry = val / 10;
        return sum;
    }

    public boolean isPalindrome(LinkedListNode head) {
        LinkedListNode reversed = reverseAndClone(head);
        return isEqual(head, reversed);
    }

    private LinkedListNode reverseAndClone(LinkedListNode node) {
        LinkedListNode head = null;
        while (node != null) {
            LinkedListNode n = new LinkedListNode(node.data);
            n.next = head;
            head = n;
            node = node.next;
        }
        return head;
    }

    public boolean isEqual(LinkedListNode one, LinkedListNode two) {
        while (one != null && two != null) {
            if (one.data != two.data) return false;
            one = one.next;
            two = two.next;
        }
        return one == null && two == null;
    }
    
}