package apkr;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ProductAnalyzerTest {

	String csvFile = "productInfo.csv";

	@Before
	public void setUp() throws Exception {
		
       //Initialize Productlist
		ProductAnalyzer.productlist = new ArrayList<Product>();
		ProductAnalyzer.readProductInfoFromCSV(csvFile);

	}

	@Test
	public void testSortProducts() {
		
		ProductAnalyzer.SortProducts("ExpirationDate");
		int indexProducts=0;
		
		//check first 10 products for arrangements
		for(Product P : ProductAnalyzer.productlist)
		{indexProducts++;
		 assertTrue(P.getExpirationDate().before(ProductAnalyzer.productlist.get(indexProducts).getExpirationDate()));
		if (indexProducts==5)break;
		}
	}

	@Test
	public void testDeleteProductsbyCriteriaStringString() {
		//check If count is 0 for number of products who are deleted
		ProductAnalyzer.DeleteProductsbyCriteria("Category","cat2");
		
		//ProductAnalyzer.productlist.stream().filter(p -> !p.getProductCategory().equals("cat2")).forEach(p->System.out.println(p));
	    assertTrue(ProductAnalyzer.productlist.stream().filter(p -> p.ProductCategory.equalsIgnoreCase("cat2")).count()==0);
		
	}

	@Test
	public void testDeleteProductsbyCriteriaStringDate() throws ParseException {
		 DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		//Try Deleting a sample date for checking testcase is successful or not.
         Date expirationDate=df.parse("7/29/2017");  
		 ProductAnalyzer.DeleteProductsbyCriteria("Date",expirationDate);
	     assertTrue(ProductAnalyzer.productlist.stream().filter(p -> p.ExpirationDate.equals(expirationDate)).count()==0);
		
	}

	@Test
	public void testDeleteProductsbyCriteriaStringInt() {
		 //Try Deleting a sample productnumber 3 for checking testcase is successful or not.
		 ProductAnalyzer.DeleteProductsbyCriteria("ProductNumber",3);
	     assertTrue(ProductAnalyzer.productlist.stream().filter(p -> (p.ProductNumber==3)).count()==0);
	}

	@Test
	public void testReadProductInfoFromCSV() throws Exception {
		//Try inserting a sample product csv for checking testcase is successful or not.
		ProductAnalyzer.readProductInfoFromCSV(csvFile);
		assertTrue(ProductAnalyzer.productlist.size()!=0);
	}

}
