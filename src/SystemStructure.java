/**
 * Project: PinballGame
 * Author: KaitoHH
 * Create Date: 2016/11/19
 * Description:
 * All rights reserved.
 */
public class SystemStructure {
	public static boolean isWindows() {
		String os = System.getProperty("os.name");
		return os.contains("win") || os.contains("Win");
	}
}
