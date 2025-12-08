// just storage to test a fragment that is not working

def results = [
    [student_id:4423,  last_name:"Baker",   first_name:"Kurt",   gpa:0.02, credits:11],
    [student_id:94337, last_name:"Aguilar", first_name:"Dee",    gpa:1.08, credits:3],
    [student_id:27998, last_name:"Hunter",  first_name:"Joe",    gpa:3.31, credits:10],
    [student_id:24633, last_name:"Gomez",   first_name:"Ed",     gpa:3.8,  credits:15],
    [student_id:8982,  last_name:"Blair",   first_name:"Ramona", gpa:1.97, credits:14]
]


// results.each { row ->
//     println "ID ${row['student_id']} - ${row['last_name']}, ${row['first_name']} (GPA: ${row['gpa']}, Credits: ${row['credits']})"
// }

// Extract headers from the first row
def headers = results[0].keySet()

// Create a CSV file
def file = new File("./Groovy/fin_aid_example/students.csv")
// check file location: println "Writing to: ${file.absolutePath}"

// Write header
file.text = headers.join(",") + System.lineSeparator()

// Write each row
results.each { row ->
    file << headers.collect { h -> row[h] }.join(",") + System.lineSeparator()}