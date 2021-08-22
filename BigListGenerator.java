import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BigListGenerator {
	public static void main(String[] args) throws IOException {
		File file;
		
		file = new File("names\\totals.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		int[] totalsF = new int[141];
		int[] totalsM = new int[141];
		
		String currentLine;
		int index1, index2, date;
		while((currentLine = br.readLine()) != null) {
			index1 = currentLine.indexOf(',');
			index2 = currentLine.indexOf(',', index1+1);
			
			date = Integer.parseInt(currentLine.substring(0, index1));
			totalsF[date-1880] = Integer.parseInt(currentLine.substring(index1+1, index2));
			totalsM[date-1880] = Integer.parseInt(currentLine.substring(index2+1));
		}
		
		FileWriter myWriter = new FileWriter("allNames.txt");
		BufferedWriter bw = new BufferedWriter(myWriter);
		String name, gender;
		int births;
		for(int i = 0; i < 141; i++) {
			file = new File("names\\yob" + (i+1880) + ".txt");
			br = new BufferedReader(new FileReader(file));
			
			while((currentLine = br.readLine()) != null) {
				index1 = currentLine.indexOf(',');
				index2 = currentLine.indexOf(',', index1+1);
				
				name = currentLine.substring(0, index1);
				gender = currentLine.substring(index1+1, index2);
				births = Integer.parseInt(currentLine.substring(index2+1));
				
				if(gender == "F") {
					births = (int) Math.round(Math.pow(births * 0.2 * (double) totalsF[140]/(double) totalsF[i], 1));
				} else {
					births = (int) Math.round(Math.pow(births * 0.2 * (double) totalsM[140]/(double) totalsM[i], 1));
				}
				
				bw.write(Integer.toString(i+1880) + "," + name + "," + gender + "," + Integer.toString(births));
			    bw.newLine();
			}
		}
		bw.close(); myWriter.close();
	}
}