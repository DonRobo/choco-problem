package at.donrobo

import org.chocosolver.solver.Model
import org.chocosolver.solver.ParallelPortfolio


fun main() {
    val parallelPortfolio = ParallelPortfolio()

    val modelCount = 1

    for (i in 0 until modelCount) {
        val model = createModel()
        model.name = "Model #$i"
        parallelPortfolio.addModel(model)
    }

    while (parallelPortfolio.solve()) {
        println("Found solution")
    }
}


fun createModel(): Model {
    val model = Model()

    val possibleOptions = (0..100).toList().toIntArray()
    val values = possibleOptions.mapIndexed { index, _ -> (100 - index) * 321 }.toIntArray()

    val resultingValue = model.intVar(0, values.sum())

    val chosenSet = model.setVar(intArrayOf(), possibleOptions)
    model.sumElements(chosenSet, values, resultingValue).post()

    return model
}
