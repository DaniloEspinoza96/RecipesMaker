import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

//aquí se guardarán los csv
val CSVFilePath = "recipes.csv" //this is the path for the csv, will be on root
val reader = { Files.newBufferedReader(Paths.get(CSVFilePath)) }


fun write() {
    val writer = Files.newBufferedWriter(Paths.get(CSVFilePath))
    val csvPrinter = CSVPrinter(writer, CSVFormat.DEFAULT
        .withHeader("name", "ingredient", "quantity", "unit"))
}

fun read() {

}

fun createCSV(){
}

fun checkCSV(): Boolean {
    return File(CSVFilePath).exists()

}