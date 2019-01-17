package com.ljc.bigdata.hadoopHdfsApi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.client.HdfsAdmin;

import com.ljc.bigdata.view.Terminal;
import com.ljc.bigdata.view.View;

public class Snapshot {
	
	private static View view = new Terminal();
	
	public static void main(String[] args) {
		Configuration configuration = new Configuration(true);
		configuration.set("fs.defaultFS", "hdfs://localhost:8020/");
		
		System.setProperty("HADOOP_USER_NAME", "hdfs");
		
		try {
			FileSystem fs = FileSystem.get(configuration);
			String home = fs.getHomeDirectory().toString();
			
			try {
				HdfsAdmin hdfsAdmin = new HdfsAdmin(new URI("hdfs://localhost:8020"), configuration);
				
				Path snapshottedDirectoryPath = new Path(home + "/data");
				
				// hdfs dfsadmin -allowSnapshot <ruta_directorio>
				hdfsAdmin.allowSnapshot(snapshottedDirectoryPath);
				
				// View Snapshottable Directory Listing using `hdfs lsSnapshottableDir`
				
				// Create a snapshot
				String snapshotName = "snapshot_" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date());
			    fs.createSnapshot(snapshottedDirectoryPath, snapshotName);
			    
			    view.snapshotCreated(snapshotName, snapshottedDirectoryPath.toString());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			fs.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
}
