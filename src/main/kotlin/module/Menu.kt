package module

import getOption
import msg1
import listOfLists// la usaré para hacer el sub menú

class Menu(list:List<Any>, subMenu: Boolean) {
    var list = list
    var subMenu = subMenu

    fun showMenu(){
        println("Ingredientes: ")
        for ((index, ingredient) in list.withIndex()) {
            println("${index} : $ingredient")
        }
        if (!subMenu){
            println("7 : Finalizar")
            println("8 : Eliminar")
            println("9 : Resumen")
        }
        println("# : Cancelar") //listo
    }

    fun handleOption(option: String, recipeName: String): Boolean {
//discrimina entre las opciones que tiene
        var finish = false
        var parameters:RecipeConstructor

        var getParameters = { optionInt:Int, recipeName:String ->
            try {
                defineParameters(optionInt,recipeName)!!
            }catch(e:Exception){
                println("Cancelado")
        }} //getParameters sólo recibe los datos de defineParameters

        if(option=="#"){//cancelamos lo que hacemos
            finish = cancel(subMenu)//y diferenciamos un menu de un sub menu
        }else {
            try {
                val optionInt = option.toInt()
                if (!subMenu) { //subrutina para manejar el menú
                    when (optionInt) {
                        in 0..4 -> {
                            //muestra la nueva lista de ingredientes
                            var showSubList = Menu(listOfLists[optionInt] as List<Any>, true)
                            getOption(showSubList) //recibirá un false si cancelamos
                        }
                        in 5..6 ->{
                            //aquí quedé ayer
                            if (getParameters(optionInt, recipeName) != null) {
                                parameters =  getParameters(optionInt, recipeName) as RecipeConstructor
                            }

                        }
                        7 -> {}//finalizar guarda la receta
                        8 -> {}//eliminar ingrediente
                        9 -> {}//resumen de los ingredientes hasta el momento
                    }

                }else{//hacer rutina para manejar el sub menú

                }
            }catch(e:Exception){
                //llega aquí si parameters es null
                println(msg1)
            }
        }
        return finish
    }
}

fun cancel(subMenu: Boolean): Boolean {//boolean sub menu, true = está en sub menu

    println("Seguro que quieres cancelar ${if(!subMenu) "la receta" else "el ingrediente"}? (y/n) ")
    if(readLine() == "y") {
        println("${if(!subMenu) "Receta cancelada" else "Ingrediente nuevo cancelado"}")
        return true
    } else if (readLine() == "n") {
        println("${if(!subMenu)"Continuando receta" else "Continuando..."}")
        return false
    } else {
        println(msg1); return false
    }

}

fun defineParameters(optionInt:Int, recipeName: String): RecipeConstructor? {
    var unit:String
    var quantity:Int
    var recipeParameter: RecipeConstructor
    val cancel = {println("Presione # y luego Enter para cancelar")
        readLine()}
    val ingredient = listOfLists[optionInt].toString()

    println("Ingrese la cantidad: ")
    var choice = cancel()
    if (choice == "#") return null
    else { quantity = choice?.toInt()!!
    }//blindar contra invalido

    println("Ingrese la unidad de medida: ")
    choice = cancel()
    if (choice == "#") return null
    else unit = choice.toString()
    //armamos con recipeconstructor nuestro objeto del orden
    recipeParameter = RecipeConstructor(recipeName, ingredient, quantity, unit)
    return recipeParameter

}