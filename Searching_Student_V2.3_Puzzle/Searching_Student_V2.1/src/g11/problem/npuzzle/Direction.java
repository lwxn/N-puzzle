package g11.problem.npuzzle;

public enum Direction {
	LEFT,
	RIGHT,
	UP,
	DOWN;
	
	public String toString() {
        return super.toString().substring(0, 1);
    }
    //根据下标返回方向
    public static Direction getName(int index){
	    for(Direction d : Direction.values()){
	        if(d.ordinal() == index){
	            return d;
            }
        }
	    return null;
    }
}
