package br.com.alura.tabelafipe.principal;

import br.com.alura.tabelafipe.models.Dados;
import br.com.alura.tabelafipe.models.Modelos;
import br.com.alura.tabelafipe.models.Veiculo;
import br.com.alura.tabelafipe.service.ConsumoApi;
import br.com.alura.tabelafipe.service.Conversor;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private Conversor conversor = new Conversor();

    public void menu(){

        var menu = """
                *** OPÇÕES ***
                Carros
                Motos
                Caminhões 
                
                Digite uma das opções para consultar:       
                """;

        System.out.println(menu);
        var entrada = scanner.nextLine().toLowerCase();
        var entradaFormatada = removerAcentuacao(entrada);

        String tipoVeiculo;

        if (entrada.contains("car")){
            tipoVeiculo = URL_BASE + "carros/marcas";
        } else if (entrada.contains("mot")) {
            tipoVeiculo = URL_BASE + "motos/marcas";
        } else {
            tipoVeiculo = URL_BASE + "caminhoes/marcas";
        }

        var json = consumoApi.obtemDados(tipoVeiculo);
        System.out.println(json);


        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o codigo da marca para consulta: ");
        var codigoMarca = scanner.nextLine();
        tipoVeiculo = tipoVeiculo + "/" + codigoMarca + "/modelos";
        json = consumoApi.obtemDados(tipoVeiculo);
        var modeloLista = conversor.obterDados(json, Modelos.class);
        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("\n Digite um trecho do nome do carro a ser buscado: ");
        var nomeVeiculo = scanner.nextLine().toLowerCase();
        var nomeVeiculoFormatado = removerAcentuacao(nomeVeiculo);

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculoFormatado))
                .collect(Collectors.toList());
        System.out.println("Modelos filtrados");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("\nDigite por favor do modelo para buscar valores de avaliação:");
        var codigoModelo = scanner.nextLine();

        tipoVeiculo = tipoVeiculo + "/" + codigoModelo + "/anos";
        json = consumoApi.obtemDados(tipoVeiculo);

        List<Dados> anos = conversor.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = tipoVeiculo + "/" + anos.get(i).codigo();
            json = consumoApi.obtemDados(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veículos encontrados com avaliações por ano:");
        veiculos.forEach(System.out::println);

    }

    public String removerAcentuacao(String palavra){
        String normalized = Normalizer.normalize(palavra, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");

//        Como isso funciona:
//
//        Entrada: "Café"
//        Normalização: O método Normalizer.normalize("Café", Normalizer.Form.NFD) transforma a string em "Cafe\u0301"
//        (onde \u0301 é o caractere de combinação diacrítica para o acento agudo).

//        Remoção de acentuações: A expressão regular \\p{InCombiningDiacriticalMarks}+ encontra \u0301
//        e replaceAll substitui por uma string vazia, resultando em "Cafe".

//        Essa é a explicação detalhada da linha de código Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
//        e seu papel na remoção de acentuações em palavras.

    }

}
