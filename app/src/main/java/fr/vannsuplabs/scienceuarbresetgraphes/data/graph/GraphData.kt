package fr.vannsuplabs.scienceuarbresetgraphes.data.graph

data class GraphData(val graphVersion:Int){

    var graph : MutableList<Node> = makeGraph(graphVersion)

    fun findNodeByName(name:String): Node?{
        return graph.find { node -> node.name.trim().equals(name.trim(),true) }
    }

    private fun makeGraph(graphVersion: Int) : MutableList<Node>{
        return when(graphVersion){
            1->exercice1Td12()
            2->metroLyon()
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

    fun metroLyon(): MutableList<Node>{

        // Métro D
        val gareDeVaise         = Node("Gare de vaise")
        val valmy               = Node("Valmy")
        val gorgeDeLoup         = Node("Gorge de Loup")
        val vieuxLyon           = Node("Vieux Lyon")
        val bellecour           = Node("Bellecour")
        val guillotiere         = Node("Guillotiere")
        val saxeGambetta        = Node("Saxe Gambetta")
        val garibaldi           = Node("Garibaldi")
        val sansSouci           = Node("Sans Souci")
        val monplaisirLumiere   = Node("Monplaisir Lumiere")
        val grangeBlanche       = Node("Grange Blanche")
        val laennec             = Node("Laennec")
        val mermoz              = Node("Mermoz")
        val parilly             = Node("Parilly")
        val gareDeVenissieux    = Node("Gare de Venissieux")

        // Métro A
        val laSoie              = Node("La Soie")
        val astroballe          = Node("Astroballe")
        val cusset              = Node("Cusset")
        val flachet             = Node("Flachet")
        val gratteCiel          = Node("Gratte-ciel")
        val republique          = Node("Republique")
        val charpennes          = Node("Charpennes")
        val massena             = Node("Massena")
        val foch                = Node("Foch")
        val hotelDeVille        = Node("Hotel de Ville")
        val cordeliers          = Node("Cordeliers")
        // BelleCour
        val ampere              = Node("Ampere")
        val perrache            = Node("Perrache")

        // Métro B
        //Charpennes
        val brotteaux           = Node("Brotteaux")
        val garePartDieu        = Node("Gare Part-Dieu")
        val placeGuichard       = Node("Place Guichard")
        //Saxe Gambetta
        val jeanMace            = Node("Jean Mace")
        val jeanJaures          = Node("Place Jean Jaures")
        val debourg             = Node("Debourg")
        val stadeDeGerland      = Node("Stade de Gerland")
        val gareOulins          = Node("Gare Oulins")

        // From Gare de vaise
        gareDeVaise.children.addAll(mutableListOf(Branch(1,valmy),Branch(16,massena)))
        // From Valmy
        valmy.children.addAll(mutableListOf(Branch(1,gareDeVaise),Branch(2,valmy)))
        // From Gorge de Loup
        gorgeDeLoup.children.addAll(mutableListOf(Branch(2,valmy),Branch(3,vieuxLyon)))
        // From Vieux Lyon
        vieuxLyon.children.addAll(mutableListOf(Branch(3,gorgeDeLoup),Branch(2,bellecour)))
        // From Bellecour
        bellecour.children.addAll(mutableListOf(Branch(2,vieuxLyon),Branch(1,cordeliers),Branch(1,guillotiere),Branch(1,ampere)))
        // From Guillotiere
        guillotiere.children.addAll(mutableListOf(Branch(1,bellecour),Branch(2,saxeGambetta)))
        // From Saxe Gambetta
        saxeGambetta.children.addAll(mutableListOf(Branch(2,guillotiere),Branch(1,placeGuichard),Branch(1,garibaldi),Branch(2,jeanMace)))
        // From Garibaldi
        garibaldi.children.addAll(mutableListOf(Branch(1,saxeGambetta),Branch(2,sansSouci)))
        // From Sans Souci
        sansSouci.children.addAll(mutableListOf(Branch(2,garibaldi),Branch(2,monplaisirLumiere)))
        // From Monplaisir Lumiere
        monplaisirLumiere.children.addAll(mutableListOf(Branch(2,sansSouci),Branch(1,grangeBlanche)))
        // From Grange Blanche
        grangeBlanche.children.addAll(mutableListOf(Branch(1,monplaisirLumiere),Branch(1,laennec)))
        // From Laennec
        laennec.children.addAll(mutableListOf(Branch(1,grangeBlanche),Branch(2,mermoz)))
        // From Mermoz
        mermoz.children.addAll(mutableListOf(Branch(2,laennec),Branch(2,parilly)))
        // From Parilly
        parilly.children.addAll(mutableListOf(Branch(2,mermoz),Branch(3,gareDeVenissieux)))
        // From Gare de Venissieux
        gareDeVenissieux.children.addAll(mutableListOf(Branch(3,parilly),Branch(22,perrache)))

        // From La Soie
        laSoie.children.addAll(mutableListOf(Branch(2,astroballe)))
        // From Astroballe
        astroballe.children.addAll(mutableListOf(Branch(2,laSoie),Branch(1,cusset)))
        // From Cusset
        cusset.children.addAll(mutableListOf(Branch(1,astroballe),Branch(1,flachet)))
        // From Flachet
        flachet.children.addAll(mutableListOf(Branch(1,cusset),Branch(2,gratteCiel)))
        // From Gratte-ciel
        gratteCiel.children.addAll(mutableListOf(Branch(2,flachet),Branch(1,republique)))
        // From Republique
        republique.children.addAll(mutableListOf(Branch(1,gratteCiel),Branch(2,charpennes)))
        // From Charpennes
        charpennes.children.addAll(mutableListOf(Branch(2,republique),Branch(1,brotteaux),Branch(2,massena)))
        // From Massena
        massena.children.addAll(mutableListOf(Branch(2,charpennes),Branch(2,foch),Branch(13,gareDeVaise)))
        // From Foch
        foch.children.addAll(mutableListOf(Branch(2,massena),Branch(2,hotelDeVille)))
        // From Hotel de Ville
        hotelDeVille.children.addAll(mutableListOf(Branch(2,cordeliers),Branch(2,foch)))
        // From Cordeliers
        cordeliers.children.addAll(mutableListOf(Branch(2,hotelDeVille),Branch(1,bellecour)))
        // From Ampere
        ampere.children.addAll(mutableListOf(Branch(1,bellecour),Branch(1,perrache)))
        // From Perrache
        perrache.children.addAll(mutableListOf(Branch(1,ampere),Branch(18,gareDeVenissieux)))

        // From Brotteaux
        brotteaux.children.addAll(mutableListOf(Branch(1,charpennes),Branch(2,garePartDieu)))
        // From Gare Part-Dieu
        garePartDieu.children.addAll(mutableListOf(Branch(2,brotteaux),Branch(6,placeGuichard)))
        // From Place Guichard
        placeGuichard.children.addAll(mutableListOf(Branch(1,saxeGambetta),Branch(6,garePartDieu)))
        // From Jean Mace
        jeanMace.children.addAll(mutableListOf(Branch(2,saxeGambetta),Branch(1,jeanJaures)))
        // From Place Jean Jaures
        jeanJaures.children.addAll(mutableListOf(Branch(1,jeanMace),Branch(2,debourg)))
        // From Debourg
        debourg.children.addAll(mutableListOf(Branch(2,jeanJaures),Branch(2,stadeDeGerland)))
        // From Stade de Gerland
        stadeDeGerland.children.addAll(mutableListOf(Branch(2,debourg),Branch(2,gareOulins)))
        // From Gare Oulins
        gareOulins.children.addAll(mutableListOf(Branch(2,stadeDeGerland)))


        return mutableListOf(
                gareDeVaise,valmy,gorgeDeLoup,vieuxLyon,bellecour,guillotiere,
                saxeGambetta,garibaldi,sansSouci,monplaisirLumiere,grangeBlanche,
                laennec,mermoz,parilly,gareDeVenissieux,laSoie,astroballe,cusset,
                flachet,gratteCiel,republique,charpennes,massena,foch,hotelDeVille,
                cordeliers,ampere,perrache,brotteaux,garePartDieu,placeGuichard,
                jeanMace,jeanJaures,debourg,stadeDeGerland,gareOulins
        )
    }
}
