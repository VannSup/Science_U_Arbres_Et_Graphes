package fr.vannsuplabs.scienceuarbresetgraphes.data.graph


class Graph(private val id: Int) {

    private var destinations: MutableList<Graph> = mutableListOf()

    private var tableau: MutableList<MutableList<Int>> = mutableListOf()

    fun createFromAdjacencyMatrix(adjacencyMatrix: MutableList<MutableList<Int>>){
        for (i in 0 until adjacencyMatrix.size){
            for (y in 0 until adjacencyMatrix[i].size)
                adjacencyMatrix[i][y]
        }
    }

    override fun toString(): String {
        var result = ""
        for (i in 0 until tableau.size){
            for (y in 0 until tableau[i].size)
                result += "[${tableau[i][y]}]"
            result += "\n"
        }
        return result
    }

}