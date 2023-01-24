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
			
			//declaracion de constantes (final) para las opciones
			final int INSERTAR = 1;
			final int ELIMINAR = 2;
			final int MODIFICAR = 3;
			final int VISUALIZAR = 4;
			final int SALIR = 0;

			Scanner teclado = new Scanner(System.in);
			int opcion_menu;

			PreparedStatement pstInsertar = con.prepareStatement("INSERT INTO arboles VALUES (null, ?, ?, ?, ?, ?)");
			Arbol arbol = new Arbol();
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
					arbol.setNombreComun(scan.nextLine());
					pstInsertar.setString(1, arbol.getNombreComun());
					
					System.out.println("Y su nombre cientifico?");
					arbol.setNombreCientifico(scan.nextLine());
					pstInsertar.setString(2, arbol.getNombreCientifico());
					
					System.out.println("En que habitat habita?");
					arbol.setHabitat(scan.nextLine());
					pstInsertar.setString(3, arbol.getHabitat());
					
					System.out.println("Cual es su altura");
					arbol.setAltura(Integer.parseInt(scan.nextLine()));
					pstInsertar.setInt(4, arbol.getAltura());
					
					System.out.println("De donde proviene?");
					arbol.setOrigen(scan.nextLine());
					pstInsertar.setString(5, arbol.getOrigen());
					pstInsertar.execute();
					
					break;
				case ELIMINAR:
					PreparedStatement pstEliminar = con.prepareStatement("DELETE FROM arboles WHERE id = ?");
					System.out.println("Eliminando arbol...\n");
					
					System.out.println("Introduce el ID del arbol a eliminar: ");
					arbol.setId(Integer.parseInt(scan.nextLine()));
					pstEliminar.setInt(1, arbol.getId());
					pstEliminar.execute();
					
					break;
				case MODIFICAR:
					System.out.println("Modificando informacion de un arbol...\n");
					
					System.out.println("Introduce el ID del arbol a modificar");
					arbol.setId(Integer.parseInt(scan.nextLine()));
					
					PreparedStatement pstNN = con.prepareStatement("UPDATE arboles SET nombre_comun = ? WHERE id = ?");
					System.out.println("Introduce nombre nuevo");
					arbol.setNombreComun(scan.nextLine());
					pstNN.setString(1, arbol.getNombreComun());
					pstNN.setInt(2, arbol.getId());
					pstNN.executeUpdate();
					
					PreparedStatement pstCN = con.prepareStatement("UPDATE arboles SET nombre_cientifico = ? WHERE id = ?");
					System.out.println("Introduce nombre cientifico nuevo");
					arbol.setNombreCientifico(scan.nextLine());
					pstCN.setString(1, arbol.getNombreCientifico());
					pstCN.setInt(2, arbol.getId());
					pstCN.executeUpdate();
					
					PreparedStatement pstH = con.prepareStatement("UPDATE arboles SET habitat = ? WHERE id = ?");
					System.out.println("Introduce habitat nuevo");
					arbol.setHabitat(scan.nextLine());
					pstH.setString(1, arbol.getHabitat());
					pstH.setInt(2, arbol.getId());
					pstH.executeUpdate();
					
					PreparedStatement pstA = con.prepareStatement("UPDATE arboles SET altura = ? WHERE id = ?");
					System.out.println("Introduce nueva altura");
					arbol.setAltura(Integer.parseInt(scan.nextLine()));
					pstA.setInt(1, arbol.getAltura());
					pstA.setInt(2, arbol.getId());
					pstA.executeUpdate();
					
					PreparedStatement pstO = con.prepareStatement("UPDATE arboles SET origen = ? WHERE id = ?");
					System.out.println("Introduce origen nuevo");
					arbol.setOrigen(scan.nextLine());
					pstO.setString(1, arbol.getOrigen());
					pstO.setInt(2, arbol.getId());
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