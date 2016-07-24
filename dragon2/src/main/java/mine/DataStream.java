




package mine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataStream {

	public DataStream() {
	}

	public static void setup(Object obj) {
		c = obj.getClass();
	}

	private static ObjectInputStream getInStream(Object obj) throws Exception {
		try {
			if (obj instanceof File) {
				FileInputStream fileinputstream = new FileInputStream(
						(File) obj);
				if (fileinputstream != null)
					return new ObjectInputStream(fileinputstream);
			} else if (obj instanceof String) {
				if (c != null) {
					java.io.InputStream inputstream = c
							.getResourceAsStream((String) obj);
					if (inputstream != null)
						return new ObjectInputStream(inputstream);
				}
				FileInputStream fileinputstream1 = new FileInputStream(
						(String) obj);
				if (fileinputstream1 != null)
					return new ObjectInputStream(fileinputstream1);
			}
		} catch (FileNotFoundException filenotfoundexception) {
			System.out.println("File not found. ( " + obj + " )");
		}
		return null;
	}

	public static Object read(Object obj) {
		Object obj1 = null;
		try {
			ObjectInputStream objectinputstream = getInStream(obj);
			if (objectinputstream != null) {
				obj1 = objectinputstream.readObject();
				objectinputstream.close();
			}
		} catch (Exception exception) {
                    throw new MineException(exception);
		}
		return obj1;
	}

	private static ObjectOutputStream getOutStream(Object obj) throws Exception {
		if (obj instanceof File) {
			FileOutputStream fileoutputstream = new FileOutputStream((File) obj);
			return new ObjectOutputStream(fileoutputstream);
		}
		if (obj instanceof String) {
			FileOutputStream fileoutputstream1 = new FileOutputStream(
					(String) obj);
			return new ObjectOutputStream(fileoutputstream1);
		} else {
			return null;
		}
	}

	public static void write(Object obj, Object obj1) {
		try {
			ObjectOutputStream objectoutputstream = getOutStream(obj);
			if (objectoutputstream != null) {
				objectoutputstream.writeObject(obj1);
				objectoutputstream.close();
			}
		} catch (Exception exception) {
			throw new MineException(exception);
		}
	}

	private static Class c;
}
