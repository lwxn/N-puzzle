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

	//得到空白位置的下标
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


	int blankPos = 0;	//空格位置
	int offset;         //空格移动的偏移量， 正数表示右移，复数表示左移
	@Override
	public void draw() {
		
	}

	@Override
	public int getValue() {
		return offset;
	}
}
