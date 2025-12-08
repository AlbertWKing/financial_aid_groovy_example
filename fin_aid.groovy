/*  ** Above all else, I need to get the Groovy kernel for Jupyter
https://github.com/lappsgrid-incubator/jupyter-groovy-kernel
because this is becoming rather messy to try and work all in one file.
For learning, I don't think it's practical to create a new file for
everything I try or change. And I can add markdown. Since this is meant
to be a brief project focused first on learning/practicing combining
SQL and Groovy, I will figure out/add the kernel in the future. */


/* https://jdbc.postgresql.org/documentation/use/ is helpful
https://jdbc.postgresql.org/download/ was super unhelpful - ChatGPT told me to use Grab and that seems to work.
*/


// pull in driver for PostgreSQL to run with Groovy and starts the driver
@Grab('org.postgresql:postgresql:42.7.8')
import java.sql.* //groovy version used below

// println "Driver loaded!"

// build connection to postgres
String url = "jdbc:postgresql://127.0.0.1/financial_aid"
Properties props = new Properties()
props.setProperty("user", "postgres")
props.setProperty("password", "root")
props.setProperty("ssl", "false")
Connection conn = DriverManager.getConnection(url, props)

// makes a query
// this should be Java at this point - not postgresql anymore
// https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html

// Statement st = conn.createStatement()
// ResultSet rs = st.executeQuery("SELECT * FROM student_bio LIMIT 10;")
// while (rs.next()) {
//     System.out.print("Column 1 returned ")
//     System.out.println(rs.getString(1))
// }
// rs.close()
// st.close()



/* annoying it looks like I have to run the full grab and import thing each time! Java seems so inefficient...
but maybe python is just inefficient in keeping things open/connected??? */



// // Collects data, iterates through each row/column to print data.
// Statement st = conn.createStatement()
// ResultSet rs = st.executeQuery("SELECT * FROM student_bio LIMIT 10;")

// // // Get metadata to know column count
// ResultSetMetaData rsmd = rs.getMetaData()
// int columnCount = rsmd.getColumnCount()

// while (rs.next()) {
//     // Loop through all columns in the current row
//     for (int i = 1; i <= columnCount; i++) {
//         String columnName = rsmd.getColumnName(i)
//         String columnValue = rs.getString(i)
        
//         System.out.print(columnName + ": " + columnValue + "\t")
//     }
//     System.out.println(); // New line after each row
// }
// rs.close()
// st.close()



// more groovy-esque method to get data from sql

// @Grab('org.postgresql:postgresql:42.7.8') //already used above
// import groovy.sql.Sql

// def url = 'jdbc:postgresql://localhost:5432/mydb'
// def user = 'myuser'
// def password = 'mypassword'
// def driver = 'org.postgresql.Driver'

// def sql = Sql.newInstance(url, user, password, driver)

// // Collect rows into a list of maps
// def results = sql.rows("SELECT * FROM student_bio LIMIT 10")

// results.each { row ->
//     println row   // row is a GroovyRowResult (map-like)
// }





/* Uses Java structures to pull out table and put in a java map for use
outside of open sql connection */

@Grab('org.postgresql:postgresql:42.7.8')
import java.sql.*

String url = "jdbc:postgresql://127.0.0.1/financial_aid"
Properties props = new Properties()
props.setProperty("user", "postgres")
props.setProperty("password", "root")
props.setProperty("ssl", "false")
Connection conn = DriverManager.getConnection(url, props)

List<Map<String, Object>> results = new ArrayList<>()

Statement st = conn.createStatement()
ResultSet rs = st.executeQuery("SELECT * FROM student_bio LIMIT 10;")
ResultSetMetaData rsmd = rs.getMetaData()
int columnCount = rsmd.getColumnCount()

while (rs.next()) {
    Map<String, Object> row = new HashMap<>()
    for (int i = 1; i <= columnCount; i++) {
        row.put(rsmd.getColumnName(i), rs.getObject(i))
    }
    results.add(row)
}

rs.close()
st.close()
conn.close()

// results accessible outside of sql connection
results.forEach(System.out::println)



/* Copilot tells me I can't remove the column (key) name without essentially iterating over each line
and making it a single string or iterating to print each row sequentially. In other words, there's not
a convenient way to display the information like a Pandas dataframe so it's practical to read, without
adding computational work. So I guess I'll deal with it, even though I don't think this is especially
human readable.
*/

// List<Map<String, Object>> results = new ArrayList<>()

// Statement st = conn.createStatement()
// ResultSet rs = st.executeQuery("SELECT * FROM student_bio LIMIT 10;")
// ResultSetMetaData rsmd = rs.getMetaData()
// int columnCount = rsmd.getColumnCount()

// while (rs.next()) {
//     System.out.print("Column 1 returned ")
//     System.out.println(rs.getString(1))
//     Map<String, Object> row = new HashMap<>()
//     for (int i = 1; i <= columnCount; i++) {
//         row.put(rsmd.getColumnName(i), rs.getObject(i))
//     }
//     results.add(row)
// }
// rs.close()
// st.close()
// results.forEach(System.out::println)



/*
To output the mapping values:
*/

// List<Map<String, Object>> results = new ArrayList<>()

// Statement st = conn.createStatement()
// ResultSet rs = st.executeQuery("SELECT * FROM student_bio LIMIT 10;")
// ResultSetMetaData rsmd = rs.getMetaData()
// int columnCount = rsmd.getColumnCount()

// while (rs.next()) {
//     Map<String, Object> row = new LinkedHashMap<>()
//     for (int i = 1; i <= columnCount; i++) {
//         row.put(rsmd.getColumnName(i), rs.getObject(i))
//     }
//     results.add(row)
// }
// rs.close()
// st.close()
// // results.forEach(System.out::println)

/*Options: prints only the value;
or prints specified values, separated by a comma here;
or just the keys for the first row;
or keys for each row*/
// // results.each { row ->
// //     println row['last_name']
// //     }

// results.each { row ->
//     println "${row['last_name']}, ${row['first_name']}"
// }

// println results[0].keySet()

// results.each { row ->
//     println row['last_name']
//     }
// // this line could alternatively be results.each { println it['last_name'] }