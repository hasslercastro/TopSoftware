package patronesSW.clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura {

    private int nroFactura;
    private Date fechaFactura;
    private int idCliente;
    private int totalFactura;
    private String estado;
    private List<Integer> items = new ArrayList<>();

    public Factura(Date fechaFactura, int idCliente, int totalFactura, String estado, List<Integer> items) {
        this.fechaFactura = fechaFactura;
        this.idCliente = idCliente;
        this.totalFactura = totalFactura;
        this.estado = estado;
        this.items = items;
    }

    public int getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(int nroFactura) {
        this.nroFactura = nroFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(int totalFactura) {
        this.totalFactura = totalFactura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "nroFactura=" + nroFactura +
                ", fechaFactura=" + fechaFactura +
                ", idCliente=" + idCliente +
                ", totalFactura=" + totalFactura +
                ", estado='" + estado + '\'' +
                ", items=" + items +
                '}';
    }
}
