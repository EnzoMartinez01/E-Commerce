package com.empresa.empresa.Services.Mails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EmailService {
    private static final Logger LOGGER = Logger.getLogger(EmailService.class.getName());

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // Send verification email
    public void sendVerificationEmail(String to, String verificationCode, String fullname) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("Bienvenido a la EMPRESA");

            String htmlContent = "<html>"
                    + "<body style=font-family: Arial, sans-serif;'>"
                    + "<div style='padding: 20px; text-align: center;'>"
                    + "<h1 style='color: #000000; font-weight: 800;'>¡Bienvenido/a, " + fullname + "!</h1>"
                    + "<p style='color: #000000;'>Nos complace darte la bienvenida al sistema de <strong style='color: #000000;'>'EMPRESARIAL'</strong>.</p>"
                    + "<p style='color: #000000;'>Como nuevo usuario, estamos seguros de que tu contribución será valiosa para alcanzar nuestros objetivos comunes.</p>"
                    + "<p style='color: #000000;'>Tu código de verificación es: <strong style='color: #000000;'>" + verificationCode + "</strong></p>"
                    + "<p style='color: #000000;'>Por favor, verifica tu dirección de correo electrónico para completar tu registro.</p>"
                    + "<p style='color: #000000;'>Si tienes alguna pregunta, no dudes en contactarnos.</p>"
                    + "<p style='color: #000000;'>Saludos cordiales,</p>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Send password reset email
    public void sendPasswordResetEmail(String to, String verificationCode, String fullname) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("Restablecimiento de contraseña");

            String htmlContent = "<html>"
                    + "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;'>"
                    + "<div style='max-width: 600px; margin: auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px #cccccc;'>"
                    + "<h2 style='color: #333333; text-align: center;'>Hola, " + fullname + "!</h2>"
                    + "<p style='color: #555555; text-align: center;'>Hemos recibido una solicitud para restablecer tu contraseña.</p>"
                    + "<p style='color: #555555; text-align: center;'>Tu código de verificación es:</p>"
                    + "<h3 style='color: #007BFF; text-align: center; background: #f8f9fa; padding: 10px; border-radius: 5px;'>" + verificationCode + "</h3>"
                    + "<p style='color: #555555; text-align: center;'>Este código expirará en 10 minutos.</p>"
                    + "<p style='color: #555555; text-align: center;'>Si no solicitaste este cambio, puedes ignorar este mensaje.</p>"
                    + "<p style='color: #555555; text-align: center;'>Saludos,</p>"
                    + "<p style='color: #007BFF; text-align: center; font-weight: bold;'>Equipo EMPRESARIAL</p>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Send payment approval or rejection emails
    public void sendPaymentApprovedEmail(String to, String fullName, String reference) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("Confirmación de pago recibido Ecommerce");

            String htmlContent = "<html>"
                    + "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;'>"
                    + "<div style='max-width: 600px; margin: auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px #cccccc;'>"
                    + "<h2 style='color: #28a745; text-align: center;'>¡Hola, " + fullName + "!</h2>"
                    + "<p style='color: #555555; text-align: center;'>Tu pago con referencia <strong>" + reference + "</strong> ha sido <strong>aprobado</strong>.</p>"
                    + "<p style='color: #555555; text-align: center;'>Gracias por tu compra. Ya estamos procesando tu pedido.</p>"
                    + "<p style='color: #555555; text-align: center;'>Saludos,</p>"
                    + "<p style='color: #007BFF; text-align: center; font-weight: bold;'>Equipo EMPRESARIAL</p>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendPaymentRejectedEmail(String to, String fullName, String reference) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("Pago rechazado Ecommerce");

            String htmlContent = "<html>"
                    + "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;'>"
                    + "<div style='max-width: 600px; margin: auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px #cccccc;'>"
                    + "<h2 style='color: #dc3545; text-align: center;'>Hola, " + fullName + ".</h2>"
                    + "<p style='color: #555555; text-align: center;'>Lamentamos informarte que tu pago con referencia <strong>" + reference + "</strong> ha sido <strong>rechazado</strong>.</p>"
                    + "<p style='color: #555555; text-align: center;'>Por favor revisa los datos del comprobante o comunícate con soporte.</p>"
                    + "<p style='color: #555555; text-align: center;'>Saludos,</p>"
                    + "<p style='color: #007BFF; text-align: center; font-weight: bold;'>Equipo EMPRESARIAL</p>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
