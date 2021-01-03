package xu.problem.block;

import java.rmi.activation.ActivationID;
import java.util.ArrayList;
import java.util.Arrays;

import core.problem.Action;
import core.problem.Problem;
import core.problem.State;
import xu.problem.npuzzle.PuzzleState;

import static xu.problem.block.PieceColor.*;


public class NBlock extends Problem {

	public NBlock() {
		// TODO Auto-generated constructor stub
	}
	
	//classical: 
	//   true：经典滑动积木块，range = 3;
	//   false：变形， range = (n+1)/2
	
	public NBlock(int size, boolean classical) {
		this(defaultInit(size), defaultGoal(size), size);
		if (!classical) range = (size + 1) / 2 + 1;   //size = 3, 4: range = 3; //size = 5, 6; range = 4
	}
	
	public NBlock(BlockState initialState, BlockState goal, int size) {
		super(initialState, goal);
		this.size = size;
	}
	
	private static BlockState defaultGoal(int size) {
		PieceColor[] status = new PieceColor[size * 2 + 1];
		for (byte i = 0; i < size; i++) {
			status[i] = WHITE;
			status[i + size] = BLACK;
		}		
		status[size * 2] = EMPTY;
		BlockState goal = new BlockState(size, status);
		goal.setBlankPos(size * 2);
		return goal;
	}
	
	private static BlockState defaultInit(int size) {
		PieceColor[] status = new PieceColor[size * 2 + 1];
		for (byte i = 0; i < size; i++) {
			status[i] = BLACK;
			status[i + size] = WHITE;
		}		
		status[size * 2] = EMPTY;
		
		BlockState init = new BlockState(size, status);
		init.setBlankPos(size * 2);
		return init;
	}

	//坐标是否越界
	public boolean isSafe(int pos){
		return pos>=0 && pos <2*size+1;
	}

	@Override
	public boolean solvable() {
		// TODO Auto-generated method stub
		return true;
	}

	//变换后的孩子结点
	@Override
	public State result(State parent, Action action) {
		int offset = ((BlockAction)action).getOffset();//偏移量
		int blankPos = ((BlockState)parent).getBlankPos();//空白所在的位置
		PieceColor[] s = Arrays.copyOf(((BlockState) parent).getStatus(),2*size+1);

		long value = ((BlockState) parent).getZobrist();

		value = value^Zobrist.Zob[blankPos][s[blankPos].byteValue()]^
		Zobrist.Zob[offset+blankPos][s[offset+blankPos].byteValue()];

		PieceColor temp = s[blankPos+offset];
		s[offset+blankPos] = s[blankPos];
		s[blankPos] = temp;

		value = value^Zobrist.Zob[blankPos][s[blankPos].byteValue()]^
				Zobrist.Zob[offset+blankPos][s[offset+blankPos].byteValue()];

		BlockState child =  new BlockState(size,s,value);
		child.setBlankPos(offset+blankPos);
		return child;
	}

	//gn  路径的耗散值
	@Override
	public int stepCost(State parent, Action action) {
		int cost = 0;//gn
		int offset = ((BlockAction)action).getOffset();//偏移量

		//计算gn
		if(Math.abs(offset) == 1) cost = Math.abs(offset);//向旁边移动
		else cost = Math.abs(offset)-1;//跨过多少积木块

		return cost;
	}

	//hn   heuristic   逆序对数
	@Override
	public int heuristic(State state) {
		return 0;//((BlockState)state).inversions();
	}

	//可能的方向
	@Override
	public ArrayList<Action> Actions(State state) {
		int blankPos = ((BlockState)state).getBlankPos();//空白的位置
		ArrayList<Action>actions = new ArrayList<>();

		for(int i = blankPos-range;i<=blankPos+range;i++){
			if(isSafe(i)&&i!=blankPos){//安全且不是原位
				actions.add(new BlockAction(blankPos,i-blankPos));
			}
		}
		return actions;
	}

	private int size = 0;
	
	private int range = 3;	//空格能够移动的范围，默认值为3

	public static void main(String[] args) {
		NBlock problem = new NBlock(9, true);
		BlockState init = (BlockState) (problem.getInitialState());
		init.draw();
		System.out.println(init.hashCode());
	}
}
