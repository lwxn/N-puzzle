package xu.problem.npuzzle;

import core.problem.Action;

public class PuzzleAction extends Action {
	public PuzzleAction() {
		// TODO Auto-generated constructor stub
	}

	public PuzzleAction(int blankPos, Direction dir) {
		super();
		this.blankPos = blankPos;
		this.dir = dir;
	}

	public int getBlankPos() {
		return blankPos;
	}

	public void setBlankPos(int blankPos) {
		this.blankPos = blankPos;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	@Override
	public int getValue() {
		return getDir().ordinal();
	}

	@Override
	public void draw() {
		for(int i = 0;i<2;i++){
			System.out.println("|");
		}
		System.out.println("V");
	}
	
	int blankPos = 0;	//�ո�λ��
	Direction dir;      //�ո��ƶ��ķ���


}
