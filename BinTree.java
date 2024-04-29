import java.io.PrintStream;

class BinTree<AnyType extends Comparable<? super AnyType>> {
    private Node<AnyType> root;

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    private Node<AnyType> insert(AnyType x, Node<AnyType> t) {
        if (t == null) {
            return new Node<>(x, null, null);
        }

        int compareResult = x.compareTo(t.payload);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        }
        return t;
    }

    public AnyType search(AnyType x) {
        return search(x, root);
    }

    private AnyType search(AnyType x, Node<AnyType> t) {
        if (t == null) {
            return null;
        }

        int compareResult = x.compareTo(t.payload);
        if (compareResult < 0) {
            return search(x, t.left);
        } else if (compareResult > 0) {
            return search(x, t.right);
        } else {
            return t.payload;
        }
    }

    public void delete(AnyType x) {
        root = delete(x, root);
    }

    private Node<AnyType> delete(AnyType x, Node<AnyType> t) {
        if (t == null) {
            return t;
        }

        int compareResult = x.compareTo(t.payload);
        if (compareResult < 0) {
            t.left = delete(x, t.left);
        } else if (compareResult > 0) {
            t.right = delete(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.payload = leftmost(t.right).payload;
            t.right = delete(t.payload, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }

        return t;
    }

    private Node<AnyType> leftmost(Node<AnyType> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        } else {
            return leftmost(t.left);
        }
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(Node<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.payload);
            printTree(t.right);
        }
    }

    public void printTreePreOrder(PrintStream f) {
        printTreePreOrder(f, root);
    }

    private void printTreePreOrder(PrintStream f, Node<AnyType> t) {
        if (t != null) {
            f.println(t.payload.toString());
            printTreePreOrder(f, t.left);
            printTreePreOrder(f, t.right);
        }
    }
}
