package core.runner;

import java.util.ArrayList;

import core.astar.AStar;
import core.astar.Node;
import core.problem.Problem;
import core.uitlity.Stopwatch;
import g11.problem.npuzzle.databaseRead;


public class SearchRunner {

	public SearchRunner() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		System.out.println("This is a test..");
		//获取n数码问题的所有实例
		ArrayList<Problem> problems = ProblemFactory.getProblems(1);
		test(problems);

		//获取滑动积木块问题的所有实例
//		ArrayList<Problem> problems2 = ProblemFactory.getProblems(2);
//		test(problems2);

	}
	
	//测试某个问题的所有实例
	public static void test(ArrayList<Problem> problems) {
		double time1 = 0;
		databaseRead.setHashInit("hashTable1.txt");

        String[] files = {"output1.txt","output2.txt","output3.txt","output4.txt"};
		databaseRead inread = new databaseRead(files);
		for (Problem problem : problems) {
			Stopwatch timer1 = new Stopwatch();
			if (!problem.solvable()) {
				System.out.println("No Solutions.");
			}
			else {
				AStar a = new AStar(problem);
				Node node = null;
				if(a.search()!=null){
					node = a.ansNode;
					System.out.println(node.getPathCost());
				}

				time1 = timer1.elapsedTime();
				if (node != null) a.solution(node);
			}
			
			System.out.printf("执行了 %.3f 秒\n", time1);
		}
	}
}
