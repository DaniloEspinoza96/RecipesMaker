/*

fun ingredienstsSelector(recipeName: String){

    println("Elige los ingredientes uno a uno.")
    var quantities: MutableList<Int> = mutableListOf()
    var units: MutableList<String> = mutableListOf()
    var ingredients: MutableList<String> = mutableListOf()
    var finish = false

    while(!finish){
        var printOptions = PrintOptions()
        printOptions.printList(category)

        val option = readLine()

        if (option == "#") {
            finish = cancel()

        } else {
            try {
                val optionInt = option?.toInt()

                var subPool = IngredientsPool()

                when (optionInt) {
                    1 -> {subPool(vegetables)}
                    2 -> {}
                    3 -> {}
                    4 ->{}
                    5 -> {}
                    in 6..7 -> {
                        ingredients.add(category[optionInt!!-1])
                        val (quantity, unit) = defineParameters()
                        quantities.add(quantity)
                        units.add(unit)
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

fun defineParameters(): Pair<Int, String> {
    println("Ingrese la cantidad: ")
    val quantity = readLine()?.toInt()!!
    println("Ingrese la unidad de medida: ")
    val unit = readLine().toString()

    return Pair(quantity, unit)
}


 */