package g11.problem.npuzzle;

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

	/**
	 *
	 * @return 方向的下标
	 */
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
	
	int blankPos = 0;	//空格位置
	Direction dir;      //空格移动的方向


}
