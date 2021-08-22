package binarytreeproject;

import java.io.File;
import java.util.Scanner;
import java.util.*;

public class BinaryTreeProject {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\ENG OZIL\\Desktop\\Dictionary.txt");
        Scanner sc = new Scanner(file);
        BinaryTreeNode root = new BinaryTreeNode(sc.next());
        while (sc.hasNextLine()) {
            insert(root, sc.next());
        }
        printchoisis();
        Scanner fin = new Scanner(System.in);
        int chois = fin.nextInt();
        switch (chois) {
            case 1:
                inorder(root);
                break;
            case 2:
                postorder(root);
                break;
            case 3:
                preorder(root);
                break;
            case 4:
                System.out.println("Number of words loaded in tree = "
                        + countSize(root));
                break;
            case 5:
                breadthFirstSearch(root);
                break;
            case 6:
                System.out.println("Height of tree is : "
                        + findHeight(root));
                break;
            case 7:
                System.out.println("Enter the key to search");
                Scanner Sin = new Scanner(System.in);
                String key = Sin.next();
                search(root, key);
                break;
            case 8:
                Scanner Tin = new Scanner(System.in);
                String word = Tin.next();
                minisearch(root, word);
                insert(root, word);
                break;
        }
    }

    public static BinaryTreeNode insert(BinaryTreeNode root, String data) {
        if (root == null) {
            return new BinaryTreeNode(data);
        }
        if (data.compareTo(root.data) < 0) {
            root.left = insert(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = insert(root.right, data);
        }
        return root;
    }

    public static void preorder(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.data);
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.data);
        inorder(root.right);
    }

    public static void postorder(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.data);
    }

    public static void printchoisis() {
        System.out.println("Enter your choise\n"
                + "1. Print tree using in order traversal\n"
                + "2. Print tree using post order traversal\n"
                + "3. Print tree using pre order traversal\n"
                + "4. Print tree size (number of words loaded in the tree)\n"
                + "5. Print tree using BFS Traversal\n"
                + "6. Print tree height\n"
                + "7. Search for a word\n"
                + "8. Insert new word in the tree");
    }

    public static int countSize(BinaryTreeNode root) {
        if (root == null) {

            return 0;
        }

        return countSize(root.left) + 1 + countSize(root.right);

    }

    public static int findHeight(BinaryTreeNode root) {
        if (root == null) {
            return -1;
        } else {
            int rightHeight = findHeight(root.right);
            int leftHeight = findHeight(root.left);

            if (rightHeight > leftHeight) {
                return rightHeight + 1;
            } else {
                return leftHeight + 1;
            }

        }

    }

    public static void breadthFirstSearch(BinaryTreeNode root) {
        Queue< BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode visitingNode = queue.poll();
            if (visitingNode == null) {
                continue;
            }
            System.out.println(visitingNode.data);
            queue.add(visitingNode.left);
            queue.add(visitingNode.right);
        }
    }

    public static void inOrderpredecessor(BinaryTreeNode root, String key) {
        String predecessor = null;
        if (root != null) {
            if (root.data.compareTo(key) == 0) {

                if (root.left != null) {
                    BinaryTreeNode newNode = root.left;

                    while (newNode.right != null) {
                        newNode = newNode.right;
                    }
                    predecessor = newNode.data;

                }
            } else if (root.data.compareTo(key) < 0) {
                predecessor = root.data;
                inOrderpredecessor(root.right, key);

            }
        }
        System.out.println(predecessor);
    }

    public static void inOrderSuccessor(BinaryTreeNode root, String key) {
        String successor = null;
        if (root != null) {
            if (root.data.compareTo(key) == 0) {
                if (root.right != null) {
                    BinaryTreeNode newNode = root.right;

                    while (newNode.left != null) {
                        newNode = newNode.left;
                    }

                    successor = newNode.data;
                }
            } else if (root.data.compareTo(key) > 0) {
                successor = root.data;
                inOrderSuccessor(root.left, key);

            }
        }
        System.out.println(successor);
    }

    public static BinaryTreeNode search(BinaryTreeNode root, String key) {
        String tmp = root.data;
        if (root.data.compareTo(key) == 0) {
            System.out.println("Word is correct");
            return root;
        } else if (root.right == null || root.left == null) {

            System.out.println("Three suggestions for the coorect word");
            System.out.println("1st \n" + root.data);
            System.out.println("2nd");
            inOrderpredecessor(root, tmp);
            System.out.println("3rd");
            inOrderSuccessor(root, tmp);
            return root;
        } else if (root.data.compareTo(key) > 0 && root.left != null) {
            search(root.left, key);
        } else if (root.data.compareTo(key) < 0 && root.right != null) {
            search(root.right, key);
        }

        return root;
    }

    public static BinaryTreeNode minisearch(BinaryTreeNode root, String key) {
        if (root.data.compareTo(key) == 0) {
            System.out.println("The word is exists");
            return root;
        } else if (root.data.compareTo(key) > 0 && root.left != null) {
            minisearch(root.left, key);
        } else if (root.data.compareTo(key) < 0 && root.right != null) {
            minisearch(root.right, key);
        } else {
            System.out.println("The word " + key + " is added");
        }
        return root;

    }

}
