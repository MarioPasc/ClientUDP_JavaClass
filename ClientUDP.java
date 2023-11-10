/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author pinilla
 */
public class ClientUDP {
	public static void main(String[] args) throws IOException {
		String serverName = args[0];
		// String serverName = "127.0.0.1"; //direccion local
		int portNumber = Integer.parseInt(args[1]);
		// int serverPort = 54322;
		DatagramSocket serviceSocket = null;
		InetAddress serverAddress = InetAddress.getByName(args[0]);

		/* COMPLETAR crear socket */
		serviceSocket = new DatagramSocket();

		/* INICIALIZA ENTRADA POR TECLADO */
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String userInput;
		System.out.println("Introduzca un texto a enviar (END-UDP para acabar)");
		userInput = stdIn.readLine(); /* CADENA ALMACENADA EN userInput */

		/* COMPLETAR Comprobar si el usuario quiere terminar servicio */
		while (userInput.compareTo("END") != 0) {
			/* COMPLETAR Crear datagrama con la cadena escrito en el cuerpo */
			byte[] sendData = userInput.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, portNumber);

			try {

				/* COMPLETAR Enviar datagrama a trav�s del socket */
				serviceSocket.send(sendPacket);

			} catch (IOException iOException) {
			}

			System.out.println("STATUS: Waiting for the reply");

			/* COMPLETAR Crear e inicializar un datagrama VACIO para recibir la respuesta */
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

			/* COMPLETAR Recibir datagrama de respuesta */
			serviceSocket.receive(receivePacket);

			/* COMPLETAR Extraer contenido del cuerpo del datagrama en variable line */
			String line = new String(receivePacket.getData(), 0, receivePacket.getLength());

			System.out.println("echo: " + line);
			System.out.println("Introduzca un texto a enviar (END para acabar)");
			userInput = stdIn.readLine();
		}

		System.out.println("STATUS: Closing lient");

		/* COMPLETAR Cerrar socket cliente */

		System.out.println("STATUS: closed");
	}
}
