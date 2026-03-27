package dev.sebas.heladosfulsta.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "helados")
@Getter
@Setter

public class Helado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codHelado")
    private Integer codHelado;

    @Column(name = "nombre")
    @Size(min = 1, max = 50)
    private String nombre;

    @Column(name = "descripcion")
    @Size(min = 1, max = 255)
    private String descripcion;

    @Column(name = "estado")
    private Integer estado;

    public Helado() {
    }

    public Helado(Integer codHelado, String nombre, String descripcion, Integer estado) {
        this.codHelado = codHelado;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Helado helados = (Helado) o;
        return Objects.equals(codHelado, helados.codHelado);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codHelado);
    }

    @Override
    public String toString() {
        return "Helados{" +
                "codHelado=" + codHelado +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                '}';
    }
}