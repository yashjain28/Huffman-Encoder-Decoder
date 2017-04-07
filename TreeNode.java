
public class TreeNode {
	int data;
	int freq;
	TreeNode left,right,prev,next,child;
	TreeNode(){
		data=-1;
		freq=0;
		left=null;
		right=null;
		prev=null;
		next=null;
		child=null;
	}
	TreeNode(int data, int freq){
		this.data=data;
		this.freq=freq;
		left=null;
		right=null;
		prev=null;
		next=null;
		child=null;		
	}
}
