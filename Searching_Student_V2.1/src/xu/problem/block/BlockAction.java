package xu.problem.block;

import core.problem.Action;

public class BlockAction extends Action{

	public BlockAction() {
		// TODO Auto-generated constructor stub
	}

	public BlockAction(int blankPos, int offset) {
		super();
		this.blankPos = blankPos;
		this.offset = offset;
	}

	//�õ��հ�λ�õ��±�
	public int getBlankPos() {
		return blankPos;
	}

	public void setBlankPos(int blankPos) {
		this.blankPos = blankPos;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}


	int blankPos = 0;	//�ո�λ��
	int offset;         //�ո��ƶ���ƫ������ ������ʾ���ƣ�������ʾ����
	@Override
	public void draw() {
		
	}

	@Override
	public int getValue() {
		return offset;
	}
}
