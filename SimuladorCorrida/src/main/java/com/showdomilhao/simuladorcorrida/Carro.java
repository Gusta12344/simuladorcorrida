/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.showdomilhao.simuladorcorrida;

import java.util.Random;
/**
 *
 * @author gusta
 */
public class Carro implements Runnable {

    private final String nome;
    private final int velocidadeMax;
    private volatile int posicao = 0;
    private volatile boolean rodando = true;
    private final Corrida corrida;
    private final Corrida.LogCallback log;
    private final Random random = new Random();

    public Carro(String nome, int velocidadeMax, Corrida corrida, Corrida.LogCallback log) {
        this.nome = nome;
        this.velocidadeMax = velocidadeMax;
        this.corrida = corrida;
        this.log = log;
    }

    @Override
    public void run() {
        while (rodando && !corrida.isEncerrada()) {
            try {
                Thread.sleep(random.nextInt(300) + 100); // 100-400ms por passo
                int avanco = random.nextInt(velocidadeMax) + 1;
                posicao += avanco;
                log.log(nome + " avancou " + avanco + "m (Total: " + posicao + "m)");
                corrida.verificarVencedor(this);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void parar() { rodando = false; }

    public String getNome() { return nome; }
    public int getPosicao() { return posicao; }
    public void resetarPosicao() { posicao = 0; }
}