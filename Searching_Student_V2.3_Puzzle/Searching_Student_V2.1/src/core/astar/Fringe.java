package core.astar;

import java.util.HashMap;
import java.util.PriorityQueue;

import core.problem.State;

//The set of all leaf nodes available for expansion at any given point
public class Fringe {
	//��Fringe��ȡ����ɢֵ��С�Ľڵ�
	public Node pop() {
		Node node = nodes.poll(); //Fix me
		maps.remove(node.getState().hashCode());
		return node;
	}
	
	//��һ���ڵ���뵽Fringe��
	public void insert(Node node){
		//Fix me
		nodes.add(node);
		maps.put(node.getState().hashCode(), node);
	}
	
	//�ж�Finge���Ƿ����״̬Ϊstate�Ľڵ�
	public boolean contains(State state) {
		return maps.containsKey(state.hashCode());
	}
	
	//����Fringe�У���״̬��state��ͬ�Ľڵ㣻��������ڣ��򷵻�null
	public Node revisited(State state) {
		return maps.get(state.hashCode());
	}
	
	//Fringe�Ƿ�Ϊ��
	public boolean isEmpty() {
		return nodes.isEmpty(); //Fix me
	}
		
	//�ýڵ�to����Finge�е�from�ڵ㣬ǰ���Ƕ��ߵ�State��ͬ��
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
