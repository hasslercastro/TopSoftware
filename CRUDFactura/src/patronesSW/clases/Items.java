package patronesSW.clases;

public class Items {

    private int idTipoItem;
    private int idItem;
    private String descripcion;
    private int valorUnidad;

    public Items(int idTipoItem, String descripcion, int valorUnidad) {
        this.idTipoItem = idTipoItem;
        this.descripcion = descripcion;
        this.valorUnidad = valorUnidad;
    }

    public int getIdTipoItem() {
        return idTipoItem;
    }

    public void setIdTipoItem(int idTipoItem) {
        this.idTipoItem = idTipoItem;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValorUnidad() {
        return valorUnidad;
    }

    public void setValorUnidad(int valorUnidad) {
        this.valorUnidad = valorUnidad;
    }

    @Override
    public String toString() {
        return "Items{" +
                "idTipoItem=" + idTipoItem +
                ", idItem=" + idItem +
                ", descripcion='" + descripcion + '\'' +
                ", valorUnidad=" + valorUnidad +
                '}';
    }
}
