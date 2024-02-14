package org.course_work.struct.linkedList;

import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;

public class List {
    private Node head;

    public void add(DataOnTheIssuanceAndAcceptanceOfBooks data ){
        if(head==null){
            head = new Node(data);
        }else {
            Node node = head;

            while (node.next!=null){
                node = node.next;
            }
            node.next = new Node(data);
        }
    }

    public void remove(DataOnTheIssuanceAndAcceptanceOfBooks data){
        if(head==null){
            return;
        }
        if(data.equals(head.data)){
            head = head.next;
            return;
        }

        Node temp = head;
        while(head.next != null){

            if(data.equals(temp.next.data)){
                temp.next = temp.next.next;
                return;
            }
            temp = head.next;
        }
    }
}

