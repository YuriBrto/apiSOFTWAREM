# d-back-end-principes_back

Sistema de controle de instalação de softwares nos laboratórios de informática da Universidade Católica do Salvador (UCSal), desenvolvido com Java e Spring Boot. Este repositório contempla a aplicação **Back-End**, responsável por processar as regras de negócio relacionadas às solicitações de softwares feitas por professores, e o controle administrativo feito por gestores acadêmicos.

---

## 📚 Descrição Funcional

### 👨‍💼 Sobre o Controle dos Softwares

1. O **administrador** é o responsável por gerenciar os softwares solicitados:
   - Cadastro dos softwares com: nome, link de instalação, versão, tipo (livre ou proprietário), e data da solicitação.
   - Cadastro dos professores com: nome e escola de origem (ex: Escola de Tecnologias).
   - Atualização das informações dos softwares sempre que necessário, via sistema front-end.
   - Feedback visual (sucesso/erro) será exibido após qualquer operação de cadastro/edição.

2. O administrador pode:
   - Consultar a lista de softwares disponíveis para instalação.
   - Tornar softwares **indisponíveis**, por exemplo, quando são descontinuados.

### 👩‍🏫 Sobre a Instalação nos Laboratórios

1. Professores cadastrados podem solicitar a instalação de softwares:
   - A partir da lista de softwares disponíveis.
   - Para um laboratório específico, informando:
     - Qual software,
     - Qual laboratório,
     - Data de início do uso.

2. Regras adicionais:
   - É possível solicitar **vários softwares ao mesmo tempo**, mas para **um único laboratório por solicitação**.
   - O sistema verificará se o software já está instalado. Se sim, a solicitação será cancelada com mensagem ao professor.
   - O laboratório será marcado como **indisponível** durante a instalação.
   - Ao término da instalação, o sistema notificará o sucesso da operação.
   - Somente o administrador poderá modificar ou excluir uma solicitação, caso ainda não finalizada.

3. Após o uso:
   - O professor confirma o uso do software instalado.
   - O sistema atualiza o espaço acadêmico com o status da instalação e problemas, se houver.

---

## 🧠 Tecnologias Utilizadas

- Java 
- Spring Boot
- Maven
- Swagger
- Postman (testes)

---

## ⚙️ Como Executar o Projeto

### Requisitos
- Java JDK 17+
- Maven 3.8+

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/ucsal/d-back-end-principes_back.git
   cd d-back-end-principes_back
   ```

2. Compile e execute:
   ```bash
   ./mvnw spring-boot:run
   ```

   Ou, com Maven instalado globalmente:
   ```bash
   mvn spring-boot:run
   ```

3. A aplicação será iniciada na porta padrão `8080`.

---

## 📁 Estrutura de Diretórios

```plaintext
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── Config/         # Configurações gerais
│   │   ├── Controller/     # Endpoints REST
│   │   ├── Exception/      # Tratamento de exceções
│   │   ├── Model/          # Entidades do sistema
│   │   ├── Repository/     # Integração com banco de dados
│   │   ├── Services/       # Regras de negócio
│   │   ├── DemoApplication.java
│   │   └── Security.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/example/demo/
        └── test.java
```

---

## 🧪 Testes Automatizados

Para executar os testes automatizados:

```bash
mvn test
```

---

## 🤝 Contribuição

Siga os passos para colaborar:

1. Fork o projeto
2. Crie uma branch:
   ```bash
   git checkout -b minha-feature
   ```
3. Commit suas alterações:
   ```bash
   git commit -m 'feat: minha nova funcionalidade'
   ```
4. Push para seu fork:
   ```bash
   git push origin minha-feature
   ```
5. Crie um Pull Request

---

## 📄 Licença

Este projeto está licenciado sob a Licença MIT.

---

## 👥 Equipe

Desenvolvido por estudantes da Universidade Católica do Salvador - UCSal.

# Yuri Brito

# Lucas Gaspari

# Matheus Pires

# Gustavo Cruz

# Luan

Repositório oficial: Git UCSAL (conforme orientação do professor responsável).
