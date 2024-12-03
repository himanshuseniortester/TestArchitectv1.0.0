package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
		    // DataProvider method to fetch login data
	    @DataProvider(name = "LoginData")
	    public String[][] getData() throws IOException {
	        // File path for the Excel data
	        String path = "./testData/Opencart_LoginData.xlsx"; 

	        // Creating an object of ExcelUtility
	        ExcelUtility xlUtil = new ExcelUtility(path);

	        // Get the total number of rows and columns from the Excel sheet
	        int totalRows = xlUtil.getRowCount("Sheet1");
	        int totalCols = xlUtil.getCellCount("Sheet1", 1);

	        // Create a two-dimensional array to store the data
	        String loginData[][] = new String[totalRows][totalCols];

	        // Nested loop to read data from the Excel sheet
	        for (int i = 1; i <= totalRows; i++) { // Start from 1 to skip the header
	            for (int j = 0; j < totalCols; j++) {
	                loginData[i - 1][j] = xlUtil.getCellData("Sheet1", i, j); // Fill data into the array
	            }
	        }

	        return loginData; // Return the 2D array
	    }
	    
	    //Data Provider 2 
	    
	    //Data Provider  3
	    
	    //Data Provider 4
	}


