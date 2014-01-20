package mine.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import mine.MineException;

/**
 * データファイルを読み書きするクラス。
 * @author k-saito
 * @version 1.0
 */
public class ObjectIO {

	/**
	 * ファイルからオブジェクトを読み込む。<p>
	 * 
	 * @param path データファイルのパス
	 * @return ファイルから読み込まれたオブジェクト
	 * @throws MineException 読み込みに失敗した。
	 */
	public static Object read(String path) throws MineException {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(FileIO.getInputStream(path));
			Object obj = in.readObject();
			return obj;
		} catch (IOException e) {
			throw new MineException(e);
		} catch (ClassNotFoundException e) {
			throw new MineException(e);
		} finally {
			FileIO.close(in);
		}
	}

	/**
	 * オブジェクトをファイルに書き出す。<p>
	 * 
	 * @param path データファイルのパス
	 * @param Object 書き出すオブジェクト
	 * @throws MineException 書き込みに失敗した。
	 */
	public static void write(String path, Object obj) throws MineException {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(FileIO.getOutputStream(path));
			out.writeObject(obj);
		} catch (IOException e) {
			throw new MineException(e);
		} finally {
			FileIO.close(out);
		}
	}
}
