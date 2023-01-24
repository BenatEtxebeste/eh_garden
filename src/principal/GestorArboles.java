package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestorArboles {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/eh_garden", "root", "");
			
			PreparedStatement pstInsertar = con.prepareStatement("INSERT INTO arboles VALUES (null, ?, ?, ?, ?, ?)");
			PreparedStatement pstEliminar = con.prepareStatement("DELETE FROM arboles WHERE id = ?");
			PreparedStatement pstNN = con.prepareStatement("UPDATE arboles SET nombre_comun = ? WHERE id = ?");
			PreparedStatement pstCN = con.prepareStatement("UPDATE arboles SET nombre_cientifico = ? WHERE id = ?");
			PreparedStatement pstH = con.prepareStatement("UPDATE arboles SET habitat = ? WHERE id = ?");
			PreparedStatement pstA = con.prepareStatement("UPDATE arboles SET altura = ? WHERE id = ?");
			PreparedStatement pstO = con.prepareStatement("UPDATE arboles SET origen = ? WHERE id = ?");
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
					pstInsertar.setString(1, nombreArbol);
					
					System.out.println("Y su nombre cientifico?");
					String nombreCientifico = teclado.nextLine();
					pstInsertar.setString(2, nombreCientifico);
					
					System.out.println("En que habitat habita?");
					String habitatArbol = teclado.nextLine();
					pstInsertar.setString(3, habitatArbol);
					
					System.out.println("Cual es su altura");
					int alturaArbol = teclado.nextInt();
					pstInsertar.setString(4, habitatArbol);
					
					System.out.println("De donde proviene?");
					String origenArbol = teclado.nextLine();
					pstInsertar.setString(5, origenArbol);
					
					break;
				case ELIMINAR:
					System.out.println("Eliminando arbol...\n");
					
					System.out.println("Introduce el ID del arbol a eliminar: ");
					int idEliminar = teclado.nextInt();
					pstEliminar.setInt(1, idEliminar);
					
					break;
				case MODIFICAR:
					System.out.println("Modificando informacion de un arbol...\n");
					
					System.out.println("Introduce el ID del arbol a modificar");
					int idModificar = teclado.nextInt();
					
					System.out.println("Introduce nombre nuevo");
					String nombreNuevo = teclado.nextLine();
					pstNN.setString(1, nombreNuevo);
					pstNN.setInt(2, idModificar);
					pstNN.executeUpdate();
					
					System.out.println("Introduce nombre cientifico nuevo");
					String nombreCientificoNuevo = teclado.nextLine();
					pstCN.setString(1, nombreCientificoNuevo);
					pstCN.setInt(2, idModificar);
					pstCN.executeUpdate();
					
					System.out.println("Introduce habitat nuevo");
					String habitatNuevo = teclado.nextLine();
					pstH.setString(1, habitatNuevo);
					pstH.setInt(2, idModificar);
					pstH.executeUpdate();
					
					System.out.println("Introduce nueva altura");
					String alturaNuevo = teclado.nextLine();
					pstA.setString(1, alturaNuevo);
					pstA.setInt(2, idModificar);
					pstA.executeUpdate();
					
					System.out.println("Introduce origen nuevo");
					String origenNuevo = teclado.nextLine();
					pstO.setString(1, origenNuevo);
					pstO.setInt(2, idModificar);
					pstO.executeUpdate();
					
					break;
				case VISUALIZAR:
					System.out.println("Visualizando arboles...\n");
					
					String sentenciaSelect = "SELECT * FROM arboles";
					ResultSet resultado = pstInsertar.executeQuery(sentenciaSelect);
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