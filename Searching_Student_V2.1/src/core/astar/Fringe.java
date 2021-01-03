package core.astar;

import core.problem.State;

import java.util.HashMap;
import java.util.PriorityQueue;

//The set of all leaf nodes available for expansion at any given point
public class Fringe {
	//从Fringe中取出耗散值最小的节点
	public Node pop() {
		nodeMap.remove(nodes.peek().getState().hashCode());
		return nodes.poll(); //Fix me
	}
	
	//将一个节点插入到Fringe中
	public void insert(Node node){
		//Fix me
		nodeMap.put(node.getState().hashCode(),node);
		nodes.add(node);
	}
	
	//判断Fringe中是否存在状态为state的节点
	public boolean contains(State state) {
		if(nodeMap.containsKey(state.hashCode())) return true;
		return false;
	}
	
	//返回Fringe中，其状态与state相同的节点；如果不存在，则返回null
	public Node revisited(State state) {
//		for (Node node : nodes)	{
//			if (node.getState().equals(state)) return node;
//		}
		if(nodeMap.containsKey(state.hashCode())) return nodeMap.get(state.hashCode());
		return null; //Fix me
	}
	
	//Fringe是否为空
	public boolean isEmpty() {
		return nodes.isEmpty(); //Fix me
	}
		
	//用节点to代替Finge中的from节点
	public void replace(Node from, Node to) {
		nodes.remove(from);
		nodes.add(to);
		//Fix me
		nodeMap.remove(from);
		nodeMap.put(to.getState().hashCode(),to);
	}
	
	//Data Structures for Fringe, implement it yourself.
	PriorityQueue<Node> nodes = new PriorityQueue<>();
	//HashMap<Node,Integer> nodeMap = new HashMap<>();
	HashMap<Integer,Node> nodeMap = new HashMap<>();
}
