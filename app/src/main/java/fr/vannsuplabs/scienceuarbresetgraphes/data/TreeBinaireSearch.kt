package fr.vannsuplabs.scienceuarbresetgraphes.data

class TreeBinaireSearch(private val id: Int, private var parent : TreeBinaireSearch? = null) {
    var parentId : Int? = null
    var childrenLeft : TreeBinaireSearch? = null
    var childrenRight : TreeBinaireSearch? = null

    fun profondeurInfixe(): String {
        var result = ""

        if (childrenLeft != null)
            result += childrenLeft!!.profondeurInfixe()

        result += " " + this.id + " "

        if (childrenRight != null)
            result += childrenRight!!.profondeurInfixe()

        return result
    }

    fun find(value: Int): TreeBinaireSearch? = when {
        this.id > value -> childrenLeft?.find(value)
        this.id < value -> childrenRight?.find(value)
        else -> this
    }

    fun parseFromList(listOfNode: MutableList<TreeBinaireSearch>, root: TreeBinaireSearch? = null): TreeBinaireSearch {
        this.parent = root
        listOfNode.forEach {
            if (this.id == it.parent?.id){
                if(this.id > it.id)
                    this.childrenLeft = it
                else
                    this.childrenRight = it
                it.parseFromList(listOfNode, this)
            }
        }
        return this
    }

    fun addChildrenFromListOfValue(values: MutableList<Int>, selfPosition: Int) {
        val length = values.size

        if (2 * selfPosition + 1 < length) {
            val leftChild = TreeBinaireSearch(values[2 * selfPosition + 1], this.parent)
            leftChild.addChildrenFromListOfValue(values, 2 * selfPosition + 1)
            childrenLeft = leftChild
        }

        if (2 * selfPosition + 2 < length) {
            val rightChild = TreeBinaireSearch(values[2 * selfPosition + 2], this.parent)
            rightChild.addChildrenFromListOfValue(values, 2 * selfPosition + 2)
            childrenRight = rightChild
        }
    }
}