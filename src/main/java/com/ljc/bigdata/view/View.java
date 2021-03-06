package com.ljc.bigdata.view;

public interface View {

	void directoryAlreadyExists(String path);

	void directoryCreated(String path);

	void fileAlreadyExists(String path);

	void fileCreated(String path);
	
	void showFileContain(String content);

	void showFileMetadata(String owner, String permission);

	void snapshotCreated(String snapshotName, String string);
	
}
