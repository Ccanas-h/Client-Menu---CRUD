package com.edutecno.service;

import java.util.List;

import com.edutecno.modelo.Client;

public abstract class AbstractExport {

		public abstract void exportar(String fileName, List<Client> listaClientes); 
	
}
