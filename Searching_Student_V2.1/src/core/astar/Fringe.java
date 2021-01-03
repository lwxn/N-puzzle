package core.astar;

import core.problem.State;

import java.util.HashMap;
import java.util.PriorityQueue;

//The set of all leaf nodes available for expansion at any given point
public class Fringe {
	//��Fringe��ȡ����ɢֵ��С�Ľڵ�
	public Node pop() {
		nodeMap.remove(nodes.peek().getState().hashCode());
		return nodes.poll(); //Fix me
	}
	
	//��һ���ڵ���뵽Fringe��
	public void insert(Node node){
		//Fix me
		nodeMap.put(node.getState().hashCode(),node);
		nodes.add(node);
	}
	
	//�ж�Fringe���Ƿ����״̬Ϊstate�Ľڵ�
	public boolean contains(State state) {
		if(nodeMap.containsKey(state.hashCode())) return true;
		return false;
	}
	
	//����Fringe�У���״̬��state��ͬ�Ľڵ㣻��������ڣ��򷵻�null
	public Node revisited(State state) {
//		for (Node node : nodes)	{
//			if (node.getState().equals(state)) return node;
//		}
		if(nodeMap.containsKey(state.hashCode())) return nodeMap.get(state.hashCode());
		return null; //Fix me
	}
	
	//Fringe�Ƿ�Ϊ��
	public boolean isEmpty() {
		return nodes.isEmpty(); //Fix me
	}
		
	//�ýڵ�to����Finge�е�from�ڵ�
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
