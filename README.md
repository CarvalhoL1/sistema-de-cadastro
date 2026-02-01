# Sistema de Cadastro de Usuários (JavaFX + SQLite)

##  Descrição

Este é um sistema desktop de **cadastro e gerenciamento de usuários**, desenvolvido em **Java** com interface gráfica em **JavaFX**, persistência de dados em **SQLite** e gerenciamento de dependências via **Maven**.

O sistema permite que usuários criem contas, façam login e gerenciem suas informações pessoais de forma simples e segura.

---

##  Funcionalidades

-  Cadastro de novos usuários  
-  Login com verificação de senha criptografada  
-  Edição de nome  
-  Atualização de frase pessoal  
-  Busca de usuários pelo email  
-  Alteração de senha  
-  Exibição do nome do usuário logado no painel principal  
-  Logout e controle de sessão

---

## Tecnologias Utilizadas

| Tecnologia | Função |
|------------|-------|
| Java 21+ | Linguagem principal |
| JavaFX | Interface gráfica |
| SQLite | Banco de dados local |
| jBCrypt | Criptografia de senhas |
| Maven | Gerenciamento de dependências |

---


## Como Executar o Projeto

### Pré-requisitos
- Java JDK 21 ou superior  
- Maven instalado  

### Rodar pelo terminal

```bash
mvn javafx:run
```

---

##  Banco de Dados

O banco de dados é criado automaticamente na primeira execução através das migrações do sistema.

### Estrutura da tabela principal

| Campo | Tipo | Descrição |
|------|------|-----------|
| id | INTEGER | Identificador do usuário |
| nome | TEXT | Nome do usuário |
| email | TEXT | Email único |
| senha_hash | TEXT | Senha criptografada |
| frase | TEXT | Frase pessoal |

---

##  Segurança

As senhas não são armazenadas em texto puro.  
O sistema utiliza **hash seguro com jBCrypt**, garantindo maior proteção dos dados dos usuários.

---


## Projeto desenvolvido como prática de:

- Programação Orientada a Objetos  
- Desenvolvimento Desktop com JavaFX  
- Integração com banco de dados  

---
