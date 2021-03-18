package fr.vannsuplabs.scienceuarbresetgraphes.data.graph

import java.lang.Double.POSITIVE_INFINITY

open class Dijkstra{
    var visited: Boolean = false
    var distanceFromSource : Int = POSITIVE_INFINITY.toInt()
    var bestParentFromSource: Node? = null
}