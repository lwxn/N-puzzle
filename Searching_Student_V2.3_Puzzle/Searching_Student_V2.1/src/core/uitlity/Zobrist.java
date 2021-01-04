package core.uitlity;

import java.security.SecureRandom;

public class Zobrist {

	public Zobrist(int size, int pieceNumber) {
		// TODO Auto-generated constructor stub
		SecureRandom rand = new SecureRandom();
		zobristSeeds = new long[size][];
		for (int i = 0; i < size; i++) {
			zobristSeeds[i] = new long[pieceNumber];
			for (int j = 0; j < pieceNumber; j++) {
				zobristSeeds[i][j] = rand.nextLong();
			}
		}
		this.size = size;
		this.pieceNumber = pieceNumber;
	}

	public Zobrist(int size, int pieceNumber,int type) {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated constructor stub
		SecureRandom rand = new SecureRandom();
		table_15 = new Long[size][];
		for (int i = 0; i < size; i++) {
			table_15[i] = new Long[pieceNumber];
			for (int j = 0; j < pieceNumber; j++) {
				table_15[i][j] = rand.nextLong();
			}
		}
		this.size = size;
		this.pieceNumber = pieceNumber;
	}

	public long[][] getZobristSeeds() {
		return zobristSeeds;
	}

	public void setZobristSeeds(long[][] zobristSeeds) {
		this.zobristSeeds = zobristSeeds;
	}

	//初始化一个16*16的随机数数组
	public long[][] zobristSeeds;
	
	//根据随机数组，计算某个状态的Zobrist值
	public long hash(byte[] status) {
		long h = 0L;
		for (int i = 0; i < size; i++) {
			if (status[i] != 0)
				h ^= zobristSeeds[i][status[i]];
		}
		return h;
	}
	
	//在当前状态下（其zobrist值为value）, 将位置srcIndex上的代号为srcPiece的棋子，移动到destIndex，
	//   destPiece为当前在目标位置destIndex上的棋子的代号，可能为空格（目标位置没有棋子）。
	public long newHash(long value, int srcIndex, byte srcPiece, int destIndex, byte destPiece) {		
//		value ^= zobristSeeds[srcIndex][srcPiece];	//异或掉要移动的子
//		if (destPiece != 0)	// 如果目标位置有棋子，则异或掉该棋子。吃子的情况
//			value ^= zobristSeeds[destIndex][destPiece];
//		value ^= zobristSeeds[destIndex][srcPiece];	//将要移动的棋子，异或到棋盘的目标位置。
		if(srcPiece!=-1){
			value ^= zobristSeeds[srcIndex][srcPiece];	//异或掉要移动的子
			if (destPiece != 0)	// 如果目标位置有棋子，则异或掉该棋子。吃子的情况
				value ^= zobristSeeds[destIndex][destPiece];
			value ^= zobristSeeds[destIndex][srcPiece];	//将要移动的棋子，异或到棋盘的目标位置。
		}
		return value;
	}

	//6_6_3    first 6  ,size是16
	public long hashCut(byte[] status,byte[] V){
		long value = 0;

		for(int i = 0;i<V.length;i++){
			value ^= table_15[getIndex(status,V[i])][V[i]];
		}
		return value;
	}

	public long newHash_empty(long hash,int i,byte value1,int j,byte value2){
		hash ^= table_15[i][0];
		hash ^= table_15[j][0];

		if(value2 == -1) return hash;

		hash ^= table_15[j][value2];
		hash ^= table_15[i][value2];

		return hash;
	}

	public int getIndex(byte[] status,int value){
		for(int i = 0;i<status.length;i++){
			if(value == status[i]){
				return i;
			}
		}
		return 0;
	}

	public long hash_empty(byte[] status,byte[] V){
		long value = 0;
		int index = getIndex(status,0);
		value ^= table_15[index][0];

		for(int i = 0;i<V.length;i++){
			value ^= table_15[getIndex(status,V[i])][V[i]];
		}
		return value;
	}
	
	int size = 0;			//棋盘的大小,看作一维的数组
	int pieceNumber = 0;	//棋子的种类个数，每个棋子的代号从1到pieceNumber,空格用0表示
	public static Long[][] table_15 = new Long[16][16];
}
