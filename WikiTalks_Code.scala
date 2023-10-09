import org.apache.spark.graphx.{Graph, GraphLoader}

// Load the graph using your dataset file
val graph = GraphLoader.edgeListFile(sc, "D:/7th Sem/Data Science System/wikipedia_talk_network.txt")

// Find the connected components
val cc = graph.connectedComponents().vertices

// Calculate the size of each connected component
val componentSizes = cc
  .map { case (_, componentId) => (componentId, 1) }
  .reduceByKey(_ + _)

// Sort the connected components by size in descending order
val sortedComponents = componentSizes
  .sortBy { case (_, size) => size }
  .collect()
  .reverse

// Print the top 50 connected components
val top50Components = sortedComponents.take(50)
top50Components.foreach { case (componentId, size) =>
  println(s"Connected Component ID: $componentId, Size: $size")
}



