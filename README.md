# 🐾 Sistema de Prontuário para Pets

API REST desenvolvida em Java com Spring Boot para gerenciamento de prontuários veterinários, permitindo o cadastro de tutores, pets, tratamentos, vacinas, medicações e relatos de saúde.

---

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias](#tecnologias)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como Executar](#como-executar)
- [Endpoints](#endpoints)
- [Agendamentos](#agendamentos)
- [Melhorias Futuras](#melhorias-futuras)

---

## 📖 Sobre o Projeto

O sistema permite que clínicas veterinárias gerenciem o histórico de saúde dos pets de forma organizada. Cada pet possui um prontuário único criado automaticamente no momento do cadastro, onde são registrados tratamentos, vacinas e relatos de saúde.

Funcionalidades principais:
- Cadastro de tutores e seus respectivos pets
- Criação automática de prontuário ao cadastrar um pet
- Registro de tratamentos com medicações associadas
- Controle de vacinas com alertas de atraso automáticos
- Relatos de saúde com controle de status
- Notificações geradas automaticamente via agendamento diário

---

## 🛠 Tecnologias

| Tecnologia | Versão | Uso |
|---|---|---|
| Java | 21 | Linguagem principal |
| Spring Boot | 3.4.4 | Framework principal |
| Spring Data JPA | — | Persistência de dados |
| Spring Validation | — | Validação de DTOs |
| MySQL | 8+ | Banco de dados |
| Lombok | — | Redução de boilerplate |
| SpringDoc OpenAPI | 2.8.6 | Documentação Swagger |

---

## 📁 Estrutura do Projeto
