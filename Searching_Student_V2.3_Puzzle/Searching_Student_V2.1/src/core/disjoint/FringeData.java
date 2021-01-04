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
	//��Fringe��ȡ����ɢֵ��С�Ľڵ�
	public NodeData pop() {
		NodeData node = nodes.poll(); //Fix me
		int blankPos = node.getBlankpos();
		maps.remove(node.getZobristValue()+blankPos);
		//setNodes.remove(node.getZobristValue()+blankPos);
		return node;
	}
	
	//��һ���ڵ���뵽Fringe��
	public void insert(NodeData node){
		//Fix me
		nodes.add(node);
		int blankPos = node.getBlankpos();
		maps.put(node.getZobristValue()+blankPos,node);
	}
	
	//�ж�Finge���Ƿ����״̬Ϊstate�Ľڵ�
	public boolean contains(NodeData node) {
		int blankPos = node.getBlankpos();
		return maps.containsKey(node.getZobristValue()+blankPos);
	}

	//Fringe�Ƿ�Ϊ��
	public boolean isEmpty() {
		return nodes.isEmpty(); //Fix me
	}
		
	//�ýڵ�to����Finge�е�from�ڵ㣬ǰ���Ƕ��ߵ�State��ͬ��
	public void replace(NodeData from, NodeData to) {
		nodes.remove(from);
		nodes.add(to);

		int blankPos = from.getBlankpos();
		maps.replace(from.getZobristValue()+blankPos,to);

		//Fix me
	}

	//����Fringe�У���״̬��state��ͬ�Ľڵ㣻��������ڣ��򷵻�null
	public NodeData revisited(NodeData state) {
		return maps.get(state.getZobristValue() + state.getBlankpos());
	}

	//Data Structures for Fringe, implement it yourself.
	private Queue<NodeData> nodes = new PriorityQueue<>();
	//private HashSet<Long> setNodes = new HashSet<>();
	private HashMap<Long,NodeData> maps = new HashMap<>();
}
