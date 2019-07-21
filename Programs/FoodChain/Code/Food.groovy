class Food{
	int type//0 = coffee, 7=drumstick

	boolean falling = true, control = true
	int jx = 128, jw = 208
	
	Food(t){
		type = t
	}
	def update(keyboard){

	}

	
	def checkBelow(ch){
//		if((x== ch.x) && y + 16 >= ch.y ){
//			falling = false
//			control = false
//		} 
	}
	
	def draw(screen, int x, int y){
		screen.sprite(type, x, y, false, false, 1)
	}
	def draw(screen, int x, int y, flip){
		screen.sprite(type, x, y, flip, false, 1)
	}
}
