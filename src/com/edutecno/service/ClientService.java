package com.edutecno.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.edutecno.modelo.CategoryEnum;
import com.edutecno.modelo.Client;

public class ClientService {

	private List<Client> listaClientes;

	public ClientService(List<Client> listaClientes) {
		super();
		this.listaClientes = new ArrayList<Client>();
	}

	public List<Client> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Client> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Client> listarClientes() {

		if (listaClientes.size() != 0) {

			for (Client cliente : listaClientes) {
				System.out.println("-----Datos de clientes-----");
				System.out.println("El RUT del Cliente: " + cliente.getRunCliente());
				System.out.println("El Nombre del Cliente: " + cliente.getNombreCliente());
				System.out.println("El Apellido del Cliente: " + cliente.getApellidoCliente());
				System.out.println("Los Años del Cliente: " + cliente.getAniosCliente());
				System.out.println("La categoria actual del Cliente: " + cliente.getNombreCategoria());
			}

			return listaClientes;

		} else {
			System.out.println("No se encuentran datos de nuevos clientes");

			return null;
		}

	}

	public void agregarCliente(String runCliente, String nombreCliente, String apellidoCliente, Integer aniosCliente,
			CategoryEnum nombreCategoria) {

		Client cliente = new Client(runCliente, nombreCliente, apellidoCliente, aniosCliente, nombreCategoria);

		if (listaClientes != null) {
			listaClientes.add(cliente);
			System.out.println("Datos de nuevo cliente agregados con EXITO");
		} else {
			System.out.println("La lista es nula: No tiene atributos. Por favor, agregue atributos");
		}

	}

	public void editarClienteSvc(List<Client> listaClientes) {

		System.out.println("Seleccione que desea hacer");
		System.out.println("1.- Editar los datos del cliente ");
		System.out.println("2.- Cambiar el estado del cliente");
		System.out.println("Ingrese opcion:  ");

		Scanner sc = new Scanner(System.in);
		int opcion = sc.nextInt();

		if (opcion == 1) {

			System.out.println("Selecciona rut de cliente a cambiar");
			// listaClientes = new ArrayList<Cliente>();

			String opcion2 = sc.next();
			for (Client cliente2 : listaClientes) {

				// System.out.println(cliente2);
				// System.out.println(cliente2.getRunCliente());

				if (opcion2.equals(cliente2.getRunCliente())) {
					System.out.println("Se ha encontrado" + opcion2);
					System.out.println("-----");
					System.out.println("Se ha encontrado " + cliente2.getRunCliente());

					// com.edutecno.modelo.Cliente.Cliente(String runCliente, String nombreCliente,
					// String apellidoCliente,
					// String aniosCliente, CategoriaEnum nombreCategoria)

					System.out.println("Escriba el nuevo RUT del Cliente");
					cliente2.setRunCliente(sc.next());
					System.out.println("Escriba el nuevo nombre del Cliente");
					cliente2.setNombreCliente(sc.next());
					System.out.println("Escriba el nuevo apellido del Cliente");
					cliente2.setApellidoCliente(sc.next());
					System.out.println("Escriba el nuevo años del Cliente");
					cliente2.setAniosCliente(sc.nextInt());

					System.out.println(cliente2);

				}

			}

		} else if (opcion == 2) {

			System.out.println("Selecciona rut de cliente a cambiar");
			String opcion2 = sc.next();

			for (Client cliente3 : listaClientes) {

				if (opcion2.equals(cliente3.getRunCliente())) {

					System.out.println("Se ha encontrado" + opcion2);
					System.out.println("-----");
					System.out.println("Se ha encontrado " + cliente3.getRunCliente());

					System.out.println("Escoga el nuevo estado del cliente " + cliente3.getNombreCliente());
					System.out.println("Para que el cliente pase a ACTIVO escriba 1");
					System.out.println("Para que el cliente pase a INACTIVO escriba 2");

					String opcionParaEnum = sc.next();

					if (opcionParaEnum.equals("1")) {
						cliente3.setNombreCategoria(CategoryEnum.Activo);
						System.out.println("El estado del cliente ha sido cambiado con exito");
						System.out.println(cliente3.toString());
					}

					if (opcionParaEnum.equals("2")) {
						cliente3.setNombreCategoria(CategoryEnum.Inactivo);
						System.out.println("El estado del cliente ha sido cambiado con exito");
						System.out.println(cliente3.toString());
					}
				}
			}

		} else {
			System.out.println("Please, choose a valid option.");
		}

	}

}
