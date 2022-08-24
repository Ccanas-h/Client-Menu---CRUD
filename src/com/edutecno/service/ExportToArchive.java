package com.edutecno.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.edutecno.modelo.Client;

public class ExportToArchive extends AbstractExport{

	@Override
	public void exportar(String fileName, List<Client> listaClientes) {
		
		
		
		if(listaClientes.size() == 0) {
			System.out.println("La lista esta vacia");
		}else{
			
			System.out.println("Exportar Datos");
			String separador = ",";
			File file = new File(fileName);

			System.out.println("Archivo creado en la ruta: " + file.getAbsolutePath());
			
			try {
				FileWriter fw = new FileWriter(file, true);  //Esto es lo que indica a JAVA que no debe borrar lo creado dentro del archivo, sino AÑADIR. 
				PrintWriter printWriter = new PrintWriter(fw);

				
				for (Client cliente3 : listaClientes) {
					
			
					
					printWriter
					.append(cliente3.getRunCliente())
					.append(separador)
					.append(cliente3.getNombreCliente())
					.append(separador)
					.append(cliente3.getApellidoCliente())
					.append(separador)
					.append(cliente3.getAniosCliente().toString())
					.append(separador)
					.append(cliente3.toString())
					.append("\n");
				
				}
				
				printWriter.close();
				System.out.println("Datos exportados correctamente");
				
			}catch(IOException ex) {
				System.out.println("El archivo no pudo ser creado");
			}finally {
				System.out.println("**********************************");
			}

 
		
		
	}


}
	
}