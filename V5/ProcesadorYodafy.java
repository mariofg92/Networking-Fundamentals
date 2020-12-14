//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;


//
// Nota: si esta clase extendiera la clase Thread, y el procesamiento lo hiciera el método "run()",
// ¡Podríamos realizar un procesado concurrente! 
//
public class ProcesadorYodafy extends Thread{
	// Referencia a un socket para enviar/recibir las peticiones/respuestas
	private Socket socketServicio;
	// stream de lectura (por aquí se recibe lo que envía el cliente)
	private InputStream inputStream;
	// stream de escritura (por aquí se envía los datos al cliente)
	private OutputStream outputStream;
	
	// Para que la respuesta sea siempre diferente, usamos un generador de números aleatorios.
	private Random random;
        
        int num;
	
	// Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
	public ProcesadorYodafy(Socket socketServicio, int numero_a_adivinar) {
		this.socketServicio=socketServicio;
		random=new Random();
                num = numero_a_adivinar;
	}
	
	
	// Aquí es donde se realiza el procesamiento realmente:
        @Override
	public void run(){
		
		// Como máximo leeremos un bloque de 1024 bytes. Esto se puede modificar.
		byte [] datosRecibidos=new byte[1024];
		int bytesRecibidos=0;
		
		// Array de bytes para enviar la respuesta. Podemos reservar memoria cuando vayamos a enviarka:
		byte [] datosEnviar;
		String respuesta;
		
		try {
			// Obtiene los flujos de escritura/lectura
			inputStream=socketServicio.getInputStream();
			outputStream=socketServicio.getOutputStream();
			
			// Lee la frase a Yodaficar:
			////////////////////////////////////////////////////////
			bytesRecibidos= inputStream.read(datosRecibidos);
			////////////////////////////////////////////////////////
			
			// Creamos un String a partir de un array de bytes de tamaño "bytesRecibidos":
			String adivinanza=new String(datosRecibidos,0,bytesRecibidos);
			int intento = Integer.parseInt(adivinanza);
                        
                        if(num>intento)
                            respuesta="MAYOR";
                        else if(num<intento)
                            respuesta="MENOR";
                        else
                            respuesta="GANAS";
                        
			// Convertimos el String de respuesta en una array de bytes:
			datosEnviar=respuesta.getBytes();
			
			// Enviamos la traducción de Yoda:
			////////////////////////////////////////////////////////
			outputStream.write(datosEnviar, 0, datosEnviar.length);
			////////////////////////////////////////////////////////
			outputStream.flush();
			
			
		} catch (IOException e) {
			System.err.println("Error al obtener los flujos de entrada/salida.");
		}

	}
}
