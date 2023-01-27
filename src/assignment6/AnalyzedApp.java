package assignment6;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class AnalyzedApp {
	public static void main(String[] args) throws IOException {
		FilesReader reader = new FilesReader();
		List<SalesData> model3Data = reader.readData("model3.csv");
		List<SalesData> modelSData = reader.readData("modelS.csv");
		List<SalesData> modelXData = reader.readData("modelX.csv");
		
		processingFile(model3Data, "Model 3");
		processingFile(modelSData, "Model S");
		processingFile(modelXData, "Model X");
	}
	
	private static void processingFile(List<SalesData> datas, String cartype) {
		System.out.println( cartype + " Yearly Sales Report ");
		System.out.println("-------------------------------");
		
		//Grouping datas by year
		Map<Integer,List<SalesData>> yearlyDatas = datas.stream().collect(
				Collectors.groupingBy(o -> o.getDate().getYear()));
		
		String yearlyReport = yearlyDatas.entrySet().stream().map(o -> o.getKey() + " -> " +
		o.getValue().stream().collect(Collectors.summingInt(SalesData :: getSales))).collect(
				Collectors.joining("\n"));
		
		System.out.println(yearlyReport);
		
		Optional<SalesData> maxSalesData = datas.stream().max((SalesData o1, SalesData o2) ->
			o1.getSales().compareTo(o2.getSales()));
		Optional<SalesData> minSalesData = datas.stream().min((SalesData o1, SalesData o2)->
			o1.getSales().compareTo(o2.getSales()));
		
		YearMonth fakeDate = YearMonth.parse("Jan-01",DateTimeFormatter.ofPattern("MMM-yy"));

		System.out.println("The best month for " + cartype + "was: " + maxSalesData.orElse(
				new SalesData(fakeDate, 0)).getDate());
		System.out.println("The worst month for " + cartype + "was: " + minSalesData.orElse(
				new SalesData(fakeDate, 0)).getDate());
		System.out.println(" ");
				
	}

}
