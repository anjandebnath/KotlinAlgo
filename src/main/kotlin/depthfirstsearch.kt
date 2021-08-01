
// Each node has a value/name and next nodes as it's children
// Go to deeper of each node by going to deep of the children
// At first get the root node and find its children
// Then for each child find its children.
// add the child on each deep step until the child has no more children
class Node(name: String){

    val name: String = name // node value
    val children = mutableListOf<Node>() // next nodes address


    fun depthFirstSearch(): List<String>{
        return depthFirstSearch(mutableListOf())
    }

    fun depthFirstSearch(array: MutableList<String>) : List<String>{
        array.add(this.name)
        for (child in this.children){
            child.depthFirstSearch(array)
        }
        return array
    }
}

fun main(args: Array<String>){
    val root = Node("A")
    val b = Node("B")
    root.children.add(b)
    val c =Node("C")
    root.children.add(c)
    val d =Node("D")
    root.children.add(d)
    val e =Node("E")
    b.children.add(e)
    val f =Node("F")
    b.children.add(f)
    val g =Node("G")
    d.children.add(g)
    val h = Node("H")
    d.children.add(h)
    val i =Node("I")
    f.children.add(i)
    val j =Node("J")
    f.children.add(j)
    val k = Node("K")
    g.children.add(k)

    print(root.depthFirstSearch())
}