# language: pt
# charset: UTF-8

Funcionalidade: Cadastro
  Eu como usuário gostaria de poder cadastrar um novo cliente.

  Contexto:
    Dado que eu acesso a pagina de cadastro

  @CT001
  Esquema do Cenario: Cenario: CT001 - Tentar cadastrar cliente com e-mail existente
    E preencho os dados de email "<email>" e senha para cadastro ""
    Quando eu confirmar o cadastro
    Entao devo ver a mensagem "<mensagem>"
    Exemplos:
      | email             | mensagem             |
      | teste@hotmail.com | E-mail já cadastrado |

  @CT002
  Esquema do Cenario: Cenario: CT002 - Tentar cadastrar cliente com senha fraca
    E preencho os dados de email "<email>" e senha para cadastro "<senha>"
    Quando eu confirmar o cadastro
    Entao devo ver a mensagem "<mensagem>"
    Exemplos:
      | email                      | mensagem    | senha |
      | rafael_desafio@hotmail.com | Senha Fraca | RL    |

  @CT003
  Esquema do Cenario: Cenario: CT003 - Tentar cadastrar cliente com cpf inválido
    E preencho os dados de email "<email>" e senha para cadastro "<senha>"
    E preencho o CPF "<cpf>"
    Quando eu confirmar o cadastro
    Entao devo ver a mensagem "<mensagem>"
    Exemplos:
      | cpf      | email                      | mensagem       | senha              |
      | 42155565 | rafael_desafio@hotmail.com | Campo inválido | desafioAmericas123 |

  @CT004
  Esquema do Cenario: Cenario: CT004 - Realizar cadastro de cliente com sucesso
    E preencho os dados de email "<email>" e senha para cadastro "<senha>"
    E preencho o CPF "<cpf>"
    E preencho os dados de nome, sobrenome, nascimento, sexo e telefone
    Quando eu confirmar o cadastro
    Entao serei direcionado para tela inicial e estarei logado automaticamente
    Exemplos:
      | cpf         | email                       | senha              |
      | 67897178030 | rafael_desafio1@hotmail.com | desafioAmericas123 |

