import java.time.LocalDate;
import java.time.LocalTime;

class Consulta {
    private LocalDate data;
    private LocalTime horario;
    private int medico;
    private String paciente;

    public Consulta(LocalDate data, LocalTime horario, int medico, String paciente) {
        this.data = data;
        this.horario = horario;
        this.medico = medico;
        this.paciente = paciente;
    }
    public LocalDate getData() {
        return data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public int getMedico() {
        return medico;
    }

    public String getPaciente() {
        return paciente;
    }
}