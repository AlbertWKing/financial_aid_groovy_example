@Grab('org.postgresql:postgresql:42.7.8')
import java.sql.*
// do I need this below???
import java.util.Properties

class run_sql {
    def run_query() {
        String url = "jdbc:postgresql://127.0.0.1/financial_aid"
        Properties props = new Properties()
        props.setProperty("user", "postgres")
        props.setProperty("password", "root")
        props.setProperty("ssl", "false")

        Connection conn = DriverManager.getConnection(url, props)

        def results = []
        try {
            Statement stmt = conn.createStatement()
            ResultSet rs = stmt.executeQuery("SELECT * FROM student_bio LIMIT 5;")

            // Extract metadata for column names
            def meta = rs.metaData
            int colCount = meta.columnCount
            def colNames = (1..colCount).collect { meta.getColumnName(it) }

            // Iterate rows and build maps
            while (rs.next()) {
                def row = [:]
                colNames.each { name ->
                    row[name] = rs.getObject(name)
                }
                results << row
            }

            rs.close()
            stmt.close()
        } finally {
            conn.close()
        }

        return results
    }
}