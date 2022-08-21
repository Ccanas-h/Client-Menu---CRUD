package com.edutecno.vistas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.edutecno.modelo.CategoryEnum;
import com.edutecno.modelo.Client;
import com.edutecno.service.ArchiveService;
import com.edutecno.service.ExportToArchive;
import com.edutecno.service.ClientService;
import com.edutecno.service.ExportToNewArchive;
import com.edutecno.service.ImportFromArchive;
import com.edutecno.utilidades.Utility;

public class Menu {

	
	ClientService clienteServicio = new ClientService(null);
	ArchiveService archivoServicio = new ArchiveService();
	ExportToArchive añadeDatos = new ExportToArchive();
	ExportToNewArchive exportadorDeCero = new ExportToNewArchive();
	ImportFromArchive importaArchivos = new ImportFromArchive();
	Scanner sc = new Scanner(System.in);
	

	
	public void iniciarMenu() throws FileNotFoundException, IOException {
		List<String> opcionesMenu = new ArrayList<>();

		opcionesMenu.add("Listar Clientes");
		opcionesMenu.add("Agregar Clientes");
		opcionesMenu.add("Editar Clientes");
		opcionesMenu.add("Importar datos de Clientes");
		opcionesMenu.add("Añadir datos de Clientes a un archivo existente");
		opcionesMenu.add("Exportar datos de Clientes eliminando el archivo anterior");
		opcionesMenu.add("Salir del programa");
		
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

		default: System.out.println("Escoja una opcion valida");
			break;
		}
		
		}
	}


	private void exportarDatos() {
		String ruta1 = "src/txt.txt";
		String ruta2 = "src/excel.csv";
		
		System.out.println("------Exportacion de datos de Clientes eliminando registros de clientes anteriores------");
		System.out.println("Aprete 1 para crear un nuevo archivo de texto con los datos de los clientes creados actualmente");
		System.out.println("O aprete 2 para crear un nuevo archivo CSV con los datos de ");
		
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
			System.out.println("Escoja una opcion valida. Volviendo al menu principal");	
			Utility.stopAndContinue();
			break;
		}
		
		Utility.stopAndContinue();
	
		
	}
	
	
	private void importarDatos() throws FileNotFoundException, IOException {
		String ruta1 = "src/txt.txt";
		String ruta2 = "src/excel.csv";
		
		System.out.println("------Importacion de datos de Clientes de archivo .CSV o .TXT------");
		System.out.println("Aprete 1 para EXTRAER y SUMAR datos desde un archivo.TXT hacia la lista de clientes actual");
		System.out.println("O aprete 2 para EXTRAER datos desde un archivo.CSV hacia la lista de clientes en uso");

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
			System.out.println("Escoja una opcion valida. Volviendo al menu principal");	
			Utility.stopAndContinue();
			break;
		}
		
		Utility.stopAndContinue();		
	}

	private void añadeDatosAlArchivo() {
		String ruta1 = "src/txt.txt";
		String ruta2 = "src/excel.csv";
		
		System.out.println("------Exportacion de datos de Clientes sin eliminar registros de clientes anteriores------");
		System.out.println("Aprete 1 para añadir datos de la lista de clientes actual al archivo de texto");
		System.out.println("O aprete 2 para añadir datos de la lista de clientes actual a un archivo CSV");

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
			System.out.println("Escoja una opcion valida. Volviendo al menu principal");	
			Utility.stopAndContinue();
			break;		
			}
		

	}



	private int imprimirMenuEleccion(List<String> opMenu) {
			
		List<String> opcionesMenu = opMenu;
		int largo = opMenu.size();

		System.out.println("Escoja el numero de la opcion que quiera usar");
		
		for (int i=0; i<largo; i++ ) {
			System.out.println("Opcion: "+(i+1)+" "+opMenu.get(i));
		}
		
		int eleccionUsuario2 = sc.nextInt();
		
		if (eleccionUsuario2 < 1 || eleccionUsuario2 > 6) {
			System.out.println("Seleccion no permitida");
		}
		
		return eleccionUsuario2; 
	}



	public void agregarCliente() {
		System.out.println("----------- Crear Cliente -----------");
		
		
		
		System.out.println("Ingrese RUT de cliente");
		String runCliente = sc.next(); 
		System.out.println("Ingrese nombre de cliente");
		String nombreCliente = sc.next(); 
		System.out.println("Ingrese apellido de cliente");
		String apellidoCliente = sc.next(); 
		System.out.println("Ingrese años de cliente");
		String aniosCliente = sc.next(); 
		CategoryEnum nombreCategoria = CategoryEnum.Activo;
		
		clienteServicio.agregarCliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, nombreCategoria);
		
		System.out.println("Cliente creado con datos agregados correctamente");
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
		
		System.out.println("Gracias por usar el sistema, nos vemos !");
		Utility.stopAndContinue();
		System.out.println("Sistema terminado de forma correcta");
		Utility.stopAndContinue();
		System.exit(0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}