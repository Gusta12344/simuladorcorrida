/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.showdomilhao.simuladorcorrida;

/**
 *
 * @author gusta
 */
public class Corrida {

    public interface LogCallback {
        void log(String msg);
    }

    private final int meta;
    private volatile boolean encerrada = false;
    private Carro vencedor = null;
    private final LogCallback logCallback;

    public Corrida(int meta, LogCallback logCallback) {
        this.meta = meta;
        this.logCallback = logCallback;
    }

    // synchronized garante que só o PRIMEIRO carro a chegar vence
    public synchronized void verificarVencedor(Carro carro) {
        if (!encerrada && carro.getPosicao() >= meta) {
            encerrada = true;
            vencedor = carro;
            logCallback.log("*** " + carro.getNome() + " VENCEU A CORRIDA! ***");
        }
    }

    public boolean isEncerrada() { return encerrada; }
    public int getMeta() { return meta; }
    public Carro getVencedor() { return vencedor; }
}
