package com.edutecno.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.edutecno.modelo.CategoryEnum;

public class ImportFromArchive extends AbstractImport{

	@Override
	public void importar(String fileName, ClientService cs) throws FileNotFoundException, IOException {
		
		List<List<String>> records = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				
				records.add(Arrays.asList(values));
				
			}
		}	
		
		for(int i = 0; i < records.size(); i++) {
			
		if (records.get(i).get(0).equals("")) {
			System.out.println("No hay nada en esa linea");
		}else {
			cs.agregarCliente(records.get(i).get(0), 
					records.get(i).get(1), 
					records.get(i).get(2), 
					Integer.parseInt(records.get(i).get(3)), 
					CategoryEnum.valueOf(records.get(i).get(4)));
			
		}

		}
		
		System.out.println(cs.listarClientes());
		System.out.print("-----------------------------------------------------------------------------------------"
						+ "\n-----------------------------------------------------------------------------------------"
						+ "\nDatos IMPORTADOS Exitosamente. Revisar listando de clientes en el menu digitando opcion 1");
	}
}

