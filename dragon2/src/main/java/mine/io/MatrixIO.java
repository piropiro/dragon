package mine.io;

import java.io.IOException;

import mine.MineException;

/**
 * データファイルを読み書きするクラス。
 *
 * @author k-saito
 * @version 1.0
 */
public class MatrixIO {

    /**
     * ファイルから数列を読み込む。
     * <p>
     *
     * @param path データファイルのパス
     * @return ファイルから読み込まれた配列
     * @throws MineException 読み込みに失敗した。
     */
    public static int[][] read(String path) throws MineException {
        try (MatrixReader in = new MatrixReader(FileIO.getInputStream(path))) {
            int[][] matrix = in.readMatrix();
            return matrix;
        } catch (IOException e) {
            throw new MineException(e);
        }
    }
    
    public static int[][] readX(String path) throws MineException {
        int[][] src = read(path);
        
        int[][] dest = new int[src[0].length][src.length];
        for (int y = 0; y < dest.length; y++) {
            for (int x = 0; x < dest[0].length; x++) {
                dest[y][x] = src[x][y];
            }
        }
        
        return dest;
    }

    /**
     * 数列をファイルに書き出す。
     * <p>
     *
     * @param path データファイルのパス
     * @param matrix 書き出す数列
     * @throws MineException 書き込みに失敗した。
     */
    public static void write(String path, int[][] matrix) throws MineException {
        try (MatrixWriter out = new MatrixWriter(FileIO.getOutputStream(path))) {
            out.writeMatrix(matrix);
        } catch (IOException e) {
            throw new MineException(e);
        }
    }
}
