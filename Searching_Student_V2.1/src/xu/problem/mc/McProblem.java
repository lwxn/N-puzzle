package xu.problem.mc;

import java.util.ArrayList;

import core.problem.Action;
import core.problem.Problem;
import core.problem.State;

public class McProblem extends Problem {

	@Override
	public State result(State parent, Action action) {
		int m = ((McState) parent).getM();
		int c = ((McState) parent).getC();
		
		int m1 = ((McAction) action).getM(); 
		int c1 = ((McAction) action).getC();
		int d = ((McAction) action).getD();
		
		if (d == 1) {	//���󰶻����Ұ�
			return new McState(m - m1, c - c1, 0).setSize(size); //���������٣��������Ұ�
		}
		else {	//���Ұ�������
			return new McState(m + m1, c + c1, 1).setSize(size); //���������࣬��������
		}
	}

	@Override
	public int stepCost(State parent, Action action) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int heuristic(State state) {		
		// TODO Auto-generated method stub
		
		McState s = (McState) state;
		return s.heuristic();
	}

	//���Ұ������ϵ���������£�����ʿҰ�˸����ֱ�Ϊm��cʱ���Ƿ�ȫ
	private boolean isSafe(int m, int c) {
		return m == 0 || m >= c;
	}
	
	@Override
	public ArrayList<Action> Actions(State state) {
		// TODO Auto-generated method stub
		
		ArrayList<Action> actions = new ArrayList<>();
		
		int m = ((McState) state).getM();
		int c = ((McState) state).getC();
		int b = ((McState) state).getB();	//���󰶻����Ұ���
		
		//��������Ұ���������Ұ�������
		if (b == 0) {
			m = size - m;
			c = size - c;
		}
		
		for (int i = 0; i <= m; i++)
			for (int j = 0; j <= c; j++) {
				if (i + j > 0 && i + j <= k && isSafe(i, j) && 
					isSafe(m - i, c - j) && 
					isSafe(size - m + i, size - c + j))
				{
					McAction action = new McAction(i, j, b); //�������򣬴����ڰ�����԰�
					actions.add(action);
				}
			}
		
		return actions;
	}
	
	public McProblem(int size, int k) {
		super(new McState(size, size, 1).setSize(size), new McState(0, 0, 0).setSize(size));
		this.size = size;
		this.k = k;
	}

	private int size;	//����ʿ��Ұ�˵ĸ���������Ĺ�ģ
	private int k;		//���Ͽ�������������
	
	@Override
	public boolean solvable() {
		// TODO Auto-generated method stub
		return true;
	}
}
