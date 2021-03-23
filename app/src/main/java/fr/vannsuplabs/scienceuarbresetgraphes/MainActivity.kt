package fr.vannsuplabs.scienceuarbresetgraphes

import android.os.Bundle
import android.widget.Button
import android.widget.ListPopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.vannsuplabs.scienceuarbresetgraphes.data.graph.Graph
import fr.vannsuplabs.scienceuarbresetgraphes.data.tree.Tree
import fr.vannsuplabs.scienceuarbresetgraphes.data.tree.TreeBinaireSearch
import fr.vannsuplabs.scienceuarbresetgraphes.data.tree.TreeData
import fr.vannsuplabs.scienceuarbresetgraphes.data.tree.TreeEquilibre

class MainActivity : AppCompatActivity() {
    private val mainTextView : TextView get() = findViewById(R.id.main_text)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start()
    }

    private fun start(){
        mainTextView.text = Graph().start()//TreeData(4).result
    }
}