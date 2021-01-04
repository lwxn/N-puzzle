package g11.problem.npuzzle;

import core.problem.State;

import java.nio.charset.StandardCharsets;

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
	}

	//设置曼哈顿距离
	public PuzzleState(int side, byte[] status,int value) {
		// TODO Auto-generated constructor stub
		this(side);
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
	    for(int i = 0;i<this.side;i++){
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

	//disjoint pattern database
	public int getDisjointValue(){
		long part1 = partHash(status,V1);
		long part2 = partHash(status,V2);
		long part3 = partHash(status,V3);
		long part4 = partHash(status,V4);
		int heuristic = databaseRead.getHeu(part1,1) + databaseRead.getHeu(part2,2)
				+ databaseRead.getHeu(part3,3) + databaseRead.getHeu(part4,4);
		return heuristic;
	}

	public long partHash(byte[] status,byte[] V){
		long h = (long) 0;
		int index;
		for(int i=0; i<V.length; i++){
			index = new String(status, StandardCharsets.UTF_8).indexOf(V[i]);
			h ^= databaseRead.hashTable[index][V[i]];
		}
		return h;
	}


	public void setManhattan() {
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
		return (int)getZobrist();
	}
		
	@Override
	public boolean equals(Object obj) {
		PuzzleState s = (PuzzleState) obj;
		return this.zobrist == s.zobrist;
	}
	
	private int side; 		// 3,4
	private byte[] status; 	// 一维数组存放将牌
	private int blankPos; 	// 空格的位置,从1开始计数

	private long zobrist;
	private int manhattan;	//当前状态跟目标状态的曼哈顿距离

	private static byte V1[] = {1,2,5,6};
	private static byte V2[] = {3,4,7,8};
	private static byte V3[] = {9,10,13,14};
	private static byte V4[] = {11,12,15};


	public static void main(String[] args) {
//		byte s[] = {1,2,3,4,
//				5,6,7,8,
//				9,10,11,12,
//				13,14,15,0};
		databaseRead.setHashInit("hashTable.txt");
		String[] ss = {"pdb_4_1.txt","pdb_4_2.txt","pdb_4_3.txt","pdb_4_4.txt"};
		databaseRead inread = new databaseRead(ss);
		byte s[] = {4,6,15,13,12,0,10,2,8,9,7,3,14,5,1,11};
		PuzzleState state = new PuzzleState(4,s);
		System.out.println(state.getDisjointValue());
		//System.out.println(state.getManhattan());
	}
}
