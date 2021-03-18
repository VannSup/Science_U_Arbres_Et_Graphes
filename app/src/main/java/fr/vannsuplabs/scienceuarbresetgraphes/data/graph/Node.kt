package fr.vannsuplabs.scienceuarbresetgraphes.data.graph

class Node(val name : String) : Dijkstra(){
    val children : MutableList<Branch> = mutableListOf()
}