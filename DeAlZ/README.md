
# Sistema de DeAlZ e-commerce

## Overview

Este projeto é um sistema de e-commerce em Java, desenvolvido para gerenciar produtos, usuários e pedidos. Ele implementa funcionalidades de cadastro, autenticação e processamento de pedidos, além de fornecer relatórios específicos para administradores. O sistema foi desenvolvido utilizando princípios de Programação Orientada a Objetos (POO).

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

```
..\DeAlZ\src\main\java\inf008\ecomerce\dealz
    ├── out
    ├── Administrator.java
    ├── AdministratorMenu.java
    ├── Authentication.java
    ├── Customer.java
    ├── CustomerMenu.java
    ├── IMenu.java
    ├── Main.java
    ├── Order.java
    ├── ShoppingCart.java
    ├── User.java
    ├── UserUi.java
    ├── FileRegister.java
```

## Funcionalidades

1. **Gerenciamento de Produtos**
   - Cadastro e obtenção de produtos.
   - Atributos: ID, Nome, Descrição, Preço, Quantidade em estoque, Categoria.

2. **Gerenciamento de Usuários**
   - Cadastro de usuários (Clientes e Administradores).
   - Atributos: ID, Nome completo, Email, Senha.
   - Clientes têm informações adicionais como endereço de entrega e histórico de compras.

3. **Processamento de Pedidos**
   - Clientes podem adicionar produtos ao carrinho e finalizar pedidos.
   - Atualização do estoque, geração de número de pedido, cálculo do total e associação ao histórico do cliente.

4. **Relatórios (somente para administradores)**
   - Maior pedido realizado.
   - Produto com menor estoque.

5. **Interface de Usuário**
   - Login e menus distintos para administradores e clientes.

## Instruções de Compilação e Execução

### Compilação

Para compilar todos os arquivos Java, navegue até o diretório `src/main/java/inf008/ecommerce/dealz` e execute o seguinte comando:

```sh
javac -d out *.java
```

### Execução

Após a compilação, execute a aplicação a partir do diretório `out`:

```sh
java -cp out inf008.ecomerce.dealz.Main
```


### Credenciais de Usuário

- Já existe um usuário "Admin" com as seguintes credenciais:
  - Email: admin@dealz.com
  - Senha: admin
- (Opicional) Para criar um novo um usuário administrador recomenda-se o email com domínio @dealz.com para diferenciá-lo do usuário cliente

## Dependências

- JDK 11 ou superior.

## Notas Finais

- Certifique-se de que todos os arquivos `.class` estão dentro do diretório `out` antes de executar a aplicação.
- O sistema deve ser executado em modo texto, com interação simples através de números para navegar pelos menus e submenus.
