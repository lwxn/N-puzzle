package g11.problem.block;

import java.util.ArrayList;
import java.util.Arrays;

import core.problem.Action;
import core.problem.Problem;
import core.problem.State;
import core.uitlity.Zobrist;


public class NBlock extends Problem {

	public NBlock() {
		// TODO Auto-generated constructor stub
	}
	
	//classical: 
	//   true�����们����ľ�飬range = 3;
	//   false�����Σ� range = (n+1)/2
	
	public NBlock(int size, boolean classical) {
		this(defaultInit(size), defaultGoal(size), size);
		if (!classical) range = (size + 1) / 2 + 1;   //size = 3, 4: range = 3; //size = 5, 6; range = 4

	}
	
	private NBlock(BlockState initialState, BlockState goal, int size) {
		super(initialState, goal);
		this.size = size;
		zobrist = new Zobrist(2*size+1,3);
		initialState.setZobrist(zobrist.hash(initialState.byteStatus()));
		goal.setZobrist(zobrist.hash(goal.byteStatus()));
	}
	
	private static BlockState defaultGoal(int size) {
		PieceColor[] status = new PieceColor[size * 2 + 1];
		for (byte i = 0; i < size; i++) {
			status[i] = PieceColor.WHITE;
			status[i + size] = PieceColor.BLACK;
		}		
		status[size * 2] = PieceColor.EMPTY;
		BlockState goal = new BlockState(size, status);
		goal.setBlankPos(size * 2);
		return goal;
	}
	
	private static BlockState defaultInit(int size) {
		PieceColor[] status = new PieceColor[size * 2 + 1];
		for (byte i = 0; i < size; i++) {
			status[i] = PieceColor.BLACK;
			status[i + size] = PieceColor.WHITE;
		}		
		status[size * 2] = PieceColor.EMPTY;
		
		BlockState init = new BlockState(size, status);
		init.setBlankPos(size * 2);
		return init;
	}

	@Override
	public boolean solvable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public State result(State parent, Action action) {
		int offset = ((BlockAction)action).getOffset();//ƫ����
		int blankPos = ((BlockState)parent).getBlankPos();//�հ����ڵ�λ��
		PieceColor[] s = Arrays.copyOf(((BlockState) parent).getStatus(),2*size+1);


		//System.out.println(offset+" "+blankPos);
		PieceColor temp = s[blankPos+offset];
		s[offset+blankPos] = s[blankPos];
		s[blankPos] = temp;


		BlockState child =  new BlockState(size,s);
		child.setBlankPos(offset+blankPos);
		child.setZobrist(zobrist.newHash(((BlockState) parent).getZobrist(),blankPos+offset,temp.byteValue(),blankPos,(byte) 0));
		return child;
	}

	@Override
	public int stepCost(State parent, Action action) {
		int cost = 0;//gn
		int offset = ((BlockAction)action).getOffset();//ƫ����

		//����gn
		if(Math.abs(offset) == 1) cost = Math.abs(offset);//���Ա��ƶ�
		else cost = Math.abs(offset)-1;//������ٻ�ľ��

		return cost;
	}

	@Override
	public int heuristic(State state) {
		return ((BlockState)state).getHeuristic();
	}

	//test the safety of the location
	boolean isSafe(int i,int size){
		return i>=0&&i<size;
	}

	@Override
	public ArrayList<Action> Actions(State state) {
		int empty = 0;//�ո��λ��
		ArrayList<Action>actionList = new ArrayList<>();//action list

		//Find the empty block
		for(int i = 0;i<2*size+1;i++){
			if(((BlockState)state).getStatus()[i] == PieceColor.EMPTY){
				empty = i;
				break;
			}
		}

		//��һ�������ĵط�
		for(int i = empty-range;i<=empty+range;i++){
			if(isSafe(i,2*size+1)&&i!=empty){
				actionList.add(new BlockAction(empty,i-empty));
			}
		}

		return actionList;
	}



	private int size = 0;
	private Zobrist zobrist;
	private int range = 3;	//�ո��ܹ��ƶ��ķ�Χ��Ĭ��ֵΪ3

	public static void main(String[] args) {
		int size = 9;
		PieceColor [] status = new PieceColor[2*size+1];
		for (byte i = 0; i < size; i++) {
			status[i] = PieceColor.WHITE;
			status[i + size] = PieceColor.BLACK;
		}
		status[size*2-1] = PieceColor.EMPTY;
		status[size * 2] = PieceColor.BLACK;
		BlockState init = new BlockState(9,status);
		init.setBlankPos(size*2-1);
		init.draw();

		PieceColor [] status1 = new PieceColor[2*size+1];
		for (byte i = 0; i < size; i++) {
			status1[i] = PieceColor.WHITE;
			status1[i + size] = PieceColor.BLACK;
		}
		status1[size*2] = PieceColor.EMPTY;
		BlockState goal = new BlockState(9,status1);
		goal.setBlankPos(size*2);

		NBlock problem = new NBlock(init,goal,size);
		int i = 0;
		for(Action action : problem.Actions(init)){
			//if(i == 3){
			State child = problem.result(init,action);
			child.draw();
			if(problem.goalTest(child)){
				System.out.println("success");
			}
			i++;
		}

	}
}
