/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Serviço de acesso às informações de configuração das regras
 * do SAEP. Uma instância dessa classe é obtida após carga e
 * pré-processamento.
 *
 * A estrutura de dados atualmente empregada tem como objetivo
 * permitir a experimentação. Pode ser substituída.
 */
public class Regras {

    public static final int PONTOS_POR_RELATO = 0;
    public static final int EXPRESSAO = 1;
    public static final int CONDICIONAL = 2;
    public static final int SOMATORIO = 3;
    public static final int MEDIA = 4;

    /**
     * Estratégia sugerida para substituir abaixo: vetor de bytes.
     */
    private List<Integer> tipo = new ArrayList<Integer>();
    private List<Float> pontos = new ArrayList<Float>();
    private List<String> sentenca = new ArrayList<String>();
    private List<String> variavel = new ArrayList<String>();
    private List<Integer> entao = new ArrayList<Integer>();
    private List<Integer> senao = new ArrayList<Integer>();

    /**
     * Define lista de variáveis que devem ser definidas para se avaliar
     * a expressão.
     */
    private Map<Integer, List<String>> dependencias = new HashMap<Integer, List<String>>();

    public Regras() {
        // CODIGO: 0
        tipo.add(PONTOS_POR_RELATO); // pontos por relato
        pontos.add(1.1f);
        sentenca.add("");
        variavel.add("x");
        dependencias.put(0, new ArrayList<String>(0));
        entao.add(-1);
        senao.add(-1);

        // CODIGO: 1
        tipo.add(EXPRESSAO); // pontos por relato
        pontos.add(0f);
        sentenca.add("8.97");
        variavel.add("y");
        dependencias.put(1, new ArrayList<String>(0));
        entao.add(-1);
        senao.add(-1);

        // CODIGO: 2
        tipo.add(EXPRESSAO); // pontos por relato
        pontos.add(0f);
        sentenca.add("x + y");
        variavel.add("z");
        ArrayList<String> utilizadas = new ArrayList<String>(0);
        utilizadas.add("x");
        utilizadas.add("y");
        dependencias.put(2, utilizadas);
        entao.add(-1);
        senao.add(-1);

        // CODIGO: 3
        tipo.add(CONDICIONAL);
        pontos.add(0f);
        sentenca.add("z > 10");
        variavel.add("c");
        ArrayList<String> empregadas = new ArrayList<String>(0);
        empregadas.add("z");
        dependencias.put(3, empregadas);
        entao.add(4);
        senao.add(5);

        // CODIGO: 4 (entao de 3)
        tipo.add(EXPRESSAO); // pontos por relato
        pontos.add(0f);
        sentenca.add("1");
        variavel.add("c"); // Mesmo da regra condicional
        dependencias.put(4, new ArrayList<String>(0));
        entao.add(-1);
        senao.add(-1);

        // CODIGO: 5 (senao de 3)
        tipo.add(EXPRESSAO); // pontos por relato
        pontos.add(0f);
        sentenca.add("0");
        variavel.add("c"); // Mesmo da regra condicional
        dependencias.put(5, new ArrayList<String>(0));
        entao.add(-1);
        senao.add(-1);

        // CODIGO: 6
        tipo.add(SOMATORIO);
        pontos.add(0f);
        sentenca.add("(c + 1) * 2");
        variavel.add("s"); // Mesmo da regra condicional
        ArrayList<String> deps = new ArrayList<String>(0);
        deps.add("c");
        dependencias.put(6, deps);
        entao.add(-1);
        senao.add(-1);
    }

    /**
     * Recupera o tipo da sentença.
     *
     * @param codigo O código da sentença.
     *
     * @return O inteiro que identifica o tipo
     * da sentença cujo código é fornecido.
     */
    public int getTipo(int codigo) {
        return tipo.get(codigo);
    }

    /**
     * Recupera a quantidade de pontos atribuída
     * para cada relato.
     *
     * @param codigo O código único da sentença que
     *               avalia o tipo de relato em
     *               questão.
     *
     * @return Total de pontos atribuído para cada
     * relato avaliada pela sentença cujo código é
     * fornecido.
     */
    public float getPontosPorRelato(int codigo) {
        return pontos.get(codigo);
    }

    /**
     * Recupera a sentença correspondente ao código. Em
     * regra condicional, a sentença é a condição.
     *
     * @param codigo O código da sentença.
     *
     * @return A sentença ou expressão associada ao código.
     */
    public String getSentenca(int codigo) {
        return sentenca.get(codigo);
    }

    /**
     * Recupera a sentença "então" correspondente ao código.
     *
     * @param codigo O código da sentença.
     *
     * @return A sentença ou expressão associada ao código.
     */
    public int getCodigoSentencaEntao(int codigo) {
        return entao.get(codigo);
    }

    /**
     * Recupera a sentença "senão" correspondente ao código.
     *
     * @param codigo O código da sentença.
     *
     * @return A sentença ou expressão associada ao código.
     */
    public int getCodigoSentencaSenao(int codigo) {
        return senao.get(codigo);
    }

    /**
     * Recupera o nome da variável associado ao resultado
     * da avaliação da sentença cujo código é fornecido.
     *
     * @param codigo O código único da sentença.
     *
     * @return O nome da variável.
     */
    public String getVariavel(int codigo) {
        return variavel.get(codigo);
    }

    public List<String> getDependencias(int codigo) {
        return dependencias.get(codigo);
    }
}
