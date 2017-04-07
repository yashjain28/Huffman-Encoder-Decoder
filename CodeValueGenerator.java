import java.util.HashMap;

public class CodeValueGenerator {
		
	HuffmanTreeConstructor hmtc;
	Minheap tree;
	HashMap<Integer, String> dataCode;
	
	public CodeValueGenerator(Minheap tree) {
			// TODO Auto-generated constructor stub
			dataCode =new HashMap<>();
			generateCodeValueDFS(tree.getRoot(),new StringBuilder());
		}
		
	public void generateCodeValueDFS(TreeNode root, StringBuilder sb){
		if(root!=null && root.left==null && root.right==null){
			dataCode.put(root.data, sb.toString());
			return;
		}
		if(root.left!=null){
			sb.append('0');
			generateCodeValueDFS(root.left, sb);
			sb.deleteCharAt(sb.length()-1);
		}
		if(root.right!=null){
			sb.append('1');
			generateCodeValueDFS(root.right, sb);
			sb.deleteCharAt(sb.length()-1);
		}
	}
		
		
}
