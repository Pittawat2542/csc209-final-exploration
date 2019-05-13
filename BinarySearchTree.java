import java.util.LinkedList;

public class BinarySearchTree {
    Node root;

    public void visit(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
        }
    }

    public void insert(int info) {
        // Tree is empty
        if (root == null) {
            root = new Node(info);
        } else {
            Node current = root;
            Node prev = null;

            while (current != null) {
                prev = current;
                if (info < current.data) { // info is less than current node's data
                    current = current.left; // go left
                } else { // info is greater than or equal to current node's data
                    current = current.right; // go right
                }
            }

            // Here, we found the almost right position to place new node
            // Check for the precise position
            if (info < prev.data) { // info is less than prev's data
                prev.left = new Node(info); // attach to left side
            } else { // info is greater than or equal to prev's data
                prev.right = new Node(info); // attach to right side
            }
        }
    }

    public Node search(int key) {
        Node current = root;
        while (current != null) {
            if (key < current.data) { // key is less than current node's data
                current = current.left; // go left
            } else if (key > current.data) { // key is greater than current node's data
                current = current.right; // go right
            } else { // we found it !
                return current;
            }
        }

        System.out.println(key + " is not found on the tree!");
        return null; // we cannot find that data or tree is empty
    }

    public void breadthFirst() {
        if (root != null) {
            LinkedList queue = new LinkedList();
            queue.add(root); // enqueue root

            while (!queue.isEmpty()) { // queue is not empty
                Node current = (Node) queue.remove(); // dequeue
                visit(current); // print

                if (current.left != null) {
                    queue.add(current.left); // enqueue left child if not null
                }

                if (current.right != null) {
                    queue.add(current.right); // enqueue right child if not null
                }
            }
            System.out.println();
        }

    }

    public void preorder() {
        preorderRecursion(root);
        System.out.println();
    }

    private void preorderRecursion(Node node) {
        if (node != null) {
            visit(node); // Parent
            preorderRecursion(node.left); // Left
            preorderRecursion(node.right); // Right
        }
    }

    public void inorder() {
        inorderRecursion(root);
        System.out.println();
    }

    private void inorderRecursion(Node node) {
        if (node != null) {
            inorderRecursion(node.left); // Left
            visit(node); // Parent
            inorderRecursion(node.right); // Right
        }
    }

    public void postorder() {
        postorderRecursion(root);
        System.out.println();
    }

    private void postorderRecursion(Node node) {
        if (node != null) {
            postorderRecursion(node.left); // Left
            postorderRecursion(node.right); // Right
            visit(node); // Parent
        }
    }

    public Node deleteByMerging(int key) {
        // Find the deletedNode
        Node deletedNode = root;
        Node parentOfDeletedNode = null;
        while (deletedNode != null && deletedNode.data != key) {
            parentOfDeletedNode = deletedNode;
            if (key < deletedNode.data) {
                deletedNode = deletedNode.left;
            } else if (key > deletedNode.data) {
                deletedNode = deletedNode.right;
            } else {
                break;
            }

        }

        if (deletedNode == null) { // We cannot find the node equal to key or the tree is empty
            return null;
        }

        Node nodeToPointTo = null;

        // Only have 1 child or no children
        if ((deletedNode.left != null && deletedNode.right == null) || (deletedNode.left == null && deletedNode.right == null)) { // only have left child or node children
            nodeToPointTo = deletedNode.left;
        } else if (deletedNode.right != null && deletedNode.left == null) { // only have right child
            nodeToPointTo = deletedNode.right;
        } else {
            // Find the rightmost node of left subtree
            Node rightmostNodeOfLeftSubtree = deletedNode.left; // start from left subtree

            while (rightmostNodeOfLeftSubtree.right != null) { // go on until we cannot go right
                rightmostNodeOfLeftSubtree = rightmostNodeOfLeftSubtree.right;
            }

            rightmostNodeOfLeftSubtree.right = deletedNode.right; // point right of the rightmost node to the right of deleted node
            nodeToPointTo = deletedNode.left;
        }

        if (deletedNode == root) { // the node we want to delete is at root
            root = nodeToPointTo;
        } else {
            if (parentOfDeletedNode.left == deletedNode) { // deleted node is on the left of parent
                parentOfDeletedNode.left = nodeToPointTo;
            } else {
                parentOfDeletedNode.right = nodeToPointTo;
            }
        }

        return deletedNode;
    }

    public Node deleteByCopying(int key) {
// Find the deletedNode
        Node deletedNode = root;
        Node parentOfDeletedNode = null;
        while (deletedNode != null && deletedNode.data != key) {
            parentOfDeletedNode = deletedNode;
            if (key < deletedNode.data) {
                deletedNode = deletedNode.left;
            } else if (key > deletedNode.data) {
                deletedNode = deletedNode.right;
            } else {
                break;
            }

        }

        if (deletedNode == null) { // We cannot find the node equal to key or the tree is empty
            return null;
        }

        Node nodeToPointTo = null;

        // Only have 1 child or no children
        if ((deletedNode.left != null && deletedNode.right == null) || (deletedNode.left == null && deletedNode.right == null)) { // only have left child or node children
            nodeToPointTo = deletedNode.left;
        } else if (deletedNode.right != null && deletedNode.left == null) { // only have right child
            nodeToPointTo = deletedNode.right;
        }

        if (deletedNode.left != null && deletedNode.right != null) {
            // Find the rightmost node of left subtree
            Node rightmostNodeOfLeftSubtree = deletedNode.left; // start from left subtree
            Node parentOfRightMostNodeOfLeftSubtree = null;

            while (rightmostNodeOfLeftSubtree != null && rightmostNodeOfLeftSubtree.right != null) { // go on until we cannot go right
                parentOfRightMostNodeOfLeftSubtree = rightmostNodeOfLeftSubtree;
                rightmostNodeOfLeftSubtree = rightmostNodeOfLeftSubtree.right;
            }

            deletedNode.data = rightmostNodeOfLeftSubtree.data;
            parentOfRightMostNodeOfLeftSubtree.right = rightmostNodeOfLeftSubtree.left;
        } else {
            if (deletedNode == root) { // the node we want to delete is at root
                root = nodeToPointTo;
            } else {
                if (parentOfDeletedNode.left == deletedNode) { // deleted node is on the left of parent
                    parentOfDeletedNode.left = nodeToPointTo;
                } else {
                    parentOfDeletedNode.right = nodeToPointTo;
                }
            }
        }

        return deletedNode;
    }
}
