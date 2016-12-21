package auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Todo: SpringWebSecurity, PreparedStatements
 * Todo: h2 und sqlite deinstallieren
 *
 * HTTP:
 * GET - sicher, keine Nebeneffekte
 * CONNECT - https herstellen?
 * PUT - ressource anlegen am server
 *
 * supported MIME-Type: Json, xml,...
 *
 * HATEOAS - JSON
 *
 * register
 * put: arg: user (muss unique sein) [ggf auch: domain/ip prüfen, ob von angemeldeter website],
 * zurück: qr-code (mit bib, (zing?)) und seed base32 - kodiert und backup-code zur deaktivierung
 *
 * checkCode
 * get: arg:user+token+Zeitstempel -
 * zurück: valid+Zeitstempel/notvalid/backupcode (0/1/-1)
 * (der Zeitstempel ist gegen replay-angriffe)
 *
 * delete
 * delete: arg: user+token+zeitstempel -
 * zurück: valid+Zeitstempel/notvalid
 *
 * Database Setup:
 */

@SpringBootApplication //short for @Configuration @EnableAutoConfiguration @EnableWebMvc @ComponentScan
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}

// WASTE:

// CREATE TABLE ALLOWEDIPS
//(
//Ip varchar(255) NOT NULL,
//PRIMARY KEY (Ip)
//)
//
// INSERT INTO ALLOWEDIPS
// VALUES ('0:0:0:0:0:0:0:1');

 //        statement.execute("CREATE TABLE ALLOWEDIPS ( Ip varchar(255) NOT NULL, PRIMARY KEY (Ip) )");
 //        statement.execute("INSERT INTO ALLOWEDIPS VALUES ('0:0:0:0:0:0:0:1');");
 //        statement.execute("INSERT INTO ALLOWEDIPS VALUES ('testIP');");
