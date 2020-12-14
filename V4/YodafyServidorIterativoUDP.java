/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

/**
 *
 * @author Jony
 */
public class YodafyServidorIterativoUDP{

    public static void main(String [] args){
		DatagramSocket socketServidor;
                int puerto=8888;
                InetAddress direccion;
                DatagramPacket paquete;
                 byte[] bufer = new byte[256];
try {
    do{
    socketServidor = new DatagramSocket(puerto);
    
     //direccion = InetAddress.getByName("ei150142.ugr.es");
                
       //       paquete = new DatagramPacket(bufer, bufer.length, direccion, puerto);
                paquete = new DatagramPacket(bufer, bufer.length);
                
                        socketServidor.receive(paquete);
                        //paquete.getData();
                        direccion = paquete.getAddress();
                        int nuevo_puerto = paquete.getPort();
                        bufer = paquete.getData();
                        int tama = paquete.getLength();
                        
                       
                        
                        String s = new String();
                        String t = new String(bufer);
                        for(int i=0;i<(tama-1);i++){
			 s += t.charAt(i);
                        }
                        System.out.println(s /*+" "+paquete.getAddress()+" "+paquete.getPort()*/);
                        
                        t= yodaDo(s);
                        
                        bufer = t.getBytes();
                        paquete = new DatagramPacket(bufer, bufer.length, direccion, nuevo_puerto);
                        socketServidor.send(paquete);

                        socketServidor.close();
    } while(true);
} catch (IOException e) { 
        System.out.println("Error: no se pudo atender en el puerto "+puerto);
}
    }
    
    public static String yodaDo(String peticion) {
		// Desordenamos las palabras:
                Random random= new Random();
		String[] s = peticion.split(" ");
		String resultado="";
		
		for(int i=0;i<s.length;i++){
			int j=random.nextInt(s.length);
			int k=random.nextInt(s.length);
			String tmp=s[j];
			
			s[j]=s[k];
			s[k]=tmp;
		}
		
		resultado=s[0];
		for(int i=1;i<s.length;i++){
		  resultado+=" "+s[i];
		}
		
		return resultado;
	}
}


