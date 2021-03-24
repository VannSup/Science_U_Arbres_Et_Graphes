package fr.vannsuplabs.scienceuarbresetgraphes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListPopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.vannsuplabs.scienceuarbresetgraphes.data.graph.Graph
import fr.vannsuplabs.scienceuarbresetgraphes.data.tree.Tree
import fr.vannsuplabs.scienceuarbresetgraphes.data.tree.TreeBinaireSearch
import fr.vannsuplabs.scienceuarbresetgraphes.data.tree.TreeData
import fr.vannsuplabs.scienceuarbresetgraphes.data.tree.TreeEquilibre

class MainActivity : AppCompatActivity() {
    private val parcoursGraph : TextView get() = findViewById(R.id.parcoursGraph)
    private val calculButton : Button get() = findViewById(R.id.calculItineraire)
    private val textStart : EditText get() = findViewById(R.id.start)
    private val textEnd : EditText get() = findViewById(R.id.end)
    private val textVersion : EditText get() = findViewById(R.id.numberGraph)

    private val textTp : TextView get() = findViewById(R.id.textTp)
    private val showTpButton : Button get() = findViewById(R.id.showTp)
    private val tpChoix : EditText get() = findViewById(R.id.tpChoix)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculButton.setOnClickListener {
            val version = try{
                textVersion.text.toString().toInt()
            }catch(ex:Exception){
                0
            }
            startGraph(version, textStart.text.toString(), textEnd.text.toString())
        }

        showTpButton.setOnClickListener {
            val version = try{
                tpChoix.text.toString().toInt()
            }catch(ex:Exception){
                0
            }
            startTp(version)
        }
    }

    private fun startGraph(version: Int,start: String, end: String){
        parcoursGraph.text = Graph(version).start(start,end)
    }

    private fun startTp(version: Int){
        textTp.text = TreeData(version).result
    }
}