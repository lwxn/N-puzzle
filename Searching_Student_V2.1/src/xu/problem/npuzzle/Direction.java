package xu.problem.npuzzle;

public enum Direction {
	LEFT,
	RIGHT,
	UP,
	DOWN;
	
	public String toString() {
        return super.toString().substring(0, 1);
    }
    public static Direction getName(int index){
	    for(Direction d : Direction.values()){
	        if(d.ordinal() == index){
	            return d;
            }
        }
	    return null;
    }
}
