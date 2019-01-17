package com.ljc.bigdata.view;

public class Terminal implements View{

	public void directoryAlreadyExists(String path) {
		// TODO Auto-generated method stub
		System.out.println("[WARN] Directory " + path + " already exists!");
	}

	public void directoryCreated(String path) {
		// TODO Auto-generated method stub
		System.out.println("[INFO] Directory was succesfully created in " + path);
	}

	public void fileAlreadyExists(String path) {
		// TODO Auto-generated method stub
		System.out.println("[ERROR] File " + path + " already exists!");
	}

	public void fileCreated(String path) {
		// TODO Auto-generated method stub
		System.out.println("[INFO] File was succesfully created in " + path);
	}

	public void showFileContain(String content) {
		// TODO Auto-generated method stub
		System.out.println("Output: " + content);
	}

	public void showFileMetadata(String owner, String permission) {
		// TODO Auto-generated method stub
		System.out.println("Owner: "+ owner + " Permissions: " + permission);
	}

	public void snapshotCreated(String snapshotName, String snapshottedDirectoryPath) {
		// TODO Auto-generated method stub
		System.out.println("Snapshot " + snapshotName + " was successfully created based from " + snapshottedDirectoryPath);
	}
	
}
