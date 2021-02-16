package fr.vannsuplabs.scienceuarbresetgraphes.data

import java.util.*

class Tree(private val id: String, private var parent: Tree?) {
    var children: MutableList<Tree> = mutableListOf()

    fun parcoursLargeur(): String {
        var resultString = "";

        val currentNodes: Queue<Tree> = LinkedList()
        currentNodes.add(this);
        val result: MutableList<Tree> = mutableListOf()

        while (currentNodes.size != 0) {
            val currentNode: Tree = currentNodes.remove();
            resultString = resultString + " " + currentNode.id;
            result.add(currentNode)
            currentNode.children.forEach { child -> currentNodes.add(child)}
        }
        return resultString;
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

        if (this.children.size >= 1)
            result += this.children[0].profondeurInfixe()

        result += " " + this.id + " "

        if (this.children.size >= 2)
            result += this.children[1].profondeurInfixe()

        return result;
    }

    fun parseFromList(listOfNode: MutableList<Tree>, root: Tree?): Tree {
        this.parent = root
        val childOfThisTree: MutableList<Tree> = mutableListOf()

        for (i in listOfNode.indices) {
            if (id == listOfNode[i].parent?.id)
                childOfThisTree.add(listOfNode[i].parseFromList(listOfNode, this))
        }

        this.children = childOfThisTree

        return this
    }

    fun AddChildrenFromListOfValue(values: MutableList<Int>, selfPosition: Int) {
        val length = values.size
        val childOfThisTree: MutableList<Tree> = mutableListOf()
        if (2 * selfPosition + 1 < length) {
            val leftChild = Tree(values[2 * selfPosition + 1].toString(), this.parent)
            leftChild.AddChildrenFromListOfValue(values, 2 * selfPosition + 1)
            childOfThisTree.add(leftChild)
        }
        if (2 * selfPosition + 2 < length) {
            val rightChild = Tree(values[2 * selfPosition + 2].toString(), this.parent)
            rightChild.AddChildrenFromListOfValue(values, 2 * selfPosition + 2)
            childOfThisTree.add(rightChild)
        }
        this.children = childOfThisTree
    }
}