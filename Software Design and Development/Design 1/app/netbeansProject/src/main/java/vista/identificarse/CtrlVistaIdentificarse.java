/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.identificarse;

import dominio.modelo.Empleado;
import dominio.modelo.Rol;
import dominio.controladoresCU.ControladorCUIdentificarse;
import dominio.modelo.SesionSingletonEmpleado;
import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import vista.statemachine.GestorInterfazUsuario;

/**
 *
 * @author miris
 */
public class CtrlVistaIdentificarse {

    private VistaIdentificarse vista;
    private ControladorCUIdentificarse controlador;   

    public CtrlVistaIdentificarse(VistaIdentificarse v) {
        vista = v;
        controlador = new ControladorCUIdentificarse();
    }

    public void login() throws IOException, ParseException {
        controlador.compruebaCredenciales(vista.getDNI(), vista.getContrasena());
        Empleado emp = SesionSingletonEmpleado.getInstancia();
        if (emp == null) {
            mensajeError();
        } else {
            Rol rolEnLaEmpresa = emp.getRolActivo();            
            switch (rolEnLaEmpresa.getTipoRol()) {
                case PersonalAlmacen:
                    GestorInterfazUsuario.mostrarOpcionesAlmacen();
                    break;
                case GerenteVentas:
                    GestorInterfazUsuario.mostrarOpcionesGerente();
                    break;
                case TecnicoDelTaller:
                    GestorInterfazUsuario.mostrarOpcionesTaller();
                    break;
                default:
                    mensajeError();
            }
        }
    }
    
    public void mensajeError() {
        JLabel DNILabel = vista.getDniLabel();
        JTextField DNITextField = vista.getDniTextField();
        JTextField contrasenaTextField = vista.getContrasenaTextField();
        if (!DNILabel.getText().contains("No existe")) {
            DNILabel.setForeground (Color.red);
            String text = "DNI<br />No existe ningún usuario en activo (contratado y trabajando) con los datos introducidos.";
            DNILabel.setText("<html><div style='text-align: center;'>" + text + "</div></html>");
            DNILabel.setHorizontalTextPosition(SwingConstants.CENTER);
            DNITextField.setText("");
            contrasenaTextField.setText("");
        }
    }

    void exit() {
        System.exit(0);
    }
}
