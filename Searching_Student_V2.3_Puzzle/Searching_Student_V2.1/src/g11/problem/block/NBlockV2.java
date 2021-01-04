package g11.problem.block;

import core.problem.State;

/**
 * ��Ӧ������ľ�������һ�ֱ��壬��
 * @author Jianliang
 *
 */
public class NBlockV2 extends NBlock{

	public NBlockV2() {
		// TODO Auto-generated constructor stub
	}
	
	public NBlockV2(int size, boolean classical) {
		super(size, classical);
	}

	@Override
	public boolean goalTest(State state) {
		return ((BlockState) state).inversions() == 0;
	}
}
