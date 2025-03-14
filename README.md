Site de Notícias (noticias-api)

* Este é um projeto de um site de notícias desenvolvido com Spring Boot e PostgreSQL. Ele permite incluir, visualizar, alterar e excluir notícias.

Funcionalidades:
- Incluir notícias: Adicione novas notícias via formulário.
- Visualizar notícias: Liste todas as notícias em uma tabela.
- Alterar notícias: Edite notícias existentes.
- Excluir notícias: Remova notícias do banco.

Tecnologias Utilizadas:
- Backend: Spring Boot 3.4.3, JPA/Hibernate
- Banco de Dados**: PostgreSQL 14
- Frontend: HTML, JavaScript (Fetch API)

Como Executar:
1. Configure o PostgreSQL e crie o banco `noticias_db`.
2. Atualize as credenciais no `application.properties`.
3. Execute o comando:
   ```bash
   ./mvnw spring-boot:run