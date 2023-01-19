package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorArboles {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/eh_garden", "root", "");
			
			PreparedStatement st = null;
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
					System.out.println("Insertando arbol...\n");
					
					System.out.println("Cual es su nombre?");
					String nombreArbol = teclado.nextLine();
					st.execute("INSERT INTO arboles (nombre_comun) VALUES ('" + nombreArbol + "')");
					
					System.out.println("Y su nombre cientifico?");
					String nombreCientifico = teclado.nextLine();
					st.execute("INSERT INTO arboles (nombre_cientifico) VALUES ('" + nombreCientifico + "')");
					
					System.out.println("En que habitat habita?");
					String habitatArbol = teclado.nextLine();
					st.execute("INSERT INTO arboles (habitat) VALUES ('" + habitatArbol + "')");
					
					System.out.println("Cual es su altura");
					int alturaArbol = teclado.nextInt();
					st.execute("INSERT INTO arboles (altura) VALUES ('" + alturaArbol + "')");
					
					System.out.println("De donde proviene?");
					String origenArbol = teclado.nextLine();
					st.execute("INSERT INTO arboles (origen) VALUES ('" + origenArbol + "')");
					
					break;
				case ELIMINAR:
					System.out.println("Eliminando arbol...\n");
					
					System.out.println("Introduce el ID del arbol a eliminar: ");
					int idEliminar = teclado.nextInt();
					st.execute("DELETE FROM arboles WHERE id = '" + idEliminar + "'");
					
					break;
				case MODIFICAR:
					System.out.println("Modificando informacion de un arbol...\n");
					
					System.out.println("Introduce el ID del arbol a modificar");
					int idModificar = teclado.nextInt();
					
					System.out.println("Introduce nombre nuevo");
					String nombreNuevo = teclado.nextLine();
					st.execute("UPDATE arboles SET nombre_comun='" + nombreNuevo + "' WHERE id = '" + idModificar + "'");
					
					System.out.println("Introduce nombre cientifico nuevo");
					String nombreCientificoNuevo = teclado.nextLine();
					st.execute("UPDATE arboles SET nombre_cientifico='" + nombreCientificoNuevo + "' WHERE id = '" + idModificar + "'");
					
					System.out.println("Introduce habitat nuevo");
					String habitatNuevo = teclado.nextLine();
					st.execute("UPDATE arboles SET habitat='" + habitatNuevo + "' WHERE id = '" + idModificar + "'");
					
					System.out.println("Introduce nueva altura");
					String alturaNuevo = teclado.nextLine();
					st.execute("UPDATE arboles SET altura='" + alturaNuevo + "' WHERE id = '" + idModificar + "'");
					
					System.out.println("Introduce origen nuevo");
					String origenNuevo = teclado.nextLine();
					st.execute("UPDATE arboles SET origen='" + origenNuevo + "' WHERE id = '" + idModificar + "'");
					
					break;
				case VISUALIZAR:
					System.out.println("Visualizando arboles...\n");
					
					String sentenciaSelect = "SELECT * FROM arboles";
					ResultSet resultado = st.executeQuery(sentenciaSelect);
					while (resultado.next()) {
						System.out.println(resultado.getInt(1) + ". " + resultado.getString(2));
					}
					break;
				case SALIR:
					System.out.println("ADIOS");
					break;
				default:
					System.out.println("Opcion incorrecta!");
				}

			} while (opcion_menu != SALIR);
			scan.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}