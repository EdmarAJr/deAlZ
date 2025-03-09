
# Sistema de DeAlZ e-commerce

## Overview

Este projeto é um sistema de e-commerce em Java, desenvolvido para gerenciar produtos, usuários e pedidos. Ele implementa funcionalidades de cadastro, autenticação e processamento de pedidos, além de fornecer relatórios específicos para administradores. O sistema foi desenvolvido utilizando princípios de Programação Orientada a Objetos (POO).

## Estrutura do projeto

O projeto está organizado da seguinte forma:

```
..\DeAlZ\app\src\main\java\br\edu\ifba\inf008\app
    ├── data
        ├── FileRegister.java
    ├── model
        ├── Administrator.java
        ├── Customer.java
        ├── Order.java
        ├── Product.java
        ├── ShoppingCart.java
        ├── User.java
    ├── shell
        ├── screen
           ├── AdministratorMenu.java
           ├── CustomerMenu.java
        ├── Authenticator.java
        ├── UserUi.java
    ├── App.java
..\DeAlZ\interfaces\src\main\java\br\edu\ifba\inf008\interfaces
    ├── interfaces.java
```

## Funcionalidades

1. **Gerenciamento de produtos**
   - Cadastro e obtenção de produtos.
   - Atributos: id, nome, descrição, preço, quantidade em estoque, categoria.

2. **Gerenciamento de usuários**
   - Cadastro de usuários (clientes e administradores).
   - Atributos: id, nome, email, senha.
   - Clientes têm informações adicionais como endereço de entrega e histórico de compras.

3. **Processamento de pedidos**
   - Clientes podem adicionar produtos ao carrinho e finalizar pedidos.
   - Atualização do estoque, geração de número de pedido, cálculo do total e associação ao histórico do cliente.

4. **Relatórios (somente para administradores)**
   - Maior pedido realizado.
   - Produto com menor estoque.

5. **Interface de usuário**
   - Login e menus distintos para administradores e clientes.

## Instruções de compilação e execução

### Compilação

Certifique-se que tem o maven instalado em sua máquina.Para compilar todos os arquivos Java, navegue até o diretório `DeAlZ` e execute o seguinte comando:

```sh
mvn clean install
```

### Execução

Para executar o projeto execute o seguinte comando:

```sh
jmvn exec:java -pl app
```


### Credenciais de usuário

- Já existe um usuário "Admin" com as seguintes credenciais:
  - Email: admin@dealz.com
  - Senha: admin
- (Opicional) Para criar um novo um usuário administrador recomenda-se o email com domínio @dealz.com para diferenciá-lo do usuário cliente

## Dependências

- JDK 21 ou superior.

## Notas finais

- O sistema deve ser executado em modo texto, com interação simples através de números para navegar pelos menus e submenus.
