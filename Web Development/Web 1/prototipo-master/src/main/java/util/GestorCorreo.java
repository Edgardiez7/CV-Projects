/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.LocalDate;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author edgarpopos
 */
public class GestorCorreo {

    public GestorCorreo() {

    }

    public void sendMail(String titulo, LocalDate fecha, String toAddress) {
        try {

            String mailServer = "edutravel.atcliente@gmail.com";
            String passServer = "passedutravel1";

            //get a mail session   
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtps.host", "smtp.gmail.com");
            props.put("mail.smtps.port", "465");
            props.put("mail.smtps.auth", "true");
            props.put("mail.smtps.quitwait", "false");
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            // Used to debug SMTP issues
            session.setDebug(true);

            //create msg
            Message message = new MimeMessage(session);
            message.setSubject("Confirmación Pedido Edutravel");
            message.setContent("<h1>Gracias por confiar en Edutravel.</h1><p>Se ha procesado"
                    + " correctamente la compra de su producto \"" + titulo + "\" reservado en la fecha \"" + fecha.toString() + "\".</p>", "text/html");

            //address msg
            Address fromAdd = new InternetAddress(mailServer);
            Address toAdd = new InternetAddress(toAddress);
            message.setFrom(fromAdd);
            message.setRecipient(Message.RecipientType.TO, toAdd);

            //send msg
            Transport transport = session.getTransport();
            transport.connect(mailServer, passServer);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException e) {
            System.err.println(e.toString());
        }        
    }
}
