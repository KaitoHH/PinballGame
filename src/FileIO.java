import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: PinballGame
 * Author: KaitoHH
 * Create Date: 2016/11/20
 * Description:
 * All rights reserved.
 */
public class FileIO {
	public static void save(List<Gizmo> components, String path) {
		List<FileGizmo> list = new ArrayList();
		for (Gizmo g : components) {
			FileGizmo fg = new FileGizmo();
			fg.setRotate(g.getRotate());
			fg.setAngle(g.getAngle());
			fg.setColor(g.getColor());
			fg.setShape(g.getShape());
			fg.setSizeRate(g.getSizeRate());
			fg.setX(g.getX());
			fg.setY(g.getY());
			list.add(fg);
		}
		writeObjectToFile(list, path);
	}

	public static List<Gizmo> load(String path) {
		List<FileGizmo> list = (List<FileGizmo>) readObjectFromFile(path);
		List<Gizmo> components = new ArrayList<>();
		for (FileGizmo g : list) {
			Gizmo gizmo = new Gizmo(g.getX(), g.getY(), g.getSizeRate(), g.getShape(), g.getColor(), g.getRotate());
			components.add(gizmo);
		}
		return components;
	}

	public static Object readObjectFromFile(String path) {
		Object temp = null;
		File file = new File(path);
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

	public static void writeObjectToFile(Object obj, String path) {
		File file = new File(path);
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

}

