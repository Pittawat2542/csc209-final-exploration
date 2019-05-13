public class BinarySearchTreeTest {

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(50);
        binarySearchTree.insert(10);
        binarySearchTree.insert(20);
        binarySearchTree.insert(30);
        binarySearchTree.insert(60);
        binarySearchTree.insert(70);
        binarySearchTree.insert(80);

        binarySearchTree.visit(binarySearchTree.search(80));
        System.out.println();
        binarySearchTree.visit(binarySearchTree.search(90));
        System.out.println();

        binarySearchTree.breadthFirst();
        binarySearchTree.preorder();
        binarySearchTree.inorder();
        binarySearchTree.postorder();

        binarySearchTree.deleteByMerging(50);
        binarySearchTree.deleteByMerging(100);
        binarySearchTree.breadthFirst();
        binarySearchTree.deleteByMerging(30);
        binarySearchTree.deleteByMerging(80);
        binarySearchTree.breadthFirst();

        binarySearchTree.insert(50);
        binarySearchTree.insert(30);
        binarySearchTree.insert(80);
        binarySearchTree.breadthFirst();

        binarySearchTree.deleteByCopying(10);
        binarySearchTree.deleteByCopying(100);
        binarySearchTree.deleteByCopying(80);
        binarySearchTree.breadthFirst();
    }
}
