package dev.sebas.heladosfulsta.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ventas")
@Getter
@Setter
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codVenta", nullable = false)
    private Integer codVenta;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "codHelado", referencedColumnName = "codHelado")
    private Helado helado;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "codCliente", referencedColumnName = "codCliente")
    private Cliente cliente;

    @Column(name = "tipoHelado", nullable = false)
    private Integer tipoHelado;

    @Column(name = "cantidadBolas", nullable = false)
    private Integer cantidadBolas;

    public Venta() {
    }

    public Venta(Integer codVenta, Helado helado, Integer tipoHelado, Integer cantidadBolas) {
        this.codVenta = codVenta;
        this.helado = helado;
        this.tipoHelado = tipoHelado;
        this.cantidadBolas = cantidadBolas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Venta ventas = (Venta) o;
        return Objects.equals(codVenta, ventas.codVenta);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codVenta);
    }

    @Override
    public String toString() {
        return "ventas{" +
                "codVenta=" + codVenta +
                ", codHelado=" + helado +
                ", tipoHelado=" + tipoHelado +
                ", cantidadBolas=" + cantidadBolas +
                '}';
    }
}