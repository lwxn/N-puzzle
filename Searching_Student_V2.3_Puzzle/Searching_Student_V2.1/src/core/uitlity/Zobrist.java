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

	//��ʼ��һ��16*16�����������
	public long[][] zobristSeeds;
	
	//����������飬����ĳ��״̬��Zobristֵ
	public long hash(byte[] status) {
		long h = 0L;
		for (int i = 0; i < size; i++) {
			if (status[i] != 0)
				h ^= zobristSeeds[i][status[i]];
		}
		return h;
	}
	
	//�ڵ�ǰ״̬�£���zobristֵΪvalue��, ��λ��srcIndex�ϵĴ���ΪsrcPiece�����ӣ��ƶ���destIndex��
	//   destPieceΪ��ǰ��Ŀ��λ��destIndex�ϵ����ӵĴ��ţ�����Ϊ�ո�Ŀ��λ��û�����ӣ���
	public long newHash(long value, int srcIndex, byte srcPiece, int destIndex, byte destPiece) {		
//		value ^= zobristSeeds[srcIndex][srcPiece];	//����Ҫ�ƶ�����
//		if (destPiece != 0)	// ���Ŀ��λ�������ӣ������������ӡ����ӵ����
//			value ^= zobristSeeds[destIndex][destPiece];
//		value ^= zobristSeeds[destIndex][srcPiece];	//��Ҫ�ƶ������ӣ�������̵�Ŀ��λ�á�
		if(srcPiece!=-1){
			value ^= zobristSeeds[srcIndex][srcPiece];	//����Ҫ�ƶ�����
			if (destPiece != 0)	// ���Ŀ��λ�������ӣ������������ӡ����ӵ����
				value ^= zobristSeeds[destIndex][destPiece];
			value ^= zobristSeeds[destIndex][srcPiece];	//��Ҫ�ƶ������ӣ�������̵�Ŀ��λ�á�
		}
		return value;
	}

	//6_6_3    first 6  ,size��16
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
	
	int size = 0;			//���̵Ĵ�С,����һά������
	int pieceNumber = 0;	//���ӵ����������ÿ�����ӵĴ��Ŵ�1��pieceNumber,�ո���0��ʾ
	public static Long[][] table_15 = new Long[16][16];
}
