package core.astar;


import core.problem.Action;
import core.problem.Problem;
import core.problem.State;
import g11.problem.npuzzle.NPuzzle;

import java.util.Stack;

public class AStar {
	
	static final int INFTY = Integer.MAX_VALUE;
	private int ans = 0;//是否找到goal
	public static int re = 0;
	public Node ansNode;//目标结点
	
	public AStar(Problem problem) {
		super();
		this.problem = problem;
	}
	
	public Node childNode(Node parent, Action action) {
		State state = problem.result(parent.getState(), action);
		int pathCost = parent.getPathCost() + problem.stepCost(parent.getState(), action);

		int heuristic = problem.heuristic(state);

		return new Node(state, parent, action, pathCost, heuristic);
	}
	
	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}



	public int IDA(Node node,int g,int threshold,int pre){
		int f = g+node.getHeuristic();
		if(f>threshold) return  f;
		else if(node.getState().equals(problem.getGoal())){
			ans = 1;
			ansNode = node;
			return 0;
		}
		int min = INFTY;
		for(Action action : problem.Actions(node.getState())){
			if(action.getValue() == (pre^1)) continue;
			Node child = childNode(node,action);
			int temp = IDA(child,child.getPathCost(),threshold,action.getValue());
			if(ans == 1) {
				return 0;
			}
			if(temp<min) min = temp;
		}
		return min;
	}

	public Node search(){
		//起始节点
		State initState = problem.getInitialState();
		Node node = new Node(initState,null,null,0,problem.heuristic(initState));
		int threshold = node.getHeuristic();
		while (true){
			int temp = IDA(node,0,threshold,-100);
			if(ans == 1){
				return ansNode;
			}
			else if(temp == INFTY) return null;
			threshold = temp;
		}
	}
	
	//展示问题的解路径
	public void solution(Node node)
	{		
		// Fix me
		Stack<Node>s = new Stack<>();
		s.push(node);

		int result = 0;
		while (node.getParent()!=null){
			node = node.getParent();
			s.push(node);
			result++;
		}

		while (!s.empty()){
			Node a = s.pop();
			a.draw();
		}
		System.out.println(result);
	}
		
	private Problem problem;

	public static void main(String[] args) {
		byte s[] = {6,10,12,2,8,5,4,15,1,7,14,3,9,13,0,11};//{1,6,2,3,10,0,8,4,5,9,7,12,13,14,11,15};//{4, 10, 6, 13, 12, 15, 0, 2, 8, 9, 7, 3, 14, 5, 1, 11};//////{6,4,7,8,5,0,3,2,1};//{1,2,3,4,5,6,7,0,8};//////{2,9,5,11,8,3,4,14,7,10,1,12,0,15,6,13};//{8,13,0,6,1,15,9,14,3,4,5,11,7,2,10,12};//////{1,2,3,4,5,6,7,0,8};//////{8,6,7,2,5,4,3,0,1};
		//byte s1[] = {1,2,3,4,5,6,7,8,0};////////

		NPuzzle problem2 = new NPuzzle(4,s);
		State initState = problem2.getInitialState();
		int result = problem2.heuristic(initState);
//		initState.draw();
//		System.out.println(result);databaseRead
		Node node = new Node(initState,null,null,0,problem2.heuristic(initState));

		AStar a = new AStar(problem2);
		int startTime = (int)System.currentTimeMillis();
		Node t = a.search();
		a.solution(a.ansNode);
//		if(a.search()){
//			a.ansNode.draw();
//			a.solution(a.ansNode);
//		}

		int endTime = (int)System.currentTimeMillis();
		System.out.println((1.0*(endTime-startTime))/1000);
	}
}
