package org.course_work.struct.tree;

import org.course_work.entity.Book;

class Node {
    protected Node left;
    protected Node right;
    protected Book book;

    protected String key;
    private int hash;

    private int height;

    public Node(Node left, Node right, Book book, String key, int hash) {
        this.left = left;
        this.right = right;
        this.book = book;
        this.key = key;
        this.hash = hash;
        this.height = 0;
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHash() {
        return hash;
    }

    public String getKey() {
        return key;
    }

    protected Node getLeft() {
        return left;
    }

    protected void setLeft(Node left) {
        this.left = left;
    }

    protected Node getRight() {
        return right;
    }

    protected void setRight(Node right) {
        this.right = right;
    }

    protected Book getBook() {
        return book;
    }

}
