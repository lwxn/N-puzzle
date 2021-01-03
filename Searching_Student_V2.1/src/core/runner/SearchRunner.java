package core.runner;

import java.util.ArrayList;
import java.util.Arrays;

import core.astar.AStar;
import core.astar.Node;
import core.problem.Problem;
import core.uitlity.Stopwatch;
import xu.problem.block.Zobrist;


public class SearchRunner {

	public SearchRunner() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		System.out.println("This is a test..");
		//��ȡn�������������ʵ��
		ArrayList<Problem> problems = ProblemFactory.getProblems(1);
		test(problems);

		//��ȡ������ľ�����������ʵ��
		Zobrist z = new Zobrist(12);
		ArrayList<Problem> problems2 = ProblemFactory.getProblems(2);
		test(problems2);
//
	}
	
	//����ĳ�����������ʵ��
	public static void test(ArrayList<Problem> problems) {
		double time1 = 0;

		for (Problem problem : problems) {
			Stopwatch timer1 = new Stopwatch();
			if (!problem.solvable()) {
				System.out.println("No Solutions.");
			}
			else {
				AStar a = new AStar(problem);
				Node node = a.search();
				System.out.println(node.getPathCost());
				//a.solution(node);
				time1 = timer1.elapsedTime();
				if (node != null) a.solution(node);
			}

			System.out.printf("ִ���� %.3f ��\n", time1);
		}
	}
}
