package controller;

import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {
	private int saldo;
	private int depsaq;
	private int transacao;
	private int cod;
	private Semaphore semaforo1;
	private Semaphore semaforo2;

	public ThreadBanco(int saldo, int transacao, Semaphore semaforo1,Semaphore semaforo2, int cod, int depsap) {
		this.semaforo1 = semaforo1;
		this.semaforo2 = semaforo2;
		this.saldo = saldo;
		this.transacao = transacao;
		this.cod = cod;
		this.depsaq = depsap;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		operacao();
	}

	private void operacao() {
		int ind = depsaq;
		int indentificador = ind % 2;
		switch (indentificador) {
		case 0:
			System.out.println("Fazendo a operação de deposito.");
			try {
				semaforo1.acquire();
				deposito();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo1.release();
			}
		case 1:
			System.out.println("Fazendo a operação de saque.");
			try {
				semaforo2.acquire();
				saque();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo2.release();
			}
			break;
		}
	}

	private void saque() {
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A conta " + cod + " esta sacando " + "R$" + transacao + ",00");
		saldo -= transacao;
		System.out.println("Saque feito com sucesso\n saldo: " + "R$" + saldo);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void deposito() {
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A conta " + cod + " esta depositando " + "R$" + transacao + ",00");
		saldo += transacao;
		System.out.println("Deposito feito com sucesso\n saldo: " + "R$" + saldo);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
