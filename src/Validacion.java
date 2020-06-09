import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validacion implements Runnable {
	private Integer indiceLinea;
	private List<String> lineaInputs;
	private SortedList indices;
	private Countdown cd;
	private Integer dimension;
	
	public Validacion(Integer indice, String lineaInputs, SortedList l, Countdown cd) {
		this.indiceLinea = indice;
		this.lineaInputs = new ArrayList<String>(Arrays.asList(lineaInputs.split(" ")));
		this.indices = l;
		this.cd = cd;
		this.dimension = new Integer(this.lineaInputs.get(0));
	}

	@Override
	public void run() {
		List<List<Integer>> cuadrado = this.leerCuadrado();
		
		boolean celdasValidas = this.validarCeldas(cuadrado);
		if (celdasValidas) {
			boolean filasValidas = Validacion.validarFilas(cuadrado);
			if (filasValidas) {
				boolean columnasValidas = this.validarColumnas(cuadrado);
				if (columnasValidas) {
					indices.agregarNro(this.indiceLinea);
				}
			}
		}
		
		cd.dec();
	}

	private boolean validarColumnas(List<List<Integer>> cuadrado) {
		boolean res = true;
		
		for (int i = 0; i < this.dimension; i++) {
			if (!res) {
				break;
			}
			
			List<Integer> columna = new ArrayList<Integer>();
			for (List<Integer> fila : cuadrado) {
				columna.add(fila.get(i));
			}
			for (Integer k : columna) {
				if (Validacion.apareceMasDeUnaVez(k, columna)) {
					res = false;
					break;
				}
			}
		}
		
		return res;
	}

	private static boolean validarFilas(List<List<Integer>> cuadrado) {
		boolean res = true;
		
		for (List<Integer> fila : cuadrado) {
			if (!res) {
				break;
			}
			
			for (Integer n : fila) {
				if (Validacion.apareceMasDeUnaVez(n, fila)) {
					res = false;
					break;
				}
			}
		}
		
		return res;
	}

	private static boolean apareceMasDeUnaVez(Integer n, List<Integer> fila) {
		List<Integer> temp = new ArrayList<Integer>();
		temp.addAll(fila);
		boolean res = false;
		
		for (Integer k : fila) {
			temp.remove(k);
			if (temp.remove(k)) {
				res = true;
				break;
			}
		}
		
		return res;
	}

	private boolean validarCeldas(List<List<Integer>> cuadrado) {
		boolean res = true;
		
		for (List<Integer> fila : cuadrado) {
			if (!res) {
				break;
			}
			
			for (Integer n : fila) {
				if ((n > this.dimension) || (n < 1)) {
					res = false;
					break;
				}
			}
		}
		
		return res;
	}

	protected List<List<Integer>> leerCuadrado() {
		List<List<Integer>> cuadrado = new ArrayList<List<Integer>>();
		
		Integer inicio = 1;
		for (int i = 0; i < this.dimension; i++) {
			List<Integer> fila = new ArrayList<Integer>();
			for (int z = 0; z < this.dimension; z++) {
				Integer celda = new Integer(this.lineaInputs.get(inicio + z));
				fila.add(celda);
			}
			inicio += this.dimension;
			cuadrado.add(fila);
		}
		
		return cuadrado;
	}
}
