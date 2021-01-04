package core.runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import core.problem.Problem;
import g11.problem.block.NBlock;
import g11.problem.block.NBlockV2;
import g11.problem.npuzzle.NPuzzle;

//这个类不可修改
public class ProblemFactory {

	public ProblemFactory() {
		// TODO Auto-generated constructor stub
	}

	// 获取某类问题的所有实例
	// type：问题类型，1: n数码；2: n滑动积木块
	public static ArrayList<Problem> getProblems(int type) {
		if (type == 1)
			return readProblemFromFile("problems.txt");
		else {
			ArrayList<Problem> problems = new ArrayList<>();
			problems.add(new NBlock(9, true));
			problems.add(new NBlock(9, false));
			problems.add(new NBlockV2(9, true));
			problems.add(new NBlockV2(9, false));
			problems.add(new NBlock(12, true));
			problems.add(new NBlock(12, false));
			problems.add(new NBlockV2(12, true));
			problems.add(new NBlockV2(12, false));
			return problems;
		}
	}

	// 将文件中的一行，解析为n数码问题
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

	//读取文件中的所有问题实例
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
