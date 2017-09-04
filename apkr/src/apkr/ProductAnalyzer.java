package apkr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductAnalyzer {
	  static List<Product> productlist;
	  static int LineNumber=1;
	
	  public static void main(String[] args) throws Exception  {
		    // TODO Auto-generated method stub
		    String csvFile = "productInfo.csv";
	       
	        productlist = new ArrayList<Product>();
            //read first 25 records
	        ProductAnalyzer.readProductInfoFromCSV(csvFile);
	        
	        //Return Products Sorted by Name
	        ProductAnalyzer.SortProducts("Name");
	        System.out.println();
	        
	        //Add the next 25
	        ProductAnalyzer.readProductInfoFromCSV(csvFile);
		    System.out.println();
		    
	       // Return Products Sorted by Category
	        ProductAnalyzer.SortProducts("Category");
		    System.out.println();
	 
		   //Add the next 25
	        ProductAnalyzer.readProductInfoFromCSV(csvFile);
		    
	   	
	        //Return Products Sorted by Expiration Date
	        ProductAnalyzer.SortProducts("ExpirationDate");
		    System.out.println();
		    
		    
	
	        //Delete All products of a specific Category
	        ProductAnalyzer.DeleteProductsbyCriteria("Category","cat1");
		    System.out.println(); 
		    
		    
		    //Return Products Sorted by ProductNumber
		    ProductAnalyzer.SortProducts("ProductNumber");
		    
	        }
	
	
	
	
	//Method for sorting products based on Condition using switch statement
	static void SortProducts(String Criteria)
	 {
		switch(Criteria) { 
		  case "Category":
			    productlist.sort((p1, p2) -> p1.getProductCategory().compareTo(p2.getProductCategory()));
			    productlist.forEach(p -> System.out.println(p)); 
	            break;
	         case "ProductNumber" :
	            productlist.sort((p1, p2) -> Integer.compare(p1.getProductNumber(),p2.getProductNumber()));
	            //Collections.sort(productlist, (p1, p2) -> (int) (p1.getProductNumber() - p2.getProductNumber()));
	        	productlist.forEach(p -> System.out.println(p)); 
	            break;
	         case "Name" :
	            productlist.sort((p1, p2) -> p1.getProductName().compareTo(p2.getProductName()));
	            productlist.forEach(p -> System.out.println(p)); 
	            break;
	         case "ExpirationDate" :
	        	productlist.sort((p1, p2) -> p1.getExpirationDate().compareTo(p2.getExpirationDate()));
	        	productlist.forEach(p -> System.out.println(p));
	        	 break;
	         default :
	             System.out.println("Invalid Criteria");
		}
		 
	 }
	
	//Overload Method 1 for delete by Product Category/Name
	static void DeleteProductsbyCriteria(String Criteria,String Condition)
	 {
		switch(Criteria) { 
		  case "Category":
			  productlist.removeIf((Product p) -> p.getProductCategory().equalsIgnoreCase(Condition));
			  productlist.forEach(p -> System.out.println(p));
			       break;
	         case "Name" :
	        	  productlist.removeIf((Product p) -> p.getProductCategory().equalsIgnoreCase(Condition));
	        	  productlist.forEach(p -> System.out.println(p));
	        	  default :
	             System.out.println("Invalid Criteria");
		}
		 
	 }
	
	//Overload Method 2 for delete by Product Expiration Data 
	static void DeleteProductsbyCriteria(String Criteria,Date expDate)
	 {
		 productlist.removeIf((Product p) -> p.getExpirationDate().equals(expDate) );
		 productlist.forEach(p -> System.out.println(p));
		 }
	
	
	//Overload Method 3 for delete by Product Number
	static void DeleteProductsbyCriteria(String Criteria,int ProductNumber)
	 {
		 productlist.removeIf((Product p) -> p.getProductNumber() == ProductNumber);
		 productlist.forEach(p -> System.out.println(p));
		 }
	
	
	//Method to Read 25 lines at once
	 static void readProductInfoFromCSV(String csvFile) throws Exception
	 { String line = "";
     String cvsSplitBy = ",";
     int lineCounter=0;
		 try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                
	            while ((line = br.readLine()) != null  ) 
	            {            	
	                // use comma as separator
	            	lineCounter++;
	            	if(lineCounter>=LineNumber && lineCounter<LineNumber+25 ) {
	                String[] prodInfo = line.split(cvsSplitBy);
	                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	                Date expirationDate=df.parse(prodInfo[2]);  
	               Product newProduct= new Product(prodInfo[0],prodInfo[1],expirationDate);
	               if(newProduct!=null) {
	                ProductAnalyzer.productlist.add(newProduct);
	               }} 
	            	else if (lineCounter==LineNumber+25)
	            	{break;}
	            	
	            	}
	       LineNumber=LineNumber+25;//26,51,76
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
}


//PRODUCT OBJECT MODEL
 class Product {
     static int PN=0;
     

	int ProductNumber;
	   String ProductName;
	   String ProductCategory;
	   Date ExpirationDate;
	
	   Product(String productName,
	   String productCategory,
	   Date expirationDate)
	   {PN++;
	   ProductNumber=PN ;
	   ProductName=productName;
	    ProductCategory=productCategory;
	    ExpirationDate=expirationDate;
	   }
	   @Override
		public String toString() {
			return "Product [ProductNumber=" + ProductNumber + ", ProductName=" + ProductName + ", ProductCategory="
					+ ProductCategory + ", ExpirationDate=" + ExpirationDate.toString() + "]";
		}
	public int getProductNumber() {
		return ProductNumber;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getProductCategory() {
		return ProductCategory;
	}
	public void setProductCategory(String productCategory) {
		ProductCategory = productCategory;
	}
	public Date getExpirationDate() {
		return ExpirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		ExpirationDate = expirationDate;
	}
	   	  
	}
 