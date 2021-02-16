import module.RecipeConstructor
import module.RecipeArchive

val category: List<String> = listOf("Verduras", "Frutas", "Cereales", "Lácteos", "Carnes", "Agua", "Aceite")
val vegetables: List<String> = listOf("Lechuga", "Apio", "Calabaza", "Papa", "Brócoli", "Cebolla", "Zanahoria")
val fruits: List<String> = listOf("Tomate", "Manzana", "Limón", "Uva", "Melón")
val cereals: List<String> = listOf("Arroz", "Maíz", "Avena", "Cebada", "Sésamo", "Garbanzo")
val dairy: List<String> = listOf("Leche", "Huevos", "Yoghurt", "Queso")
val meat: List<String> = listOf("Vacuno", "Cerdo", "Pollo", "Pavo")
val listOfLists = listOf(vegetables, fruits, cereals, dairy, meat, "Agua", "Aceite")
const val msg1: String = "Opción inválida."
const val msg2: String = "Ingrese el nombre de la receta"
val welcomeParagraph =
    """-------------------------------
:: Bienvenido a Recipe Maker ::
            
Selecciona la opción deseada:

1. Hacer una receta
2. Ver mis recetas
3. Salir
""".trimIndent()
val paragraphReader = """Elegiste:
        |:: Lector de Recetas ::
        |
    """.trimMargin()
val paragraphCreator = """Elegiste:
        |:: Creador de Recetas ::
        |
    """.trimMargin()
var recipes = mutableListOf<RecipeArchive>()

//TODO: hacer rutina CSV

fun viewRecipes() {

    println()
    println(paragraphReader)

    while (true) {
        println("Lista de recetas:")
        if (recipes.size == 0) {
            println("\nActualmente, no hay recetas para mostrar\n")
            break

        } else {
            for ((index, recipe) in recipes.withIndex()) {
                println("$index: ${recipe.parametersList[0].name}")
            }
            println("\nQué desea hacer?:\n1: Leer recetas\n2: Borrar recetas\n3: Modificar recetas\n4: Volver")
            val choice = readLine()
            when (choice) {
                "1" -> {
                    print("Ingrese el índice de la receta que desee ver: \n")
                    ;readRecipe()
                }
                "2" -> deleteRecipe()
                "3" -> {
                    print("\nIngrese el índice de la receta a modificar: \n"); modifyRecipe()
                }
                "4" -> {
                    return
                }
                else -> {
                    println(msg1)
                    println()
                }
            }
        }
    }
} //shows recipe names

fun modifyRecipe() {
    val index = readRecipe()
    val recipeName = recipes[index].parametersList[0].name
    if (index != -1) {
        //here starts the modifying routine

        while (true) {
            println("Qué cambios desea hacer?\n1: Añadir Ingrediente\n2:Eliminar Ingrediente\n3: Volver")
            val option = readLine()
            when (option) {
                "1" -> {
                    addIngredient(recipeName,
                        recipes[index].parametersList)// it adds instantly to this object dont need to replace


                }//add ingredient
                "2" -> {
                    //recipes[index] = RecipeArchive(deleteIngredient(recipes[index].parametersList))//test if i delete ingredients first then add ingredients, what happens?
                    deleteIngredient(recipes[index].parametersList)

                }//delete ingredient
                "3" -> {
                    if (recipes[index].parametersList.isEmpty()) {
                        recipes.removeAt(index)
                    }
                    return
                }//back
                else -> {
                    println(msg1)
                }
            }
        }
    }
}

fun addIngredient(
    recipeName: String,
    parametersList: MutableList<RecipeConstructor>,
) {//must optimize this, but not truly
    var finish = false
    var parameters: RecipeConstructor? // = null
    var ingredient: String
    var parametersList = parametersList
    val recipeName = recipeName

    ingredientNull@ while (!finish) {
        println()
        ingredient = ""

        println("Elige los ingredientes uno a uno.")

        showMenu(category, false, true)//shows all the options available

        val option = readLine()
        //lets try  to get the option to Int
        try {
            val optionInt = option?.toInt()
            when (optionInt) {
                in 0..4 -> {
                    val list = listOfLists[optionInt!!]
                    showMenu(list as List<String>,
                        true,
                        false) //the modify flag is false, must see what happens bc ill probably need to cancel
                    ingredient = subList(list)
                    if (ingredient == "") continue@ingredientNull //this to avoid exception when ingredient

                }// if user chooses a subList, must show another Menu
                in 5..6 -> {
                    ingredient = listOfLists[optionInt!!] as String

                }//is user chooses a single item, go to defineParameters
            }
        } catch (e: Exception) {
            when (option) {
                "D" -> {
                    parametersList = deleteIngredient(parametersList)
                }//delete ingredient
                "d" -> {
                    parametersList = deleteIngredient(parametersList)
                }//delete ingredient
                "R" -> summary(parametersList)//summary
                "r" -> summary(parametersList)

                "#" -> finish = true

                else -> println(msg1)
            }
        }

        if (ingredient != "") {
            parameters = defineParameters(ingredient, recipeName)
            if (parameters != null) {
                //here i can choose the way i treat the parameters
                parametersList.add(parameters)
            }
        } //adds items to parametersList
    }


}

fun ingredientsSelector(recipeName: String) {
    var finish = false
    var parameters: RecipeConstructor? // = null
    var ingredient: String
    var parametersList = mutableListOf<RecipeConstructor>()

    ingredientNull@ while (!finish) {
        println()
        ingredient = ""

        println("Elige los ingredientes uno a uno.")

        showMenu(category, false, false)//shows all the options available

        val option = readLine()
        //lets try  to get the option to Int
        try {
            val optionInt = option?.toInt()
            when (optionInt) {
                in 0..4 -> {
                    val list = listOfLists[optionInt!!]
                    showMenu(list as List<String>, true, false)
                    ingredient = subList(list)//possible future debug
                    if (ingredient == "") continue@ingredientNull //this to avoid exception when ingredient

                }// if user chooses a subList, must show another Menu
                in 5..6 -> {
                    ingredient = listOfLists[optionInt!!] as String

                }//is user chooses a single item, go to defineParameters
            }
        } catch (e: Exception) {
            when (option) {
                "C" -> {
                    finish = cancel(false)
                }//cancel
                "c" -> {
                    finish = cancel(false)
                }//cancel
                "D" -> {
                    parametersList = deleteIngredient(parametersList)
                }//delete ingredient
                "d" -> {
                    parametersList = deleteIngredient(parametersList)
                }//delete ingredient
                "R" -> summary(parametersList)//summary
                "r" -> summary(parametersList)

                "#" -> finish = finish(parametersList) //save recipe parameters

                else -> println(msg1)
            }
        }

        if (ingredient != "") {
            parameters = defineParameters(ingredient, recipeName)
            if (parameters != null) {
                //here i can choose the way i treat the parameters
                parametersList.add(parameters)
            }
        } //adds items to parametersList
    }

}//from here i must get the ingredients i shall use on my recipe

fun deleteIngredient(parametersList: MutableList<RecipeConstructor>): MutableList<RecipeConstructor> { //elimina un ingrediente de la receta, sólo se llama en makeRecipe()

    if (!summary(parametersList)) {
        do {//if summary has element, proceed
            println("\nIngrese el índice del ingrediente a borrar: ")
            println("R: Listo")
            val option = readLine()
            if (option == "R" || option == "r") {
                break
            } else {
                try {
                    val optionInt = option?.toInt()
                    optionInt?.let { parametersList.removeAt(it) }
                    println("Ingrediente eliminado\n")

                } catch (e: Exception) {
                    println(msg1)
                }
            }

        } while (!summary(parametersList))

    }
    return parametersList
}

fun readRecipe(): Int {
    try {
        val index = readLine()?.toInt()
        //make a nice looking interface to see the recipes
        val recipe = recipes[index!!].parametersList
        println("Nombre de la receta: ${recipe[0].name}\nIngredientes:\n")
        val space = " "
        for ((index, item) in recipe.withIndex()) println("$index: ${item.ingredient}" + space.repeat(13 - item.ingredient.length) + "${item.quantity} ${item.unit}")
        println()
        return index
    } catch (o: Exception) {
        println(msg1)
        return -1
    }
}

fun deleteRecipe() {
    print("Ingrese el índice de la receta que desee borrar: ")

    try {
        val index = readLine()?.toInt()
        val name = recipes[index!!].parametersList[0].name
        println("Está seguro que desea eliminar la receta \"${name}\"? (Y para confirmar)")
        val option = readLine()
        if (option == "y" || option == "Y") {
            recipes.removeAt(index)
            println("Receta \"$name\" eliminada\n")
        }


    } catch (o: Exception) {
        println(msg1)
    }

}

fun makeRecipes() {
    println()
    println(paragraphCreator)

    println("$msg2: ")//solicitamos un nombre para la receta
    print("Nombre elegido: ")
    val recipeName: String = readLine().toString()
    checkName(recipeName)
    println()

    ingredientsSelector(recipeName)

}//una pequeña interfaz para ir al selector de ingredientes

fun finish(parametersList: MutableList<RecipeConstructor>): Boolean {

    println("Finalizando receta, desea guardar los cambios? (Y para confirmar)")
    val option = readLine()

    if (option == "y" || option == "Y") {
        try {
            println("\nGuardando receta...")
            recipes.add(RecipeArchive(parametersList))
            println("Receta: ${parametersList[0].name} añadida\n")
            return true
        } catch (e: Exception) {
            println("No hay ingredientes en la receta, desea cancelar? (Y para confirmar)")
            val option2 = readLine()
            if (option2 == "y" || option2 == "Y") {
                println("Continuando receta...");return true
            } else {
                println("Receta cancelada.");return false
            }
        }
    } else {
        return false
    }
}// has to do with viewRecipe()

fun checkName(recipeName: String) {

    var recipeName = recipeName
    var check = { recipeName.filterNot { it.isWhitespace() } == "" }

    while (check()) {
        println(" $msg2 :)")
        print("Nombre elegido: ")
        recipeName = readLine().toString()
    }
    //println("Nombre elegido: $recipeName")
}//comprueba que name no está vacío

fun cancel(subMenu: Boolean): Boolean {//boolean sub menu, true = está en sub menu

    println("Seguro que quieres cancelar ${if (!subMenu) "la receta" else "el ingrediente"}? (Y para confirmar) ")
    val option = readLine()
    if (option == "y" || option == "Y") {
        println("${if (!subMenu) "Receta cancelada" else "Ingrediente nuevo cancelado"}")
        return true
    } else {
        println("${if (!subMenu) "Continuando receta" else "Continuando..."}")
        return false
    }

}

fun summary(parametersList: MutableList<RecipeConstructor>?): Boolean {
    try {
        //modify lower line for accucary in the recipe name and the for loop for recipes division
        println("Receta: ${parametersList?.get(0)?.name}")
        val space = " "

        for ((index, item) in parametersList?.withIndex()!!) {
            println("$index: ${item.ingredient}" + space.repeat(13 - item.ingredient.length) + "${item.quantity} ${item.unit}")
        }
        return false
    } catch (e: Exception) {
        println("\nNo hay ingredientes agregados a la receta\n")
        return true
    }
}

fun defineParameters(ingredient: String, recipeName: String): RecipeConstructor? {
    val ingredient = ingredient
    var unit: String
    var quantity: Int
    var recipeParameters: RecipeConstructor

    val cancel = {
        println("C: Cancelar")
        readLine()
    }

    println("Ingrese la cantidad: ")
    var choice = cancel()
    if (choice == "C" || choice == "c") return null
    else {
        try {
            quantity = choice?.toInt()!!
        } catch (e: Exception) {
            println("Cantidad Inválida")
            return null
        }
    }

    println("Ingrese la unidad de medida: ")
    choice = cancel()
    if (choice == "C" || choice == "c") return null
    else unit = choice.toString()

    recipeParameters = RecipeConstructor(recipeName, ingredient, quantity, unit)
    //here i have the parameters constructed
    return recipeParameters
}

fun subList(list: List<String>): String {
    val option = readLine()
    if (option == "C" || option == "c") {
        cancel(true); return ""
    }
    try {
        val optionInt = option?.toInt()
        val ingredient = list[optionInt!!]
        return ingredient

    } catch (e: Exception) {
        println(msg1)
        return ""
    }
}//return selected ingredient if out of range, null

fun showMenu(list: List<String>, subMenu: Boolean, modify: Boolean) {
    for ((index, item) in list.withIndex()) {
        println("$index: $item")
    }
    if (!modify) println("C: Cancelar")
    if (!subMenu) {
        println("D: Eliminar Ingrediente")
        println("R: Resumen")
        println("#: Finalizar")
    }
}

fun recipesMaker(finish: Boolean): Boolean {
    //función de bienvenida y menú inicial

    var finish = finish // con esto continuamos el loop while
    println(welcomeParagraph)

    val option = readLine()
    when (option) {
        "1" -> {
            makeRecipes()
        }
        "2" -> {
            viewRecipes()
        }
        "3" -> {
            println("Salir"); finish = true
        }
        "4" -> {
            println("only for debuggers, remember to comment")//generates a standard recipe
            recipes.add(RecipeArchive(mutableListOf<RecipeConstructor>(RecipeConstructor("Pan con chancho",
                "Lechuga",
                3,
                "camionadas"))))//doesnt contain "chancho"
        }
        else -> {
            println(msg1)
        }
    }

    return finish

}

fun main() {
    var finish = false
    while (!finish) finish = recipesMaker(finish)

}
