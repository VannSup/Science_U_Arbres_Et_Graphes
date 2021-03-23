package fr.vannsuplabs.scienceuarbresetgraphes.data.graph

data class GraphData(val graphVersion:Int){

    var graph : MutableList<Node> = makeGraph(graphVersion)

    fun findNodeByName(name:String): Node?{
        return graph.find { node -> node.name == name }
    }

    private fun makeGraph(graphVersion: Int) : MutableList<Node>{
        return when(graphVersion){
            1->exercice1Td12()
            else -> exercice1Td12()
        }
    }

    fun exercice1Td12(): MutableList<Node>{
        val nodeA = Node("A")
        val nodeB = Node("B")
        val nodeC = Node("C")
        val nodeD = Node("D")
        val nodeE = Node("E")
        val nodeF = Node("F")
        val nodeS = Node("S")
        val AC = Branch(3,nodeC)
        val AD = Branch(5,nodeD)
        val AE = Branch(4,nodeE)
        nodeA.children.addAll(mutableListOf(AC,AD,AE))

        val BC = Branch(5,nodeC)
        val BE = Branch(2,nodeE)
        val BF = Branch(3,nodeF)
        nodeB.children.addAll(mutableListOf(BC,BE,BF))

        val CA = Branch(3,nodeA)
        val CB = Branch(5,nodeB)
        val CD = Branch(1,nodeD)
        val CF = Branch(4,nodeF)
        val CS = Branch(5,nodeS)
        nodeC.children.addAll(mutableListOf(CA,CB,CD,CF,CS))

        val DA = Branch(5,nodeA)
        val DC = Branch(1,nodeC)
        val DS = Branch(4,nodeS)
        nodeD.children.addAll(mutableListOf(DA,DC,DS))

        val EA = Branch(4,nodeA)
        val EB = Branch(2,nodeB)
        nodeE.children.addAll(mutableListOf(EA,EB))

        val FB = Branch(3,nodeB)
        val FC = Branch(4,nodeC)
        val FS = Branch(8,nodeS)
        nodeF.children.addAll(mutableListOf(FB,FC,FS))

        val SC = Branch(5,nodeC)
        val SD = Branch(4,nodeD)
        val SF = Branch(8,nodeF)
        nodeS.children.addAll(mutableListOf(SC,SD,SF))

        return mutableListOf(nodeA,nodeB,nodeC,nodeD,nodeE,nodeF,nodeS)
    }
}
