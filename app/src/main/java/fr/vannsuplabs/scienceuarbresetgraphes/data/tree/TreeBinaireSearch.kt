package fr.vannsuplabs.scienceuarbresetgraphes.data.tree

class TreeBinaireSearch(val id: Int, private var parent: TreeBinaireSearch? = null) {
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

    fun findToString(value: Int): String? = when {
        this.id > value -> this.id.toString() + " -> " + childrenLeft?.findToString(value)
        this.id < value -> this.id.toString() + " -> " + childrenRight?.findToString(value)
        else -> this.id.toString()
    }

    fun insert(value: Int) {
        if (value > this.id) {
            if (this.childrenRight == null) {
                this.childrenRight = TreeBinaireSearch(value, this)
            } else {
                this.childrenRight!!.insert(value)
            }
        } else if (value < this.id) {
            if (this.childrenLeft == null) {
                this.childrenLeft = TreeBinaireSearch(value, this)
            } else {
                this.childrenLeft!!.insert(value)
            }
        }
        //Si on tombe sur la valeur bah on fait rien
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

    override fun toString(): String {
        val result : StringBuilder = StringBuilder()
        result.append(this.id)
        val pointerRight = "└──"
        val pointerLeft = if (this.childrenRight != null) "├──" else "└──"
        traverseNodes(result, "", pointerLeft, this.childrenLeft, this.childrenRight != null)
        traverseNodes(result, "", pointerRight, this.childrenRight, false)
        return result.toString()
    }

    private fun traverseNodes(sb: StringBuilder, padding: String?, pointer: String?, node: TreeBinaireSearch?, hasRightSibling: Boolean) {
        if (node != null) {
            sb.append("\n")
            sb.append(padding)
            sb.append(pointer)
            sb.append(node.id)
            val paddingBuilder = StringBuilder(padding)
            if (hasRightSibling) {
                paddingBuilder.append("│     ")
            } else {
                paddingBuilder.append("        ")
            }
            val paddingForBoth = paddingBuilder.toString()
            val pointerRight = "└──"
            val pointerLeft = if (node.childrenRight != null) "├──" else "└──"
            traverseNodes(sb, paddingForBoth, pointerLeft, node.childrenLeft, node.childrenRight != null)
            traverseNodes(sb, paddingForBoth, pointerRight, node.childrenRight, false)
        }
    }

}
