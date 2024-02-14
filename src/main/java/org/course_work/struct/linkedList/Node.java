package org.course_work.struct.linkedList;

import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;

class Node {
    protected Node next;
    protected DataOnTheIssuanceAndAcceptanceOfBooks data;

    protected Node(DataOnTheIssuanceAndAcceptanceOfBooks data){
        this.data = data;
        next = null;
    }

}
