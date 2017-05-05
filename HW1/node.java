package HW1;
public class node {
		private int index;
		private int value;
		private node next;
		private node prev;
		public node(int index1,int value1,node p,node n) {
			index=index1;
			value=value1;
			next=n;
			prev=p;
		}
	public int getelement(){
		return value;
	}
	public int getindex(){
		return index;
	}
	public node getnext(){
		return next;
	}
	public void setnext(node n){
		next =n;
	}
	public node getprev(){
		return prev;
	}
	public void setprev(node p){
		prev =p;
	}
	public void setelement(int i){
		value=i;
	}
}

