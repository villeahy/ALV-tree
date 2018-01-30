

package btree;

public class BinaryTree {

    private Node root;
    public BinaryTree(String rootValue) {
        root = new Node(rootValue);
    }
    public BinaryTree(){
        root = null;
    }
    /*public BinaryTree(String rootValue, BinaryTree left, BinaryTree right){
        root = new Node(rootValue, left, right);
    } */

    public void insert(String data){
    	if(root==null){
    		root=new Node(data);
    	}else if(root.getData().compareToIgnoreCase(data)>0){
    		if(root.left()!=null){
    			BinaryTree vas = root.left();
    			vas.insert(data);
    		}else{
    			BinaryTree puu = new BinaryTree(data);
    			root.setLeft(puu);;
    		}

			System.out.println("vas");
		}else if(root.getData().compareToIgnoreCase(data)<0){
			if(root.right()!=null){
    			BinaryTree oikea = root.right();
    			oikea.insert(data);
    		}else{
    			BinaryTree puu = new BinaryTree(data);
    			root.setRight(puu);;
    		}
			System.out.println("oikea");
		}else{
			System.out.println("On jo olemassa Saatana!");
		}
    	balance();
    }



    public BinaryTree find(String data){
    	BinaryTree palautus=null;
    	if(root.getData().compareToIgnoreCase(data)==0){
    		palautus= this;
    	}else if(root.getData().compareToIgnoreCase(data)>0){
    		if(root.left()!=null){
    			BinaryTree puu = root.left();
        		palautus=puu.find(data);
    		}
    	}else if(root.getData().compareToIgnoreCase(data)<0){
    		if(root.right()!=null){
    			BinaryTree puu = root.right();
        		palautus=puu.find(data);
    		}
    	}
    	return palautus;

    }
    public void preOrder() {
        if (root != null) {
            System.out.println(root.getData()+',');
            if (root.left() != null) // pääseeekö vasemmalle?
                root.left().preOrder();
            if (root.right() != null) // pääseekö oikealle?
                root.right().preOrder();
        }

    }

    public void setLeft(BinaryTree tree) {
        root.setLeft(tree);
    }
    public void setRight(BinaryTree tree) {
        root.setRight(tree);
    }
    
    public void delete(String data){
    	BinaryTree asd=null;
    	String asd2;
    	if(root.getData().compareToIgnoreCase(data)==0){
    		if(root.right()!=null){
    			asd2=minValue(root.right());
    			delete(asd2);
        		root=new Node(asd2,root.left(),root.right());
    		}else if(root.left()!=null&&root.right()==null){
    			asd2=maxValue(root.left());
    			delete(asd2);
        		root=new Node(asd2,root.left(),root.right());
    		}
    		else{
    			System.out.println("Haista peruna!");
    		}
    	}else if(root.getData().compareToIgnoreCase(data)>0){
    		asd=root.left();
    		if(asd.root.getData().compareToIgnoreCase(data)==0&&asd.root.left()==null &&asd.root.right()==null){
    			root.setLeft(null);
    			asd=null;
    		}else if(asd.root.getData().compareToIgnoreCase(data)==0&&asd.root.left()!=null &&asd.root.right()==null){
        		root.setLeft(asd.root.left());
        	}else if(asd.root.getData().compareToIgnoreCase(data)==0&&asd.root.left()==null &&asd.root.right()!=null){
        		root.setLeft(asd.root.right());
        	}else{
        		asd.delete(data);
        	}
    	}else if(root.getData().compareToIgnoreCase(data)<0){
    		asd=root.right();
    		if(asd.root.getData().compareToIgnoreCase(data)==0&&asd.root.left()==null &&asd.root.right()==null){
    			root.setRight(null);
    			asd=null;
    		}else if(asd.root.getData().compareToIgnoreCase(data)==0&&asd.root.left()!=null &&asd.root.right()==null){
        		root.setRight(asd.root.left());
        	}else if(asd.root.getData().compareToIgnoreCase(data)==0&&asd.root.left()==null &&asd.root.right()!=null){
        		root.setRight(asd.root.right());
        	}else{
        		asd.delete(data);
        	}
    	}
    	balance();
    }
    public String minValue(BinaryTree puu){
    	String minv= puu.root.getData();
        while (puu.root.left() != null){
        	minv=puu.root.left().root.getData();
            puu = puu.root.left();
        }
        return minv;
    }
    public String maxValue(BinaryTree puu){
    	String maxv= puu.root.getData();
        while (puu.root.right() != null){
        	maxv=puu.root.right().root.getData();
            puu = puu.root.right();
        }
        return maxv;
    }
    public int height(){
    	int palautus=0;
    	if(root==null){
    		palautus=-1;
    	}else if(root.left()==null&&root.right()==null){
    		palautus=0;
    	}else if(root.left()!=null&&root.right()==null){
    		palautus=root.left().height()+1;
    	}else if(root.left()==null&&root.right()!=null){
    		palautus=root.right().height()+1;
    	}else{
    		if(root.left().height()<=root.right().height()){
    			palautus=root.right().height()+1;
    		}else{
    			palautus=root.left().height()+1;
    		}
    	}
    	return palautus;
    }
    public void balance(){
    	int left,right,compare;
    	String one,two;
    	if(root.left()!=null){
    		left=root.left().height();
    	}else{
    		left=0;
    	}
    	if(root.right()!=null){
    		right=root.right().height();
    	}else{
    		right=0;
    	}
    	if(left!=0&&right!=0){
    		compare=left%right;
    	}else if(left!=0&&right==0){
    		compare=left;
    	}else if(left==0&&right!=0){
    		compare=right;
    	}else{
    		compare=0;
    	}

    	if(left>right){
    		if(compare>1){
    			one=root.getData();
    			two=maxValue(root.left());
    			delete(two);
    			root=new Node(two,root.left(),root.right());
    			insert(one);
    		}
    	}else if(left<right){
    		if(compare>1){
    			one=root.getData();
    			two=minValue(root.right());
    			delete(two);
    			root=new Node(two,root.left(),root.right());
    			insert(one);
    		}
    	}
    }
}
