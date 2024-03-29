package org.course_work.struct.tree;

import org.course_work.entity.Book;
import org.course_work.struct.MyStruct;

import java.util.Arrays;

//AVL TREE
//АВЛ ДЕРЕВО
public class Tree extends MyStruct {
    private static int nodes;
    private Node root;

    void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    int height(Node n) {
        return n == null ? -1 : n.height;
    }

    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    public void add(Book book) {
        String key = book.getCipher();
        root = insert(root, key, book);
    }
    public void remove(String key) {
        int hash = hash(key);
        root = delete(root, hash);
    }

    public Book get(String key) {

        int hash = hash(key);
        Node temp = root;
        Book result = null;

        while (temp != null) {

            if (temp.hash == hash) {
                result = temp.book;
                return result;
            }else if(hash < temp.hash){
                temp = temp.left;
            }else if(hash> temp.hash){
                temp = temp.right;
            }
        }

        return result;
    }

    private Node insert(Node root, String key, Book book) {
        nodes++;
        int hash = hash(key);
        if (root == null) {
            return new Node(null, null, book, key, hash);
        } else if (hash < root.hash) {
            root.left = insert(root.left, key, book);
        } else if (hash > root.hash) {
            root.right = insert(root.right, key, book);
        } else {
            throw new RuntimeException("Duplicate Key!");
        }
        return rebalance(root);
    }

    public Book[] traversal() {
        Book[] result = new Book[nodes];
        int[] index = {0};

        traversal(root, result, index);

        Book[] finalResult = new Book[index[0]];
        System.arraycopy(result, 0, finalResult, 0, index[0]);

        return finalResult;
    }

    private void traversal(Node node, Book[] array, int[] index) {
        if (node != null) {
            traversal(node.left, array, index);

            if (index[0] == array.length) {
                array = increaseArraySize(array);
            }
            array[index[0]++] = node.book;

            traversal(node.right, array, index);
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

        return result % 999;

    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node delete(Node node, int hash) {
        if (node == null) {
            return node;
        } else if (node.hash > hash) {
            node.left = delete(node.left, hash);
        } else if (node.hash < hash) {
            node.right = delete(node.right, hash);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node mostLeftChild = mostLeftChild(node.right);
                node.key = mostLeftChild.key;
                node.right = delete(node.right, node.hash);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    private Node mostLeftChild(Node node) {
        if (node.left == null) {
            return node;
        }
        return mostLeftChild(node.left);
    }

    private Node rebalance(Node z) {
        updateHeight(z);

        int balance = getBalance(z);
        if (balance > 1) {
            if (getBalance(z.right) >= 0) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (getBalance(z.left) <= 0)
                z = rotateRight(z);
            else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

}
