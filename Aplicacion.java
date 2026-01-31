/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.vacunas;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author a19lucianabp
 */
public class Aplicacion {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		VacAlmacen almacenVacunas = new VacAlmacen();
		int opcionMenu = 0;
		Scanner input = new Scanner(System.in);

		do {
			menu();
			opcionMenu = ControlData.leerInt(input); // se lee de teclado una opcion
			if (opcionMenu >= 1 && opcionMenu <= 11) { // se comprueba que es una opcion correcta del menu

				switch (opcionMenu) {
				case 1:
					almacenVacunas.listarVacunas();
					break;
				case 2:
					System.out.print("Introduce el código de una vacuna:");
					String codigoVacuna = ControlData.leerString(input);
					Vacuna v = almacenVacunas.buscarVacuna(codigoVacuna);
					if (v == null) {
						System.out.println("No existe una vacuna con el código introducido.");
					} else {
						System.out.println("---Vacuna encontrada:");
						System.out.println(v.toString());
						System.out.println();
					}
					break;
				case 3:
					Vacuna vac = crearNuevaVacuna(input);
					almacenVacunas.agregar(vac);

					break;
				case 4:
					System.out.print("Introduce el código de la vacuna a eliminar:");
					String codigo = ControlData.leerString(input);
					almacenVacunas.eliminar(codigo);
					break;
				case 5:
					introducirResultadoFases(almacenVacunas, input);
					break;
				case 6:
					autorizarRechazarVacuna(almacenVacunas, input);
					break;
				case 7:
					almacenVacunas.verVacunasAutorizadas();
					break;
				case 8:
					almacenVacunas.verVacunasRechazadas();
					break;
				case 9:
					almacenVacunas.verVacunasPendientes();
					break;
				case 10:
					almacenVacunas.verUltimaFaseInvestigada();
					break;

				case 11:
					System.out.println("*** Fin.***");
					break;

				}

			} else {
				// no es una opcion correcta del menu
				System.out.println("*** Por favor, introduzca una opcion valida.***");
			}

		} while (opcionMenu != 11);

	}

	private static void autorizarRechazarVacuna(VacAlmacen almacenVacunas, Scanner input) {
		System.out.print("Introduce el código de una vacuna:");
		String codigoVacuna = ControlData.leerString(input);
		Vacuna vacuna = almacenVacunas.buscarVacuna(codigoVacuna);
		System.out.println(vacuna.toString());

		byte faseVacuna = vacuna.getFasesCompletadas();
		boolean resultadoFaseVacuna = vacuna.getResultadoUltimaFase();

		if ((faseVacuna == 3) && (resultadoFaseVacuna == true)) {

			System.out.println("Autorizar o Rechazar esta vacuna (a/r)?");
			char accion = ControlData.leerChar(input);
			if (accion == 'a') {
				vacuna.autorizar();
			} else if (accion == 'r') {
				vacuna.rechazar();
			} else {
				System.out.println("No se a introducido ni una 'a' ni una 'r'.");
				System.out.println("No se autoriza ni rechaza la vacuna.");
			}

		} else {
			System.out.println("Esta vacuna no ha superado las tres fases. No se puede autorizar.");

			System.out.println("Desea Rechazar esta vacuna (s/n)?");
			char accion = ControlData.leerChar(input);
			if (accion == 's') {
				vacuna.rechazar();
				System.out.println("Vacuna rechazada.");
			} else {
				System.out.println("Vacuna NO rechazada.");
			}

		}

	}

	private static void introducirResultadoFases(VacAlmacen almacenVacunas, Scanner input) {
		boolean resultadoFase;
		System.out.print("Introduce el código de una vacuna:");
		String codigoVacuna = ControlData.leerString(input);
		Vacuna vacuna = almacenVacunas.buscarVacuna(codigoVacuna);
		if (vacuna == null) {
			System.out.println("No existe una vacuna con el código introducido.");
		} else {
			byte fasesCompletadas = vacuna.getFasesCompletadas();
			if (fasesCompletadas == 3) {
				System.out.println("Las tres fases ya han sido completadas.");
				System.out.println("No se pueden introducir mas fases.");
			} else {
				boolean resultadoUltimaFase = vacuna.getResultadoUltimaFase();
				if (resultadoUltimaFase) {
					System.out.println("Introduce el resultado de la fase:" + (fasesCompletadas + 1));
					System.out.println("Aprobar (a)/Rechazar (r)");
					char resultado = ControlData.leerChar(input);

					if (resultado == 'a') {
						resultadoFase = true;
						vacuna.modificarFase((byte) (fasesCompletadas + 1), resultadoFase);
					}

					if (resultado == 'r') {
						resultadoFase = false;
						vacuna.modificarFase((byte) (fasesCompletadas + 1), resultadoFase);
					}

					if ((resultado != 'a') && (resultado != 'r')) {
						System.out.println("No se a introducido ni una 'a' ni una 'r'.");
						System.out.println("No se modifica el resultado de la fase " + (fasesCompletadas + 1)
								+ " de la vacuna: " + codigoVacuna);
					}

				} else {
					System.out.println("No se puede modificar la fase:" + (fasesCompletadas + 1));
					System.out.println("El resultado de la fase " + (fasesCompletadas) + " es:" + resultadoUltimaFase);
				}
			}
		}
	}

	private static Vacuna crearNuevaVacuna(Scanner input) {

		boolean codigoValido;
		
		System.out.println("Introduce el código de la nueva vacuna:");
		String codigo;
		do {			
			codigo = ControlData.leerString(input);
			codigoValido = validarCodigoVacuna(codigo);
			if (!codigoValido) {
				System.out.println("El código introducido no es valido.");
				System.out.println("Por favor, introduce un código valido:");
			}
		} while (!codigoValido);

		System.out.println("Introduce el nombre de la nueva vacuna:");
		String nombre = ControlData.leerString(input);

		System.out.println("Introduce el principio activo de la nueva vacuna:");
		String principioActivo = ControlData.leerString(input);

		System.out.println("Introduce el nombre de la farmaceutica de la nueva vacuna:");
		String farmaceutica = ControlData.leerString(input);

		System.out.println("Introduce el precio recomendado de la nueva vacuna:");
		double precioRecomendado = ControlData.leerDouble(input);

		return new Vacuna(codigo, nombre, principioActivo, farmaceutica, precioRecomendado);
	}

	private static boolean validarCodigoVacuna(String codigo) {

		String expReg = "^V[A,E,I,O,U][a-z]{3,4}([4-7]{2}|[8])";
		Pattern pat = Pattern.compile(expReg);
		Matcher mat = pat.matcher(codigo);

		if (mat.matches()) {
			return true;
		} else {
			return false;
		}

	}

	private static void menu() {
		System.out.println();
		System.out.println("**************GESTIÓN VACUNAS CONTRA EL COVID-19************");
		System.out.println("1- Listar Vacunas y mostrar datos");
		System.out.println("2- Buscar Vacuna");
		System.out.println("3- Agregar Vacuna");
		System.out.println("4- Eliminar vacuna");
		System.out.println("5- Introducir resultado de las fases de la vacuna");
		System.out.println("6- Autorización/Rechazar vacuna");
		System.out.println("7- Ver vacunas autorizadas");
		System.out.println("8- Ver vacunas rechazadas");
		System.out.println("9- Ver vacunas pendientes de autorizar/rechazar");
		System.out.println("10- Ver la última fase investida de cada vacuna almacenada");
		System.out.println("11- Salir");
		System.out.println("Introducir una opción:");
	}

}
