class Food{
	int type//0 = coffee, 7=drumstick, 8 = removed
	
	Food(t){
		type = t
	}

	def draw(screen, int x, int y){
		if(type==8)return
		screen.sprite(type, x, y, false, false, 1)
	}
	//Adding the flip for when the cursor is hovering over
	def draw(screen, int x, int y, flip){
		if(type == 8)return
		screen.sprite(type, x, y, flip, false, 1)
	}
}
