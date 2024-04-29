public class Node<AnyType> {

    //
    AnyType payload;
    Node<AnyType> left;
    Node<AnyType> right;


    // Default constructor
    public Node() {
        this.left = null;
        this.right = null;
        this.payload = null;
    }

    public Node(AnyType payload, Node<AnyType> left, Node<AnyType> right) {
        this.payload = payload;
        this.left = left;
        this.right = right;
    }

    // Mutators
    public void setLeft(Node<AnyType> left) {
        this.left = left;
    }

    public void setRight(Node<AnyType> right) {
        this.right = right;
    }

    public void setPayload(AnyType payload) {
        this.payload = payload;
    }

    // Accessors
    public Node<AnyType> getLeft() {
        return left;
    }

    public Node<AnyType> getRight() {
        return right;
    }

    public AnyType getPayload() {
        return payload;
    }
}
