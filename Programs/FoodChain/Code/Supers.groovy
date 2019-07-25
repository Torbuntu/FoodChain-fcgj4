class Supers {

	//We know that the col and row numbers are 6 and 8 from the FoodChain.groovy. And we will not change this.
	int row=8, col=8
    //USE MEAT SUPER. ta=type A, tb = type B
    def useSuper(jar, ta, tb){
    	int tmp = 0
    	for(int i = 0; i < col; i++){
    		for(int j = 0; j < row; j++){
    			if(jar[i][j].type == ta || jar[i][j].type == tb){
    				jar[i][j].type = 8
    				tmp++
    			}				
    		}
    	}
    	return tmp
    }
    
}
