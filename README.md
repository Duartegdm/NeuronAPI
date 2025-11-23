
# Neuron API – Plataforma de Monitoramento Emocional

API RESTful desenvolvida em Java com Quarkus para o sistema Neuron, uma solução voltada ao acompanhamento do bem-estar emocional de colaboradores.

## 🎯 Visão Geral
A API fornece funcionalidades para autenticação, registro emocional, respostas de formulários, gestão de usuários, departamentos, acessos e categorias de emoções.

## 🧰 Tecnologias Utilizadas
- Java 17
- Quarkus
- RESTEasy / JAX-RS
- JDBC
- JWT
- Maven

## 📦 Estrutura do Projeto
- beans/
- dao/
- dto/
- exception/
- model/
- resource/
- service/
- utils/
- application.properties
- tabelas.sql

## 🚀 Endpoints Principais
### Auth
- POST /auth/login
- POST /auth/register
- GET /auth/me

### Usuários
- GET /usuarios
- GET /usuarios/{id}
- PUT /usuarios/{id}
- DELETE /usuarios/id/{id}
- DELETE /usuarios/email/{email}

### Departamentos
- POST /departamentos
- GET /departamentos

### Emoções & Categorias
- POST /catg-emocoes
- GET /catg-emocoes
- POST /emocoes
- GET /emocoes
- GET /emocoes/{idCategoria}

### Registro Emocional
- POST /registro-emocao
- GET /registro-emocao/usuario/{idUsuario}

### Respostas de Formulário
- POST /resposta-formulario
- GET /resposta-formulario/usuario/{idUsuario}

## ▶️ Execução
```
./mvnw quarkus:dev
```

## 👥 Autores
Equipe Neuron (FIAP, 2025)
- Anna Clara Luca  
- Gabriel Duarte Maciel  
- Tiago Guedes
