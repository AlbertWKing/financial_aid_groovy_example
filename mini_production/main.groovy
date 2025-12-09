class Main {
    static void main(String[] args) {
        String query = "SELECT * FROM student_bio LIMIT 15;"

        def sql_query = new run_sql()
        def results = sql_query.run_query(query)

        def csv_output = new output_to_csv()
        // println "Working directory: " + new File(".").absolutePath
        csv_output.write_to_csv(results, "/Users/albert/Documents/Groovy/fin_aid_example/mini_production/example_students.csv")

        println "Data exported successfully."
    }
}