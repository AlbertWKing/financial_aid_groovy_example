// just storage to test a fragment that is not working

@Grab('org.postgresql:postgresql:42.7.8')
import java.sql.*

String url = "jdbc:postgresql://127.0.0.1/financial_aid"
Properties props = new Properties()
props.setProperty("user", "postgres")
props.setProperty("password", "root")
props.setProperty("ssl", "false")
Connection conn = DriverManager.getConnection(url, props)

// PARENT
// Statement st = conn.createStatement()
// ResultSet rs = st.executeQuery("SELECT * FROM student_bio LIMIT 10;")
// while (rs.next()) {
//     System.out.print("Column 1 returned ")
//     System.out.println(rs.getString(1))
// }
// rs.close()
// st.close()



List<Map<String, Object>> results = new ArrayList<>()

Statement st = conn.createStatement()
ResultSet rs = st.executeQuery("SELECT * FROM student_bio LIMIT 10;")
ResultSetMetaData rsmd = rs.getMetaData()
int columnCount = rsmd.getColumnCount()

while (rs.next()) {
    System.out.print("Column 1 returned ")
    System.out.println(rs.getString(1))
    Map<String, Object> row = new HashMap<>()
    for (int i = 1; i <= columnCount; i++) {
        row.put(rsmd.getColumnName(i), rs.getObject(i))
    }
    results.add(row)
}
rs.close()
st.close()

// while (rs.next()) {
//     Map<String, Object> row = new HashMap<>()
//     for (int i = 1; i <= columnCount; i++) {
//         row.put(rsmd.getColumnName(i), rs.getObject(i))
//     }
//     results.add(row)
// }

// // while (rs.next()) {
// //     Map<String, Object> row = new HashMap<>()
// //     for (int i = 1; i <= columnCount; i++) {
// //         row.put(rsmd.getColumnName(i), rs.getObject(i))
// //     }
// //     results.add(row)
// // }

// // rs.close()
// // st.close()
// // conn.close()

// // Now you can use results outside the connection
// results.forEach(System.out::println)
