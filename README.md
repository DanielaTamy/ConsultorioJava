# 🏥 Sistema de Gestão de Consultas Médicas em Java
Junho/2024
Programação Orientada a Objeto PUCPR - 3° período Ciênia da computação


Este é um projeto de um **Sistema de Gestão de Consultas Médicas** que permite o gerenciamento de consultas entre médicos e pacientes. A interface interage com o usuário via terminal para consultar e exibir informações como médicos, pacientes e consultas agendadas.

## 📁 Estrutura do Projeto

- **Consulta.java**: Define a classe `Consulta` que armazena a data, horário, médico e paciente da consulta.
- **InterfaceUsuario.java**: Contém a interface para interação com o usuário e várias opções de consulta.
- **LeitorCSV.java**: Responsável pela leitura dos dados de arquivos CSV para médicos, pacientes e consultas.
- **Medico.java**: Define a classe `Medico` com nome e código.
- **Paciente.java**: Define a classe `Paciente` com nome e CPF.

## 🚀 Funcionalidades

1. **Pacientes de um médico específico**: Veja quais pacientes estão vinculados a um médico.
2. **Consultas agendadas de um médico**: Exibe consultas agendadas de um médico em um determinado período.
3. **Médicos que atenderam um paciente**: Veja quais médicos já atenderam determinado paciente.
4. **Consultas entre paciente e médico**: Exibe as consultas que um paciente fez com um médico específico.
5. **Consultas agendadas de um paciente**: Lista todas as consultas futuras de um paciente.
6. **Pacientes que não consultam há um tempo**: Identifica pacientes que não consultam com um médico há mais de um determinado tempo.

## 📊 Estrutura dos Arquivos CSV

- `medico.csv`: Armazena os médicos com nome e código.
    ```csv
    nome;codigo
    Dr. João;123
    Dr. Maria;456
    ```
- `paciente.csv`: Armazena os pacientes com nome e CPF.
    ```csv
    nome;cpf
    Carlos Silva;11122233344
    Ana Souza;55566677788
    ```
- `consulta.csv`: Armazena as consultas com data, horário, código do médico e CPF do paciente.
    ```csv
    data;horario;codigoMedico;cpfPaciente
    10/09/2024;15:00;123;11122233344
    12/09/2024;09:00;456;55566677788
    ```

## ⚙️ Requisitos

- **Java** 
- Arquivos CSV com os dados de médicos, pacientes e consultas

