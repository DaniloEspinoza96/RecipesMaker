package module

import java.io.Serializable

data class RecipeConstructor(
    var name: String,
    var ingredient: String,
    var quantity: Int,
    var unit: String,
): Serializable {
    init {
        println("\nNombre de la receta: $name")
        println("Ingrediente: $ingredient")
        println("Cantidad: $quantity $unit\n")
    }
}

data class RecipeArchive(
    var parametersList: MutableList<RecipeConstructor>,
):Serializable