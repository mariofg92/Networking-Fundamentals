/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


/**
 *
 * 
 */
public class YodafyClienteUDP {
    public static void main(String[] args) {
		
		DatagramSocket socket;
                
		int puerto=8888;
                InetAddress direccion;
                DatagramPacket paquete;
                
                byte[] bufer;
                
                try {    
                socket = new DatagramSocket(); 
                bufer="Al monte del volcán debes ir sin demora".getBytes();
                
                direccion = InetAddress.getByName("localhost");
                
                paquete = new DatagramPacket(bufer, bufer.length, direccion, puerto);
                
                socket.send(paquete);
		
                
			paquete = new DatagramPacket(bufer, bufer.length);
                        socket.receive(paquete);
                        paquete.getData();
                        paquete.getAddress();
                        paquete.getPort();
                       
                        String t = new String(paquete.getData());
                        
                        System.out.println(t /*+" "+paquete.getAddress()+" "+paquete.getPort()*/);
                        
                        socket.close();
                        
			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
    
}
