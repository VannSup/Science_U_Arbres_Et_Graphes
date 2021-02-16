package fr.vannsuplabs.scienceuarbresetgraphes

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.vannsuplabs.scienceuarbresetgraphes.data.Tree

class MainActivity : AppCompatActivity() {
    private val mainTextView : TextView get() = findViewById(R.id.main_text)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start()
    }

    private fun start(){
        var resultString = ""

        val rootA = Tree("A", null)
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
        rootA.parseFromList(tree, null)

        resultString += "Parcours en largeur arbre 1: ${rootA.parcoursLargeur()}\n"
        resultString += "Profondeur préfixe arbre 1: ${rootA.profondeurPrefixe()}\n"
        resultString += "Profondeur sufixe arbre 1: ${rootA.profondeurSuffixe()}\n\n"

        val root20  = Tree("20", null)
        val node5   = Tree("5", root20)
        val node25  = Tree("25", root20)
        val node3   = Tree("3", node5)
        val node12  = Tree("12", node5)
        val node21  = Tree("21", node25)
        val node28  = Tree("28", node25)
        val node8   = Tree("8", node12)
        val node13  = Tree("13", node12)
        val node6   = Tree("6", node13)
        val tree2 = mutableListOf(node5, node25, node3, node12, node8, node13, node6, node21, node28)
        root20.parseFromList(tree2, null)

        resultString +="Parcours en largeur arbre 2: ${root20.parcoursLargeur()}\n"
        resultString +="Profondeur préfixe arbre 2: ${root20.profondeurPrefixe()}\n"
        resultString +="Profondeur sufixe arbre 2: ${root20.profondeurSuffixe()}\n"
        resultString += "Profondeur infixe arbre 2: ${root20.profondeurInfixe()}\n\n"

        val listOfValues = mutableListOf(20, 2, 35, 50, 12, 885, 9, 7)
        val tasRoot = Tree("20",null)
        tasRoot.AddChildrenFromListOfValue(listOfValues,0)

        resultString +="Parcours en largeur arbre 3: ${tasRoot.parcoursLargeur()}\n"
        resultString +="Profondeur préfixe arbre 3: ${tasRoot.profondeurPrefixe()}\n"
        resultString +="Profondeur sufixe arbre 3: ${tasRoot.profondeurSuffixe()}\n"
        resultString += "Profondeur infixe arbre 3: ${tasRoot.profondeurInfixe()}\n\n"

        mainTextView.text = resultString;
    }
}