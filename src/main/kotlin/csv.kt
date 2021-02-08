package csv

import java.io.*
import java.lang.Exception

private val CSV_HEADER = "nombre, ingredientes, cantidades, unidades"
val fileName = "recipes.csv"


fun writer(cantidades: MutableList<Int>,
         unidades: MutableList<String>,
         ingredientes: List<String>,
         recipeName: String){

    val cantidades = cantidades
    val unidades = unidades
    val ingredientes = ingredientes
    val recipeName = recipeName

    var fileWriter: FileWriter? =null

    var file = File(fileName)
    var fileExists = file.exists()

    try {
        fileWriter = FileWriter("recipes.csv")

        if(fileExists){
        fileWriter.append(CSV_HEADER)
        fileWriter.append('\n')
        }

        for(index in 1..8){
            fileWriter.append(recipeName)
            fileWriter.append(',')
            fileWriter.append(ingredientes[index])
            fileWriter.append(',')
            fileWriter.append(cantidades[index].toString())
            fileWriter.append(',')
            fileWriter.append(unidades[index])
            fileWriter.append('\n')
        }
        println("Escritura completa!")
    }catch (e: Exception){
        println("Escritura Fallida")
    }
}


fun reader(args: Array<String>?) {
    var file = File(fileName)
    var fileExists = file.exists()

    var fileReader: BufferedReader? = null
    if (fileExists) {
        try {
            var recipes = ArrayList<String>()
            var line: String?

            fileReader = BufferedReader(FileReader("recipes.csv"))

            // Read CSV header
            fileReader.readLine()

            // Read the file line by line starting from the second line
            line = fileReader.readLine()

            while (line != null){
                val elementos = line.split(",")
                if (elementos.size > 0){
                    val recipe: List<String> = listOf<String>(elementos[0], elementos[1],elementos[2],elementos[3])
                    recipes.add(recipe.toString())
                }
                line = fileReader.readLine()
            }

            //Extraer todas las recetas únicas
            println(recipes)

            // Print the new customer list
            for (recipe in recipes) {
                println(recipe)
            }
        } catch (e: Exception) {
            println("Reading CSV Error!")
            e.printStackTrace()
        } finally {
            try {
                fileReader!!.close()
            } catch (e: IOException) {
                println("Closing fileReader Error!")
                e.printStackTrace()
            }
        }
    }else{
        println("No hay recetas guardadas")
    }
}
