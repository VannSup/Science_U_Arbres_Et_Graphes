package fr.vannsuplabs.scienceuarbresetgraphes.data.graph

open class Dijkstra{
    var visited: Boolean = false
    var distanceFromSource : Int = Double.POSITIVE_INFINITY.toInt()
    var bestParentFromSource: Node? = null
}