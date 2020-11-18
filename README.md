# api_client_BrasilPrev
Test BrasilPrev


Api simulação Cadastro Cliente

Faça o clone da aplicação no github.
Alterar usuário e senha do banco de dados no arquivo application.properties.
O banco de dados mysql está sendo criado automaticamente, desde que a url de conexão, usuário e senha estejam corretos.

Como devemos usar? 

Executar o metodo AsaveTest() da classe ClientRepositoryTests para cadastrar um cliente.
Apos ter o cliente cadastrado na base podemos tentar fazer o login 

 
Exemplo requisição [/clients/login] tipo: POST

{
    "email": "test@gmail.com",
    "password":"12345678"

}

Exemplo retorno [/clients/login]   

{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZWRyby5zb3V6YUBnbWFpbC5jb20iLCJleHAiOjE2MDU5ODg0NDgsInJvbGUiOlsiUk9MRV9BRE1JTklTVFJBVE9SIl19.CEn_iiKgFJEYs-	X1XRYKlEI3FtcTTe5svbMQNxZMC-Tz0qMAYI440dq02kf0a1g4VXr5Oysi6mTBFWoxguh39w",
    "expireIn": 1605988448006,
    "tokenprovider": "Bearer"
}


Apos isso todas as funcionalidades exigem a utilização do token no header da requisição 

*obs aconselho utilizar o Postman para realizar as requisições

Clique na aba headers

- no campo key coloque Authorization
- no campo value coloque Bearer + token 

exemplo: BearereyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZWRyby5zb3V6YUBnbWFpbC5jb20iLCJleHAiOjE2MDU5ODYyMTUsInJvbGUiOlsiUk9MRV9BRE1JTklTVFJBVE9SIl19.BqDt9ucLcKKIsoH13VMtBjGwXS7jZsMs3bSmPgJztIqREUal-IgAq1N4htxkbaD3keE4l-D_QnVrlYmqiD4Vgw




Exemplo requisição [/clients/1] tipo: GET

Exemplo retorno [/clients/1] 

{
    "id": 1,
    "name": "Nicolas Moreira",
    "cpf": "43256789756",
    "address": "rua osvaldo",
    "email": "nicolas.souza@gmail.com",
    "role": "ADMINISTRATOR"
}



Exemplo requisição [/clients] tipo: POST 

Necessário perfil de Administrador
{   
    "name": "Thiago",
    "cpf": "43256789756",
    "address": "rua 9 de julho",
    "email": "thiago@gmail.com",
    "role": "ADMINISTRATOR",
    "password": "12345678"
	
}   
Obs: a senha está sendo salva na base criptografada

Exemplo retorno [/clients] 

{
    "id": 3,
    "name": "Thiago",
    "cpf": "43256789756",
    "address": "rua 9 de julho",
    "email": "thiago@gmail.com",
    "role": "ADMINISTRATOR"
}


Exemplo requisição [/clients/1] tipo: PUT

{   
    "name": "Nicolas Moreira",
    "cpf": "43256789756",
    "address": "rua São Bento",
    "email": "nicolas.souza@gmail.com",
    "password": "123456789"
	
}

Exemplo retorno [/clients/1] 
{
    "id": 1,
    "name": "Nicolas Moreira",
    "cpf": "43256789756",
    "address": "rua São Bento",
    "email": "nicolas.souza@gmail.com"
}

Exemplo requisição [/clients] tipo GET 

Traz a lista de clientes 



Exemplo requisição [/clients/role/7] tipo patch 

Necessário perfil de Administrador

{
    "role" : "ADMINISTRATOR"
}

Obs: Temos dois perfis de acesso ADMINISTRATOR E SIMPLE

Não tem retorno 



Principais Ferramentas utilizadas: Banco mysql, Hibernate para implementação da JPA, Spring Boot e Security, Junit para testes unitarios, JWT token. 

Pode ser que o Eclipse ou outra IDE que você for utilizar não reconhece as anotações @Getter e @Setter será necessário 

Ir no jar e executá-lo manualmente 

C:\Users\nico1\.m2\repository\org\projectlombok\lombok\1.18.4
