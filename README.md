# Sistema de Cadastro e Login em Java

Projeto desenvolvido para praticar **Java, JDBC, SQLite e segurança de autenticação**.  
O sistema permite o cadastro, login, gerenciamento de conta e armazenamento de dados de usuários de forma segura.

---

## Funcionalidades

-  Cadastro de usuário  
-  Login com verificação de senha segura (BCrypt)  
-  Deletar conta  
-  Adicionar/editar frase pessoal do usuário  
-  Banco de dados criado automaticamente (migration)

---

## Tecnologias Utilizadas

- **Java 21**
- **SQLite**
- **JDBC**
- **BCrypt (jBCrypt)** para hash de senhas
- **Maven** para gerenciamento de dependências

---

## Segurança

As senhas **não são armazenadas em texto puro**.

O sistema utiliza **BCrypt**, que:

- Gera hash seguro automaticamente  
- Inclui salt embutido no hash  
- Protege contra ataques de força bruta  

---

## Estrutura do Projeto

```
src/main/java
 ├── App.java                  # Interface de console
 ├── db
 │    ├── ConnectionFactory.java  # Conexão com o banco
 │    └── Migrations.java         # Criação automática das tabelas
 └── security
      └── SenhaSegura.java        # Hash e verificação de senha (BCrypt)
```

---

## Banco de Dados

O banco é criado automaticamente ao iniciar o programa.

### Tabela: `usuarios`

| Campo       | Tipo    | Descrição                    |
|------------|---------|------------------------------|
| id         | INTEGER | Chave primária               |
| nome       | TEXT    | Nome do usuário              |
| email      | TEXT    | Email único                  |
| senha_hash | TEXT    | Hash da senha (BCrypt)       |
| frase      | TEXT    | Frase pessoal (opcional)     |

---

## Como Executar o Projeto

### 1️: Clonar o repositório
### 2️: Compilar e rodar

```bash
mvn compile exec:java -Dexec.mainClass=App
```

Ou execute diretamente pelo botão **Run** no `App.java` (VS Code).

---

## Fluxo do Sistema

1. O banco e as tabelas são criados automaticamente  
2. Usuário pode:
   - Cadastrar conta  
   - Fazer login  
   - Deletar conta  
   - Salvar/editar sua frase  
3. Senhas são validadas usando BCrypt  

---

## Aprendizados com o Projeto

Este projeto foi feito com foco em:

- Conexão Java ↔ Banco de Dados  
- Uso de PreparedStatement (prevenção de SQL Injection)  
- Estruturação básica de autenticação  
- Armazenamento seguro de senhas  
- Organização de código em camadas simples  

---

## Possíveis Melhorias Futuras

- Interface gráfica ou web  
- Recuperação de senha  
- Sistema de sessões 

---
