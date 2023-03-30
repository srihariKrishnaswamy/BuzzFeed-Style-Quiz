import java.util.*;
import java.io.*;
public class QuizTree {
    private QuizTreeNode overallRoot;

    public QuizTree(Scanner inputFile) {
        overallRoot = quizTreeHelper(inputFile);
    }

    private QuizTreeNode quizTreeHelper(Scanner inputFile) {
        if(inputFile.hasNext()) {
            String currLine = inputFile.nextLine();
            if(currLine.length() >= 4 && currLine.substring(0,4).equals("END:")) {   
                return new QuizTreeNode(currLine.substring(4, currLine.length()));
            } else {
                QuizTreeNode curr = new QuizTreeNode(currLine);
                curr.left = quizTreeHelper(inputFile);
                curr.right = quizTreeHelper(inputFile);
                return curr;
            }
        }
        return null;
    }

    public void takeQuiz(Scanner console) {
        takeQuiz(console, overallRoot);
    }

    private void takeQuiz(Scanner console, QuizTreeNode root) {
        if(root.left == null && root.right == null) {
            System.out.println("Your result is: " + root.data);
        } else {
            String first = root.data.substring(0, root.data.indexOf("/"));
            String second = root.data.substring(root.data.indexOf("/") + 1, root.data.length());
            System.out.print("Do you prefer " + first + " or " + second + "? ");
            String input = console.nextLine();
            if(input.equalsIgnoreCase(first)) {
                takeQuiz(console, root.left);
            } else if (input.equalsIgnoreCase(second)) {
                takeQuiz(console, root.right);
            } else {
                System.out.println("  Invalid response; try again.");
                takeQuiz(console, root);
            }
        }
    }

    public void export(PrintStream outputFile) {
        export(outputFile, overallRoot);
    }

    private void export(PrintStream outputFile, QuizTreeNode root) {
        if(root != null) {
            if(root.left == null && root.right == null) {
                outputFile.println("END:" + root.data);
            } else {
                outputFile.println(root.data);
            }
            export(outputFile, root.left);
            export(outputFile, root.right);
        }
    }

    public void addQuestion(String toReplace, String leftChoice, String rightChoice, 
                        String leftResult, String rightResult) {
        if(!containsLeaf(toReplace, overallRoot)) {
            throw new IllegalArgumentException();
        }
        addQuestion(toReplace, leftChoice, rightChoice, leftResult, rightResult, overallRoot);
    }

    private void addQuestion(String toReplace, String leftChoice, String rightChoice, 
                        String leftResult, String rightResult, QuizTreeNode root) {
        if(toReplace.equalsIgnoreCase(root.data) && (root.left == null && root.right == null)) {
            root.data = leftChoice + "/" + rightChoice;
            root.left = new QuizTreeNode(leftResult);
            root.right = new QuizTreeNode(rightResult);
        } else if (root.left != null && root.right != null) {
            addQuestion(toReplace, leftChoice, rightChoice, leftResult, rightResult, root.left);
            addQuestion(toReplace, leftChoice, rightChoice, leftResult, rightResult, root.right);
        }
    }

    private boolean containsLeaf(String toReplace, QuizTreeNode root) {
        if (root == null) {
            return false;
        } else if (root.data.equalsIgnoreCase(toReplace) && 
                root.left == null && root.right == null) {
            return true;
        } else {
            return (containsLeaf(toReplace, root.left) || 
                containsLeaf(toReplace, root.right));
        }
    }

    public static class QuizTreeNode {
        public String data;
        public QuizTreeNode left;
        public QuizTreeNode right;

        public QuizTreeNode(String data) {
            this.data = data;
        }
    }
}