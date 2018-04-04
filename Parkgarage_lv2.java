import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Parkgarage_lv2 {

	public static void main(String[] args) {
		
		String input = "";
		if (args.length > 0) {
			String inputData = "";
			inputData = loadInputData(args[0]);
			if (!inputData.isEmpty()) {
				input = inputData;
			}
		}
		if (input.length() < 1) {
			System.out.println("no input data found.");
			System.exit(0);
		}
		
		String[] zeilen = input.split("\r\n");
		
		String[] config = zeilen[0].split(" ");
		
		int parkplaetze = Integer.parseInt(config[0]);
		int autosHeute = Integer.parseInt(config[1]);

		int g = 0;
		
		String[] list = zeilen[1].split(" ");
		int[] k = new int[list.length];
		
		for(int i = 0; i < list.length; i++) {
			k[i] = Integer.parseInt(list[i]);
		}
		
		int warteschlange = 0;
		int currentCars = 0;
		for(int i = 0; i < k.length; i++) {
			if (k[i] > 0) {
				currentCars++;
			} else if (k[i] < 0) {
				currentCars--;
			}

			if((currentCars > parkplaetze) && (currentCars - parkplaetze > warteschlange)) {
				warteschlange = currentCars - parkplaetze;
			}
			if((currentCars > g) && (currentCars <= parkplaetze)) {
				g = currentCars;
			}
		}

		JOptionPane.showMessageDialog(null,
				"<html>"
				+"Die maximale Anzahl der Autos die gleichzeitig in der Garage stehen können, ist " + g + "<br>"
				+ " und die max. Anzahl der Autos in der Warteschlange ist " + warteschlange
				+"</html>");
	}
	
	public static String loadInputData(String datName) {
		String fileData = "";
		File file = new File(datName);

		if (!file.canRead() || !file.isFile()) {
			return "";
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(datName));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				fileData += zeile+"\r\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return fileData;
	}
}
