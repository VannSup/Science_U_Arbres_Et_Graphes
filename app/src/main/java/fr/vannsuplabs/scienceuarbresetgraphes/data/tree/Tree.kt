package fr.vannsuplabs.scienceuarbresetgraphes.data.tree

import java.util.*

class Tree(private val id: String, private var parent: Tree? = null) {
    var children: MutableList<Tree> = mutableListOf()

    fun parcoursLargeur(): String {
        var resultString = ""
        val currentNodes: Queue<Tree> = LinkedList()
        currentNodes.add(this)
        val result: MutableList<Tree> = mutableListOf()

        while (currentNodes.size != 0) {
            val currentNode: Tree = currentNodes.remove()
            resultString += " ${currentNode.id}"
            result.add(currentNode)
            currentNode.children.forEach { child -> currentNodes.add(child)}
        }
        return resultString
    }

    fun profondeurPrefixe(): String {
        var result = " " + this.id;
        this.children.forEach{ result += it.profondeurPrefixe()}
        return result;
    }

    fun profondeurSuffixe(): String {
        var result = "";
        this.children.forEach { result += it.profondeurSuffixe()}
        return result + " " + this.id
    }

    fun profondeurInfixe(): String {
        var result = ""

        if(this.children.size > 2)
            return "Impossible de continué l'arbre n'est pas binaire"

        if (this.children.size >= 1)
            result += this.children[0].profondeurInfixe()

        result += " " + this.id + " "

        if (this.children.size >= 2)
            result += this.children[1].profondeurInfixe()

        return result;
    }

    fun parseFromList(listOfNode: MutableList<Tree>, root: Tree? = null): Tree {
        this.parent = root
        val childOfThisTree: MutableList<Tree> = mutableListOf()

        for (i in listOfNode.indices) {
            if (id == listOfNode[i].parent?.id)
                childOfThisTree.add(listOfNode[i].parseFromList(listOfNode, this))
        }

        this.children = childOfThisTree

        return this
    }

    fun addChildrenFromListOfValue(values: MutableList<Int>, selfPosition: Int) {
        val length = values.size
        val childOfThisTree: MutableList<Tree> = mutableListOf()
        if (2 * selfPosition + 1 < length) {
            val leftChild = Tree(values[2 * selfPosition + 1].toString(), this.parent)
            leftChild.addChildrenFromListOfValue(values, 2 * selfPosition + 1)
            childOfThisTree.add(leftChild)
        }
        if (2 * selfPosition + 2 < length) {
            val rightChild = Tree(values[2 * selfPosition + 2].toString(), this.parent)
            rightChild.addChildrenFromListOfValue(values, 2 * selfPosition + 2)
            childOfThisTree.add(rightChild)
        }
        this.children = childOfThisTree
    }

    fun showResultOfParcour():String{
        return "Parcours en largeur arbre : ${this.parcoursLargeur()}\n" +
                "Profondeur préfixe arbre : ${this.profondeurPrefixe()}\n" +
                "Profondeur sufixe arbre : ${this.profondeurSuffixe()}\n" +
                "Profondeur infixe arbre : ${this.profondeurInfixe()}\n\n"
    }

    /*override fun toString(): String {
        var result = this.id
        if (children.isNotEmpty()) {
            result += " {" + children.map { "$it" } + "} "
        }
        return result
    }*/

    override fun toString(): String {

        val result : StringBuilder = StringBuilder()
        result.append(this.id)
        this.children.forEachIndexed { index, tree ->
            val isLast = index != this.children.size-1
            val nextPointer = if(isLast) "├──" else "└──"
            traverseNodes(result,"",nextPointer,tree, isLast)
        }
        return result.toString()
    }

    private fun traverseNodes(sb: StringBuilder, padding: String?, pointer: String?, node: Tree?,
                              hasNextSibling: Boolean) {
        if (node != null) {
            sb.append("\n")
            sb.append(padding)
            sb.append(pointer)
            sb.append(node.id)
            val paddingBuilder = StringBuilder(padding)
            if (hasNextSibling) {
                paddingBuilder.append("│     ")
            } else {
                paddingBuilder.append("        ")
            }
            val paddingForBoth = paddingBuilder.toString()

            node.children.forEachIndexed { index, tree ->
                val isLast = index != node.children.size-1
                val nextPointer = if(isLast) "├──" else "└──"
                traverseNodes(sb,paddingForBoth,nextPointer,tree, isLast)
            }
        }
    }

}