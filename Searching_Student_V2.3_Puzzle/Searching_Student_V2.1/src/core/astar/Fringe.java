package core.astar;

import java.util.HashMap;
import java.util.PriorityQueue;

import core.problem.State;

//The set of all leaf nodes available for expansion at any given point
public class Fringe {
	//从Fringe中取出耗散值最小的节点
	public Node pop() {
		Node node = nodes.poll(); //Fix me
		maps.remove(node.getState().hashCode());
		return node;
	}
	
	//将一个节点插入到Fringe中
	public void insert(Node node){
		//Fix me
		nodes.add(node);
		maps.put(node.getState().hashCode(), node);
	}
	
	//判断Finge中是否存在状态为state的节点
	public boolean contains(State state) {
		return maps.containsKey(state.hashCode());
	}
	
	//返回Fringe中，其状态与state相同的节点；如果不存在，则返回null
	public Node revisited(State state) {
		return maps.get(state.hashCode());
	}
	
	//Fringe是否为空
	public boolean isEmpty() {
		return nodes.isEmpty(); //Fix me
	}
		
	//用节点to代替Finge中的from节点，前提是二者的State相同。
	public void replace(Node from, Node to) {
		nodes.remove(from);
		nodes.add(to);
		maps.put(to.getState().hashCode(), to);
//		maps.put(Pair<to.getState().hashCode(), ((PuzzleState)to.getState()).getBlankPos()>,to);
		//Fix me
	}
	
	//Data Structures for Fringe, implement it yourself.
	private PriorityQueue<Node> nodes = new PriorityQueue<>();
	private HashMap<Integer, Node> maps = new HashMap<>();
//	private HashMap<Pair<Integer,Integer>, Node> maps = new HashMap<>();
}
