# Consultando a tabela FIPE

Aplicação para consultar o valor médio de veículos (carros, motos ou caminhões) de acordo com a tabela FIPE, que pode ser acessada através [desse site](https://veiculos.fipe.org.br/).

- A consulta aos valores dos veículos pelo site tem o seguinte fluxo:
- Primeiramente é necessário escolher o tipo do veículo: carro, moto ou caminhão.

![image](https://github.com/phelipemon/tabela-fipe/blob/main/assets/Screenshot_1.jpg)


- Depois disso, é necessário preencher a MARCA, MODELO e ANO para consulta.

![image](https://github.com/phelipemon/tabela-fipe/blob/main/assets/Screenshot_2.jpg)


- Por fim, é exibida a avaliação apenas daquele ano escolhido.

  ![image](https://github.com/phelipemon/tabela-fipe/blob/main/assets/Screenshot_3.jpg)



  ## 🔨 Objetivos do projeto
  - O objetivo do projeto é ter um fluxo similar ao que é feito no site, porém com algumas melhorias.
  - Projeto Spring com linha de comando, utilizando a classe Scanner para fazer interações com o usuário via terminal.
  - O usuário digita o tipo de veículo desejado (carro, caminhão ou moto).
  - Feito isso, é listado todas as marcas daquele tipo de veículo, solicitando que o usuário escolha uma marca pelo código.
  - Após essa escolha, listaremos todos os modelos de veículos daquela marca.
  - Solicitaremos que o usuário digite um trecho do modelo que ele quer visualizar, por exemplo **PALIO**.
  - Listaremos apenas os modelos que tiverem a palavra **PALIO** no nome.
  - Usuário escolherá um modelo específico pelo código e, diferente do site, já listaremos as avaliações para **TODOS** os anos disponíveis daquele modelo, retornando uma lista de forma similar à imagem abaixo:

![image](https://github.com/jacqueline-oliveira/3257-java-desafio/assets/66698429/3d0ac772-3eff-4bad-a1fd-e7c2f34a39bc)

## Observações:

- Para realização do desafio faremos o consumo de uma API, documentada [nesse link](https://deividfortuna.github.io/fipe/).
- Biblioteca Jackson utilizada para a desserialização dos dados.

