import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class Prueba {
	private static String nuevaCarpeta = "Newton";
	
	public static void main(String[] args) {
		Configuration configuration = new Configuration(true);
		configuration.set("fs.defaultFS", "hdfs://localhost:8020/");
		
		System.setProperty("HADOOP USER NAME", "hdfs");
		
		try {
			FileSystem fs = FileSystem.get(configuration);
			
			String home = fs.getHomeDirectory().toString();
			System.out.println(home);
			
			boolean fileExists = fs.exists(new Path(home + "/" + nuevaCarpeta));
			System.out.println(fileExists);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
