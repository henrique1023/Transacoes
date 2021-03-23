package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBanco;

public class Banco {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo1 = new Semaphore(permissoes);
		Semaphore semaforo2 = new Semaphore(permissoes);
		for(int usuario=1; usuario<=20;usuario++) {
			int saldo = (int)((Math.random() * 10000) + 10);
			int transacao =  (int)((Math.random() * 5000) + 10);
			int cod = (int)((Math.random() * 5000) + 10);
			Thread tUsuario = new ThreadBanco(saldo, transacao, semaforo1, semaforo2,cod, usuario);
			tUsuario.start();
		}
	}

}
