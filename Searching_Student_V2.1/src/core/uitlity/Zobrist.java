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
	
	//��ʼ��һ��16*16�����������
	private long[][] zobristSeeds;
	
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
		value ^= zobristSeeds[srcIndex][srcPiece];	//����Ҫ�ƶ�����
		if (destPiece != 0)	// ���Ŀ��λ�������ӣ������������ӡ����ӵ���� 
			value ^= zobristSeeds[destIndex][destPiece];
		value ^= zobristSeeds[destIndex][srcPiece];	//��Ҫ�ƶ������ӣ�������̵�Ŀ��λ�á�
		
		return value;
	}
	
	int size = 0;			//���̵Ĵ�С,����һά������
	int pieceNumber = 0;	//���ӵ����������ÿ�����ӵĴ��Ŵ�1��pieceNumber,�ո���0��ʾ
}
