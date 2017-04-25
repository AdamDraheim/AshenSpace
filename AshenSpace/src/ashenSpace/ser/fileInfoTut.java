package ashenSpace.ser;

import java.io.Serializable;

public class fileInfoTut implements Serializable{

	boolean complete;
	
	public fileInfoTut(boolean complete){
		
		this.complete = complete;
		
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	
	
}
