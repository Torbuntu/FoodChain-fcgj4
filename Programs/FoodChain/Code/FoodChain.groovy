import Food

class FoodChain extends leikr.Engine {

	int state = 0//0=title, 1=play, 2=gameover
	
	//title variables
	int blink = 0
	//end title variables
	
	//play variables
	int flipNext = 0
	boolean select = false
	//END play variables
	
	//Container variables
	int row=8, col=6
	def jar = new Food[col][row]
	
	//cursor x, y and flash
	int cx=0, cy=0, cf = 0
	//END container variables
	
    void create(){
        loadImages()
    }
    void update(float delta){
        switch(state){
        	case 0:
    			if(keyPress("Space")) {
    				state++
        			for(int i = 0; i < col; i++){
		    			for(int j = 0; j < row; j++){
		    				jar[i][j] = new Food(randInt(7))
		    			}
		    		}
        		}
        		if(blink > 20) blink = 0
        		blink++
        		break;
        	case 1:
        		if(flipNext > 20) flipNext = 0
        		if(cf > 20) cf = 0
        		cf++
        		flipNext++
        		if(keyPress("Space")) {
        			for(int i = 0; i < col; i++){
		    			for(int j = 0; j < row; j++){
		    				jar[i][j] = new Food(randInt(7))
		    			}
		    		}
        		}     
        		   		
        		//Cursor movement
        		if(!select){
		    		if(keyPress("Right") && cx < 5) cx ++
		    		if(keyPress("Left") && cx > 0) cx--
		    		if(keyPress("Up") && cy > 0) cy--
		    		if(keyPress("Down") && cy < 7) cy++
		    		if(keyPress("Enter")) select = true
		    		
		    		if(mouseClick(0)){
		    			select = true
		    			cx=(mouseX()-128)/16 as int
		    			cy = (mouseY()-16)/16 as int
		    		}
        		}else{
        			if(keyPress("Enter")) select = false
        			if(keyPress("Right") && cx < 5) {
        				def tmp = jar[cx][cy]
        				jar[cx][cy] = jar[cx+1][cy]
        				jar[cx+1][cy] = tmp        
        				cx++				
        			}
		    		if(keyPress("Left") && cx > 0) {
		    			def tmp = jar[cx][cy]
        				jar[cx][cy] = jar[cx-1][cy]
        				jar[cx-1][cy] = tmp   
        				cx--
		    		}
		    		if(keyPress("Up") && cy > 0) {
		    			def tmp = jar[cx][cy]
        				jar[cx][cy] = jar[cx][cy-1]
        				jar[cx][cy-1] = tmp   
        				cy--
		    		}
		    		if(keyPress("Down") && cy < 7){
		    			def tmp = jar[cx][cy]
        				jar[cx][cy] = jar[cx][cy+1]
        				jar[cx][cy+1] = tmp   
        				cy++
		    		}
        		}
        		if(keyPress("T"))
	        		checkMatches()

        		break;
        	case 2:
        	
        		break;
        }
    }
    void render(){	
		switch(state){
        	case 0:
        		image("title",0,0)
        		if(blink > 10){
        			text("Press Space", 64, 124, 16)
        		}else{
        			text("Press Space", 64, 124, 32)
        		}
    			
        		break;
        	case 1://game
        		
        		image("bg",0,0)
        		
        		//Draws the items in the jar
        		for(int i = 0; i < col; i++){
	    			for(int j = 0; j < row; j++){
	    				if(select && i ==cx && j==cy){
	    					if(cf > 10){
	    						jar[i][j].draw(screen,(int)(128+ i*16),(int)(16+j*16), true)
	    					}else{
	    						jar[i][j].draw(screen,(int)(128+ i*16),(int)(16+j*16), false)
	    					}	    				
	    				}else{    				
		    				jar[i][j].draw(screen,(int)(128+ i*16),(int)(16+j*16))
	    				}
	    			}
	    		}
	    		
	    		//draw cursor
	    		if(cf>10){
	    			drawColor(21)
	    			rect((int)(128+cx*16), (int)(16+cy*16), 16, 16)
	    		}else{
	    			drawColor(24)
	    			rect((int)(128+cx*16), (int)(16+cy*16), 16, 16)
	    		}
	    		
        		if(flipNext > 10){
        			sprite(3, 80, 16, false, false, 1)
        		}else{
        			sprite(3, 80, 16, true, false, 1)
        		}
        		
        		//mouse
        		sprite(64, mouseX() as int, mouseY() as int)
        		break;
        	case 2:
        	
        		break;
        }
    }
    
    
    def checkMatches(){
    	def count = 0
    	println("Checking matches...")
    	for(int i = 0; i < col-2; i++){
    		for(int j = 0; j < row; j++){
				if(jar[i][j].type == jar[i+1][j].type && jar[i+1][j].type == jar[i+2][j].type){
					count++
				}
				println("Type: " + jar[i][j].type)
    		}
    	}
    	println("Finished. Final matches: $count")
    }
}

