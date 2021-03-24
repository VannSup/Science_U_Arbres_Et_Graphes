package fr.vannsuplabs.scienceuarbresetgraphes.data.graph

import java.util.*

class Graph(private val version: Int) {

    private var graph = GraphData(version)

    fun start(startNodeName: String, endNodeName: String) : String{

        var result ="Dijkstra :\n"
        var begin = System.nanoTime()
        result += parcoursDijkstra(startNodeName, endNodeName)+"\n"
        var end = System.nanoTime()
        result += "Temps de calcul du parcours ${end-begin} nanoseconde\n\n\n"


        //Clean graph
        graph = GraphData(version)

        result +="Parcours profondeur adapter :\n"
        begin = System.nanoTime()
        result += parcoursProfondeurModifierStringResult(startNodeName, endNodeName)+"\n"
        end = System.nanoTime()
        result += "Temps de calcul du parcours ${end-begin} nanoseconde\n\n\n"

        return result
    }

    private fun parcoursDijkstra(startNodeName: String, endNodeName: String) : String{
        //Si l'un des deux élément rechercher n'existe pas on léve une erreur
        val startNode: Node = graph.findNodeByName(startNodeName) ?: return "Le noeud de départ n'existe pas"
        val endNode: Node = graph.findNodeByName(endNodeName) ?: return "Le noeud d'arriver n'existe pas"

        startNode.distanceFromSource = 0
        var currentNode :Node? = null
        var minimumDistanceFromSource : Int = Double.POSITIVE_INFINITY.toInt()
        graph.graph.forEach { node: Node ->
            if(!node.visited){
                if(node.distanceFromSource < minimumDistanceFromSource) {
                    minimumDistanceFromSource = node.distanceFromSource
                    currentNode = node
                }
            }
        }

        while (currentNode != null){
            currentNode?.visited = true
            currentNode?.children?.forEach { branch: Branch ->
                if (branch.destination.distanceFromSource> currentNode?.distanceFromSource?.plus(branch.weight)!!){
                    branch.destination.distanceFromSource = currentNode?.distanceFromSource?.plus(branch.weight)!!
                    branch.destination.bestParentFromSource = currentNode
                }
            }
            currentNode = null
            minimumDistanceFromSource = Double.POSITIVE_INFINITY.toInt()
            graph.graph.forEach { node: Node ->
                if(!node.visited){
                    if(node.distanceFromSource < minimumDistanceFromSource) {
                        minimumDistanceFromSource = node.distanceFromSource
                        currentNode = node
                    }
                }
            }
        }

        //On crée le tableau du plus cours chemin en partant de la fin puis on le retourne
        currentNode = endNode
        val result = mutableListOf<Node>()
        while (currentNode != null){
            result.add(currentNode!!)

            currentNode = if(currentNode?.name == startNodeName)
                null
            else
                currentNode?.bestParentFromSource
        }
        result.reverse()

        //Construction du resultas texte
        var resultString = ""
        result.forEach {
            resultString += if (result.last() == it)
                "${it.name}\nNombre de poids parcourus : ${it.distanceFromSource}"
            else
                "${it.name} -> "
        }
        return resultString
    }

    private fun parcoursProfondeurModifierStringResult(startNodeName: String, endNodeName: String) : String
    {
        //Si l'un des deux élément rechercher n'existe pas on léve une erreur
        val startNode: Node = graph.findNodeByName(startNodeName) ?: return "Le noeud de départ n'existe pas"
        val endNode: Node = graph.findNodeByName(endNodeName) ?: return "Le noeud d'arriver n'existe pas"

        val result = mutableListOf<Node>()
        parcoursProfondeurModifier(startNode, result, endNode,0)
        result.reverse()
        var resultString = ""
        result.forEach {
            resultString += if (result.last() == it)
                "${it.name}\nNombre de poids parcourus : ${it.distanceFromSource}"
            else
                "${it.name} -> "
        }
        return resultString
    }

    private fun parcoursProfondeurModifier(node :Node, result: MutableList<Node>, nodeSearch:Node, distanceFromSource:Int): Boolean{
        if(node == nodeSearch){
            node.distanceFromSource = distanceFromSource
            result.add(node)
            return true
        }
        else if(!node.visited){
            node.visited = true
            node.children.forEach { branch: Branch ->
                if(parcoursProfondeurModifier(branch.destination, result,nodeSearch,distanceFromSource + branch.weight)) {
                    result.add(node)
                    return true
                }
            }
        }
        return false
    }
}