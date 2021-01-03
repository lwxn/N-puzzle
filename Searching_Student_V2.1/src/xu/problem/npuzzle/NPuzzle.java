/**
 * 
 */
package xu.problem.npuzzle;

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
	public static int [][]mahantanValue;//存储曼哈顿距离的数组

	public NPuzzle(State initialState, State goal, int side) {
		super(initialState, goal);
		this.side = side;
		zobrist = new Zobrist(side * side, side * side);
		//setMahantanValue();
	}

	public NPuzzle(int side, byte[] status) {
		this(new PuzzleState(side, status), defaultGoal(side), side);
	}
	
	public NPuzzle(int side, byte[] status,int value) {
		this(new PuzzleState(side, status,value), defaultGoal(side), side);
	}

//	//设置初始的曼哈顿数组的值
//	public void setMahantanValue(){
//		mahantanValue = new int[side*side][side*side];//n*n n*n的数组
//		for(int i = 0;i<side*side;i++){
//			for(int j = 0;j<side*side;j++){
//				int sx = i/side;
//				int sy = i%side;
//				int tx = j/side;
//				int ty = j%side;
//				mahantanValue[i][j] =  (Math.abs(sx-tx)+Math.abs(sy-ty));
//			}
//		}
//	}
	
	private static PuzzleState defaultGoal(int side) {
		byte[] status = new byte[side * side];
		for (byte i = 0; i < side * side - 1; i++)
			status[i] = (byte) (i + 1);
		status[side * side - 1] = 0;
		PuzzleState goal = new PuzzleState(side, status);
 		return goal;
	}

	/* (non-Javadoc)
	 * @see core.problem.Problem#result(core.problem.State, core.problem.Action)
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

//		value -= mahantanValue[tx*n+ty][s[tx*n+ty]-1];
		int tmp = s[tx*n+ty];
		value -= Math.abs((tmp-1)/n - tx) + Math.abs((tmp-1)%n-ty);

		//互换
        byte temp = s[sx*n + sy];
        s[sx*n + sy] = s[tx*n + ty];
        s[tx*n + ty] = temp;

//		value += mahantanValue[sx*n+sy][s[sx*n+sy]-1];
		tmp = s[sx*n+sy];
		value += Math.abs((tmp-1)/n - sx) + Math.abs((tmp-1)%n-sy);

		return new PuzzleState(n,s,value);
//		return new PuzzleState(n,s);
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
	 */
	@Override
	public int heuristic(State state) {		
		return ((PuzzleState)state).getManhattan();
	}

	/* (non-Javadoc)
	 * @see core.problem.Problem#Actions(core.problem.State)
	 */
	public boolean IsSafe(int x,int y){
	    return x>=0&&x<this.side&&y>=0&&y<this.side;
    }

	@Override
	public ArrayList<Action> Actions(State state) {
		// TODO Auto-generated method stub
        ArrayList<Action>arrayList =  new ArrayList<>();
        byte s[] = ((PuzzleState)state).getStatus();
        int n = this.side;
        int sx = ((PuzzleState)state).getBlankPos()/this.side;
        int sy = ((PuzzleState)state).getBlankPos()%this.side;
		//System.out.println(sx + " "+sy);

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

	public static void main(String[] args) {

		byte s[] = {1,2,3,4,5,6,7,0,8};
		PuzzleState state = new PuzzleState(3,s);
		NPuzzle npuzzle = new NPuzzle(state,NPuzzle.defaultGoal(3),3);
		ArrayList<Action>arrayList = new ArrayList<>();
		arrayList = npuzzle.Actions(state);
		for(Action a : arrayList)
		{
			System.out.println(((PuzzleAction)a).getDir());
			State tmp = npuzzle.result(state,a);
			tmp.draw();

		}
	}

}
