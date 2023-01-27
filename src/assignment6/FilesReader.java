package assignment6;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FilesReader {
	
	public List<SalesData> readData (String path) throws IOException {
			List<SalesData> salesData = new ArrayList<>();
			BufferedReader reader = null;
			/*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd h:mm:ss a");
			formatter.setTimeZone(TimeZone.getTimeZone("Germany"));*/
			
			try {
				reader = new BufferedReader(new FileReader(path));
				String line = reader.readLine();
				
				while((line = reader.readLine()) != null) {
					String[] datas = line.split(",");
					YearMonth dateData = YearMonth.parse(datas[0],DateTimeFormatter.ofPattern("MMM-yy"));
					Integer salesD = Integer.parseInt(datas[1]);
					salesData.add(new SalesData(dateData, salesD));
					
				}
				return salesData;
			} 
			finally {
					if(reader != null) {
						reader.close();
					}
			}
		
		
	}

}
