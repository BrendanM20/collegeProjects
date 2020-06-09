
import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
public class Game {
    public static void main(String[] args) throws IOException {
        Scanner sc;
        boolean restart = false;
        BinaryTree<String> gameTree = new BinaryTree<String>();
        createGameTree1(gameTree); // this creates the initalized tree below
        String userInput = "";
        sc = new Scanner(System.in);
        boolean invalid = false;
        System.out.println("enter y or n");
        BinaryNodeInterface<String> currentNode = gameTree.getRootNode();  //this node traverses the tree with the user
        BinaryNodeInterface<String> leftChild = currentNode.getLeftChild();
        System.out.println(gameTree.getRootData()); // prints out the first question to the user
        /*
        the below while loop restarts the program when it is called below
         */
        while (!restart) {
            /*
            the below while loop is used until you get to the end of the tree
             */
            while (!currentNode.isLeaf()) {
                /*
                the two below nodes get the left and right nodes respectively
                 */
                leftChild = currentNode.getLeftChild();
                BinaryNodeInterface<String> rightChild = currentNode.getRightChild();
                userInput = sc.nextLine();
                /*
                the two below strings get the data for the left and right child nodes respectively
                 */
                String leftData = leftChild.getData();
                String rightData = rightChild.getData();
                /*
                if the user inputs "y" it get the left child and prints out the data in that child node
                 */
                if (userInput.equals("y")) {
                    System.out.println(leftData);
                    currentNode = currentNode.getLeftChild();
                }
                /*
                same for if the user inputs "n" except it goes to the right instead of left
                 */
                if (userInput.equals("n")) {
                    System.out.println(rightData);
                    currentNode = currentNode.getRightChild();
                }
            }
                /*
                when the user reaches the leaf nodes (i.e the answers) they are presented which the yes or no choice
                 */
                if (currentNode.isLeaf()) {
                    System.out.println("Is that your guess?(please enter yes or no)");
                    String answer = "";
                    answer = sc.nextLine();
                    /*
                    pathname for the file where the tree is saved to
                     */
                    File f = new File("gameTree.txt");
                    FileOutputStream fileOut = new FileOutputStream(f);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    BinaryTreeInterface<String> storedTree;
                    if (answer.equals("yes")) {
                        System.out.println("Yesssss, the tree guessed correctly!");
                        if (userInput.equals("play again")) {
                            restart = true; //this restarts the game by going to the while loop at the top
                        }
                        if (userInput.equals("store tree")) {
                            out.writeObject(gameTree);
                            out.close();
                            fileOut.close();
                            // stores tree for later use
                        }

                        if (userInput.equals("load")) {
                            try {
                                FileInputStream fileIn = new FileInputStream("gameTree.txt");
                                ObjectInputStream in = new ObjectInputStream(fileIn);
                                gameTree = (BinaryTree<String>) in.readObject();
                                in.close();
                                fileIn.close();
                                // loads the tree from the file
                            } catch (IOException i) {
                                i.printStackTrace();
                                return;
                            } catch (ClassNotFoundException c) {
                                System.out.println("GameTree not found");
                                c.printStackTrace();
                                return;
                            }
                        }
                        //quits the program
                        if (userInput.equals("quit")) {
                            System.exit(0);
                        }
                    }
                    if (answer.equals("no")) {
                        /*
                        if the guess is incorrect, the user enters in the question that would have got there guess
                        and the yes/no answers to accompany it
                         */
                        System.out.println("please enter a question that would help the tree guess your answer");
                        String guess = "";
                        guess = sc.nextLine();
                        System.out.println("please enter a answer that would be correct for your question");
                        String yes = "";
                        yes = sc.nextLine();
                        System.out.println("please enter a answer that would be incorrect for your question");
                        String no = "";
                        no = sc.nextLine();
                        /*
                        the tree below initalizes the users new tree
                         */
                        BinaryTree<String> newYes = new BinaryTree<String>(yes);
                        BinaryTree<String> newNo = new BinaryTree<String>(no);
                        BinaryTree<String> newQuestion = new BinaryTree<String>(guess, newYes, newNo);
                        gameTree.setRootNode(newQuestion.getRootNode()); // this replaces the node the user arrived at with the new Question
                        out.writeObject(gameTree);// this overwrites tree with new nodes
                        out.flush();
                        out.close();
                        fileOut.close();

                    }
                }
        }
    }
         public static void createGameTree1 (BinaryTree < String > tree)
        {
            /*
            initalizes the inital tree
             */
            BinaryNodeInterface<String> printNode = tree.getRootNode();

            BinaryTree<String> hTree = new BinaryTree<String>("Is it a bird?");
            BinaryTree<String> iTree = new BinaryTree<String>("Is it a fish?");
            BinaryTree<String> dTree = new BinaryTree<String>("Can it fly?",hTree,iTree);
            /////////////////////////////////////////////////////////////////////////////////
            BinaryTree<String> jTree = new BinaryTree<String>("Is it a ostrich?");
            BinaryTree<String> kTree = new BinaryTree<String>("Is it a horse?");
            BinaryTree<String> eTree = new BinaryTree<String>("Does it walk on two legs?",jTree,kTree);
            //////////////////////////////////////////////////////////////////////////////////
            BinaryTree<String> lTree = new BinaryTree<String>("Is it Donald Trump?");
            BinaryTree<String> mTree = new BinaryTree<String>("Is it Kendall Jenner?");
            BinaryTree<String> fTree = new BinaryTree<String>("Is it a boy?",lTree,mTree);
            ////////////////////////////////////////////////////////////////////////////////
            BinaryTree<String> nTree = new BinaryTree<String>("Is it Kim Kardashian?");
            BinaryTree<String> oTree = new BinaryTree<String>("Is it Barack Obama?");
            BinaryTree<String> gTree = new BinaryTree<String>("Is it a girl",nTree,oTree);
            //////////////////////////////////////////////////////////////////////////////////
            BinaryTree<String> bTree = new BinaryTree<String>("Is it a mammal?", dTree, eTree);
            BinaryTree<String> cTree = new BinaryTree<String>("Is it a human?", fTree, gTree);
            //The root
            tree.setTree("Is it an animal?", bTree, cTree);
            /*
            this prints out what the tree looks like
             */
            System.out.println("\nTree looks like this:\n");
            System.out.println("     "+tree.getRootData()+"      ");
            System.out.println("                  ");
            System.out.println(" B= " +tree.getRootNode().getLeftChild().getData() + "         C="+ tree.getRootNode().getRightChild().getData()+"   ");
            System.out.println("                  ");
            System.out.println("D="+  tree.getRootNode().getLeftChild().getLeftChild().getData()+ "        E="+ tree.getRootNode().getLeftChild().getRightChild().getData() +"    F=" +tree.getRootNode().getRightChild().getLeftChild().getData() +"     G =" + tree.getRootNode().getRightChild().getRightChild().getData());
            System.out.println("                  ");
            System.out.println("H = " +tree.getRootNode().getLeftChild().getLeftChild().getLeftChild().getData()+  "I = " +tree.getRootNode().getLeftChild().getLeftChild().getRightChild().getData() +    "J = " +tree.getRootNode().getLeftChild().getRightChild().getLeftChild().getData()+"    K = " +tree.getRootNode().getLeftChild().getRightChild().getRightChild().getData()+"   L = " +tree.getRootNode().getRightChild().getLeftChild().getLeftChild().getData()+"     M = " +tree.getRootNode().getRightChild().getLeftChild().getRightChild().getData()+ "   N = " +tree.getRootNode().getRightChild().getRightChild().getLeftChild().getData() +"   O = " +tree.getRootNode().getRightChild().getRightChild().getRightChild().getData());
        }
    }
