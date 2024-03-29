package vista.identificarse;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VistaIdentificarse extends javax.swing.JFrame {

    private CtrlVistaIdentificarse controlador;

    /**
     * Creates new form vistaIdentificarse
     */
    public VistaIdentificarse() {
        initComponents();
        controlador = new CtrlVistaIdentificarse(this);

    }

    public String getDNI() {
        return DNITextField.getText();
    }

    public String getContrasena() {
        return contrasenaTextField.getText();
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        DNILabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        DNITextField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        contrasenaLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        contrasenaTextField = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        botonExit = new javax.swing.JButton();
        loginBoton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(350, 300));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel6.setLayout(new java.awt.GridLayout(5, 0));

        jPanel5.setLayout(new java.awt.CardLayout());

        DNILabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        DNILabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DNILabel.setText("DNI");
        jPanel5.add(DNILabel, "card2");

        jPanel6.add(jPanel5);

        jPanel4.setLayout(new java.awt.CardLayout());

        DNITextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        DNITextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DNITextField.setToolTipText("Introduzca su usuario");
        DNITextField.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel4.add(DNITextField, "card2");

        jPanel6.add(jPanel4);

        jPanel3.setLayout(new java.awt.CardLayout());

        contrasenaLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        contrasenaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contrasenaLabel.setText("Contraseña");
        jPanel3.add(contrasenaLabel, "card2");

        jPanel6.add(jPanel3);

        jPanel2.setLayout(new java.awt.CardLayout());

        contrasenaTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(contrasenaTextField, "card2");

        jPanel6.add(jPanel2);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 20, 20));

        botonExit.setText("Exit");
        botonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonExitActionPerformed(evt);
            }
        });
        jPanel1.add(botonExit);

        loginBoton.setLabel("Log in");
        loginBoton.setPreferredSize(new java.awt.Dimension(75, 25));
        loginBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBotonActionPerformed(evt);
            }
        });
        jPanel1.add(loginBoton);

        jPanel6.add(jPanel1);

        getContentPane().add(jPanel6);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBotonActionPerformed
        try {
            controlador.login();
        } catch (IOException ex) {
            Logger.getLogger(VistaIdentificarse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(VistaIdentificarse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_loginBotonActionPerformed

    private void botonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExitActionPerformed
        controlador.exit();
    }//GEN-LAST:event_botonExitActionPerformed

    public JLabel getDniLabel(){
        return DNILabel;
    }
    
    public JLabel getContrasenaLabel(){
        return contrasenaLabel;
    }
    
    public JTextField getDniTextField(){
        return DNITextField;
    }
    public JTextField getContrasenaTextField(){
        return contrasenaTextField;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DNILabel;
    private javax.swing.JTextField DNITextField;
    private javax.swing.JButton botonExit;
    private javax.swing.JLabel contrasenaLabel;
    private javax.swing.JPasswordField contrasenaTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton loginBoton;
    // End of variables declaration//GEN-END:variables
}
