package mine.io;

import java.io.IOException;

import mine.MineException;

/**
 * データファイルを読み書きするクラス。
 * @author k-saito
 * @version 1.0
 */
public class MatrixIO {

	/**
	 * ファイルから数列を読み込む。<p>
	 * 
	 * @param path データファイルのパス
	 * @return ファイルから読み込まれた配列
	 * @throws MineException 読み込みに失敗した。
	 */
	public static int[][] read(String path) throws MineException {
		MatrixReader in = new MatrixReader(FileIO.getInputStream(path));
		try {
			int[][] matrix = in.readMatrix();
			return matrix;
		} catch (IOException e) {
			throw new MineException(e);
		} finally {
			FileIO.close(in);
		}
	}

	/**
	 * 数列をファイルに書き出す。<p>
	 * 
	 * @param path データファイルのパス
	 * @param matrix 書き出す数列
	 * @throws MineException 書き込みに失敗した。
	 */
	public static void write(String path, int[][] matrix) throws MineException {
		MatrixWriter out = new MatrixWriter(FileIO.getOutputStream(path));
		try {
			out.writeMatrix(matrix);
		} catch (IOException e) {
			throw new MineException(e);
		} finally {
			FileIO.close(out);
		}
	}
}
