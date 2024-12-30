package com.mycompany.loginhere.modal;


import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.sql.*;

public class Mail {

    private String generateVerificationToken() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    public void sendVerificationEmail(String recipientEmail, String basemail, String basemailpassword, Connection con) throws MessagingException {
        String token = generateVerificationToken();
        String verificationLink = "http://localhost:8082/LoginHere/D.M?action=verify&email=" + recipientEmail + "&token=" + token;

        String subject = "Email Verification";
        String message = "Click the link below to verify your email:\n" + verificationLink;

        sendEmail(recipientEmail, subject, message, basemail, basemailpassword);

        saveVerificationToken(recipientEmail, token, con);
    }

    private void sendEmail(String recipientEmail, String subject, String messageContent, String basemail, String basemailpassword) throws MessagingException {
        String host = "smtp.gmail.com";
        final String username = basemail;
        final String password = basemailpassword;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject(subject);
        message.setText(messageContent);

        Transport.send(message);
    }

    private void saveVerificationToken(String email, String token, Connection con) {
        String updateQuery = "UPDATE login SET token = ? WHERE email = ?";
        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(updateQuery);
            statement.setString(1, token);
            statement.setString(2, email);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public boolean verifyEmailToken(String email, String token, Connection con) {
        String selectQuery = "SELECT token FROM login WHERE email = ? AND token = ?";
        String updateQuery = "UPDATE login SET verify = 'yes' WHERE email = ?";
        PreparedStatement selectStatement = null;
        PreparedStatement updateStatement = null;

        try {
            selectStatement = con.prepareStatement(selectQuery);
            updateStatement = con.prepareStatement(updateQuery);
            selectStatement.setString(1, email);
            selectStatement.setString(2, token);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                updateStatement.setString(1, email);
                updateStatement.executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
