/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.modelo;

/**
 *
 * @author edgarpopos
 */
public class SesionPedido {

    protected static PedidoPCs pedido;
    protected static int cantidadRestante;

    protected SesionPedido(PedidoPCs p, int cant) {
        this.pedido = pedido;
        this.cantidadRestante=cant;
    }
    
    public static PedidoPCs getSesionPedido() {
        return pedido;
    }
    
    public static int getCantidadRestante() {
        return cantidadRestante;
    }

    public static void setSesionPedido(PedidoPCs p) {
        pedido = p;
    }
    public static void setCantidadRestante(int c) {
        cantidadRestante = c;
    }
    
    public static void destroySesion(){
        setSesionPedido(null);
    }

}
