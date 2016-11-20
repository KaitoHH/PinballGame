import javax.swing.filechooser.FileFilter;
import java.io.*;

/**
 * Project: PinballGame
 * Author: KaitoHH
 * Create Date: 2016/11/20
 * Description:
 * All rights reserved.
 */
public class FileIO {
	public static void save(Object obj) {
		File file = new File("test.dat");
		FileOutputStream out;
		try {
			out = new FileOutputStream(file);
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			objOut.writeObject(obj);
			objOut.flush();
			objOut.close();
			System.out.println("write object success!");
		} catch (IOException e) {
			System.out.println("write object failed");
			e.printStackTrace();
		}
	}

	public static Object readObjectFromFile() {
		Object temp = null;
		File file = new File("test.dat");
		FileInputStream in;
		try {
			in = new FileInputStream(file);
			ObjectInputStream objIn = new ObjectInputStream(in);
			temp = objIn.readObject();
			objIn.close();
			System.out.println("read object success!");
		} catch (IOException e) {
			System.out.println("read object failed");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
	}
}

