
/* https://jdbc.postgresql.org/documentation/use/ is helpful
https://jdbc.postgresql.org/download/ was super unhelpful - ChatGPT told me to use Grab and that seems to work.
*/


// pull in driver for PostgreSQL to run with Groovy and starts the driver
@Grab('org.postgresql:postgresql:42.7.8')
import java.sql.*

println "Driver loaded!"

// connect to database???
String url = "jdbc:postgresql://127.0.0.1/financial_aid"
Properties props = new Properties()
props.setProperty("user", "postgres")
props.setProperty("password", "root")
props.setProperty("ssl", "false")
Connection conn = DriverManager.getConnection(url, props)

// makes a query


/* Full example code from jdbc website:

Statement st = conn.createStatement();
ResultSet rs = st.executeQuery("SELECT * FROM mytable WHERE columnfoo = 500");
while (rs.next()) {
    System.out.print("Column 1 returned ");
    System.out.println(rs.getString(1));
}
rs.close();
st.close();

*/

Statement st = conn.createStatement()
ResultSet rs = st.executeQuery("SELECT * FROM student_bio LIMIT 10;")
while (rs.next()) {
    System.out.print("Column 1 returned ")
    System.out.println(rs.getString(1))
}
rs.close()
st.close()

/* annoying it looks like I have to run the full grab and import thing each time! Java seems so inefficient...
but maybe python is just inefficient in keeping things open/connected??? */
