package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader 
{
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		//1.reading json file to string
		//System.getproperty("user.dir") helps whatever the external file we want to use it creates the path of that file dynamically
	String jsoncontent=	FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json"),
			StandardCharsets.UTF_8);
	//2.convert this string to hashmap
	//download Jackson Databind-dependency-hepls to convert string content to hashmap
	//By creating the object of ObjectMapper class we are read value()-read the string and convert it to hashmap from the file
	ObjectMapper mapper=new ObjectMapper();
	
	List<HashMap<String,String>>data=mapper.readValue(jsoncontent,new TypeReference<List<HashMap<String,String>>>(){
			
	
	
	});
	return data;	}

}
