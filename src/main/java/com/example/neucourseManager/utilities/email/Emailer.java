package com.example.neucourseManager.utilities.email;



import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;




public class Emailer{


    // Constants & properties

    Properties emailProp;


    // default from address for team notification
    private static final  String DEFAULT_FROM =  "neucoursemanager@gmail.com";


    /**
     * Default Constructor : Sets the email property value from EmailConfigUtil class
     */
    public Emailer() throws IOException {
        this.emailProp = EMailConfigUtil.returnProperties();
    }


    /**
     * @param textBody the body of the email to be sent
     * @param subject the subject of the email
     * @throws AddressException
     * @throws MessagingException
     */
    public void sendEmail( String toEmailId, String subject,String textBody) throws  MessagingException {

        String sender = DEFAULT_FROM;

        String[] recipients  = {toEmailId};


        // Session created based upon the email server properties obj
        Session session = Session.getInstance(emailProp);

        //Message created froms session
        Message msg = new MimeMessage(session);

        if (sender != null) {
            msg.setFrom(new InternetAddress(sender));
        } else {
            msg.setFrom();
        }

        // Attach all to address in message metadata
        for (int i = 0; i < recipients.length; i++) {
            msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipients[i]));
        }

        // add subject
        msg.setSubject(subject);

        // attach body
        msg.setText(textBody);
        msg.setSentDate(new Date());

        Transport transport = session.getTransport(emailProp.getProperty("mail.transport.protocol"));
        transport.connect(
                emailProp.getProperty("mail.smtp.host"),
                emailProp.getProperty("mail.smtp.user"),
                emailProp.getProperty("mail.smtp.password"));
        Address[] addresses = new InternetAddress[recipients.length];

        for (int i = 0; i < recipients.length; i++) {
            addresses[i] = new InternetAddress(recipients[i]);
        }

        transport.sendMessage(msg, addresses);
        transport.close();

    }




}
