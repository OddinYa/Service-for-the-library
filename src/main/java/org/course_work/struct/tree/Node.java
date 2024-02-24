package org.course_work.struct.tree;

import org.course_work.entity.Book;

class Node {
    protected Node left;
    protected Node right;
    protected Book book;

    protected String key;
    protected int hash;

    protected int height;

    public Node(Node left, Node right, Book book, String key, int hash) {
        this.left = left;
        this.right = right;
        this.book = book;
        this.key = key;
        this.hash = hash;
        this.height = 0;
    }
}
