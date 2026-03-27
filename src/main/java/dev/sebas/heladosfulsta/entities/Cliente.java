package dev.sebas.heladosfulsta.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codCliente", nullable = false)
    private Integer codCliente;

    @Column(name = "nombres", nullable = false)
    @Size(min = 1, max = 120)
    private String nombres;

    @Column(name = "estado", nullable = false)
    private Integer estado;


    public Cliente() {
    }

    public Cliente(Integer codCliente, String nombres, Integer estado) {
        this.codCliente = codCliente;
        this.nombres = nombres;
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente clientes = (Cliente) o;
        return Objects.equals(codCliente, clientes.codCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codCliente);
    }

    @Override
    public String toString() {
        return "clientes{" +
                "codCliente=" + codCliente +
                ", nombres='" + nombres + '\'' +
                ", estado=" + estado +
                '}';
    }
}
