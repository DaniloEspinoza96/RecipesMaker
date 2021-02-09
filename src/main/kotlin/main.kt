import module.Menu

val category :List<String> = listOf("Verduras", "Frutas", "Cereales", "Lácteos", "Carnes", "Agua", "Aceite")
val vegetables: List<String> = listOf("Lechuga", "Apio", "Calabaza", "Papa", "Brócoli", "Cebolla", "Zanahoria")
val fruits: List<String> = listOf("Tomate", "Manzana", "Limón", "Uva", "Melón")
val cereals: List<String> = listOf("Arroz", "Maíz", "Avena", "Cebada", "Sésamo", "Garbanzo")
val dairy: List<String> = listOf("Leche", "Huevos", "Yoghurt", "Queso")
val meat: List<String> = listOf("Vacuno", "Cerdo", "Pollo", "Pavo")
//prueba
val listOfLists = listOf(vegetables, fruits, cereals, dairy, meat, "Agua", "Aceite")

const val msg1:String = "Opción inválida."
const val msg2:String = "Ingrese el nombre de la receta "

val welcomeParagraph = """:: Bienvenido a Recipe Maker ::
        
        
Selecciona la opción deseada
1. Hacer una receta
2. Ver mis recetas
3. Salir
""".trimIndent()

val paragraphReader = """Elegiste:
        |:: Lector de Recetas ::
        |
        |Lista de recetas:
        |
    """.trimMargin()

val paragraphCreator = """Elegiste:
        |:: Creador de Recetas ::
        |
    """.trimMargin()

fun recipesMaker(finish: Boolean): Boolean{
    //función de bienvenida y menú inicial

    var finish = finish // con esto continuamos el loop while
    println(welcomeParagraph)

    val option = readLine()?.toInt() ?: 0
    when(option){
        1 -> {println("Escribir Receta"); makeRecipe()}
        2 -> {println("Lista de recetas"); viewRecipe()}
        3 -> {println("Salir"); finish = true }
        else -> { println(msg1)}
    }
    return finish

}

fun viewRecipe(){
    //TODO función para ver las recetas hechas
    println(paragraphReader)
    readRecipe()
    deleteRecipe()

} // interfaz para leer las recetas ya hechas

fun makeRecipe() {

    println(paragraphCreator)

    println("$msg2: ")//solicitamos un nombre para la receta
    val recipeName: String = readLine().toString()
    checkName(recipeName)

    //selector de ingredientes
    //decidir si usarlo o no
    ingredientsSelector(recipeName)
}//una pequeña interfaz para ir al selector de ingredientes

fun ingredientsSelector(recipeName: String){
    println("Elige los ingredientes uno a uno.")
    var quantities: MutableList<Int> = mutableListOf()
    var units: MutableList<String> = mutableListOf()
    var ingredients: MutableList<String> = mutableListOf()
    var finish = false

    var showList = Menu(category, false)// inicializamos la instancia de menu
    //subMenu sirve para hacer una pequeña diferenciación
    while(!finish){
        finish = getOption(showList, recipeName)

    }
}

fun checkName(recipeName: String){

    var recipeName = recipeName
    var check = {recipeName.filterNot{it.isWhitespace()} == ""}

    while(check()) {
        println("$msg2:) :")
        recipeName = readLine().toString()
    }
}

fun deleteIngredient(){ //elimina un ingrediente de la receta, sólo se llama en makeRecipe()

}

fun summary(){ //muestra los ingredientes que van hasta el momento

}

fun readRecipe(){

}

fun deleteRecipe(){

}

fun getOption(instance: Menu, recipeName: String=""): Boolean {
    var finish = false
    //muestra la lista de ingredientes y
    // consigue la opción de manera null safe
    instance.showMenu()
//aquí va el blindaje contra opciones invalidas
    val option = readLine()

    if (option != null) {
        finish = instance.handleOption(option, recipeName)
    }else{
        println(msg1)//opción inválida
        finish = false //al retornar false, se sigue en el loop
    }
    return finish
    //blindaje contra opciones invalidas
}

fun main(){
    var finish = false
    while(!finish) finish = recipesMaker(finish)
}