package Concurrencia;

import java.awt.*;
import java.util.concurrent.Semaphore;
import java.util.Scanner;

public class Monitor {
	
	
	private Rectangle rectangulo,rec1,rec2,rec3,rec4;		//rectangulo del monitor y los subrectangulos(4)
	private int[][] incidencia;   //Matriz de incidencia	
	public int[] marcado;    //Vector de marcado actual
	int ID;
	//Semáforo de ingreso al monitor
	private Semaphore ingreso = new Semaphore(1,true);
	//Cola para los hilos que comienzan las líneas de producción (L1R1, L2R2(1), L3R3(1), L3R3(2))
	private Semaphore colaInicial = new Semaphore(0,true);
	//Cola para los demás hilos
	private Semaphore colaComun = new Semaphore(0,true);
	
public Monitor(int i){				//el argumento indica al constructor cual matriz va a cargar
									// me parece q la carga de las matrices conviene hacerlas por aca manualmente, 
									//ya que no esta como requerimiento q se agreguen mas manzanas, entonces los monitores
									//serian siempre los mismos
	ID = i;
	switch (i) {
	case 0: {incidencia =null ; marcado= null;
			rectangulo = new Rectangle (0,0,0,0);}
	case 1 :{incidencia =null ; marcado= null;
			rectangulo = new Rectangle (0,0,0,0);}
	case 2: {incidencia =null ; marcado= null;
			rectangulo = new Rectangle (0,0,0,0);}
	case 3 :{incidencia =null ; marcado= null;
			rectangulo = new Rectangle (0,0,0,0);}
	case 4: {incidencia =null ; marcado= null; 						//ACA CARGAR LAS MATRICES DE CADA MONITOR!
			rectangulo = new Rectangle (0,0,0,0);}					//Y TMB LAS COORDENADAS DEL RECTANCULO DE CADA MONITOR!!
	case 5: {incidencia =null ; marcado= null;
			rectangulo = new Rectangle (0,0,0,0);}
	case 6: {incidencia =null ; marcado= null;
			rectangulo = new Rectangle (0,0,0,0);}
	case 7: {incidencia =null ; marcado= null;
			rectangulo = new Rectangle (0,0,0,0);}
	case 8: {incidencia =null ; marcado= null;
			rectangulo = new Rectangle (0,0,0,0);}
	
	}
	}
	
	
	
public void SubRect(){						//IMPLEMENTAR metodo para crear los subrectangulos (rec1/2/3/4 usando de alguna forma
											// las coordenadas del rectangulo grande.. o puede ser manual de ultima..
	
}

public void transicion(int tipoHilo, int transicion){
	
	//Entra al monitor
	ingreso.acquireUninterruptibly(); 
	
	//El valor de "transicion" representa la transición a disparar
	//transicion = X significa que quiero disparar TX
	//HAY QUE TENER ORDENADAS LAS COLUMNAS DE LA MATRIZ DE INCIDENCIA DEL PIPE
	
	//Creamos un vector cuyo tamaño sea el número de transiciones, que es el número de columnas
	//de la matriz de incidencia
	int[] vectorT = new int[incidencia[0].length];
	
	for(int i = 0; i < incidencia[0].length; i++){
		vectorT[i] = 0;
	}
	
	vectorT[transicion] = 1;
	
	//Para saber si la transición está habilitada, el monitor multiplica la matriz de incidencia
	//por el vector de disparo, y lo suma al vector de marcado actual. Si algún elemento del vector
	//resultante es menor a cero, no está habilitada esa transición. De lo contrario, el marcado
	//actual es reemplazado por el vector resultante.
	
	marcado = sensibilidad(incidencia, vectorT, tipoHilo);
	
	//Liberar Cola
	liberarCola();
		
	//Libero el monitor
	ingreso.release();
	
	return;
}

private void liberarCola() {
	//La cola común tiene prioridad, así evitamos el interbloqueo
	if(colaComun.hasQueuedThreads()){
		colaComun.release();
	} else {
		colaInicial.release();
	}	
}

private int[] sensibilidad(int[][] incidencia, int[] vectorT, int tipoHilo) {
	boolean habilitado = false;
	int[] producto;
	int[] resultado = null;
	
	while(!habilitado){
		boolean negativo = false; //Bandera que detecta valores negativos en el vector
		
		producto = matriz_por_vector(incidencia, vectorT);
		resultado = suma_vectorial(producto, marcado);
		
		for(int j = 0; j < resultado.length; j++){
			if(resultado[j] < 0){
				negativo = true;
			}
		}
		
		//Finalizado el cálculo, si la transición está habilitada modifico el marcado, y despierto
		//al hilo asociado. Caso contrario, el hilo actual va a ir a su respectiva cola a esperar.
		if(!negativo){
			habilitado = true;
		} else{
		//Aquí mando el hilo a la cola que corresponda, dependiendo si es un hilo de incio de línea
		//o un hilo común. También libero el semáforo de ingreso a monitor.
			if(tipoHilo == 1){
				ingreso.release();
				colaInicial.acquireUninterruptibly();
				ingreso.acquireUninterruptibly(); //Me voy al final de la cola de ingreso al monitor
			}
			else if(tipoHilo == 0){
				ingreso.release();
				colaComun.acquireUninterruptibly();
				ingreso.acquireUninterruptibly(); //Me voy al final de la cola de ingreso al monitor
			}
		}
	}	
	
	return resultado;
}

private int[] matriz_por_vector(int[][] matriz, int[] vector){
	//El vector resultante tiene un tamaño igual al número de plazas, que es el número de filas
	//de la matriz de incidencia
	int[] aux = new int[incidencia.length];
	int suma = 0;
	
	for(int i=0; i< matriz.length; i++){
		for(int j=0; j<matriz[0].length; j++){
			suma = suma + matriz[i][j]*vector[j];
		}
		aux[i] = suma;
		suma = 0;
	}
	return aux;
}

private int[] suma_vectorial(int[] vector1, int[] vector2){
	int[] aux = new int[vector1.length];
	
	for(int i=0; i<vector1.length; i++){
		aux[i] = vector1[i] + vector2[i];
	}
	
	return aux;
}

}
