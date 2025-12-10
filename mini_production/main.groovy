class Main {
    static void main(String[] args) {
        // String query = """
        // SELECT *
        // FROM student_bio
        // LIMIT 10;"""

        String query = """
        SELECT bio.student_id AS "Student ID", detail.award_type AS "Award Type", detail.award_amount AS "Award Amount"
        FROM student_bio AS bio
        JOIN student_award AS detail
        ON bio.student_id = detail.student_id
        LIMIT 15;"""

        // String query = """
        // SELECT bio.student_id as "Student ID", detail.award_type as "Award Type", detail.award_amount as "Award Amount"
        // FROM student_bio AS bio
        // JOIN student_award as detail
        // ON bio.student_id = detail.student_id
        // LIMIT 15;"""

        def sql_query = new run_sql()
        def results = sql_query.run_query(query)

        def csv_output = new output_to_csv()
        // println "Working directory: " + new File(".").absolutePath
        csv_output.write_to_csv(results, "/Users/albert/Documents/Groovy/fin_aid_example/mini_production/example_students.csv")

        println "Data exported successfully."
    }
}