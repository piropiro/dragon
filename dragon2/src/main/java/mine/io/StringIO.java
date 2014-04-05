/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import mine.MineException;

/**
 *
 * @author rara
 */
public class StringIO {

    /**
     * ファイルから文字列を読み込む。
     *
     * @param file
     * @return
     * @throws mine.MineException
     */
    public static String[] read(String file) throws MineException {
        try (
                InputStream is = FileIO.getInputStream(file);
                InputStreamReader isr =new InputStreamReader(is, "UTF-8");
                BufferedReader in = new BufferedReader(isr)) {
            List<String> list = new ArrayList<>();
            for (String s = in.readLine(); s != null; s = in.readLine()) {
                list.add(s);
            }
            return list.toArray(new String[0]);
        } catch (IOException e) {
            throw new MineException(e);
        }

    }

    /**
     * ファイルに文字列を書き込む。
     *
     * @param file
     * @param list
     * @throws MineException
     */
    public static void write(String file, String[] list) throws MineException {
        try (
                OutputStream os = FileIO.getOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
                PrintWriter out = new PrintWriter(osw)) {
            for (String str : list) {
                out.println(str);
            }
        } catch (IOException e) {
            throw new MineException(e);
        }
    }
}
