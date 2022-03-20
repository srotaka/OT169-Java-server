package com.alkemy.ong.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
@Slf4j
public class EmailService {

    public void sendMail(String emailTo, String emailSubject, Content body) throws IOException {
        Email from = new Email(System.getenv().get("MAIL_ONG"));
        Email to = new Email(emailTo);
        String subject = emailSubject;
        Content content = body;

        Logger logger = Logger.getLogger(EmailService.class.getName());

        Mail mail = new Mail(from, subject, to, content);
        SendGrid sgKey = new SendGrid(System.getenv().get("SENDGRID_KEY"));
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sgKey.api(request);
            if (response != null) {
                logger.log(Level.INFO, "codigo respuesta desde sendgrid {0} ", response.getStatusCode());
                // Aquí imprime en la terminal el mail de destino
                log.info("Mail enviado a {}", emailTo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendWelcomeMail(String emailTo) throws IOException {
        String emailSubject = "Bienvenidos a Somos Más";
        Content body = new Content("text/html", welcomeEmailTemplate());
        try {
            sendMail(emailTo, emailSubject, body);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public String welcomeEmailTemplate() {
        return "<!DOCTYPE html\n" +
                "    PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
                "    style=\"font-family:arial, 'helvetica neue', helvetica, sans-serif\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "    <title>Somos Mas - Bienvenida</title>\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Noticia+Text:400,400i,700,700i\" rel=\"stylesheet\">\n" +
                "    <style type=\"text/css\">\n" +
                "        #outlook a {\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        .es-button {\n" +
                "            mso-style-priority: 100 !important;\n" +
                "            text-decoration: none !important;\n" +
                "        }\n" +
                "\n" +
                "        a[x-apple-data-detectors] {\n" +
                "            color: inherit !important;\n" +
                "            text-decoration: none !important;\n" +
                "            font-size: inherit !important;\n" +
                "            font-family: inherit !important;\n" +
                "            font-weight: inherit !important;\n" +
                "            line-height: inherit !important;\n" +
                "        }\n" +
                "\n" +
                "        .es-desk-hidden {\n" +
                "            display: none;\n" +
                "            float: left;\n" +
                "            overflow: hidden;\n" +
                "            width: 0;\n" +
                "            max-height: 0;\n" +
                "            line-height: 0;\n" +
                "            mso-hide: all;\n" +
                "        }\n" +
                "\n" +
                "        [data-ogsb] .es-button {\n" +
                "            border-width: 0 !important;\n" +
                "            padding: 10px 30px 10px 30px !important;\n" +
                "        }\n" +
                "\n" +
                "        @media only screen and (max-width:600px) {\n" +
                "\n" +
                "            p,\n" +
                "            ul li,\n" +
                "            ol li,\n" +
                "            a {\n" +
                "                line-height: 150% !important\n" +
                "            }\n" +
                "\n" +
                "            h1,\n" +
                "            h2,\n" +
                "            h3,\n" +
                "            h1 a,\n" +
                "            h2 a,\n" +
                "            h3 a {\n" +
                "                line-height: 120% !important\n" +
                "            }\n" +
                "\n" +
                "            h1 {\n" +
                "                font-size: 36px !important;\n" +
                "                text-align: left\n" +
                "            }\n" +
                "\n" +
                "            h2 {\n" +
                "                font-size: 26px !important;\n" +
                "                text-align: left\n" +
                "            }\n" +
                "\n" +
                "            h3 {\n" +
                "                font-size: 20px !important;\n" +
                "                text-align: left\n" +
                "            }\n" +
                "\n" +
                "            .es-header-body h1 a,\n" +
                "            .es-content-body h1 a,\n" +
                "            .es-footer-body h1 a {\n" +
                "                font-size: 36px !important;\n" +
                "                text-align: left\n" +
                "            }\n" +
                "\n" +
                "            .es-header-body h2 a,\n" +
                "            .es-content-body h2 a,\n" +
                "            .es-footer-body h2 a {\n" +
                "                font-size: 26px !important;\n" +
                "                text-align: left\n" +
                "            }\n" +
                "\n" +
                "            .es-header-body h3 a,\n" +
                "            .es-content-body h3 a,\n" +
                "            .es-footer-body h3 a {\n" +
                "                font-size: 20px !important;\n" +
                "                text-align: left\n" +
                "            }\n" +
                "\n" +
                "            .es-menu td a {\n" +
                "                font-size: 12px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-header-body p,\n" +
                "            .es-header-body ul li,\n" +
                "            .es-header-body ol li,\n" +
                "            .es-header-body a {\n" +
                "                font-size: 14px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-content-body p,\n" +
                "            .es-content-body ul li,\n" +
                "            .es-content-body ol li,\n" +
                "            .es-content-body a {\n" +
                "                font-size: 14px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-footer-body p,\n" +
                "            .es-footer-body ul li,\n" +
                "            .es-footer-body ol li,\n" +
                "            .es-footer-body a {\n" +
                "                font-size: 14px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-infoblock p,\n" +
                "            .es-infoblock ul li,\n" +
                "            .es-infoblock ol li,\n" +
                "            .es-infoblock a {\n" +
                "                font-size: 12px !important\n" +
                "            }\n" +
                "\n" +
                "            *[class=\"gmail-fix\"] {\n" +
                "                display: none !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-txt-c,\n" +
                "            .es-m-txt-c h1,\n" +
                "            .es-m-txt-c h2,\n" +
                "            .es-m-txt-c h3 {\n" +
                "                text-align: center !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-txt-r,\n" +
                "            .es-m-txt-r h1,\n" +
                "            .es-m-txt-r h2,\n" +
                "            .es-m-txt-r h3 {\n" +
                "                text-align: right !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-txt-l,\n" +
                "            .es-m-txt-l h1,\n" +
                "            .es-m-txt-l h2,\n" +
                "            .es-m-txt-l h3 {\n" +
                "                text-align: left !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-txt-r img,\n" +
                "            .es-m-txt-c img,\n" +
                "            .es-m-txt-l img {\n" +
                "                display: inline !important\n" +
                "            }\n" +
                "\n" +
                "            .es-button-border {\n" +
                "                display: inline-block !important\n" +
                "            }\n" +
                "\n" +
                "            a.es-button,\n" +
                "            button.es-button {\n" +
                "                font-size: 20px !important;\n" +
                "                display: inline-block !important\n" +
                "            }\n" +
                "\n" +
                "            .es-adaptive table,\n" +
                "            .es-left,\n" +
                "            .es-right {\n" +
                "                width: 100% !important\n" +
                "            }\n" +
                "\n" +
                "            .es-content table,\n" +
                "            .es-header table,\n" +
                "            .es-footer table,\n" +
                "            .es-content,\n" +
                "            .es-footer,\n" +
                "            .es-header {\n" +
                "                width: 100% !important;\n" +
                "                max-width: 600px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-adapt-td {\n" +
                "                display: block !important;\n" +
                "                width: 100% !important\n" +
                "            }\n" +
                "\n" +
                "            .adapt-img {\n" +
                "                width: 100% !important;\n" +
                "                height: auto !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p0 {\n" +
                "                padding: 0 !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p0r {\n" +
                "                padding-right: 0 !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p0l {\n" +
                "                padding-left: 0 !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p0t {\n" +
                "                padding-top: 0 !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p0b {\n" +
                "                padding-bottom: 0 !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p20b {\n" +
                "                padding-bottom: 20px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-mobile-hidden,\n" +
                "            .es-hidden {\n" +
                "                display: none !important\n" +
                "            }\n" +
                "\n" +
                "            tr.es-desk-hidden,\n" +
                "            td.es-desk-hidden,\n" +
                "            table.es-desk-hidden {\n" +
                "                width: auto !important;\n" +
                "                overflow: visible !important;\n" +
                "                float: none !important;\n" +
                "                max-height: inherit !important;\n" +
                "                line-height: inherit !important\n" +
                "            }\n" +
                "\n" +
                "            tr.es-desk-hidden {\n" +
                "                display: table-row !important\n" +
                "            }\n" +
                "\n" +
                "            table.es-desk-hidden {\n" +
                "                display: table !important\n" +
                "            }\n" +
                "\n" +
                "            td.es-desk-menu-hidden {\n" +
                "                display: table-cell !important\n" +
                "            }\n" +
                "\n" +
                "            .es-menu td {\n" +
                "                width: 1% !important\n" +
                "            }\n" +
                "\n" +
                "            table.es-table-not-adapt,\n" +
                "            .esd-block-html table {\n" +
                "                width: auto !important\n" +
                "            }\n" +
                "\n" +
                "            table.es-social {\n" +
                "                display: inline-block !important\n" +
                "            }\n" +
                "\n" +
                "            table.es-social td {\n" +
                "                display: inline-block !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p5 {\n" +
                "                padding: 5px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p5t {\n" +
                "                padding-top: 5px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p5b {\n" +
                "                padding-bottom: 5px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p5r {\n" +
                "                padding-right: 5px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p5l {\n" +
                "                padding-left: 5px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p10 {\n" +
                "                padding: 10px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p10t {\n" +
                "                padding-top: 10px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p10b {\n" +
                "                padding-bottom: 10px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p10r {\n" +
                "                padding-right: 10px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p10l {\n" +
                "                padding-left: 10px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p15 {\n" +
                "                padding: 15px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p15t {\n" +
                "                padding-top: 15px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p15b {\n" +
                "                padding-bottom: 15px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p15r {\n" +
                "                padding-right: 15px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p15l {\n" +
                "                padding-left: 15px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p20 {\n" +
                "                padding: 20px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p20t {\n" +
                "                padding-top: 20px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p20r {\n" +
                "                padding-right: 20px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p20l {\n" +
                "                padding-left: 20px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p25 {\n" +
                "                padding: 25px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p25t {\n" +
                "                padding-top: 25px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p25b {\n" +
                "                padding-bottom: 25px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p25r {\n" +
                "                padding-right: 25px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p25l {\n" +
                "                padding-left: 25px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p30 {\n" +
                "                padding: 30px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p30t {\n" +
                "                padding-top: 30px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p30b {\n" +
                "                padding-bottom: 30px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p30r {\n" +
                "                padding-right: 30px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p30l {\n" +
                "                padding-left: 30px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p35 {\n" +
                "                padding: 35px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p35t {\n" +
                "                padding-top: 35px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p35b {\n" +
                "                padding-bottom: 35px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p35r {\n" +
                "                padding-right: 35px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p35l {\n" +
                "                padding-left: 35px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p40 {\n" +
                "                padding: 40px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p40t {\n" +
                "                padding-top: 40px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p40b {\n" +
                "                padding-bottom: 40px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p40r {\n" +
                "                padding-right: 40px !important\n" +
                "            }\n" +
                "\n" +
                "            .es-m-p40l {\n" +
                "                padding-left: 40px !important\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body\n" +
                "    style=\"width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                "    <div class=\"es-wrapper-color\" style=\"background-color:#FAFAFA\">\n" +
                "        <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#FAFAFA\">\n" +
                "            <tr>\n" +
                "                <td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                "                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\"\n" +
                "                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "                        <tr>\n" +
                "                            <td class=\"es-info-area\" align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "                                <table class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                    style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\"\n" +
                "                                    bgcolor=\"#FFFFFF\">\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"left\" style=\"padding:20px;Margin:0\">\n" +
                "                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"center\" valign=\"top\"\n" +
                "                                                        style=\"padding:0;Margin:0;width:560px\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"center\"\n" +
                "                                                                    style=\"padding:0;Margin:0;display:none\"></td>\n" +
                "                                                            </tr>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\"\n" +
                "                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "                        <tr>\n" +
                "                            <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "                                <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\"\n" +
                "                                    cellspacing=\"0\"\n" +
                "                                    style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"left\"\n" +
                "                                            style=\"Margin:0;padding-top:15px;padding-left:20px;padding-right:20px;padding-bottom:30px\">\n" +
                "                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"center\" valign=\"top\"\n" +
                "                                                        style=\"padding:0;Margin:0;width:560px\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                            role=\"presentation\"\n" +
                "                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"center\"\n" +
                "                                                                    style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px;font-size:0px\">\n" +
                "                                                                    <img src=\"https://kxciwa.stripocdn.email/content/guids/CABINET_ea5d6da1f0d443fa2e93ab933cbf241b/images/logosomos_mas.png\"\n" +
                "                                                                        alt\n" +
                "                                                                        style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"\n" +
                "                                                                        width=\"150\"></td>\n" +
                "                                                            </tr>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"center\" class=\"es-m-p0r es-m-p0l\"\n" +
                "                                                                    style=\"Margin:0;padding-top:10px;padding-bottom:10px;padding-left:40px;padding-right:40px\">\n" +
                "                                                                    <p\n" +
                "                                                                        style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'noticia text', georgia, 'times new roman', serif;line-height:27px;color:#333333;font-size:18px\">\n" +
                "                                                                        ¡Muchas gracias por registrarse!&nbsp;</p>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"center\"\n" +
                "                                                                    style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:5px\">\n" +
                "                                                                    <span class=\"es-button-border\"\n" +
                "                                                                        style=\"border-style:solid;border-color:#2CB543;background:#9ac9fb;border-width:0px;display:inline-block;border-radius:5px;width:auto\"><a\n" +
                "                                                                            href=\"\" class=\"es-button\" target=\"_blank\"\n" +
                "                                                                            style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#FFFFFF;font-size:20px;border-style:solid;border-color:#9ac9fb;border-width:10px 30px 10px 30px;display:inline-block;background:#9ac9fb;border-radius:5px;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-weight:normal;font-style:normal;line-height:24px;width:auto;text-align:center\">INICIAR\n" +
                "                                                                            SESIÓN</a></span></td>\n" +
                "                                                            </tr>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"left\"\n" +
                "                                            style=\"Margin:0;padding-bottom:10px;padding-top:20px;padding-left:20px;padding-right:20px\">\n" +
                "                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"center\" valign=\"top\"\n" +
                "                                                        style=\"padding:0;Margin:0;width:560px\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                            role=\"presentation\"\n" +
                "                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"center\" class=\"es-m-txt-l\"\n" +
                "                                                                    style=\"padding:0;Margin:0;padding-bottom:10px\">\n" +
                "                                                                    <h1\n" +
                "                                                                        style=\"Margin:0;line-height:55px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:46px;font-style:normal;font-weight:bold;color:#9ac9fb\">\n" +
                "                                                                        ¿Quiénes somos?</h1>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"left\"\n" +
                "                                            style=\"padding:0;Margin:0;padding-bottom:20px;padding-left:20px;padding-right:20px\">\n" +
                "                                            <!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:90px\" valign=\"top\"><![endif]-->\n" +
                "                                            <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\"\n" +
                "                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"es-m-p0r es-m-p20b\" align=\"center\"\n" +
                "                                                        style=\"padding:0;Margin:0;width:90px\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                            role=\"presentation\"\n" +
                "                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"center\" class=\"es-m-txt-l\"\n" +
                "                                                                    style=\"padding:0;Margin:0;font-size:0px\"><img\n" +
                "                                                                        src=\"https://kxciwa.stripocdn.email/content/guids/CABINET_ea5d6da1f0d443fa2e93ab933cbf241b/images/iconnosotros.png\"\n" +
                "                                                                        alt\n" +
                "                                                                        style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"\n" +
                "                                                                        width=\"90\"></td>\n" +
                "                                                            </tr>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </table>                                            \n" +
                "                                            <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\"\n" +
                "                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"center\" style=\"padding:0;Margin:0;width:440px\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                            role=\"presentation\"\n" +
                "                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"left\" class=\"es-m-txt-l\"\n" +
                "                                                                    style=\"padding:0;Margin:0;padding-bottom:10px\">\n" +
                "                                                                    <h3\n" +
                "                                                                        style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:20px;font-style:normal;font-weight:bold;color:#333333\">\n" +
                "                                                                        Sobre Nosotros</h3>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"left\" style=\"padding:0;Margin:0\">\n" +
                "                                                                    <p\n" +
                "                                                                        style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">\n" +
                "                                                                        Somos una asociación civil sin fines de lucro\n" +
                "                                                                        que se creó en 1997 con la intención de dar\n" +
                "                                                                        alimento a las familias del barrio. Trabajamos\n" +
                "                                                                        con los chicos y chicas, mamás y papás, abuelos\n" +
                "                                                                        y vecinos del barrio La Cava generando procesos\n" +
                "                                                                        de crecimiento y de inserción social.</p>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"left\" style=\"padding:20px;Margin:0\">                                            \n" +
                "                                            <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\"\n" +
                "                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"es-m-p20b\" align=\"center\"\n" +
                "                                                        style=\"padding:0;Margin:0;width:90px\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                            role=\"presentation\"\n" +
                "                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"center\" class=\"es-m-txt-l\"\n" +
                "                                                                    style=\"padding:0;Margin:0;font-size:0px\"><img\n" +
                "                                                                        src=\"https://kxciwa.stripocdn.email/content/guids/CABINET_ea5d6da1f0d443fa2e93ab933cbf241b/images/iconobjetivos.png\"\n" +
                "                                                                        alt\n" +
                "                                                                        style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"\n" +
                "                                                                        width=\"90\"></td>\n" +
                "                                                            </tr>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </table>                                            \n" +
                "                                            <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\"\n" +
                "                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"es-m-p0r\" align=\"center\"\n" +
                "                                                        style=\"padding:0;Margin:0;width:440px\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                            role=\"presentation\"\n" +
                "                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"left\" class=\"es-m-txt-l\"\n" +
                "                                                                    style=\"padding:0;Margin:0;padding-bottom:10px\">\n" +
                "                                                                    <h3\n" +
                "                                                                        style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:20px;font-style:normal;font-weight:bold;color:#333333\">\n" +
                "                                                                        Nuestra Visión</h3>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"left\" style=\"padding:0;Margin:0\">\n" +
                "                                                                    <p\n" +
                "                                                                        style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">\n" +
                "                                                                        Mejorar la calidad de vida de niños y familias\n" +
                "                                                                        en situación de vulnerabilidad en el barrio La\n" +
                "                                                                        Cava, otorgando un cambio de rumbo en cada\n" +
                "                                                                        individuo a través de la educación, salud,\n" +
                "                                                                        trabajo, deporte, responsabilidad y compromiso.\n" +
                "                                                                    </p>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"left\" style=\"padding:20px;Margin:0\">                                            \n" +
                "                                            <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\"\n" +
                "                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"es-m-p0r es-m-p20b\" align=\"center\"\n" +
                "                                                        style=\"padding:0;Margin:0;width:90px\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                            role=\"presentation\"\n" +
                "                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"center\" class=\"es-m-txt-l\"\n" +
                "                                                                    style=\"padding:0;Margin:0;font-size:0px\"><img\n" +
                "                                                                        src=\"https://kxciwa.stripocdn.email/content/guids/CABINET_ea5d6da1f0d443fa2e93ab933cbf241b/images/iconactividad_MsK.png\"\n" +
                "                                                                        alt\n" +
                "                                                                        style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"\n" +
                "                                                                        width=\"90\"></td>\n" +
                "                                                            </tr>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </table>\n" +
                "                                            <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\"\n" +
                "                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"center\" style=\"padding:0;Margin:0;width:440px\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                            role=\"presentation\"\n" +
                "                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"left\" class=\"es-m-txt-l\"\n" +
                "                                                                    style=\"padding:0;Margin:0;padding-bottom:10px\">\n" +
                "                                                                    <h3\n" +
                "                                                                        style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:20px;font-style:normal;font-weight:bold;color:#333333\">\n" +
                "                                                                        Actividad de la ONG</h3>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"left\" style=\"padding:0;Margin:0\">\n" +
                "                                                                    <ul>\n" +
                "                                                                        <li\n" +
                "                                                                            style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;Margin-bottom:15px;margin-left:0;color:#333333;font-size:14px\">\n" +
                "                                                                            <p\n" +
                "                                                                                style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">\n" +
                "                                                                                Programas Educativos</p>\n" +
                "                                                                        </li>\n" +
                "                                                                        <li\n" +
                "                                                                            style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;Margin-bottom:15px;margin-left:0;color:#333333;font-size:14px\">\n" +
                "                                                                            <p\n" +
                "                                                                                style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">\n" +
                "                                                                                Apoyo Escolar para el nivel Primario</p>\n" +
                "                                                                        </li>\n" +
                "                                                                        <li\n" +
                "                                                                            style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;Margin-bottom:15px;margin-left:0;color:#333333;font-size:14px\">\n" +
                "                                                                            <p\n" +
                "                                                                                style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">\n" +
                "                                                                                Apoyo Escolar Nivel Secundaria</p>\n" +
                "                                                                        </li>\n" +
                "                                                                        <li\n" +
                "                                                                            style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;Margin-bottom:15px;margin-left:0;color:#333333;font-size:14px\">\n" +
                "                                                                            <p\n" +
                "                                                                                style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">\n" +
                "                                                                                Tutorías</p>\n" +
                "                                                                        </li>\n" +
                "                                                                    </ul>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    <tr>\n" +
                "                                        <td class=\"esd-structure es-p20\" align=\"left\" esd-custom-block-id=\"388973\">\n" +
                "                                            <!--[if mso]><table dir=\"ltr\" cellpadding=\"0\"><table dir=\"rtl\" width=\"560\" cellpadding=\"0\" cellspacing=\"0\"><tr><td dir=\"ltr\" width=\"440\" valign=\"top\"><![endif]-->\n" +
                "                                            <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td width=\"90\" class=\"esd-container-frame es-m-p20b\" align=\"center\">\n" +
                "                                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr>\n" +
                "                                                                        <td align=\"center\" class=\"esd-block-image es-m-txt-l\" style=\"font-size: 0px;\">\n" +
                "                                                                            <a target=\"_blank\"><img src=\"https://kxciwa.stripocdn.email/content/guids/CABINET_ea5d6da1f0d443fa2e93ab933cbf241b/images/iconayudanos.png\" alt style=\"display: block;\" width=\"90\"></a>\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "                                            <!--[if mso]></td><td dir=\"ltr\" width=\"30\"></td><td dir=\"ltr\" width=\"90\" valign=\"top\"><![endif]-->\n" +
                "                                            <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td width=\"440\" class=\"es-m-p0r esd-container-frame\" align=\"center\">\n" +
                "                                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr>\n" +
                "                                                                        <td align=\"center\" class=\"esd-block-text\">\n" +
                "                                                                            <p style=\"font-family: merriweather, georgia, 'times new roman', serif; font-size: 20px;\"><strong>Hoy somos un centro comunitario que acompaña a más de 700 personas. ¡Necesitamos de tu ayuda <br>para seguir creciendo!</strong></p>\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "                                            <!--[if mso]></td></tr></table></table><![endif]-->\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"left\"\n" +
                "                                            style=\"padding:0;Margin:0;padding-left:20px;padding-right:20px;padding-bottom:30px\">\n" +
                "                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"center\" valign=\"top\"\n" +
                "                                                        style=\"padding:0;Margin:0;width:560px\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-radius:5px\"\n" +
                "                                                            role=\"presentation\">\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"center\"\n" +
                "                                                                    style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px\">\n" +
                "                                                                    <span class=\"es-button-border\"\n" +
                "                                                                        style=\"border-style:solid;border-color:#2CB543;background:#9ac9fb;border-width:0px;display:inline-block;border-radius:6px;width:auto\"><a\n" +
                "                                                                            href=\"\" class=\"es-button\" target=\"_blank\"\n" +
                "                                                                            style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#FFFFFF;font-size:20px;border-style:solid;border-color:#9ac9fb;border-width:10px 30px 10px 30px;display:inline-block;background:#9ac9fb;border-radius:6px;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-weight:normal;font-style:normal;line-height:24px;width:auto;text-align:center;border-left-width:30px;border-right-width:30px\">COMENZAR\n" +
                "                                                                            A COLABORAR</a></span></td>\n" +
                "                                                            </tr>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\"\n" +
                "                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "                        <tr>\n" +
                "                            <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "                                <table class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                    style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\">\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"left\"\n" +
                "                                            style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:20px;padding-right:20px\">\n" +
                "                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"left\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                            role=\"presentation\"\n" +
                "                                                            style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"center\"\n" +
                "                                                                    style=\"padding:0;Margin:0;padding-top:15px;padding-bottom:15px;font-size:0\">\n" +
                "                                                                    <table cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                                        class=\"es-table-not-adapt es-social\"\n" +
                "                                                                        role=\"presentation\"\n" +
                "                                                                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                                        <tr>\n" +
                "                                                                            <td align=\"center\" valign=\"top\"\n" +
                "                                                                                style=\"padding:0;Margin:0;padding-right:40px\">\n" +
                "                                                                                <img title=\"Facebook\"\n" +
                "                                                                                    src=\"https://kxciwa.stripocdn.email/content/assets/img/social-icons/logo-black/facebook-logo-black.png\"\n" +
                "                                                                                    alt=\"Fb\" width=\"32\"\n" +
                "                                                                                    style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\">\n" +
                "                                                                            </td>\n" +
                "                                                                            <td align=\"center\" valign=\"top\"\n" +
                "                                                                                style=\"padding:0;Margin:0;padding-right:40px\">\n" +
                "                                                                                <img title=\"Twitter\"\n" +
                "                                                                                    src=\"https://kxciwa.stripocdn.email/content/assets/img/social-icons/logo-black/twitter-logo-black.png\"\n" +
                "                                                                                    alt=\"Tw\" width=\"32\"\n" +
                "                                                                                    style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\">\n" +
                "                                                                            </td>\n" +
                "                                                                            <td align=\"center\" valign=\"top\"\n" +
                "                                                                                style=\"padding:0;Margin:0;padding-right:40px\">\n" +
                "                                                                                <img title=\"Instagram\"\n" +
                "                                                                                    src=\"https://kxciwa.stripocdn.email/content/assets/img/social-icons/logo-black/instagram-logo-black.png\"\n" +
                "                                                                                    alt=\"Inst\" width=\"32\"\n" +
                "                                                                                    style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\">\n" +
                "                                                                            </td>\n" +
                "                                                                            <td align=\"center\" valign=\"top\"\n" +
                "                                                                                style=\"padding:0;Margin:0\"><img\n" +
                "                                                                                    title=\"Youtube\"\n" +
                "                                                                                    src=\"https://kxciwa.stripocdn.email/content/assets/img/social-icons/logo-black/youtube-logo-black.png\"\n" +
                "                                                                                    alt=\"Yt\" width=\"32\"\n" +
                "                                                                                    style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\">\n" +
                "                                                                            </td>\n" +
                "                                                                        </tr>\n" +
                "                                                                    </table>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            <tr>\n" +
                "                                                                <td align=\"center\"\n" +
                "                                                                    style=\"padding:0;Margin:0;padding-bottom:35px\">\n" +
                "                                                                    <p\n" +
                "                                                                        style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;color:#333333;font-size:12px\">\n" +
                "                                                                        Somos Más © 2021 | Todos los derechos reservados\n" +
                "                                                                    </p>\n" +
                "                                                                    <p\n" +
                "                                                                        style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;color:#333333;font-size:12px\">\n" +
                "                                                                        Dirección 1234, Localidad, Provincia, CP</p>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                            <tr>\n" +
                "                                                                <td style=\"padding:0;Margin:0\">\n" +
                "                                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                                        class=\"es-menu\" role=\"presentation\"\n" +
                "                                                                        style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                                                        <tr class=\"links\">\n" +
                "                                                                            <td align=\"center\" valign=\"top\"\n" +
                "                                                                                width=\"33.33%\"\n" +
                "                                                                                style=\"Margin:0;padding-left:5px;padding-right:5px;padding-top:5px;padding-bottom:5px;border:0\">\n" +
                "                                                                                <a target=\"_blank\" href=\"\"\n" +
                "                                                                                    style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#333333;font-size:12px\">Visita\n" +
                "                                                                                    nuestra página</a></td>\n" +
                "                                                                            <td align=\"center\" valign=\"top\"\n" +
                "                                                                                width=\"33.33%\"\n" +
                "                                                                                style=\"Margin:0;padding-left:5px;padding-right:5px;padding-top:5px;padding-bottom:5px;border:0;border-left:1px solid #cccccc\">\n" +
                "                                                                                <a target=\"_blank\" href=\"\"\n" +
                "                                                                                    style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#333333;font-size:12px\">Políticas\n" +
                "                                                                                    de privacidad</a></td>\n" +
                "                                                                            <td align=\"center\" valign=\"top\"\n" +
                "                                                                                width=\"33.33%\"\n" +
                "                                                                                style=\"Margin:0;padding-left:5px;padding-right:5px;padding-top:5px;padding-bottom:5px;border:0;border-left:1px solid #cccccc\">\n" +
                "                                                                                <a target=\"_blank\" href=\"\"\n" +
                "                                                                                    style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#333333;font-size:12px\">Términos\n" +
                "                                                                                    y condiciones</a></td>\n" +
                "                                                                        </tr>\n" +
                "                                                                    </table>\n" +
                "                                                                </td>\n" +
                "                                                            </tr>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </table>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                </table>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

}
