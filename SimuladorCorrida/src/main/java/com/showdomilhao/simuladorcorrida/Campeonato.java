/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.showdomilhao.simuladorcorrida;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author gusta
 */
public class Campeonato {

    private final Map<String, Integer> pontos = new LinkedHashMap<>();

    public void registrarVitoria(String nomeCarro) {
        Integer pts = pontos.get(nomeCarro);
        if (pts == null) pts = 0;
        pontos.put(nomeCarro, pts + 1);
    }

    public String getClassificacao() {
        if (pontos.isEmpty()) return "Campeonato: sem corridas ainda";

        // Bubble sort por pontos (maior primeiro)
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(pontos.entrySet());
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(j).getValue() > lista.get(i).getValue()) {
                    Map.Entry<String, Integer> tmp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, tmp);
                }
            }
        }

        StringBuilder sb = new StringBuilder("Campeonato: ");
        for (Map.Entry<String, Integer> e : lista) {
            sb.append(e.getKey()).append(":").append(e.getValue()).append("pt(s)  ");
        }
        return sb.toString().trim();
    }

    public void salvarEmArquivo() {
        try (FileWriter fw = new FileWriter("ranking_corrida.txt")) {
            fw.write("=== RANKING DO CAMPEONATO ===\n");
            fw.write(getClassificacao() + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }
}
