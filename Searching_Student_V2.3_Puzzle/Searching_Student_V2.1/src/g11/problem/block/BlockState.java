package g11.problem.block;

import core.problem.State;

public class BlockState extends State{

	public BlockState() {
		// TODO Auto-generated constructor stub
	}
	
	public BlockState(int size, PieceColor[] status) {
		super();
		this.size = size;
		this.status = status;
		this.heuristic = inversions();
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

	public void setBlankPos(int blankPos) {
		this.blankPos = blankPos;
	}

	public long getZobrist() {
		return zobrist;
	}

	public void setZobrist(long zobrist) {
		this.zobrist = zobrist;
	}
	
	public int inversions() {
		int result = 0;
		int temp = 0;
		for(int i = 0;i<2*size+1;i++){
			if(this.status[i] == PieceColor.BLACK){//如果是黑色的
				temp++;
			}
			else if(this.status[i] == PieceColor.WHITE){//如果是白色的
				result+=temp;
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		return (int)(zobrist);
	}
		
	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	int size = 0;  //问题规模，
	PieceColor[] status;
	private int blankPos; 	// 空格的位置,从1开始计数	
	private long zobrist;
	private int heuristic;  //hn
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		for (int i = 0; i < size * 2 + 1; i++) {
			System.out.print(status[i].shortName());
		}
		System.out.println("--" + inversions());
	}
}
