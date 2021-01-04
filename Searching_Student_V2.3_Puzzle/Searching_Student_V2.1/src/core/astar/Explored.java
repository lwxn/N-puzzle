package core.astar;

import java.util.HashMap;

import core.problem.State;

//The set that remembers every expanded node
public class Explored {
	
	public void insert(State state){
		maps.put(state.hashCode(), state);
		//Fix me
	}
	
	public boolean contains(State state) {
		return maps.containsKey(state.hashCode()); //Fix me
	}
	
	private HashMap<Integer, State> maps = new HashMap<>();
	//ArrayList<State> nodes = new ArrayList<>(100000);
	//Data Structures for Explored, implement it yourself.
}
