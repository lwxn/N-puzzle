/**
 * 
 */
package g11.problem.npuzzle;

import java.util.ArrayList;
import java.util.Arrays;

import core.problem.Action;
import core.problem.Problem;
import core.problem.State;
import core.uitlity.Zobrist;

/**
 * @author Jianliang
 *
 */
public class NPuzzle extends Problem {
	public NPuzzle() {
		super();
	}

	public NPuzzle(State initialState, State goal, int side) {
		super(initialState, goal);
		this.side = side;
		zobrist = new Zobrist(side*side,side*side);
		((PuzzleState)initialState).setZobrist(zobrist.hash(((PuzzleState) initialState).getStatus()));
		((PuzzleState)goal).setZobrist(zobrist.hash(((PuzzleState) goal).getStatus()));
	}

	public NPuzzle(int side, byte[] status) {
		this(new PuzzleState(side, status), defaultGoal(side), side);
	}
	
	public NPuzzle(int side, byte[] status,int value) {
		this(new PuzzleState(side, status,value), defaultGoal(side), side);
	}

	//默认的目标结点
	public static PuzzleState defaultGoal(int side) {
		byte[] status = new byte[side * side];
		for (byte i = 0; i < side * side - 1; i++)
			status[i] = (byte) (i + 1);
		status[side * side - 1] = 0;
		PuzzleState goal = new PuzzleState(side, status);
 		return goal;
	}



	/* (non-Javadoc)
	 * @see core.problem.Problem#result(core.problem.State, core.problem.Action)
	 * 根据方向得到下一个新的状态
	 */
	@Override
	public State result(State parent, Action action) {
        int empty = ((PuzzleState)parent).getBlankPos();
        int dir = ((PuzzleAction)action).dir.ordinal();//l r u d
        int n = this.side;
        byte [] s = Arrays.copyOf(((PuzzleState)parent).getStatus(),n*n);
		int value = ((PuzzleState)parent).getManhattan();//parent的曼哈顿距离

        int sx =  empty/n;    //空格的位置
        int sy = empty%n;

        int tx = sx + dx[dir];//变化后的位置
        int ty = sy + dy[dir];

		byte tmp = s[tx*n+ty];
		value -= Math.abs((tmp-1)/n - tx) + Math.abs((tmp-1)%n-ty);//Mahantun变换


		//数组Value互换
        byte temp = s[sx*n + sy];
        s[sx*n + sy] = s[tx*n + ty];
        s[tx*n + ty] = temp;


		tmp = s[sx*n+sy];
		value += Math.abs((tmp-1)/n - sx) + Math.abs((tmp-1)%n-sy);//Mahantun变换

		PuzzleState result = new PuzzleState(n,s,value);
		result.setZobrist(zobrist.newHash(((PuzzleState) parent).getZobrist(),tx*n+ty,tmp,empty,(byte) 0));
		return result;

	}

	/* (non-Javadoc)
	 * @see core.problem.Problem#stepCost(core.problem.State, core.problem.Action)
	 */
	@Override
	public int stepCost(State parent, Action action) {
		// TODO Auto-generated method stub
		return 1;
	}
	
	/* (non-Javadoc)
	 * @see core.problem.Problem#heuristic(core.problem.State)
	 * Hn函数,曼哈顿距离
	 */
	@Override
	public int heuristic(State state) {
		if(side == 3){
			return ((PuzzleState)state).getManhattan();
		}
		else{
			return ((PuzzleState)state).getDisjointValue();
		}
	}

	/* (non-Javadoc)
	 * @see core.problem.Problem#Actions(core.problem.State)
	 * 此坐标是否超出范围
	 */
	public boolean IsSafe(int x,int y){
	    return x>=0&&x<this.side&&y>=0&&y<this.side;
    }

	/**
	 *
	 * @param state 现在的状态
	 * @return 所有可能的下一步动作
	 */
	@Override
	public ArrayList<Action> Actions(State state) {
		// TODO Auto-generated method stub
        ArrayList<Action>arrayList =  new ArrayList<>();
        byte s[] = ((PuzzleState)state).getStatus();
        int n = this.side;
        int sx = ((PuzzleState)state).getBlankPos()/this.side;
        int sy = ((PuzzleState)state).getBlankPos()%this.side;

        //东南西北
        for(int i = 0;i<4;i++){
            int tx = sx + dx[i];
            int ty = sy + dy[i];

            if(IsSafe(tx,ty)){
                arrayList.add(new PuzzleAction(tx*side + ty,Direction.getName(i)));
            }
        }
		return arrayList;
	}
	
	private int side;	//边长：3, 4
	
	private Zobrist zobrist;

	private static int dx[] = {0,0,-1,1};//x方向
	private static int dy[] = {-1,1,0,0};//y方向

	@Override
	public boolean solvable() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * test
	 * @param args
	 */
	public static void main(String[] args) {

		byte s[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
		PuzzleState state = new PuzzleState(4,s);
		PuzzleState goal = NPuzzle.defaultGoal(4);
		NPuzzle npuzzle = new NPuzzle(state,goal,4);
		//npuzzle.setZob();

		//System.out.println(goal.getDisjointValue());
//		System.out.println(goal.getZobrist());
//		ArrayList<Action>arrayList = new ArrayList<>();
//		arrayList = npuzzle.Actions(state);
//		for(Action a : arrayList)
//		{
//			State child = npuzzle.result(state,a);
//			System.out.println(((PuzzleState)child).getZobrist());
//			//System.out.println(((PuzzleAction)a).getDir());
//			//State tmp = npuzzle.result(state,a);
//			//tmp.draw();
//
//		}
	}

}
