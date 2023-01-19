//{ Driver Code Starts
//Initial Template for Java

//Contributed by Sudarshan Sharma
import java.util.LinkedList; 
import java.util.Queue; 
import java.io.*;
import java.util.*;

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}

class GfG {
    
    static Node buildTree(String str){
        
        if(str.length()==0 || str.charAt(0)=='N'){
            return null;
        }
        
        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue
        
        Queue<Node> queue = new LinkedList<>(); 
        
        queue.add(root);
        // Starting from the second element
        
        int i = 1;
        while(queue.size()>0 && i < ip.length) {
            
            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();
                
            // Get the current node's value from the string
            String currVal = ip[i];
                
            // If the left child is not null
            if(!currVal.equals("N")) {
                    
                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }
                
            // For the right child
            i++;
            if(i >= ip.length)
                break;
                
            currVal = ip[i];
                
            // If the right child is not null
            if(!currVal.equals("N")) {
                    
                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));
                    
                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }
        
        return root;
    }
    static void printInorder(Node root)
    {
        if(root == null)
            return;
            
        printInorder(root.left);
        System.out.print(root.data+" ");
        
        printInorder(root.right);
    }
    
	public static void main (String[] args) throws IOException{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        int t=Integer.parseInt(br.readLine());
    
	        while(t-- > 0){
	            String s = br.readLine();
    	    	Node root = buildTree(s);
                Solution obj = new Solution();
                ArrayList <Integer> res = obj.verticalOrder(root);
                for (Integer num : res) System.out.print(num + " "); 
                System.out.println();
    	        
	        }
	}
}

// } Driver Code Ends


//User function Template for Java
class Tuple {
    int row;
    int col;
    Node node;
    public Tuple(Node node, int row, int col)  {
        this.node = node;
        this.row = row;
        this.col = col;
    }
}

class Solution
{
    //Function to find the vertical order traversal of Binary Tree.
    static ArrayList <Integer> verticalOrder(Node root)
    {
        // add your code here
        ArrayList<Integer> list = new ArrayList<>();
        TreeMap<Integer, TreeMap<Integer, ArrayList<Integer>>> map = new TreeMap<>();
        Queue<Tuple> q = new LinkedList<>();
        q.offer(new Tuple(root, 0, 0));
        while(!q.isEmpty()) {
            Tuple tuple = q.poll();
            Node node = tuple.node;
            int x = tuple.row;
            int y = tuple.col;

            if(!map.containsKey(x)) {
                map.put(x, new TreeMap<>());
            }
            if(!map.get(x).containsKey(y)) {
                map.get(x).put(y, new ArrayList<>());
            }
            map.get(x).get(y).add(node.data);
            if(node.left != null) {
                q.offer(new Tuple(node.left, x-1, y+1));
            }
            if(node.right != null) {
                q.offer(new Tuple(node.right, x+1, y+1));
            }
        }

        for(TreeMap<Integer, ArrayList<Integer>> ys : map.values()) {
            for(ArrayList<Integer> nodes : ys.values()) {
                for(int i : nodes) {
                    list.add(i);
                }
            }
        }
        return list;
    }
}