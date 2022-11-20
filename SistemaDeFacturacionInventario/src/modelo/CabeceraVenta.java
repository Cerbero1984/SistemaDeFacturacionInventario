package modelo;

/**
 *
 * @author admin
 */
public class CabeceraVenta {
    
    // Atributos
    private int idCabeceraVenta;
    private int idCliente;
    private double valorPagar;
    private String fechaVenta;
    private int estado;
    
    // Constructor
    public CabeceraVenta() {
        this.idCabeceraVenta = 0;
        this.idCliente = 0;
        this.valorPagar = 0.0;
        this.fechaVenta = "";
        this.estado = 0;
    }
    
    // Constructor sobrecargado

    public CabeceraVenta(int idCabeceraVenta, int idCliente, double valorPagar, String fechaVenta, int estado) {
        this.idCabeceraVenta = idCabeceraVenta;
        this.idCliente = idCliente;
        this.valorPagar = valorPagar;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
    }
    
    // Getters y Setters

    public int getIdCabeceraVenta() {
        return idCabeceraVenta;
    }

    public void setIdCabeceraVenta(int idCabeceraVenta) {
        this.idCabeceraVenta = idCabeceraVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    //  metodo toString

    @Override
    public String toString() {
        return "CabeceraVenta{" + "idCabeceraVenta=" + idCabeceraVenta + ", idCliente=" + idCliente + ", valorPagar=" + valorPagar + ", fechaVenta=" + fechaVenta + ", estado=" + estado + '}';
    }

    public void getEstado(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
