package com.ljc.bigdata.hadoopHdfsApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.AclEntry;
import org.apache.hadoop.fs.permission.FsPermission;

import com.ljc.bigdata.view.Terminal;
import com.ljc.bigdata.view.View;

public class Hdfs {
	
	private static String newDirectory = "People";
	private static String newFile = "Newton";
	private static String content = "Hi everybody there!";
	
	private static View view = new Terminal();
	
	public static void main(String[] args) {
		Configuration configuration = new Configuration(true);
		configuration.set("fs.defaultFS", "hdfs://localhost:8020/");
		
		System.setProperty("HADOOP_USER_NAME", "hdfs");
		
		try {
			FileSystem fs = FileSystem.get(configuration);
			
			String home = fs.getHomeDirectory().toString();
			
			Path desiredDirectoryPath = new Path(home + "/" + newDirectory);
			if (!fs.exists(desiredDirectoryPath)) {
				fs.mkdirs(desiredDirectoryPath);
				view.directoryCreated(desiredDirectoryPath.toString());
			} else {
				view.directoryAlreadyExists(desiredDirectoryPath.toString());
			}
			
			Path desiredFilePath = new Path(home + "/" + newDirectory + "/"
					+ newFile);
			
			if(!fs.exists(desiredFilePath)) {
				FSDataOutputStream fSDataOutputStream = fs.create(desiredFilePath);
				fSDataOutputStream.writeBytes(content);
				fSDataOutputStream.close();
				view.fileCreated(desiredFilePath.toString());
				
			} else {
				view.fileAlreadyExists(desiredFilePath.toString());
			}
			
			//Reading the file we just created
			FSDataInputStream fSDataInputStream = fs.open(desiredFilePath);
			view.showFileContain(IOUtils.toString(fSDataInputStream, "UTF-8"));
			
			FileStatus fileStatus = fs.getFileStatus(desiredFilePath);
			view.showFileMetadata(fileStatus.getOwner(), fileStatus.getPermission().toString());
			
			fs.setOwner(desiredFilePath, "cloudera", "cloudera");
			
			FsPermission fSPermission = FsPermission.valueOf("-rwxrwxrwx");
			fs.setPermission(desiredFilePath, fSPermission);
			
			fileStatus = fs.getFileStatus(desiredFilePath);
			view.showFileMetadata(fileStatus.getOwner(), fileStatus.getPermission().toString());
			
			//Al igual que hemos realizado con la línea de comandos, podemos mover fichero de Local al HDFS y viceversa.
			//Local --> HDFS
			fs.copyFromLocalFile(false, true, new Path("/home/cloudera/archivos/bmw-browsers.csv"), desiredDirectoryPath);

			// ACL
			AclEntry aclUser = AclEntry.parseAclEntry("user::rwx", true);
			AclEntry aclCloudera = AclEntry.parseAclEntry("user:cloudera:r-x", true);
		    AclEntry aclGroup = AclEntry.parseAclEntry("group::r-x", true);
		    AclEntry aclOther = AclEntry.parseAclEntry("other::r--", true);
		    List<AclEntry> aclEntryList = new ArrayList<AclEntry>();
		    aclEntryList.add(aclUser);
		    aclEntryList.add(aclCloudera);
		    aclEntryList.add(aclGroup);
		    aclEntryList.add(aclOther);
			
		    fs.setAcl(desiredDirectoryPath, aclEntryList);
		    
		    fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
