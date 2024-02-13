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
}

