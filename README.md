BuzzFeed-Style Quiz

System Structure
This project used a binary tree where leaf nodes are possible results, and non-leaf nodes (branches) represent choices the user makes.
When a user takes the quiz, they will be presented with the root node of the tree. Based on their response, the system will traverse to either the left or right child of the root. If the node found is a leaf, the user will be shown their result. Otherwise, the process will repeat from the new node until a leaf is reached.
Each node in the tree will be represented by a single line in the file containing the text for that node.
"Choice" nodes will be written with the two choices separated by a single slash (/) character. When taking the quiz, choosing the option before the slash will move to the left child of the node, whereas choosing the option after the slash will move the right child of the node.
"Result" nodes will be written as the result option prefixed with the text END:.

Usage
Here is a sample quiz file (just a normal txt file) representing a quiz that asks users to choose between colors to find their preferred breakfast cereal. Spaces are new lines.

red/blue
yellow/green
END:Froot Loops
END:Raisin Bran
purple/orange
END:Frosted Flakes
black/white
END:Rice Krispies
END:Fruity Pebbles

Notice that the file represents a pre-order traversal of the resulting tree. So, for example, "red/blue" is the root of the tree and "yellow/green" is the left child of the root.
Sample Executions
Here are a few sample executions of taking the sample quiz above:
Do you prefer red or blue? red
Do you prefer yellow or green? green
Your preferred breakfast cereal is Froot Loops.

Do you prefer red or blue? blue
Do you prefer purple or orange? purple
Do you prefer black or white? black
Your preferred breakfast cereal is Rice Krispies.

Running this program:
In order to run this program:
1. clone the repository by, in terminal, typing 'git clone ' and then the repo link
2. navigate to the src file in terminal 
3. in terminal, type 'javac App.java'
4. then type 'java App'
If this doesn't work, you may have to install the Java Development Kit (JDK) do so here: https://www.oracle.com/java/technologies/downloads/
