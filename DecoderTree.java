public class DecoderTree {
	DecoderNode root;
	public DecoderTree() {
		root=new DecoderNode();
	}
	public DecoderNode getRoot(){
		return root;
	}
	public void insert(String code, DecoderNode node, Integer data){
		for(int i=0;i<code.length();i++){
			if(code.charAt(i)=='0'){
				if(node.left==null){
					node.left=new DecoderNode();
				}
				node=node.left;
			}
			else{
				if(node.right==null){
					node.right=new DecoderNode();
				}
				node=node.right;
			}
		}
		node.data=data;
		node.isEnd=true;
	}
	
}
