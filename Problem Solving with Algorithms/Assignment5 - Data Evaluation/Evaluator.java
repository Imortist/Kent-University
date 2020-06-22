/**
 * @date 13/04/20
 * @author Aleksej Bratkovskij (ab2323) & Gregory Fomichev (gf217)
 */

import java.util.Arrays;
import java.util.List;

public class Evaluator {
    List<String> tree;
    int score;

    /**
     * Evaluator needs generated tree provided in order to work on data properly
     * @param tree source of binary tree
     */
    public Evaluator(List<String> tree){
        this.tree = tree;
        // variable initialisation
        score = 0;
    }

    /**
     * Get index of node information in collection.
     * @param nodeIndex - node number
     * @return int value of node information in collection
     */
    private int getCollectionIndexForNode(int nodeIndex){
        int i = 0;
        while(!tree.get(i).startsWith(String.valueOf(nodeIndex))){
            i++;
        }
        return i;
    }

    /**
     * Create node from node information in txt file.
     * @param index - position of node information in collection.
     * @return generated node from provided values.
     */
    private Node createNodeFromIndex(int index){
        String[] info = tree.get(index).split(",");
        return new Node(Integer.parseInt(info[0]),info[1],Integer.parseInt(info[2]),
                Integer.parseInt(info[3]),Integer.parseInt(info[4]));
    }

    /**
     * Traverse binary tree and return score
     * @param nodeIndex - node number
     * @param line - line of data.
     * @return integer value of score stored in the node
     */
    private int getScore(int nodeIndex, List<String> line){
            Node node = createNodeFromIndex(getCollectionIndexForNode(nodeIndex));
            if(line.contains(node.getValue()))
                 getScore((node).getLeft(),line);
            else if(node.getRight() != -1) getScore(node.getRight(),line);
                 else score = node.getScore();

        return score;
    }

    /**
     * Return complete tree traversal score.
     * @param data data.txt file
     * @return integer value of total score.
     */
    public int evaluate(List<String> data){
        int result = 0;
        for(String line : data){
            result += getScore(0,Arrays.asList(line.split(",")));
        }
        return result;
    }
}
