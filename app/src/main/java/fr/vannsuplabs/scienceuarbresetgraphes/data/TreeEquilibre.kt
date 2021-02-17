package fr.vannsuplabs.scienceuarbresetgraphes.data

class TreeEquilibre(private val id: Int, private var parent : TreeEquilibre? = null) {
    var parentId: Int? = null
    var childrenLeft: TreeEquilibre? = null
    var childrenRight: TreeEquilibre? = null

    fun profondeurInfixe(): String {
        var result = ""

        if (childrenLeft != null)
            result += childrenLeft!!.profondeurInfixe()

        result += " " + this.id + " "

        if (childrenRight != null)
            result += childrenRight!!.profondeurInfixe()

        return result
    }

    fun hauteurMax() : Int{
        var result = 1

        var hauteurLeft = 0

        var hauteurRight = 0

        if (childrenLeft != null)
            hauteurLeft = childrenLeft!!.hauteurMax() + 1

        if (childrenRight != null)
            hauteurRight = childrenRight!!.hauteurMax() + 1

        if(hauteurLeft != hauteurRight) {
            result = if (hauteurLeft > hauteurRight)
                hauteurLeft
            else
                hauteurRight
        }
        return result
    }

    fun facteurEquilibrage() : Int {

        var hauteurLeft = 0

        var hauteurRight = 0

        if (childrenLeft != null)
            hauteurLeft = childrenLeft!!.hauteurMax() + 1

        if (childrenRight != null)
            hauteurRight = childrenRight!!.hauteurMax() + 1

        return if(hauteurLeft != hauteurRight) {
            if (hauteurLeft < hauteurRight)
                hauteurRight - hauteurLeft
            else
                hauteurLeft - hauteurRight
        }
        else
            0
    }

    fun parseFromList(listOfNode: MutableList<TreeEquilibre>, root: TreeEquilibre? = null): TreeEquilibre {
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
            val leftChild = TreeEquilibre(values[2 * selfPosition + 1], this.parent)
            leftChild.addChildrenFromListOfValue(values, 2 * selfPosition + 1)
            childrenLeft = leftChild
        }

        if (2 * selfPosition + 2 < length) {
            val rightChild = TreeEquilibre(values[2 * selfPosition + 2], this.parent)
            rightChild.addChildrenFromListOfValue(values, 2 * selfPosition + 2)
            childrenRight = rightChild
        }
    }

}