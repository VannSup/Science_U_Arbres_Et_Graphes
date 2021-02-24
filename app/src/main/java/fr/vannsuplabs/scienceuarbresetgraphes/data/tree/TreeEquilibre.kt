package fr.vannsuplabs.scienceuarbresetgraphes.data.tree

class TreeEquilibre(private val id: Int, private var parent: TreeEquilibre? = null) {
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

    fun hauteur() : Int{
        val hauteurLeft = if(this.childrenLeft?.hauteur() != null ) this.childrenLeft?.hauteur()!! else -1
        val hauteurRight = if(this.childrenRight?.hauteur() != null) this.childrenRight?.hauteur()!! else -1
        return (1 + Math.max(hauteurLeft, hauteurRight))
    }

    fun facteurEquilibrage() : Int {
        val hauteurLeft = if(this.childrenLeft?.hauteur() != null ) this.childrenLeft?.hauteur()!! else 0
        val hauteurRight = if(this.childrenRight?.hauteur() != null) this.childrenRight?.hauteur()!! else 0
        return hauteurLeft - hauteurRight
    }

    fun isEquilibe(): Boolean{
        return (this.facteurEquilibrage() >= -1 && this.facteurEquilibrage() <= 1)
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

    fun rotationSimpleGauche(){
        val b: TreeEquilibre? = this.childrenRight
        b!!.parent = this.parent
        this.childrenRight = b.childrenLeft
        if (this.childrenRight != null) this.childrenRight!!.parent = this
        b.childrenLeft = this
        this.parent = b
        if (b.parent != null) {
            if (b.parent!!.childrenRight == this)
                b.parent!!.childrenRight = b
            else
                b.parent!!.childrenLeft = b
        }
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

    override fun toString(): String {

        val result : StringBuilder = StringBuilder()
        result.append(this.id)
        val pointerRight = "└──"
        val pointerLeft = if (this.childrenRight != null) "├──" else "└──"
        traverseNodes(result, "", pointerLeft, this.childrenLeft, this.childrenRight != null)
        traverseNodes(result, "", pointerRight, this.childrenRight, false)
        return result.toString()
    }

    private fun traverseNodes(sb: StringBuilder, padding: String?, pointer: String?, node: TreeEquilibre?,
                              hasRightSibling: Boolean) {
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