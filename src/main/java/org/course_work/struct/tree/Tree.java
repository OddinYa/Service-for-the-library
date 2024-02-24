package org.course_work.struct.tree;

import org.course_work.entity.Book;

import java.util.Arrays;

public class Tree {

    private Node rootNode;

    public Tree() {
        rootNode = null;
    }

    public boolean find(Node node, int hash) {

        if (node == null) {
            return false;
        }
        if (hash(node.getBook().getCipher()) == hash) {
            return true;
        }
        if (hash(node.getBook().getCipher()) > hash) {
            return find(node.getLeft(), hash);
        } else {
            return find(node.getRight(), hash);
        }
    }

    public void insert(Book book) throws Exception {

        int hash = hash(book.getCipher());
        Node newNode = new Node(null, null, book, book.getCipher(), hash);

        boolean flag = find(rootNode, hash);

        if (rootNode == null) {
            rootNode = newNode;

        } else {
            if (flag) {
                boolean equals = search(book.getCipher()).equals(book);

                if (equals) {
                    System.out.println("Вы пытаетесь добавить уже существующую книгу!");
                    return;
                }
            }
            Node current = rootNode;
            Node parentNode;

            while (true) {
                parentNode = current;


                if (hash < current.getHash()) {
                    current = current.getLeft();
                    if (current == null) {
                        parentNode.setLeft(newNode);

                        rootNode = balance(rootNode);
                        updateHeight(parentNode);
                        break;
                    }

                } else {
                    current = current.getRight();
                    if (current == null) {
                        parentNode.setRight(newNode);

                        rootNode = balance(rootNode);
                        updateHeight(parentNode);
                        break;
                    }
                }
            }
        }

    }


    public void delete(Book book) {

    }

    public Book search(String chipher) throws Exception {
        int hash = hash(chipher);
        Book result;

        Node current = rootNode;
        Node parentNode;

        while (current != null) {
            parentNode = current;

            if (current.getHash() == hash && current.getBook().getCipher().equals(chipher)) {
                return result = current.getBook();

            } else if (current.getHash() > hash) {
                current = parentNode.getLeft();

            } else {
                current = parentNode.getRight();
            }

        }
        throw new Exception("Книга с заданным шифром не найдена");
    }



    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }

        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    public Book[] traversal() {
        Book[] result = new Book[10];
        int[] index = {0};

        traversal(rootNode, result, index);

        Book[] finalResult = new Book[index[0]];
        System.arraycopy(result, 0, finalResult, 0, index[0]);

        return finalResult;
    }

    private void traversal(Node node, Book[] array, int[] index) {
        if (node != null) {
            traversal(node.getLeft(), array, index);

            if (index[0] == array.length) {
                array = increaseArraySize(array);
            }
            array[index[0]++] = node.getBook();

            traversal(node.getRight(), array, index);
        }
    }

    private Book[] increaseArraySize(Book[] array) {
        int newSize = array.length * 2;
        return Arrays.copyOf(array, newSize);
    }

    private int hash(String key) {
        int prime = 31;
        int result = 11;
        result = result * Integer.parseInt(key.substring(0, 3)) + prime;
        result = result * Integer.parseInt(key.substring(4));

        return result%999;

    }

    private Node balance(Node node) {
        if (node == null) {
            return null;
        }

        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (getBalanceFactor(node.getLeft()) < 0) {
                // Необходим большой правый поворот
                return bigRightRotate(node);
            } else {
                return rightRotate(node);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.getRight()) > 0) {
                // Необходим большой левый поворот
                return bigLeftRotate(node);
            } else {
                return leftRotate(node);
            }
        }

        return node;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
        }
    }

    private void updateHeight(Node node) {
        if (node == null) {
            return;
        }
        int leftHeight = (node.getLeft() == null) ? 0 : node.getLeft().getHeight();
        int rightHeight = (node.getRight() == null) ? 0 : node.getRight().getHeight();
        node.setHeight(1 + Math.max(leftHeight, rightHeight));
    }


    private Node rightRotate(Node a) {
        Node b = a.getLeft();
        a.setLeft(b.getRight());
        b.setRight(a);

        updateHeight(a);
        updateHeight(b);

        return b;
    }

    private Node leftRotate(Node b) {
        Node a = b.getRight();
        b.setRight(a.getLeft());
        a.setLeft(b);

        updateHeight(b);
        updateHeight(a);

        return a;
    }
    private Node bigLeftRotate(Node x){

        x.setLeft(leftRotate(x.getLeft()));
        updateHeight(x.getLeft());
        return rightRotate(x);
    }

    private Node bigRightRotate(Node y){

        y.setRight(rightRotate(y.getRight()));
        updateHeight(y.getRight());
        return leftRotate(y);
    }


}
