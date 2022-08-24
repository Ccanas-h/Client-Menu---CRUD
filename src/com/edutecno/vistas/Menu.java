package com.edutecno.vistas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.edutecno.modelo.CategoryEnum;
import com.edutecno.modelo.Client;
import com.edutecno.service.ExportToArchive;
import com.edutecno.service.ClientService;
import com.edutecno.service.ExportToNewArchive;
import com.edutecno.service.ImportFromArchive;
import com.edutecno.utilidades.Utility;

public class Menu {

	
	ClientService clienteServicio = new ClientService(null);
	ExportToArchive añadeDatos = new ExportToArchive();
	ExportToNewArchive exportadorDeCero = new ExportToNewArchive();
	ImportFromArchive importaArchivos = new ImportFromArchive();
	Scanner sc = new Scanner(System.in);
	

	
	public void iniciarMenu() throws FileNotFoundException, IOException {
		List<String> opcionesMenu = new ArrayList<>();

		opcionesMenu.add("List Clients");
		opcionesMenu.add("Add Clients");
		opcionesMenu.add("Edit Clients");
		opcionesMenu.add("Import Client data from a file");
		opcionesMenu.add("Add actual Client data to an existing file");
		opcionesMenu.add("Export actual Client data deleting the previous file");
		opcionesMenu.add("Exit the program");
		
		Menu menu = new Menu();
		menu.seleccionOpcion(opcionesMenu);
	}

	private void seleccionOpcion(List<String> opcionesMenu) throws FileNotFoundException, IOException {

		List<String> opMenu = opcionesMenu;
		
		boolean boleano = false;
		
		while(boleano == false) {
		
		int eleccionUsuario = imprimirMenuEleccion(opMenu);
		
		switch (eleccionUsuario) {
		case 1: listarCliente();
			
			break;
		case 2:	agregarCliente();
			
			break;
		case 3: editarCliente();
			
			break;
		case 4: importarDatos();
			
			break;
		case 5:	añadeDatosAlArchivo();
			
			break;
		case 6:	exportarDatos();
		
		break;
		case 7: terminarPrograma();
			
			break;

		default: System.out.println("Choose a valid option");
			break;
		}
		
		}
	}


	private void exportarDatos() {
		String ruta1 = "src/txt.txt";
		String ruta2 = "src/excel.csv";
		
		System.out.println("------Client data export by deleting previous customer records------");
		System.out.println("Choose 1 to create a new text file with the new clients added");
		System.out.println("Or choose 2 to create a new CSV file with the new clients added");
		
		int eleccionUsuario = sc.nextInt();
		switch (eleccionUsuario) {
		case 1: {
			exportadorDeCero.exportar(ruta1, clienteServicio.listarClientes());
			break;
		}case 2: {
			exportadorDeCero.exportar(ruta2, clienteServicio.listarClientes());	
			break;
		}
		default:
			System.out.println("Choose a valid option. 1 or 2. Returning to main menu");	
			Utility.stopAndContinue();
			break;
		}
		
		Utility.stopAndContinue();
	
		
	}
	
	
	private void importarDatos() throws FileNotFoundException, IOException {
		String ruta1 = "src/txt.txt";
		String ruta2 = "src/excel.csv";
		
		System.out.println("------Import of Client data to file .CSV or .TXT------");
		System.out.println("Press 1 to EXTRACT data from a .TXT file into the current client list");
		System.out.println("Or Press 2 to EXTRACT data from a .CSV file into the current client list");

		int eleccionUsuario = sc.nextInt();
		switch (eleccionUsuario) {
		case 1: {
			importaArchivos.importar(ruta1, clienteServicio);
			break;
		}case 2: {
			importaArchivos.importar(ruta2, clienteServicio);	
			break;
		}
		default:
			System.out.println("Choose a valid option. 1 or 2. Returning to main menu");	
			Utility.stopAndContinue();
			break;
		}
		
		Utility.stopAndContinue();		
	}

	private void añadeDatosAlArchivo() {
		String ruta1 = "src/txt.txt";
		String ruta2 = "src/excel.csv";
		
		System.out.println("------Export data client to a file without erasing previous data client------");
		System.out.println("Press 1 to export the current data client to a TXT file without erasing previous data");
		System.out.println("Or  Press 2 to export the current data client to a CSV file without erasing previous data");

		int eleccionUsuario = sc.nextInt();
		switch (eleccionUsuario) {
		case 1: {
			añadeDatos.exportar(ruta1, clienteServicio.listarClientes());
			Utility.stopAndContinue();
			break;
		}case 2: {
			añadeDatos.exportar(ruta2, clienteServicio.listarClientes());	
			Utility.stopAndContinue();
			break;
		}
		default:
			System.out.println("Choose a valid option. 1 or 2. Returning to main menu");	
			Utility.stopAndContinue();
			break;		
			}
		

	}



	private int imprimirMenuEleccion(List<String> opMenu) {
			
		List<String> opcionesMenu = opMenu;
		int largo = opMenu.size();

		System.out.println("Choose the desired option");
		
		for (int i=0; i<largo; i++ ) {
			System.out.println("Option: "+(i+1)+" "+opMenu.get(i));
		}
		
		int eleccionUsuario2 = sc.nextInt();
		
		if (eleccionUsuario2 < 1 || eleccionUsuario2 > 8) {
			System.out.println("selection Not allowed ");
		}
		
		return eleccionUsuario2; 
	}



	public void agregarCliente() {
		System.out.println("----------- Create Client -----------");
		
		
		
		System.out.println("Enter Client RUT (National ID)");
		String runCliente = sc.next(); 
		System.out.println("Enter Client Name");
		String nombreCliente = sc.next(); 
		System.out.println("Enter the customer's last name.");
		String apellidoCliente = sc.next(); 
		System.out.println("Enter customer age");
		Integer aniosCliente = sc.nextInt(); 
		CategoryEnum nombreCategoria = CategoryEnum.Activo;
		
		clienteServicio.agregarCliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, nombreCategoria);
		
		System.out.println("Client created correctly");
		Utility.stopAndContinue();
		
	}

	public void listarCliente() {
		clienteServicio.listarClientes();
		Utility.stopAndContinue();
	}
		
	public void editarCliente() {
		List <Client> listaClientes = null;
		listaClientes = clienteServicio.listarClientes();
		clienteServicio.editarClienteSvc(listaClientes);
		Utility.stopAndContinue();

	}
	
	
	
	
	private void terminarPrograma() {
		
		System.out.println("Thanks for using the program, Good Bye!");
		Utility.stopAndContinue();
		System.out.println("Exiting the program");
		Utility.stopAndContinue();
		System.exit(0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}