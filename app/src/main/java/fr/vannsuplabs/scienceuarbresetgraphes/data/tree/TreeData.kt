package fr.vannsuplabs.scienceuarbresetgraphes.data.tree

data class TreeData(val tpNumber:Int){

    var result = start(tpNumber)

    private fun start(tpNumber: Int) : String{
        return when(tpNumber){
            1-> td1()
            2-> td2()
            3-> td3()
            4-> td4()
            else -> td1()
        }
    }

    private fun td1() : String{
        var result = "Tp1 :\n"

        val rootA = Tree("A")
        val nodeB = Tree("B", rootA)
        val nodeC = Tree("C", rootA)
        val nodeD = Tree("D", rootA)
        val nodeE = Tree("E", nodeB)
        val nodeF = Tree("F", nodeB)
        val nodeG = Tree("G", nodeD)
        val nodeH = Tree("H", nodeD)
        val nodeI = Tree("I", nodeD)
        val nodeJ = Tree("J", nodeE)
        val nodeK = Tree("K", nodeE)
        val nodeL = Tree("L", nodeE)
        val nodeM = Tree("M", nodeG)
        val nodeN = Tree("N", nodeI)
        val nodeO = Tree("O", nodeI)
        val nodeP = Tree("P", nodeM)
        val tree = mutableListOf(nodeB, nodeC, nodeD, nodeE, nodeF, nodeG, nodeH, nodeI, nodeJ, nodeK, nodeL, nodeM, nodeN, nodeO, nodeP)
        rootA.parseFromList(tree)

        result += "Arbre 1 :\n"
        result += "${rootA}\n"
        result += rootA.showResultOfParcour()

        val root20  = Tree("20")
        val node5   = Tree("5", root20)
        val node25  = Tree("25", root20)
        val node3   = Tree("3", node5)
        val node12  = Tree("12", node5)
        val node21  = Tree("21", node25)
        val node28  = Tree("28", node25)
        val node8   = Tree("8", node12)
        val node13  = Tree("13", node12)
        val node6   = Tree("6", node8)
        val tree2 = mutableListOf(node5, node25, node3, node12, node8, node13, node6, node21, node28)
        root20.parseFromList(tree2)

        result += "Arbre 2 :\n" +
                "${root20}\n" +
                root20.showResultOfParcour()

        return result
    }

    private fun td2() : String {
        var result = "Tp2 :\n"

        val listOfValues = mutableListOf(20, 2, 35, 50, 12, 885, 9, 7)
        val root = Tree("20")
        root.addChildrenFromListOfValue(listOfValues,0)

        result += "Arbre :\n " +
                "${root}\n" +
                root.showResultOfParcour()

        return result
    }

    private fun td3() : String {
        var result = "Tp3 :\n"

        val root = TreeBinaireSearch(20)
        val node5= TreeBinaireSearch(5,root)
        val node25= TreeBinaireSearch(25,root)
        val node3= TreeBinaireSearch(3,node5)
        val node12= TreeBinaireSearch(12,node5)
        val node21= TreeBinaireSearch(21,node25)
        val node28= TreeBinaireSearch(28,node25)
        val node8 = TreeBinaireSearch(8,node12)
        val node13 = TreeBinaireSearch(13,node12)
        val node6 = TreeBinaireSearch(6,node8)
        val tree = mutableListOf(node5, node25, node3, node12, node21, node28, node8, node13, node6)
        root.parseFromList(tree)

        result += "Arbre :\n " +
                "${root}\n" +
                " Ajout de 50 et de 19\n"

        root.insert(50)
        root.insert(19)

        result += "${root}\n" +
                "Parcours infixe : ${root.profondeurInfixe()}\n"+
                "Recherche de 8: ${root.findToString(8)}\n" +
                "Recherche de 9: ${root.findToString(9)}\n\n"

        val root2 =  TreeBinaireSearch((0..9999).random())

        val listOfValues = mutableListOf<Int>()
        val listOfValuesToSearch = mutableListOf<Int>()
        for (i in 0..10000)
            listOfValues.add((0..9999).random())
        root2.addChildrenFromListOfValue(listOfValues,0)

        for (i in 0..10000)
            listOfValuesToSearch.add((0..9999).random())

        result +="Recherche de 100 items dans une liste de 10 000 items :\n"

        var begin = System.nanoTime()
        listOfValuesToSearch.forEach { root2.find(it) }
        var end = System.nanoTime()
        result += "Temps recherche parcours arbre ${end-begin} nanoseconde\n"

        begin = System.nanoTime()
        listOfValuesToSearch.forEach { elementSearch -> listOfValues.find { it == elementSearch } }
        end = System.nanoTime()
        result += "Temps recherche classique ${end-begin} nanoseconds\n\n"

        begin = System.nanoTime()
        root2.profondeurInfixe()
        end = System.nanoTime()
        result +="Temps de trie avec parcour infixe : ${end-begin} ns\n"

        begin = System.nanoTime()
        listOfValuesToSearch.sort()
        end = System.nanoTime()
        result +="Temps de trie classique : ${end-begin} ns\n"

        return result + "\n\n"
    }

    private fun td4() : String {
        var result = "Tp4 :\n"

        val root = TreeEquilibre(20)
        val node5= TreeEquilibre(5,root)
        val node25= TreeEquilibre(25,root)
        val node3= TreeEquilibre(3,node5)
        val node12= TreeEquilibre(12,node5)
        val node21= TreeEquilibre(21,node25)
        val node28= TreeEquilibre(28,node25)
        val node8 = TreeEquilibre(8,node12)
        val node13 = TreeEquilibre(13,node12)
        val node6 = TreeEquilibre(6,node8)
        val tree = mutableListOf(node5, node25, node3, node12, node21, node28, node8, node13, node6)
        root.parseFromList(tree)

        result += "Arbre :\n " +
                "${root}\n" +
                "Hauteur : ${root.hauteur()}\n" +
                "Facteur d'équilibrage : ${root.facteurEquilibrage()}\n" +
                "L'arbre es-il équilibré : ${root.isEquilibe()}\n"

        val root2 = TreeEquilibre(10)
        val node5_2= TreeEquilibre(5,root2)
        val node12_2= TreeEquilibre(12,root2)
        val node2= TreeEquilibre(2,node5_2)
        val node7= TreeEquilibre(7,node5_2)
        val node4= TreeEquilibre(4,node2)
        val node15= TreeEquilibre(15,node12_2)
        val node17 = TreeEquilibre(17,node15)
        val tree2 = mutableListOf(node5_2,node12_2,node2,node7,node4,node15,node17)
        root2.parseFromList(tree2)
        result += "Arbre :\n " +
                "${root2}\n" +
                "Rotation gauche noeud 12\n"
        node12_2.rotationSimpleGauche()
        result += "Arbre :\n " +
                "${root2}\n"

        return result + "\n\n"
    }
}
