package org.course_work.struct.tree;

import org.course_work.entity.Book;

public class TreeSet {

    private Node rootNode;

    public TreeSet(){
        rootNode = null;
    }

    public void insert(Book book){

        int hash = hash(book.getCipher());

        Node newNode = new Node(null,null,book, book.getCipher(), hash);

        if(rootNode == null){
            rootNode = newNode;
        }else {
            Node current = rootNode;
            Node parentNode;

            while (true){
                parentNode = current;

                if(hash == current.getHash()){
                    return;
                }
                else if(hash < current.getHash()){

                    current = current.getLeft();

                    if(current==null){

                        parentNode.setLeft(newNode);
                        rootNode = balance(rootNode); //balance
                        updateHeight(rootNode);
                        return;
                    }
                }else {
                    current = current.getRight();
                    if(current == null){
                        parentNode.setRight(newNode);
                        rootNode = balance(rootNode); //balance
                        updateHeight(rootNode);
                        return;
                    }
                }
            }

        }

    }
    public void delete(Book book){

    }
    public Book search(){
        return null;
    }

    public int getHeight(){
        return 1;
    }
    public int getBalanceFactor(Node node){
        return 1;
    }

    public Book[] traversal(){
        return null;
    }

    private int hash(String key){
        int prime = 31;
        int result = 11;
        result = result * Integer.parseInt(key.substring(0,3))+prime;
        result = result * Integer.parseInt(key.substring(4));

        return result;

    }
    private Node balance(Node node) {
        updateHeight(node); // Обновляем высоту текущего узла

        int balanceFactor = getBalanceFactor(node);

        // Если дерево несбалансировано вправо
        if(balanceFactor > 1) {
            // Дерево несбалансировано вправо-лево
            if(getBalanceFactor(node.getRight()) < 0) {
                node.setRight(rightRotate(node.getRight())); // Большой правый поворот для правого поддерева
            }
            return leftRotate(node); // Малый левый поворот для текущего узла
        }
        // Если дерево несбалансировано влево
        else if(balanceFactor < -1) {
            // Дерево несбалансировано влево-право
            if(getBalanceFactor(node.getLeft()) > 0) {
                node.setLeft(leftRotate(node.getLeft())); // Большой левый поворот для левого поддерева
            }
            return rightRotate(node);  // Малый правый поворот для текущего узла
        }
        return node;
    }
    private void updateHeight(Node node) {
        int leftHeight = (node.getLeft() == null) ? -1 : node.getLeft().getHeight();
        int rightHeight = (node.getRight() == null) ? -1 : node.getRight().getHeight();
        node.setHeight(1 + Math.max(leftHeight, rightHeight));
    }

    private Node rightRotate(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        // Выполняем поворот
        x.setRight(y);
        y.setLeft(T2);

        // Обновляем высоты узлов
        updateHeight(y);
        updateHeight(x);

        return x; // Новый корень поддерева после поворота
    }

    private Node leftRotate(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

        // Выполняем поворот
        y.setLeft(x);
        x.setRight(T2);

        // Обновляем высоты узлов
        updateHeight(x);
        updateHeight(y);

        return y; // Новый корень поддерева после поворота
    }


}
