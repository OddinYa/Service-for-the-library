package org.course_work.struct.linkedList;

import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.struct.MyStruct;

public class List extends MyStruct {
    private Node head;

    public void add(DataOnTheIssuanceAndAcceptanceOfBooks data) {

        if (head == null) {
            head = new Node(data);
        } else {
            Node node = head;

            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node(data);
        }
    }

    public void remove(DataOnTheIssuanceAndAcceptanceOfBooks data) {
        if (head == null) {
            return;
        }
        if (data.equals(head.data)) {
            head = head.next;
            return;
        }

        Node temp = head;
        while (head.next != null) {

            if (data.equals(temp.next.data)) {
                temp.next = temp.next.next;
                return;
            }
            temp = head.next;
        }
    }

    public void sort() {
        head = mergeSort(head);
    }

    private Node mergeSort(Node start) {
        if (start == null || start.next == null) {
            return start;
        }

        Node middle = getMiddle(start);
        Node nextToMiddle = middle.next;
        middle.next = null;

        Node left = mergeSort(start);
        Node right = mergeSort(nextToMiddle);

        return merge(left, right);
    }

    private Node getMiddle(Node head) {
        if (head == null) return head;

        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Node merge(Node left, Node right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        Node result;
        if (left.data.compare(right.data) <= 0) {
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }

        return result;
    }

    public void print() {
        while (head != null) {
            System.out.println(head.data.toString());
            head = head.next;
        }
    }

    public DataOnTheIssuanceAndAcceptanceOfBooks find(String libraryCardNumber, String cipher) {
        DataOnTheIssuanceAndAcceptanceOfBooks result;
        Node cur = head;
        while (cur != null) {
            if (cur.data.getCipher().equals(cipher) && cur.data.getLibraryCardNumber().equals(libraryCardNumber)) {
                result = cur.data;
                return result;
            }
            cur = cur.next;
        }
        return null;
    }

    public void removeCipher(String cipher) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.getCipher().equals(cipher)) {
                this.remove(temp.data);
                temp = temp.next;
            } else {
                temp = temp.next;
            }
        }
    }
    public void removeTicket(String ticket){
        Node temp = head;
        while (temp != null) {
            if (temp.data.getLibraryCardNumber().equals(ticket)) {
                this.remove(temp.data);
                temp = temp.next;
            } else {
                temp = temp.next;
            }
        }
    }

    public List getDataWithCardNumber(String card) {

        Node temp = head;
        List listResult = new List();

        while (temp != null) {

            DataOnTheIssuanceAndAcceptanceOfBooks result = temp.data;

            if (result.getLibraryCardNumber().equals(card)) {
                listResult.add(result);
            }

            temp = temp.next;
        }
        return listResult;
    }

    public int length() {
        Node temp = head;
        int result = 0;

        while (temp != null) {
            result++;
            temp = temp.next;
        }

        return result;
    }

    public DataOnTheIssuanceAndAcceptanceOfBooks[] listToArray() {

        this.sort();

        int length = this.length();

        DataOnTheIssuanceAndAcceptanceOfBooks[] result = new DataOnTheIssuanceAndAcceptanceOfBooks[length];
        Node temp = head;
        for (int i = 0; i < length; i++) {
            result[i] = temp.data;
            temp = temp.next;
        }
        return result;
    }
}



