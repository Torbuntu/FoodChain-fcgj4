import Food
import Supers

class FoodChain extends leikr.Engine {

	int state = 0//0=title, 1=instructions, 2=Game play, 3=gameover
	int bSpeed = 0
	//title variables
	int blink = 0
	//end title variables
	
	//play variables
	int flipNext = 0
	boolean select = false
	int megaScore = 0 // used for advancing to next level, 96 = max
	int level = 0 //might settle on 8 levels
	int available = 0//a meter of available moves
	int row=8, col=6
	def jar = new Food[col][row]
	int fruits= 0, veggies = 0, meats = 0, drinks = 0
	boolean fSuper = false, vSuper = false, mSuper = false, dSuper = false
	Supers supers = new Supers()
	
	//cursor x, y and flash
	int cx=0, cy=0, cf = 0
	//END play variables
	
	def init(){
		state = 0//0=title, 1=instructions, 2=Game play, 3=gameover
		bSpeed = 0
		//title variables
		blink = 0
		//end title variables
		
		//play variables
		flipNext = 0
		select = false
		megaScore = 0 // used for advancing to next level, 96 = max
		level = 0 //might settle on 8 levels

		row=8
		col=6
		jar = new Food[col][row]
		fruits= 0
		veggies = 0
		meats = 0
		drinks = 0
		fSuper = false
		vSuper = false
		mSuper = false
		dSuper = false
		Supers supers = new Supers()
		
		//cursor x, y and flash
		cx=0
		cy=0
		cf = 0
		//END play variables
	}
	
    void create(){
        loadImages()
    }
    void update(float delta){
    	bSpeed++
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
        		if(blink > 20) blink = 0
        		blink++
        		if(keyPress("Space") || button(BTN.SELECT) && bSpeed > 5){
        			bSpeed = 0
        			state++
        		}
        	case 2://Game play
        		if(flipNext > 20) flipNext = 0
        		if(cf > 20) cf = 0
        		cf++
        		flipNext++
        		
        		//CHECL AVAILABLE MOVES
        		if(!movesExist()){
        			state=3//game over
        		}
        		
        		//START STATE 1 INPUT
        		handleInput()
        		//START STATE 1 UPDATE DROP
        		updateDrop()
        		
        		//CHECK LVL UP
        		if(megaScore >= 96){
        			megaScore = 0
        			level++
        			if(level==8) state = 4
        		}
        		break;
        	case 3:
        		if(keyPress("Space")){
        			init()
        		}
        		break;
        	case 4: //YOU WIN
        		if(keyPress("Space")){
        			init()
        		}
        		break;
        }
    }
    void render(){	
		switch(state){
        	case 0:
        		image("title",0,0)
        		if(blink > 10){
        			text("Press Space/Select", 58, 124, 120, 1, 16)
        		}else{
        			text("Press Space/Select", 58, 124, 120, 1, 32)
        		}
    			
        		break;
        	case 1:
        		text("Instructions:\nArrows/D-Pad: Move cursor\nA: Select target\nX, Y, Z, or B: Use super", 0,0,32)
        		if(blink > 10){
        			text("Press Space/Select", 58, 124, 120, 1, 16)
        		}else{
        			text("Press Space/Select", 58, 124, 120, 1, 32)
        		}
        		break;
        	case 2://game play
        		//background image. Obvi
        		image("bg",0,0)
        		
        		//Draws the items in the jar
        		for(int i = 0; i < col; i++){
	    			for(int j = 0; j < row; j++){
	    				if(i ==cx && j==cy){
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
	    		drawCursor()
	    		
	    		//Draw container scores
				drawScores()
	    		
	    		//Draw megaScore
	    		drawColor(23)
	    		
	    		rect(128, 7, megaScore, 4, true)
	    		
	    		//draw available
	    		rect(230, 143, 2, -available*4, true)
	    		
	    		//temp
	    		text("Level: $level", 0, 150, 32)
        		
        		break;
        	case 3:// Gameover condition
        		text("GAME OVER", 0, 0, 32)
        		break;
        		
    		case 4: //YOU WIN
    			text("YOU WON! Press Space/Select to play again!", 0, 0, 32)
    			break;
        }
    }
    
    def movesExist(){
    	def match = 0
    	for(int i = 0; i < col; i++){
			for(int j = 0; j < row; j++){
				int tp = jar[i][j].type
				if((i > 0 && jar[i-1][j].type != tp || i == 0) 
						&& (i < col-1 && jar[i+1][j].type != tp || i == 5) 
						&& (j > 0 && jar[i][j-1].type != tp || j == 0) 
						&& (j< row-1 && jar[i][j+1].type != tp || j == 7) ) {
							//no matches yet
				}else{
					match++
				}
			}
		}
	    println ("Pairs found: $match")
	    available = match
		if(match == 0 && !mSuper && !vSuper && !fSuper && !dSuper) return false//No moves
		return true
    }
    
    def checkMatches(){
    	def tp = jar[cx][cy].type//temp type

 		//If no matches on any sides in middle.
	    if(	(cx > 0 && jar[cx-1][cy].type != tp || cx == 0) 
	    	&& (cx < col-1 && jar[cx+1][cy].type != tp || cx == 5) 
	    	&& (cy > 0 && jar[cx][cy-1].type != tp || cy == 0) 
	    	&& (cy < row-1 && jar[cx][cy+1].type != tp || cy == 7) ) return

	    
    	jar[cx][cy].type = 8
    	
    	int score = -3//sets the score correctly for the i variable offset
    	
    	//Scan the x axis (horiz)
    	int i = 1
    	while(cx-i >= 0){
    		if(jar[cx-i][cy].type == tp){
    			jar[cx-i][cy].type = 8
    		} else{
    			break
    		}
    		i++
    	}
    	score += i
    	i = 1
    	while(cx+i < col){
    		if(jar[cx+i][cy].type == tp) {
    			jar[cx+i][cy].type = 8
    		}else{
    			break
    		}
    		i++
    	}
    	score += i
    	//Scan the y axis (verti)
    	i = 1
    	while(cy-i >= 0){
    		if(jar[cx][cy-i].type == tp){
    			jar[cx][cy-i].type = 8
    		} else{
    			break
    		}
    		i++
    	}
    	score += i
    	i = 1
    	while(cy+i < row){
    		if(jar[cx][cy+i].type == tp) {
    			jar[cx][cy+i].type = 8
    		}else{
    			break
    		}
    		i++
    	}
    	
    	//Update the score and megaScore
    	score += i
    	megaScore+=i
    	
    	//if drink type
    	if(tp == 0 || tp == 1) {
    		drinks += score
    		if(drinks >= 41){
    			drinks = 41
    			dSuper = true
    		} 
    	}
    	//if veggie type
    	if(tp == 2 || tp == 3) {
    		veggies += score
    		if(veggies >= 41) {
    			veggies = 41
    			vSuper = true
    		}
    	}
    	//if fruit type
    	if(tp == 4 || tp == 5) {
    		fruits += score
    		if(fruits >= 41) {
    			fruits = 41
    			fSuper = true
    		}
    	}
    	//if meat type
    	if(tp == 6 || tp == 7) {
    		meats += score
    		if(meats >= 41) {
    			meats = 41
    			mSuper = true
    		}
    	}

    }
    
    //STATE 1 INPUT 
    def handleInput(){
    	//Debug
		if(keyPress("Space")) {
			for(int i = 0; i < col; i++){
				for(int j = 0; j < row; j++){
					jar[i][j] = new Food(randInt(7))
				}
			}
		}   
		
		if(keyPress("C")){
			checkAvailableMoves()
		}
		//End debug
		
		//Check for Super usage input
		if(mSuper && (keyPress("X") || button(BTN.X) && bSpeed > 5)){
			bSpeed = 0
			mSuper = false
			megaScore += supers.useSuper(jar, 6, 7)
			meats = 0
		}
		if(vSuper && (keyPress("Y") || button(BTN.X) && bSpeed > 5)){
			bSpeed = 0
			vSuper = false
			megaScore += supers.useSuper(jar, 2, 3)
			veggies = 0
		}
		if(fSuper && (keyPress("Z") || button(BTN.X) && bSpeed > 5)){
			bSpeed = 0
			fSuper = false
			megaScore += supers.useSuper(jar, 4, 5)
			fruits = 0
		}
		if(dSuper && (keyPress("B") || button(BTN.X) && bSpeed > 5)){
			bSpeed = 0
			dSuper = false
			megaScore += supers.useSuper(jar, 0, 1)
			drinks = 0
		}
		//Cursor movement
		if(!select){
			if((keyPress("Right") || button(BTN.RIGHT) && bSpeed > 5) && cx < 5) {
				cx ++
				bSpeed = 0
			}
			if((keyPress("Left")|| button(BTN.LEFT) && bSpeed > 5) && cx > 0) {
				cx --
				bSpeed = 0
			}
			if((keyPress("Up") || button(BTN.UP) && bSpeed > 5) && cy > 0) {
				cy--
				bSpeed = 0
			}
			if((keyPress("Down")|| button(BTN.DOWN) && bSpeed > 5) && cy < 7){
				cy++
				bSpeed = 0
			} 
			if((keyPress("A")|| button(BTN.A) && bSpeed > 5)) {
				select = true
				bSpeed = 0
			}
		}else{			
			checkMatches()
			
			select = false
		}
    }
    //END STATE 1 INPUT
    
    //STATE 1 UPDATE DROP
    //updateDrop checks every position to see if an item can be dropped a row.
    def updateDrop(){
    	if(jar[0][0].type == 8){
    		jar[0][0] = new Food(randInt(7))
    	}
    	for(int i = 0; i < col; i++){
    		if(jar[i][0].type==8) jar[i][0] = new Food(randInt(7))
    		for(int j = 0; j < row; j++){
    			if(j==row)return
				if(j+1 < row && jar[i][j].type!=8 && jar[i][j+1].type==8){
					def tmp = jar[i][j]
					jar[i][j] = jar[i][j+1]
					jar[i][j+1] = tmp   
				}
    		}
    	}
    	
    }
    //END STATE 1 UPDATE DROP
    
    //Draw cursor
    def drawCursor(){
    	if(cf>10){
			drawColor(21)
			rect((int)(128+cx*16), (int)(16+cy*16), 16, 16)
			
			if(meats == 41)
				spriteSc(65, 29, 55, 0)
			if(veggies == 41)
				spriteSc(66, 52, 55, 0)
			if(fruits == 41)
				spriteSc(67, 77, 55, 0)
			if(drinks == 41)
				spriteSc(68, 101, 55, 0)
		}else{
			drawColor(24)
			rect((int)(128+cx*16), (int)(16+cy*16), 16, 16)
			
			if(meats == 41)
				spriteSc(65, 29, 55, 0.5f)
			if(veggies == 41)
				spriteSc(66, 52, 55, 0.5f)
			if(fruits == 41)	
				spriteSc(67, 77, 55, 0.5f)
			if(drinks == 41)
				spriteSc(68, 101, 55, 0.5f)
		}
    }
    //Draw the container scores:
    def drawScores(){
		drawColor(23)
		rect(24, 48, 16, -meats,   true)//meats
		drawColor(15)
		rect(48, 48, 16, -veggies, true)//veggies
		drawColor(1)
		rect(72, 48, 16, -fruits,  true)//fruits
		drawColor(32)
		rect(96, 48, 16, -drinks,  true)//drinks
    }

}

