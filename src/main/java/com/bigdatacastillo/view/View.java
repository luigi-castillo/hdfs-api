package com.bigdatacastillo.view;

public interface View {

	void directoryAlreadyExists(String path);

	void directoryCreated(String path);

	void fileAlreadyExists(String path);

	void fileCreated(String path);
	
}
