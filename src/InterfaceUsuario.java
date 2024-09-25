import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.PrintWriter;
import java.io.IOException;

class InterfaceUsuario {
    public static void main(String[] args) {
        // Aqui você pode implementar a lógica para interagir com o usuário via console
        List<Medico> medicos = LeitorCSV.lerMedicos("medico.csv");
        // Exemplo de uso dos dados lidos
        for (Medico medico : medicos) {
            System.out.println(medico.getNome() + ": " + medico.getCodigo());
        }

        List<Paciente> pacientes = LeitorCSV.lerPacientes("paciente.csv");
        // Exemplo de uso dos dados lidos
        for (Paciente paciente : pacientes) {
            System.out.println(paciente.getNome() + ": " + paciente.getCpf());
        }

        List<Consulta> consultas = LeitorCSV.lerConsultas("consulta.csv");
        // Exemplo de uso dos dados lidos
        for (Consulta consulta : consultas) {
            System.out.println(consulta.getData() + "; " + consulta.getHorario() + "; " + consulta.getMedico() + "; " + consulta.getPaciente());
        }


        Scanner teclado = new Scanner(System.in);

        System.out.println("Escolha dentre as opções: \n" +
                "1 = Pacientes de um médico específico \n" +
                "2 = Consultas agendadas de um médico em determinado período \n" +
                "3 = Médicos que determinado paciente já consultou \n" +
                "4 = Consultas de determinado paciente realizou com determinado médico \n" +
                "5 = Consultas agendadas de um paciente \n" +
                "6 = Paciente de determinado médico que não consultam há mais de um determinado tempo \n");

        System.out.println("Digite a opção desejada: ");
        int opcao = teclado.nextInt();

        System.out.println("Opção selecionada:" + opcao);
        System.out.println("Deseja exibir o resultado na tela (T) ou salvar em arquivo (A)?");
        String escolha = teclado.next();
        String nomeArquivo = null; // Inicializa a variável fora do bloco condicional

        if (escolha.equalsIgnoreCase("A")) {
            System.out.println("Digite o nome do arquivo para salvar os resultados:");
            nomeArquivo = teclado.next();
        }

        try {
            PrintWriter writer = null; // Inicializa o PrintWriter fora do bloco condicional
            if (nomeArquivo != null) { // Verifica se o nome do arquivo foi definido
                writer = new PrintWriter(new FileWriter(nomeArquivo));
            }


            switch (opcao) {
                case 1:
                    System.out.println("Digite o código do médico:");
                    int codigoMedico = teclado.nextInt();

                    // armazena os resultados
                    StringBuilder resultados = new StringBuilder();

                    // adiciona os resultados na string
                    resultados.append("Pacientes do médico com código ").append(codigoMedico).append(":\n");
                    for (Consulta consulta : consultas) {
                        if (consulta.getMedico() == codigoMedico) {
                            for (Paciente paciente : pacientes) {
                                if (paciente.getCpf().equals(consulta.getPaciente())) {
                                    resultados.append(paciente.getNome()).append(": ").append(paciente.getCpf()).append("\n");
                                }
                            }
                        }
                    }
                    if (escolha.equalsIgnoreCase("T")) {
                        // Exibir a string na tela
                        System.out.println(resultados.toString());
                    } else {
                        // Escrever a string no arquivo
                        writer.println(resultados.toString());
                    }
                    break;

                case 2:
                    System.out.println("Digite o código do médico:");
                    int codigoMedicoConsulta = teclado.nextInt();

                    System.out.println("Digite a data de início do período (formato: dd/mm/yyyy):");
                    String dataInicioString = teclado.next();
                    LocalDate dataInicio = LocalDate.parse(dataInicioString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    System.out.println("Digite a data de fim do período (formato: dd/mm/yyyy):");
                    String dataFimString = teclado.next();
                    LocalDate dataFim = LocalDate.parse(dataFimString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    // Criar uma string para armazenar os resultados
                    resultados = new StringBuilder();

                    // Adicionar os resultados à string
                    resultados.append("Consultas do médico com código ").append(codigoMedicoConsulta)
                            .append(" no período de ").append(dataInicio).append(" a ").append(dataFim).append(":\n");

                    for (Consulta consulta : consultas) {
                        if (consulta.getMedico() == codigoMedicoConsulta && consulta.getData().compareTo(dataInicio) >= 0 && consulta.getData().compareTo(dataFim) <= 0) {
                            resultados.append("Data: ").append(consulta.getData()).append("; Horário: ").append(consulta.getHorario()).append("; Paciente: ").append(consulta.getPaciente()).append("\n");
                        }
                    }

                    // Verificar se deve exibir na tela ou salvar em arquivo
                    if (escolha.equalsIgnoreCase("T")) {
                        // Exibir a string na tela
                        System.out.println(resultados.toString());
                    } else {
                        // Escrever a string no arquivo
                        writer.println(resultados.toString());
                    }
                    break;
                case 3:
                    System.out.println("Digite o CPF do paciente:");
                    String cpfPacienteConsulta = teclado.next();

                    // Criar uma string para armazenar os resultados
                    resultados = new StringBuilder();

                    // Adicionar os resultados à string
                    resultados.append("Médicos que atenderam o paciente com CPF ").append(cpfPacienteConsulta).append(":\n");
                    for (Consulta consulta : consultas) {
                        if (consulta.getPaciente().equals(cpfPacienteConsulta)) {
                            for (Medico medico : medicos) {
                                if (medico.getCodigo() == consulta.getMedico()) {
                                    resultados.append(medico.getNome()).append("\n");
                                    break;
                                }
                            }
                        }
                    }

                    if (escolha.equalsIgnoreCase("T")) {
                        System.out.println(resultados.toString());
                    } else {
                        writer.println(resultados.toString());
                    }
                    break;
                case 4:
                    System.out.println("Digite o CPF do paciente:");
                    String cpfPaciente = teclado.next();
                    System.out.println("Digite o código do médico:");
                    int codigoMed = teclado.nextInt();

                    // Criar uma string para armazenar os resultados
                    resultados = new StringBuilder();

                    // Adicionar os resultados à string
                    resultados.append("Consultas realizadas pelo paciente com CPF ").append(cpfPaciente)
                            .append(" com o médico de código ").append(codigoMed).append(":\n");

                    LocalDate dataAtual = LocalDate.now();
                    for (Consulta consulta : consultas) {
                        LocalDate dataConsulta = consulta.getData();
                        if (consulta.getPaciente().equals(cpfPaciente) && consulta.getMedico() == codigoMed && dataConsulta.isBefore(dataAtual)) {
                            resultados.append("Data: ").append(consulta.getData()).append(", Horário: ").append(consulta.getHorario()).append("\n");
                        }
                    }

                    // Verificar se deve exibir na tela ou salvar em arquivo
                    if (escolha.equalsIgnoreCase("T")) {
                        // Exibir a string na tela
                        System.out.println(resultados.toString());
                    } else {
                        // Escrever a string no arquivo
                        writer.println(resultados.toString());
                    }
                    break;
                case 5:
                    System.out.println("Digite o CPF do paciente desejado:");
                    cpfPacienteConsulta = teclado.next();

                    // Criar uma string para armazenar os resultados
                    resultados = new StringBuilder();

                    // Adicionar os resultados à string
                    resultados.append("Consultas agendadas para o paciente de CPF: ").append(cpfPacienteConsulta).append(":\n");

                    LocalDate hoje = LocalDate.now();
                    for (Consulta consulta : consultas) {
                        if (consulta.getPaciente().equals(cpfPacienteConsulta) && consulta.getData().isAfter(hoje)) {
                            resultados.append("Data: ").append(consulta.getData()).append("; Hora: ").append(consulta.getHorario()).append("; Médico responsável: ").append(consulta.getMedico()).append("\n");
                        }
                    }

                    // Verificar se deve exibir na tela ou salvar em arquivo
                    if (escolha.equalsIgnoreCase("T")) {
                        // Exibir a string na tela
                        System.out.println(resultados.toString());
                    } else {
                        // Escrever a string no arquivo
                        writer.println(resultados.toString());
                    }
                    break;
                case 6:
                    System.out.println("Digite o código do médico:");
                    int codigoMedicoPacientes = teclado.nextInt();

                    System.out.println("Digite o tempo em meses desde a última consulta:");
                    int meses = teclado.nextInt();

                    // Criar uma string para armazenar os resultados
                    resultados = new StringBuilder();

                    // Adicionar os resultados à string
                    resultados.append("Pacientes do médico de código ").append(codigoMedicoPacientes)
                            .append(" que não consultam há ").append(meses).append(" meses:\n");

                    dataAtual = LocalDate.now();
                    LocalDate dataLimite = dataAtual.minusMonths(meses);

                    for (Paciente paciente : pacientes) {
                        boolean consultouRecentemente = false;
                        boolean consultouComMedico = false;
                        for (Consulta consulta : consultas) {
                            if (consulta.getMedico() == codigoMedicoPacientes && consulta.getPaciente().equals(paciente.getCpf())) {
                                consultouComMedico = true;
                                if (dataLimite.isBefore(consulta.getData())) {
                                    consultouRecentemente = true;
                                    break;
                                }
                            }
                        }
                        if (consultouComMedico && !consultouRecentemente) {
                            resultados.append(paciente.getNome()).append(": ").append(paciente.getCpf()).append("\n");
                        }
                    }

                    // Verificar se deve exibir na tela ou salvar em arquivo
                    if (escolha.equalsIgnoreCase("T")) {
                        // Exibir a string na tela
                        System.out.println(resultados.toString());
                    } else {
                        // Escrever a string no arquivo
                        writer.println(resultados.toString());
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

            // Fecha o PrintWriter se estiver aberto
            if (writer != null) {
                writer.close();
                System.out.println("Resultados salvos no arquivo " + nomeArquivo);
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo.");
            e.printStackTrace();
        }
    }









class LeitorCSV {
    //classe da leitura dos médicos
    public static List<Medico> lerMedicos(String nomeArquivo) {
        List<Medico> medicos = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(nomeArquivo))) {
            // Ignorar a primeira linha (cabeçalho)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(";");
                String nome = dados[0];
                int codigo = Integer.parseInt(dados[1]);
                medicos.add(new Medico(nome, codigo));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return medicos;
    }

    public static List<Paciente> lerPacientes(String nomeArquivo) {
        List<Paciente> pacientes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(nomeArquivo))) {
            // Ignorar a primeira linha (cabeçalho)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(";");
                String nome = dados[0];
                String cpf = dados[1];
                pacientes.add(new Paciente(nome, cpf));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return pacientes;
    }


    public static List<Consulta> lerConsultas(String nomeArquivo) {
        List<Consulta> consultas = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Definir o padrão de formatação
        try (Scanner scanner = new Scanner(new File(nomeArquivo))) {
            // Ignorar a primeira linha (cabeçalho)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(";");
                LocalDate data = LocalDate.parse(dados[0], formatter); // Usar o padrão de formatação especificado
                LocalTime horario = LocalTime.parse(dados[1]); // Converter string para LocalTime
                int codigoMedico = Integer.parseInt(dados[2]);
                String cpfPaciente = dados[3];
                consultas.add(new Consulta(data, horario, codigoMedico, cpfPaciente));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return consultas;
    }


}}


