package core.runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import core.problem.Problem;
import xu.problem.block.NBlock;
import xu.problem.block.NBlockV2;
import xu.problem.block.Zobrist;
import xu.problem.npuzzle.NPuzzle;

//����಻���޸�
public class ProblemFactory {

	public ProblemFactory() {
		// TODO Auto-generated constructor stub
	}

	// ��ȡĳ�����������ʵ��
	// type���������ͣ�1: n���룻2: n������ľ��
	public static ArrayList<Problem> getProblems(int type) {
		if (type == 1)
			return readProblemFromFile("problems.txt");
		else {
			ArrayList<Problem> problems = new ArrayList<>();
//			problems.add(new NBlock(9, true));
//			problems.add(new NBlock(9, false));
			problems.add(new NBlockV2(9, true));
//			problems.add(new NBlockV2(9, false));
//			problems.add(new NBlock(12, true));
//			problems.add(new NBlock(12, false));
//			problems.add(new NBlockV2(12, true));
//			problems.add(new NBlockV2(12, false));
			return problems;
		}
	}

	// ���ļ��е�һ�У�����Ϊn��������
	private static Problem parseString(String problem) {

		String[] cells = problem.split(",");
		int side = Integer.valueOf(cells[0].trim()).intValue();

		byte[] status = new byte[cells.length - 1];
		for (int i = 0; i < status.length; i++) {
			status[i] = Byte.valueOf(cells[i + 1].trim()).byteValue();
		}

		Problem npuzzle = new NPuzzle(side, status);
		return npuzzle;
	}

	//��ȡ�ļ��е���������ʵ��
	private static ArrayList<Problem> readProblemFromFile(String fileName) {
		ArrayList<Problem> problems = new ArrayList<>();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				problems.add(parseString(tempString));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return problems;
	}
}
