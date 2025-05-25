package Practice.framework;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bsh.org.objectweb.asm.Type;

public class Hashmapdata {
	
	public List<HashMap<String,String>> getjsondatatoMap() throws IOException
	{
		String jsoncontent=FileUtils.readFileToString(new File("C:\\Users\\mamatha.kp\\eclipse-workspace\\Newproject\\src\\Practice\\framework\\data.json"),StandardCharsets.UTF_8);
	    ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
	return data;
	}

}
