import Food

class FoodChain extends leikr.Engine {

	int state = 0//0=title, 1=play, 2=gameover
	
	//title variables
	int blink = 0
	//end title variables
	
	//play variables
	int flipNext = 0
	//END play variables
	
	//Container variables
	def jar = []
	Food f 
	//END container variables
    void create(){
        loadImages()
    }
    void update(float delta){
        switch(state){
        	case 0:
        		if(keyPress("Space")){
        			state++
        			jar[0] = new Food(randInt(7),128+ (randInt(5)*16))
        		}
        		if(blink > 20) blink = 0
        		blink++
        		break;
        	case 1:
        		if(flipNext > 20) flipNext = 0
        		flipNext++
        		if(keyPress("Space")) jar += new Food(randInt(7),128+ (randInt(5)*16))
        		jar.each{
        			it.update()
        		}
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
        	case 1:
        		
        		image("bg",0,0)
        		jar.each{
        			it.draw(screen)
        		}
        		if(flipNext > 10){
        			sprite(3, 80, 16, false, false, 1)
        		}else{
        			sprite(3, 80, 16, true, false, 1)
        		}
        		
        		break;
        	case 2:
        	
        		break;
        }
    }
}

