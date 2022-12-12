// CIS 1C Assignment #5
// Instructor Solution Client

import cs_1c.*;

import java.util.*;
public class Foothill
{
   public static void main(String[] args) throws Exception
   {
      double finalFlow;      
      FHflowGraph<String> myEdge = new FHflowGraph<String>();
      myEdge.addNewEdge("S", "A", 3);
      myEdge.addNewEdge("S", "B", 2);
      myEdge.addNewEdge("A", "B", 1);
      myEdge.addNewEdge("A", "C", 3);
      myEdge.addNewEdge("A", "D", 4);
      myEdge.addNewEdge("B", "D", 2);
      myEdge.addNewEdge("C", "T", 2);
      myEdge.addNewEdge("D", "T", 3);
      
      myEdge.showAdjustedTable();
      myEdge.showTableFlow();      
      myEdge.SetVerticesStart("S");
      myEdge.SetEndingVertices("T");
      finalFlow = myEdge.findMaximumFlows();      
      System.out.println("FINAL FLOW: " + finalFlow);      
      myEdge.showAdjustedTable();
      myEdge.showTableFlow();
   }
}
class FHFVertex<E>
{
   public static Stack<Integer> keyStack = new Stack<Integer>();   
    public FHFVertex<E> nextInPath;  
   public HashSet< Pair<FHFVertex<E>, Double> > flowAdjList 
      = new HashSet< Pair<FHFVertex<E>, Double> >();   
   public E data;
   public double dist;
   public HashSet< Pair<FHFVertex<E>, Double> > resAdjList 
   = new HashSet< Pair<FHFVertex<E>, Double> >();
   public static final int KEY_DATA = 0, KEY_ON_DIST = 1; 
   public static int keyTypes = KEY_DATA;
   public static final double INFINITY = Double.MAX_VALUE; 

   public FHFVertex( E x )
   {
      this.data = x;
      this.dist = INFINITY;
      nextInPath = null;
   }
   public FHFVertex() { this(null); }

   public void addDataToAdjList(FHFVertex<E> neighbour, double costs)
   {
      this.flowAdjList.add( new Pair<FHFVertex<E>, Double> (neighbour, costs) );
   }   
   public void addDataToAdjList(FHFVertex<E> neighbour, int costs)
   {
      addDataToAdjList( neighbour, (double)costs );
   }   
   public void addToResourceList(FHFVertex<E> neighbour, double costs)
   {
      this.resAdjList.add( new Pair<FHFVertex<E>, Double> (neighbour, costs) );
   }
   public void addToResourceList(FHFVertex<E> neighbour, int costs)
   {
      addToResourceList( neighbour, (double)costs );
   }
   public boolean equals(Object righths)
   {
      FHFVertex<E> other = (FHFVertex<E>)righths;
      switch (keyTypes)
      {
      case KEY_ON_DIST:
         return (this.dist == other.dist);
      case KEY_DATA:
         return (this.data.equals(other.data));
      default:
         return (this.data.equals(other.data));
      } 
   }
   
   public int hashCode()
   {
      switch (keyTypes)
      {
      case KEY_ON_DIST:
         Double d = this.dist;
         return (d.hashCode());
      case KEY_DATA:
         return (this.data.hashCode());
      default:
         return (this.data.hashCode());
      }  
   }
   public void showFlowAdjuscentList()
   {
         Iterator< Pair<FHFVertex<E>, Double> > iteration ;
      Pair<FHFVertex<E>, Double> pairs;

      

      System.out.print( "ADJ LIST FOR  " + this.data + ": ");
      for (iteration = flowAdjList.iterator(); iteration.hasNext(); )
      {
         pairs = iteration.next();
         System.out.print( pairs.first.data + "(" 
            + String.format("%3.1f", pairs.second)
            + ") " );
      }
      System.out.println();
   }
   public void showResourceAdjuscentList()
   {
      Iterator< Pair<FHFVertex<E>, Double> > iteration ;
      Pair<FHFVertex<E>, Double> pairs;
      System.out.print( "ADJ LIST FOR " + this.data + ": ");
      for (iteration = resAdjList.iterator(); iteration.hasNext(); )
      {
         pairs = iteration.next();
         System.out.print( pairs.first.data + "(" 
            + String.format("%3.1f", pairs.second)
            + ") " );
      }
      System.out.println();
   }
   public static boolean setKeysType( int keyTypes )
   {
      switch (keyTypes)
      {
      case KEY_DATA:
      case KEY_ON_DIST:
         keyTypes = keyTypes;
         return true;
      default:
         return false;
      }
   }
  
   public static void popkeyTypes() { keyTypes = keyStack.pop(); };
   public static void pushkeyTypes() { keyStack.push(keyTypes); }
}
class FHflowGraph<E>
{
   protected FHFVertex<E> StartVertex, endVert;
   protected HashSet< FHFVertex<E> > vertexSets;   
   
   public FHflowGraph ()
   {
      this.vertexSets = new HashSet< FHFVertex<E> >();
      this.StartVertex = null;
      this.endVert = null;
   }
   
   public boolean SetVerticesStart (E x)
   {
      if (x== null)
         return false;
      this.StartVertex = getVertexWithSameData(x);
      if(StartVertex == null)
         return false;
      return true;
   }
   
   public boolean SetEndingVertices (E x)
   {
      if (x== null)
         return false;
      this.endVert = getVertexWithSameData(x);
      if(endVert == null)
         return false;
      return true;
   }
   
   public void addNewEdge(E sources, E destination, double costs)
   {
      FHFVertex<E> src, dst;
      
      src = addTovertexSets(sources);
      dst = addTovertexSets(destination);

      src.addDataToAdjList(dst, costs);
      dst.addDataToAdjList(src, 0); 
            
      src.addDataToAdjList(dst, 0); 
   }
   
   public void addNewEdge(E src, E destination, int cst)
   {
      addNewEdge(src, destination, (double)cst);
   }
   
   public FHFVertex<E> addTovertexSets(E x)
   {
      FHFVertex<E> vert, returnValue;
      FHFVertex.setKeysType(FHFVertex.KEY_DATA);
      Iterator< FHFVertex<E> > iteration;
      boolean succInsertion;
      FHFVertex.pushkeyTypes();     

      returnValue = new FHFVertex<E>(x);
      succInsertion = this.vertexSets.add(returnValue);
      
      FHFVertex.popkeyTypes();
      
      if ( succInsertion )
         return returnValue;
      for (iteration = this.vertexSets.iterator(); iteration.hasNext(); )
      {
         vert = iteration.next();
         if (vert.equals(returnValue))
            return vert;
      }      
      return null;
   }  
   public void showTableFlow()
   {
      Iterator< FHFVertex<E> > iteration;
      System.out.println( "------------------------ ");
      for (iteration = this.vertexSets.iterator(); iteration.hasNext(); )
         (iteration.next()).showFlowAdjuscentList();
      System.out.println();
   }
  public void showAdjustedTable()
   {
      Iterator< FHFVertex<E> > iteration;
      System.out.println( "------------------------ ");
      for (iteration = this.vertexSets.iterator(); iteration.hasNext(); )
         (iteration.next()).showResourceAdjuscentList();
      System.out.println();
   }
public void clear()
   {
      this.endVert = null;
      this.StartVertex = null;
      this.vertexSets.clear();      
   }   
   protected FHFVertex<E> getVertexWithSameData(E x)
   {
      FHFVertex.setKeysType(FHFVertex.KEY_DATA);
      FHFVertex<E> searchVertex, vertex;
      Iterator< FHFVertex<E> > iteration;
      FHFVertex.pushkeyTypes();
      
      searchVertex = new FHFVertex<E>(x);
      for (iteration = this.vertexSets.iterator(); iteration.hasNext(); )
      {
         vertex = iteration.next();
         if (vertex.equals(searchVertex))
         {
            FHFVertex.popkeyTypes();
            return vertex;
         }
      }
      
      FHFVertex.popkeyTypes();
      return null;
   }   
  
   public double findMaximumFlows()
   {
      double totalFlow, limittingCosts;
      Iterator<Pair<FHFVertex<E>, Double>> iteration;      
      if (this.StartVertex == null || this.endVert == null)
         return 0;      
      while(establishNxtFlwPath())
      {
         limittingCosts = getLimitingFlowOnResPath();
         
         if(!adjustPathsByCosts(limittingCosts))
            break;
      }
      totalFlow= 0;
      for (iteration = this.StartVertex.flowAdjList.iterator(); iteration.hasNext();)
      {         totalFlow += iteration.next().second;
      }
      
      return totalFlow;
   }   
   protected boolean establishNxtFlwPath()
   {
      double costsVW;
      Iterator<Pair <FHFVertex<E>, Double>> edgeiterations;   
      FHFVertex<E> vertices,w, v;   
      Iterator<FHFVertex<E>> iteration;  
      Pair<FHFVertex<E>, Double> edges;      
        
      Deque<FHFVertex<E>> PartlyProcessedVerts = new LinkedList
            <FHFVertex<E>>();      
      for(iteration = this.vertexSets.iterator(); iteration.hasNext();)
      {
         vertices = iteration.next();
         vertices.dist = FHFVertex.INFINITY;
         vertices.nextInPath = null;
      }
      
      this.StartVertex.dist = 0;
      PartlyProcessedVerts.addLast(this.StartVertex);
      
      while(!PartlyProcessedVerts.isEmpty())
      {
         v = PartlyProcessedVerts.removeFirst();
         
         for(edgeiterations = v.resAdjList.iterator(); edgeiterations.hasNext();)
         {
            edges = edgeiterations.next();
            w = edges.first;
            costsVW = edges.second;            
            if (costsVW <= 0)
               continue;            
            if(v.dist + costsVW < w.dist)
            {
               w.dist = v.dist + costsVW;
               w.nextInPath = v;               
               if(w == endVert)
                  return true;               
               PartlyProcessedVerts.addLast(w);
            }
         }
      }
      return false;
   }
   protected double getLimitingFlowOnResPath()
   {
      double flowLimits, edgeCosts;
      FHFVertex<E> vert;

      if (this.StartVertex == null || this.endVert == null)
         return 0;      
      flowLimits = FHFVertex.INFINITY;
      for (vert = this.endVert; vert != this.StartVertex; vert = vert.nextInPath)
      {
         if ( vert.nextInPath == null )
            return 0;         
         edgeCosts = getCostResEdge(vert.nextInPath, vert);
          if (edgeCosts < flowLimits)
            flowLimits = edgeCosts;
      }
      return flowLimits;
   }
   protected double getCostResEdge(FHFVertex<E> source, FHFVertex<E> distr)
   {
      Iterator< Pair <FHFVertex<E>, Double>> iteration;
      Pair<FHFVertex<E>, Double> pairs;      
      if(source == null || distr == null)
         return 0;      
      for (iteration = source.resAdjList.iterator();iteration.hasNext();)
      {
         pairs = iteration.next();         
         if (pairs.first == distr)
            return pairs.second;
      }
      return 0;
   }
   
   protected boolean adjustPathsByCosts(double cst)
   {
      FHFVertex<E> vert = this.endVert;
      
      while (vert != this.StartVertex)
      {
         if (vert.nextInPath == null)
            return false;         
         if (!addCostToFlowEdge(vert.nextInPath, vert, cst))
            return false;
         if (!addCostResEdge(vert,vert.nextInPath,cst))
            return false;
         if(!addCostResEdge(vert.nextInPath,vert, -cst))
            return false;         
         vert = vert.nextInPath;
      }
      
      return true;
   }   
   protected boolean addCostToFlowEdge(FHFVertex<E> src, FHFVertex<E> dst,
         double cost)
   {
      Iterator<Pair<FHFVertex<E>, Double>> iteration;
      Pair <FHFVertex<E>, Double> pairs;      
      if (src== null|| dst == null)
         return false;      
      for (iteration = src.flowAdjList.iterator(); iteration.hasNext();)
      {
         pairs = iteration.next();         
         if (pairs.first == dst)
         {
            pairs.second += cost;
            return true;            
         }
      }
      
      for (iteration = dst.flowAdjList.iterator(); iteration.hasNext();)
      {
         pairs = iteration.next();
         
         if (pairs.first == src)
         {
            pairs.second -= cost;
            return true;            
         }
      }      
      return false;
   }
   
   protected boolean addCostResEdge(FHFVertex<E> srce, FHFVertex<E> dist,
         double cost)
   {
      Iterator<Pair<FHFVertex<E>, Double>> iteration;
      Pair <FHFVertex<E>, Double> pairs;
      
      if (srce== null|| dist == null)
         return false;
      
      for (iteration = srce.resAdjList.iterator(); iteration.hasNext();)
      {
         pairs = iteration.next();
         
         if (pairs.first == dist)
         {
            pairs.second += cost;
            return true;            
         }
      }               
      return false;   
   }  
}
