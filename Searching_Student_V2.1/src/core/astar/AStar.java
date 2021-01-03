package core.astar;

import core.problem.Action;
import core.problem.Problem;
import core.problem.State;

import java.util.Stack;

public class AStar {

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

    public Node search() {
        //起始节点
        State initState = problem.getInitialState();
        Node node = new Node(initState, null, null, 0, problem.heuristic(initState));

        Fringe fringe = new Fringe();
        fringe.insert(node);

        Explored explored = new Explored();

        while (true) {

            node = fringe.pop();
            if (problem.goalTest(node.getState())) return node;
            explored.insert(node.getState());
            for (Action action : problem.Actions(node.getState())) {
                Node child = childNode(node, action);
                if (!explored.contains(child.getState()) && !fringe.contains(child.getState())) {
                    fringe.insert(child);
                }
                else {
                    Node revisited = fringe.revisited(child.getState());
                    if (revisited != null && revisited.evaluation() > child.evaluation()) {
                        fringe.replace(revisited, child);
                    }
                }
            }
        }
    }




    //用动画展示问题的解路径
    public void solution(Node node) {
        /// Fix me
        Stack<Node> s = new Stack<>();
        s.push(node);

        int result = 0;
        while (node.getParent()!=null){
            node = node.getParent();
            s.push(node);
            result++;
        }

        while (!s.empty()){
            s.pop().draw();
        }
        System.out.println(result);
    }

    private Problem problem;
}
