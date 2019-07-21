class Food{
	int type//0 = coffee, 7=drumstick
	int x,y
	Food(r,z){
	println "$r, $z"
		y = 8
		x = z
		type = r
	}
	
	def update(){
		if(y < 128 ) y++
	}
	
	def draw(screen){
		screen.sprite(type, x, y, false, false, 1)
	}
}
