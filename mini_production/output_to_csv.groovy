class output_to_csv {
    def write_to_csv(List<Map> results, String filePath) {
        if (results.isEmpty()) return

        def headers = results[0].keySet()
        def file = new File(filePath)

        file.text = headers.join(",") + System.lineSeparator()

        results.each { row ->
            file << headers.collect { h -> row[h] }.join(",") + System.lineSeparator()
        }
    }
}