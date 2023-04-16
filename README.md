# PetShop API
Esta API possibilta gerenciar um petshop e apresenta funcionalidades como: inserir, buscar, atualizar e remover clientes, pets, endereços e atendimentos. 
 
## Tecnologias utilizadas
Essa API utilizou as seguintes tecnologias:
> - Java 17;
> - Spring Boot 3.0.2;
> - OpenApi (Swagger) 2.0.2;
> - PostgreSQL 15;
> - MockMVC;
> - Maven.

## Como utilizar
Para alterar configurações como banco de dados e porta que a aplicação utiliza (atualmente 8070), é necessário alterar o arquivo "application.properties"

Os seguintes endpoints foram criados:
> - "/usuario": responsável por gerenciar dados dos usuarios;
> - "/cliente": responsável por gerenciar dados dos clientes;
> - "/endereço": responsável por gerenciar dados dos endereços dos clientes;
> - "/contato": responsável por gerenciar dados de contato dos clientes;
> - "/pet": responsável por gerenciar dados dos pets dos clientes;
> - "/atendimento": responsável por gerenciar dados de atendimento dos pets;
> - "/raca": responsável por gerenciar dados da raça dos pets.

cada um possibilita realizar as operações de inserir, buscar, atualizar e remover. Mais informações sobre a documentaçõ e como testar a API podem ser visualizadas em http://localhost:8070/swagger-ui/

Vale ressaltar que a aplicação também utiliza autenticação por token JWT, sendo necessário cadastrar primeiramente um usuário válido antes de utilizar os endpoints.
