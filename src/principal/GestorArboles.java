package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GestorArboles {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/eh_garden", "root", "");
			
			//declaracion de constantes (final) para las opciones
			final int INSERTAR = 1;
			final int ELIMINAR = 2;
			final int MODIFICAR = 3;
			final int VISUALIZAR = 4;
			final int SALIR = 0;

			Scanner teclado = new Scanner(System.in);
			int opcion_menu;

			do {
				System.out.println("------MENU-------");
				System.out.println(INSERTAR + ". Insertar arbol");
				System.out.println(ELIMINAR + ". Eliminar arbol");
				System.out.println(MODIFICAR + ". modificar informacion del arbol");
				System.out.println(VISUALIZAR + ". visualizar arboles");
				System.out.println(SALIR + ". Salir");
				System.out.println("Elije una de las opciones");
				opcion_menu = Integer.parseInt(scan.nextLine());

				switch (opcion_menu) {
				case INSERTAR:
					System.out.println("primera opcion seleccionada\n");
					break;
				case ELIMINAR:
					System.out.println("segunda opcion seleccionada\n");
					break;
				case MODIFICAR:
					System.out.println("tercera opcion seleccionada\n");
					break;
				case VISUALIZAR:
					System.out.println("cuarta opcion seleccionada\n");
					break;
				case SALIR:
					System.out.println("ADIOS");
					break;
				default:
					System.out.println("Opcion incorrecta!");
				}

			} while (opcion_menu != SALIR);
			scan.close();
			
			Statement st = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}