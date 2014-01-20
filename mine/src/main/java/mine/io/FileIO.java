/*
 * 作成日: 2003/09/28
 */
package mine.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import mine.MineException;
import mine.MineUtils;

/**
 * @author k-saito
 *
 */
public class FileIO {

	/**
	 * 指定されたファイルの入力ストリームを取得する。<p>
	 * 
	 * @param path ファイルパス
	 * @return 指定されたファイルの入力ストリーム
	 * @throws MineException ファイルが見つからない。
	 */
	public static BufferedInputStream getInputStream(String path) throws MineException {
		
		MineUtils.checkNull(path, "FilePath");

		try {
			InputStream is = FileIO.class.getResourceAsStream("/" + path);
			if (is == null) {
				is = new FileInputStream(path);
			}
			return new BufferedInputStream(is);
		} catch (FileNotFoundException e) {
			System.out.println("File is not Found. [" + new File(path).getAbsolutePath() + "]");
			throw new MineException(e);
		}
	}

	/**
	 * 指定されたファイルの出力ストリームを取得する。<p>
	 * 
	 * @param path ファイルパス
	 * @return 指定されたファイルの出力ストリーム
	 * @throws MineException ファイルが見つからない。
	 */
	public static BufferedOutputStream getOutputStream(String path) throws MineException {

		MineUtils.checkNull(path, "FilePath");

		try {
			OutputStream os = new FileOutputStream(path);
			return new BufferedOutputStream(os);
		} catch (FileNotFoundException e) {
			throw new MineException(e);
		}
	}

	/**
	 * 入力ストリームを閉じる。<p>
	 * 
	 * @param in
	 */
	public static void close(InputStream in){
		try {
			if (in != null){
				in.close();
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * 出力ストリームを閉じる。<p>
	 * 
	 * @param out
	 */
	public static void close(OutputStream out){
		try {
			if (out != null){
				out.close();
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * リーダーを閉じる。<p>
	 * 
	 * @param in
	 */
	public static void close(Reader in) {
		try {
			if (in != null){
				in.close();
			}
		} catch (IOException e){
			e.printStackTrace();
		}	
	}

	/**
	 * ライターを閉じる。<p>
	 * 
	 * @param out
	 */
	public static void close(Writer out){
		try {
			if (out != null){
				out.close();
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}
