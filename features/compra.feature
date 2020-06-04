# language: pt
# charset: UTF-8

Funcionalidade: Fluxo de compra de produto
  Eu como usuário gostaria de poder buscar um produto e validar seu preço.

  @COMPRACT001
  Esquema do Cenario: Cenario: CT001 - Buscar e adicionar produtos na cesta validando o parcelamento.
    Dado que eu busco pelo produto "<produto>"
    E seleciono o primeiro retorno
    Quando clico em comprar
    Entao valido o "<produto>" no carrinho
    Exemplos:
      | produto  | parcelamento | valor |
      | Iphone 8 | 10x          | 5000  |