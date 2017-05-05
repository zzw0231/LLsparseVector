package HW1;
public  class LLSparseVec implements SparseVec {
	private int length=0;
	private int nelem=0;
	public node head=null;
	public node tail=null;
        public LLSparseVec(int len){
        	length=len;
        	head=new node(-1,0,null,null);
        	tail=new node(len,0,head,null);
        	head.setnext(tail);
            return;
        }

	public int getLength() {
		return length;
	}
	public void sethead(node e){
		head=e;
	}
	public node gethead(){
		return head;
	}
	public node gettail(){
		return tail;
	}
	public void settail(node e){
		tail=e;
	}

	public int numElements() {
		int nelement=0;
		node current=head.getnext();
		for(int i=0;current.getelement()!=0;i++){
			nelement++;
			current=current.getnext();
		}
		return nelement;
	}

	@Override
	public int getElement(int idx) {
		node current=head.getnext();
		while(current.getindex()!=idx){
			current=current.getnext();
		}
		return current.getelement();
	}

	@Override
	public void clearElement(int idx) {
		node current=head;
		while(current!=null&&current.getindex()!=idx){
			current=current.getnext();
		}
		if(current!=null){
			current.setelement(0);	
			node temp=current.getprev();
			node temp2=current.getnext();
			temp.setnext(temp2);
			temp2.setprev(temp);	
		}	
	}

	@Override
	public void setElement(int idx, int val) {
		if(head.getnext().getelement()==0){
			node newnode=new node(idx,val,head,tail);
			head.setnext(newnode);
			tail.setprev(newnode);
		}
		else {
		node current=head.getnext();

		while(current.getindex()<idx){
			current=current.getnext();
		}
		if(current.getindex()==idx){
				node newest=new node(idx,val,current.getprev(),current.getnext());
				current.getprev().setnext(newest);
				current.getnext().setprev(newest);	
		
		}
		else if(current.getindex()>idx){
			if(head.getindex()>idx){	
				node newest=new node(idx,val,null,head);
				tail=head;
				head.setprev(newest);
				head=newest;	
				tail.setnext(null);
			}
			else{
			node temp=current.getprev();		
			node newest=new node(idx,val,temp,current);
			temp.setnext(newest);
			current.setprev(newest);
			}
		}
		else if(current.getindex()==length){
			node newest=new node(idx,val,tail.getprev(),tail);
			tail.getprev().setnext(newest);			
		}
		}
}
	


	@Override
	public int[] getAllIndices() {
		int [] array=new int[numElements()];
		int k=0;
		node current=head.getnext();
		while(current!=null&&current.getelement()!=0){
			array[k]=current.getindex();
			current=current.getnext();
			k++;
		}
		return array;
	}

	@Override
	public int[] getAllValues() {
		int [] array=new int[numElements()];
		int k=0;
		node current=head.getnext();
		while(current!=null&&current.getelement()!=0){
			array[k]=current.getelement();
			current=current.getnext();
			k++;
		}
		return array;
	}

	@Override
	public SparseVec addition(SparseVec otherV) {
		if(length!=otherV.getLength()){	
		return null;
		}
		LLSparseVec result=new LLSparseVec(length);
		node current=head.getnext();
		node other=otherV.gethead().getnext();
		int val=0;
		int idx=0;
		node temp;
		node tempcurr=new node(0,0,null,null);
		while(current.getindex()!=length||other.getindex()!=length){
			if((current.getindex()<other.getindex())||other.getindex()==otherV.getLength()){
				val=current.getelement();
				idx=current.getindex();
				current=current.getnext();
				}
			else if((current.getindex()>other.getindex())||current.getindex()==length){
				val=other.getelement();
				idx=other.getindex();
				other=other.getnext();
				}
			else if(current.getindex()==other.getindex()){
				val=current.getelement()+other.getelement();
				idx=current.getindex();
				current=current.getnext();
				other=other.getnext();
				}
			temp=new node(idx,val,null,null);
			if(result.gethead().getnext().getelement()==0&&val!=0){
				temp =new node(idx,val,result.gethead(),result.gettail());
				result.gethead().setnext(temp);
				result.gettail().setprev(temp);
				tempcurr=temp;
				}
			else if(result.gethead().getnext().getelement()!=0){	
				temp=new node(idx,val,tempcurr,result.gettail());
				tempcurr.setnext(temp);
				result.gettail().setprev(temp);
				tempcurr=temp;
				}					
		}
		return result;		
	}

	@Override
	public SparseVec substraction(SparseVec otherV) {
		if(length!=otherV.getLength()){	
			return null;
			}
			LLSparseVec result=new LLSparseVec(length);
			node current=head.getnext();
			node other=otherV.gethead().getnext();
			int val=0;
			int idx=0;
			node temp;
			node tempcurr=new node(0,0,null,null);
			while(current.getindex()!=length||other.getindex()!=length){
				if((current.getindex()<other.getindex())||other.getindex()==otherV.getLength()){
					val=current.getelement();
					idx=current.getindex();
					current=current.getnext();
					}
				else if((current.getindex()>other.getindex())||current.getindex()==length){
					val=-other.getelement();
					idx=other.getindex();
					other=other.getnext();
					}
				else if(current.getindex()==other.getindex()){
					val=current.getelement()-other.getelement();
					idx=current.getindex();
					current=current.getnext();
					other=other.getnext();
					}
				temp=new node(idx,val,null,null);
				if(result.gethead().getnext().getelement()==0&&val!=0){
					temp =new node(idx,val,result.gethead(),result.gettail());
					result.gethead().setnext(temp);
					result.gettail().setprev(temp);
					tempcurr=temp;
					}
				else if(result.gethead().getnext().getelement()!=0){	
					temp=new node(idx,val,tempcurr,result.gettail());
					tempcurr.setnext(temp);
					result.gettail().setprev(temp);
					tempcurr=temp;
					}					
			}
			return result;	
	}

	@Override
	public SparseVec multiplication(SparseVec otherV) {
		if(length!=otherV.getLength()){	
			return null;
			}
			LLSparseVec result=new LLSparseVec(length);
			node current=head.getnext();
			node other=otherV.gethead().getnext();
			int val=0;
			int idx=0;
			node temp;
			node tempcurr=new node(0,0,null,null);
			while(current.getindex()!=length||other.getindex()!=length){
				if((current.getindex()<other.getindex())||other.getindex()==otherV.getLength()){
					val=0;
					idx=current.getindex();
					current=current.getnext();
					System.out.println("1");
					}
				else if((current.getindex()>other.getindex())||current.getindex()==length){
					val=0;
					idx=other.getindex();
					other=other.getnext();
					System.out.println("2");
					}
				else if(current.getindex()==other.getindex()){
					val=current.getelement()*other.getelement();
					idx=current.getindex();
					current=current.getnext();
					other=other.getnext();
					System.out.println("3");
					}
				temp=new node(idx,val,null,null);
				if(result.gethead().getnext().getelement()==0&&val!=0){
					temp =new node(idx,val,result.gethead(),result.gettail());
					result.gethead().setnext(temp);
					result.gettail().setprev(temp);
					tempcurr=temp;
					System.out.println("4");
					}
				else if(result.gethead().getnext().getelement()!=0&&val!=0){	
					temp=new node(idx,val,tempcurr,result.gettail());
					tempcurr.setnext(temp);
					result.gettail().setprev(temp);
					tempcurr=temp;
					System.out.println("5");
					}					
			}
			return result;	
	}

}
