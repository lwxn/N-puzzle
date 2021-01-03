package xu.problem.npuzzle;

import core.problem.State;

public class PuzzleState extends State {
	private PuzzleState(int side) {
		// TODO Auto-generated constructor stub
		this.side = side;
	}
	
	public PuzzleState(int side, byte[] status) {
		// TODO Auto-generated constructor stub
		this(side);
		setStatus(status);
		setManhattan();
		//System.out.println("hhhhhhhhhhhhhhhhhh");
	}

	//设置曼哈顿距离
	public PuzzleState(int side, byte[] status,int value) {
		// TODO Auto-generated constructor stub

		this(side);//System.out.println("dddd");
		setStatus(status);
		manhattan = value;
	}
	
	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public byte[] getStatus() {
		return status;
	}

	public void setStatus(byte[] status) {
		this.status  = status;
		setBlankPos();
	}
	
	public int getInversions() {		
		return 0;
	}
	
	//不在位将牌的个数
	public int misplaced() {
	    int sum = 0;
	    for(int i = 0;i<this.side*this.side;i++){
	        if(this.status[i]!=i+1 &&this.status[i]!=0){
	            sum++;
            }
        }
		return sum;
	}

	//曼哈顿距离
	public int getManhattan() {
		return manhattan;
	}

	public void setManhattan() {
//		System.out.println("test1");
        int sum = 0;
        int n = this.side;//n*n
        for(int i = 0;i<this.side*this.side;i++){
            int t = this.status[i];
            if(t!=0){
                int tx = (t-1)/n;
                int ty = (t-1)%n;
                sum+=Math.abs(tx-i/n) + Math.abs(ty-i%n);
            }
        }
        this.manhattan = sum;
	}
	
	public long getZobrist() {
		return zobrist;
	}

	public void setZobrist(long zobristValue) {
		this.zobrist = zobristValue;
	}

	@Override
	public void draw() {
		for (int i = 0; i < side; i++) {
			drawRow(i);
		}
		drawLine(); // 画底线
	}
	
	private void drawRow(int row) {
		drawLine();
		for (int j = 0; j < side; j++) {
			int index = row * side + j;
			if (status[index] != 0)
				System.out.printf("|" + "%2d" + " ", status[index]);
			else
				System.out.print("| # ");
		}
		System.out.println("|");
	}

	private void drawLine() {
		for (int j = 0; j < side; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
	}

	//设置空白将牌的位置
	private void setBlankPos() {
	    for(int i = 0;i<this.side*this.side;i++){
	        if(this.status[i] == 0){
	            this.blankPos = i;
	            break;
            }
        }
	}

	public int getBlankPos() {
		return blankPos;
	}

	@Override
	public int hashCode() {
		
		return 0;
	}
		
	@Override
	public boolean equals(Object obj) {
		PuzzleState s = (PuzzleState) obj;
		return this.zobrist == s.zobrist;
	}
	
	private int side; 		// 9, 16
	private byte[] status; 	// 一维数组存放将牌
	private int blankPos; 	// 空格的位置,从1开始计数
	
	private long zobrist;
	
	private int manhattan;	//当前状态跟目标状态的曼哈顿距离

	public static void main(String[] args) {
		byte s[] = {1,2,3,4,5,6,7,0,8};
		PuzzleState state = new PuzzleState(3,s);
		System.out.println(state.getBlankPos());
	}
}
