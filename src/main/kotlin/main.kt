
import module.RecipeConstructor
import java.lang.Exception

val category :List<String> = listOf("Agua", "Lácteos", "Proteínas", "Frutas", "Cereales", "Aceite", "Verduras")
val vegetables: List<String> = listOf("Lechuga", "Apio", "Calabaza", "Papa", "Brócoli", "Cebolla", "Zanahoria")
val fruits: List<String> = listOf("Tomate", "Manzana", "Limón", "Uva", "Melón")
val cereals: List<String> = listOf("Arroz", "Maíz", "Avena", "Cebada", "Sésamo", "Garbanzo")
val dairy: List<String> = listOf("Leche", "Huevos", "Yoghurt", "Queso")
val meat: List<String> = listOf("Vacuno", "Cerdo", "Pollo", "Pavo")

const val mensaje1:String = "Opción inválida."
const val mensaje2:String = "Ingrese el nombre de la receta "

fun main(){
    var finish = false
    while(!finish) finish = recipesMaker(finish)
}

fun recipesMaker(finish: Boolean): Boolean {
    var finish = finish
    val paragraph =
        """:: Bienvenido a Recipe Maker ::
        
        
Selecciona la opción deseada
1. Hacer una receta
2. Ver mis recetas
3. Salir
""".trimIndent()
    println(paragraph)

    val option = readLine()?.toInt() ?: 0
    when(option){
        1 -> {println("Escribir Receta"); makeRecipe()}
        2 -> {println("Lista de recetas"); viewRecipe()}
        3 -> {println("Salir"); finish = true }
        else -> { println(mensaje1)}
    }
    return finish
}

fun viewRecipe(){
    val paragraph_reader = """Elegiste:
        |:: Lector de Recetas ::
        |
        |Lista de recetas:
        |
    """.trimMargin()

    println(paragraph_reader)

}

fun makeRecipe() {

    val paragraph_creator = """Elegiste:
        |:: Creador de Recetas ::
        |
    """.trimMargin()
    println(paragraph_creator)

    println("$mensaje2: ")
    val recipeName: String = readLine().toString()
    checkName(recipeName)

    //selector de ingredientes
    ingredientsSelector(recipeName)
}


fun ingredientsSelector(recipeName: String){

    println("Elige los ingredientes uno a uno.")
    var quantities = mutableListOf<Int>(0, 0, 0, 0, 0, 0, 0, 0)
    var units = mutableListOf("", "", "", "", "", "", "", "")
    var finish = false

    while(!finish) {
        printOptions()

        val opcion = readLine()

        if (opcion == "#") {
            finish = cancel()
        } else {
            try {
                when (val opcionInt = opcion?.toInt()) {
                    in 1..8 -> {
                       val (quantity, unit) = defineParameters()
                        quantities[opcionInt?.minus(1)!!] = quantity
                        units[opcionInt.minus(1)] = unit

                        //mejorar código para que almacene sólo los datos que se modifican, sin necesitar una lita entera llena de 0's
                    }
                    0 -> {
                        println("Guardando receta")
                        //hay que construir lo que se guardará
                        //recipe = RecipeConstructor(recipeName, ingredient, )

                        for ((index, quantity) in quantities.withIndex()) {
                            if (quantity != 0) {
                                println("${category[index]}: ${quantity} ${units[index]}")
                                //TODO hacer una rutina de guardado de las recetas
                                // supongo que con un dataclass será más facil almacenar todo
                            }
                        }
                        //TODO: hacer una rutina de escritura en csv

                        finish = true
                    }
                    else -> {
                        println("Opción fuera de rango")
                    }
                }
            } catch (e: Exception) {
                println(mensaje1)
            }
        }
    }
}

fun cancel(): Boolean {
    println("Seguro que quieres cancelar? (y/n) ")
    if (readLine()=="y"){
        println("Receta cancelada")
        return true
    }
    else if (readLine() == "n"){
        println("Continuando receta")
        return false
    }
    else {println("Opción inválida"); return false}
}

fun defineParameters(): Pair<Int, String> {
    println("Ingrese la cantidad: ")
    val quantity = readLine()?.toInt()!!
    println("Ingrese la unidad de medida: ")
    val unit = readLine().toString()

    return Pair(quantity, unit)
}

fun printOptions(){
    println("Ingredientes (categoría): ")
    for ((index, ingredient) in category.withIndex()) {
        println("${index + 1} : $ingredient")
    }
    println("0 : Finalizar")
    println("# : Cancelar")
}

fun checkName(recipeName: String){
    // arreglar esto
    var recipeName = recipeName
    var check = {recipeName.filterNot{it.isWhitespace()} == ""}


    while(check()) {
        println("$mensaje2:) :")
        recipeName = readLine().toString()
        }
}