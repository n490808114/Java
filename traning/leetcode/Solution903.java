import java.util.ArrayList;

public class Solution903 {
    public static void main(String[] args){

    }
    public int numPermsDISequence(String S) {
        int result = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0;i<S.length();i++){
            list.add(i);
        }
        for(int a=0;a<=S.length();a++){

        }
        return result;
    }
}
class Node{
    int value;
    Node leftChild;
    Node rightChild;
    public Node(int value){
        this.value = value;
    }

    public void setLeftChild(Node leftChild)
        throws Exception{
        this.leftChild = leftChild;
        if(leftChild.value >= value){
            throw new Exception();
        }
    }
    public void setRightChild(Node rightChild)
        throws Exception{
        this.rightChild = rightChild;
        if(rightChild.value <= value){
            throw new Exception();
        }
    }
}

