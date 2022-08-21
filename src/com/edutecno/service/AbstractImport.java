package com.edutecno.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class AbstractImport {

	public abstract void importar(String fileName, ClientService cs) throws FileNotFoundException, IOException;
	

}
