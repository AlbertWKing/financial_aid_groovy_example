// I'm pretty sure this won't work because I'm having trouble with groovy.sql.Sql

import groovy.sql.Sql

class run_sql_groovy {
    def fetchStudents() {
        def sql = Sql.newInstance(
            "jdbc:mysql://localhost:3306/mydb",
            "user",
            "password",
            "com.mysql.cj.jdbc.Driver"
        )

        def results = []
        sql.eachRow("SELECT * FROM students LIMIT 5") { row ->
            results << [
                student_id: row.student_id,
                last_name: row.last_name,
                first_name: row.first_name,
                gpa: row.gpa,
                credits: row.credits
            ]
        }
        sql.close()
        return results
    }
}