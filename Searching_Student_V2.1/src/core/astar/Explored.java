package core.astar;

import core.problem.State;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//The set that remembers every expanded node
public class Explored {
    //Data Structures for Explored, implement it yourself.
    Set<Integer>hashSet = new HashSet<>();
    HashMap<Integer,Integer>minGn = new HashMap<>();

	public void insert(State state){
		hashSet.add(state.hashCode());
		//Fix me
	}
	
	public boolean contains(State state) {
		return hashSet.contains(state.hashCode()); //Fix me
	}

}

