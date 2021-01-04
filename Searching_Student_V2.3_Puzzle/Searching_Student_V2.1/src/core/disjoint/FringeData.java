package core.disjoint;

import core.astar.Node;
import core.problem.State;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

//The set of all leaf nodes available for expansion at any given point
public class FringeData {
	//从Fringe中取出耗散值最小的节点
	public NodeData pop() {
		NodeData node = nodes.poll(); //Fix me
		int blankPos = node.getBlankpos();
		maps.remove(node.getZobristValue()+blankPos);
		//setNodes.remove(node.getZobristValue()+blankPos);
		return node;
	}
	
	//将一个节点插入到Fringe中
	public void insert(NodeData node){
		//Fix me
		nodes.add(node);
		int blankPos = node.getBlankpos();
		maps.put(node.getZobristValue()+blankPos,node);
	}
	
	//判断Finge中是否存在状态为state的节点
	public boolean contains(NodeData node) {
		int blankPos = node.getBlankpos();
		return maps.containsKey(node.getZobristValue()+blankPos);
	}

	//Fringe是否为空
	public boolean isEmpty() {
		return nodes.isEmpty(); //Fix me
	}
		
	//用节点to代替Finge中的from节点，前提是二者的State相同。
	public void replace(NodeData from, NodeData to) {
		nodes.remove(from);
		nodes.add(to);

		int blankPos = from.getBlankpos();
		maps.replace(from.getZobristValue()+blankPos,to);

		//Fix me
	}

	//返回Fringe中，其状态与state相同的节点；如果不存在，则返回null
	public NodeData revisited(NodeData state) {
		return maps.get(state.getZobristValue() + state.getBlankpos());
	}

	//Data Structures for Fringe, implement it yourself.
	private Queue<NodeData> nodes = new PriorityQueue<>();
	//private HashSet<Long> setNodes = new HashSet<>();
	private HashMap<Long,NodeData> maps = new HashMap<>();
}
