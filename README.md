# Orientações Iniciais

​	Producer: Trata-se da aplicação responsável por receber as requisições e enviá-las, por meio de mensageria, para a aplicação consumer.

​	Consumer: Trata-se da aplicação responsável por receber e armazenar, no banco de dados, as mensagens da aplicação producer e também possibilitar a visualização e novas inserções por meio de requisições HTTP.



# Orientações para "rodar" o projeto

​	Dentro da pasta de cada projeto existe um arquivo chamado docker-compose.yml, ele já está configurado com o container da aplicação sendo necessário apenas utilizar o seguinte comando em uma CLI:

```` dockerfile
docker-compose up
````

**Observação 1** :  Para o projeto iniciar sem problemas é necessário que o banco de dados e o rabbitmq estejam  iniciados e prontos para receberem conexões. Caso o container do projeto "suba" antes dos demais, espere os demais containers  terminarem de inicializar e "suba" o container da aplicação novamente.

**Observação 2**: Na aplicação consumer, caso o rabbitmq não estiver pronto para uso, a aplicação ficará tentando conectar-se com o sistema de mensageria.



# Documentação

**Producer**: A aplicação producer está configurada para funcionar na porta 3000, portanto para acessar à documentação e enviar as requisições utilize:

```http
http://localhost:3000/swagger-ui/index.html#/
```

/docs

``` http
http://localhost:3000/v3/api-docs
```



**Consumer: ** A aplicação consumer está configurada para funcionar na porta 8080, portanto para acessar à documentação e enviar as requisições utilize:

```http
http://localhost:8080/swagger-ui/index.html#/
```

/docs

``` http
http://localhost:8080/v3/api-docs
```



# Orientações Finais

​	Para um melhor entendimento do projeto, foi disponibilizado um imagem contendo a modelagem lógica do banco de dados. 

​	Com o objetivo de entregar o quanto antes, nem todas as funcionalidade foram implementadas. Com o objetivo de informar as próximas implementações foi disponibilizado o arquivo Release.md