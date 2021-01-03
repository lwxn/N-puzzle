package xu.problem.block;

import core.problem.Action;
import core.problem.Problem;
import core.problem.State;

import static xu.problem.block.PieceColor.*;


public class BlockState extends State{

	public BlockState() {
		// TODO Auto-generated constructor stub
	}
	
	public BlockState(int size, PieceColor[] status) {
		super();
		this.size = size;
		this.status = status;
		//init();
		setZobrist();
	}

	public BlockState(int size,PieceColor[] status,long value){
		super();
		this.size = size;
		this.status = status;
		this.zobrist = value;
	}

	public static void init(){

		if(count == 0){System.out.println(count);
			Zobrist z = new Zobrist(12);
		}
		else count++;
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public PieceColor[] getStatus() {
		return status;
	}

	//返回一个status的copy
	public byte[] byteStatus() {
		byte[] status = new byte[this.status.length];
		
		for (int i = 0; i < this.status.length; i++) {
			status[i] = this.status[i].byteValue();
		}
		return status;
	}
	
	public void setStatus(PieceColor[] status) {
		this.status = status;
	}
	

	public int getBlankPos() {
		return blankPos;
	}

	//设置空白所在的位置
	public void setBlankPos(int blankPos) {
		this.blankPos = blankPos;
	}

	public long getZobrist() {
		return zobrist;
	}

	//设置Zobrist的值,只计算一次
	public void setZobrist() {
		long sum = 0;
		for(int i = 0;i<2*size+1;i++){
			sum^=Zobrist.Zob[i][status[i].byteValue()];
		}
		this.zobrist = sum;
	}

	//逆序数
	public int inversions() {
		int sum = 0;
		int temp = 0;
		for(int i = 0;i<2*size+1;i++){
			if(status[i].byteValue() == 1){//BLACK
				temp++;
			}
			else if(status[i].byteValue() == 2){//White
				sum+=temp;
			}
		}
		return sum;
	}

	//zobrist
	@Override
	public int hashCode() {
		return (int)(zobrist);
	}
		
	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

	int size = 0;  //问题规模，
	PieceColor[] status;
	private int blankPos; 	// 空格的位置,从1开始计数	
	private long zobrist;
	private static int count = 0;
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		for (int i = 0; i < size * 2 + 1; i++) {
			System.out.print(status[i].shortName());
		}
		System.out.println("--" + inversions());
	}

	public static void main(String[] args) {
		int size = 9;
		Zobrist z = new Zobrist(size);
		PieceColor [] status = new PieceColor[2*size+1];
		for (byte i = 0; i < size; i++) {
			status[i] = WHITE;
			status[i + size] = BLACK;
		}
		status[size*2-1] = EMPTY;
		status[size * 2] = BLACK;
		BlockState init = new BlockState(9,status);
		init.setBlankPos(size*2-1);
		init.draw();

		PieceColor [] status1 = new PieceColor[2*size+1];
		for (byte i = 0; i < size; i++) {
			status1[i] = WHITE;
			status1[i + size] = BLACK;
		}
		status1[size*2] = EMPTY;
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
