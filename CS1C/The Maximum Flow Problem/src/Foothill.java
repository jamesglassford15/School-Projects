import java.util.*;
import cs_1c.*;

public class Foothill
{
   // ------- main --------------
   public static void main(String[] args) throws Exception
   {
      double finalFlow;

      // build graph
      FHflowGraph<String> myG = new FHflowGraph<String>();

      myG.addEdge("s", "a", 3);
      myG.addEdge("s", "b", 2);
      myG.addEdge("a", "b", 1);
      myG.addEdge("a", "c", 3);
      myG.addEdge("a", "d", 4);
      myG.addEdge("b", "d", 2);
      myG.addEdge("c", "t", 2);
      myG.addEdge("d", "t", 3);
      
      // show the original flow graph
      myG.showResAdjTable();
      myG.showFlowAdjTable();

      myG.setStartVert("s");
      myG.setEndVert("t");
      finalFlow = myG.findMaxFlow();

      System.out.println("Final flow: " + finalFlow);

      myG.showResAdjTable();
      myG.showFlowAdjTable();
   }
}

class FHflowVertex<E>
{
   public static Stack<Integer> keyStack = new Stack<Integer>();
   public static final int KEY_ON_DATA = 0, KEY_ON_DIST = 1;
   public static int keyType = KEY_ON_DATA;
   public static final double INFINITY = Double.MAX_VALUE;
   public E data;
   public double dist;
   public FHflowVertex<E> nextInPath;
   public HashSet<Pair<FHflowVertex<E>, Double>> flowAdjList = 
         new HashSet<Pair<FHflowVertex<E>, Double>>();
   public HashSet<Pair<FHflowVertex<E>, Double>> resAdjList = 
         new HashSet<Pair<FHflowVertex<E>, Double>>();

   public FHflowVertex(E x)
   {
      data = x;
      dist = INFINITY;
      nextInPath = null;
   }

   public FHflowVertex() { this(null); }

   public void addToFlowAdjList(FHflowVertex<E> neighbour, double cost)
   {
      flowAdjList.add(new Pair<FHflowVertex<E>, Double>(neighbour, cost));
   }

   public void addToFlowAdjList(FHflowVertex<E> neighbour, int costs)
   {
      addToFlowAdjList(neighbour, (double) costs);
   }

   public void addToResAdjList(FHflowVertex<E> neighbour, double costs)
   {
      resAdjList.add(new Pair<FHflowVertex<E>, Double>(neighbour, costs));
   }

   public void addToResAdjList(FHflowVertex<E> neighbour, int costs)
   {
      addToResAdjList(neighbour, (double) costs);
   }

   public boolean equals(Object rhs)
   {
      FHflowVertex<E> other = (FHflowVertex<E>) rhs;
      switch (keyType)
      {
      case KEY_ON_DIST:
         return (dist == other.dist);
      case KEY_ON_DATA:
         return (data.equals(other.data));
      default:
         return (data.equals(other.data));
      }
   }

   public int hashCode()
   {
      switch (keyType)
      {
      case KEY_ON_DIST:
         Double d = dist;
         return (d.hashCode());
      case KEY_ON_DATA:
         return (data.hashCode());
      default:
         return (data.hashCode());
      }
   }

   public Object clone() throws CloneNotSupportedException
   {
      FHflowVertex<E> newObject = (FHflowVertex<E>) super.clone();
      newObject.flowAdjList = (HashSet<Pair<FHflowVertex<E>, Double>>) 
            flowAdjList.clone();
      return newObject;
   }

   public void showFlowAdjList()
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;

      System.out.print("Adj flow list for " + data + ": ");
      for (iter = flowAdjList.iterator(); iter.hasNext();)
      {
         pair = iter.next();
         System.out.print(pair.first.data + "(" + 
         String.format("%3.1f", pair.second) + ") ");
      }
      System.out.println();
   }

   public void showResAdjList()
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;
      
      System.out.print("Adj res list for " + data + ": ");
      for (iter = resAdjList.iterator(); iter.hasNext();)
      {
         pair = iter.next();
         System.out.print(pair.first.data + "(" + 
         String.format("%3.1f", pair.second) + ") ");
      }
      System.out.println();
   }

   public static boolean setKeyType(int whichType)
   {
      switch (whichType)
      {
      case KEY_ON_DATA:
      case KEY_ON_DIST:
         keyType = whichType;
         return true;
      default:
         return false;
      }
   }

   public static void pushKeyType()
   {
      keyStack.push(keyType);
   }

   public static void popKeyType()
   {
      keyType = keyStack.pop();
   };
}

class FHflowGraph<E>
{
   protected HashSet<FHflowVertex<E>> vertexSet;
   protected FHflowVertex<E> startVert, endVert;

   public FHflowGraph()
   {
      vertexSet = new HashSet<FHflowVertex<E>>();
      startVert = null;
      endVert = null;
   }

   public void addEdge(E source, E dest, double cost)
   {
      FHflowVertex<E> src, dst;

      src = addTovertexSet(source);
      dst = addTovertexSet(dest);

      src.addToResAdjList(dst, cost);
      dst.addToResAdjList(src, 0);

      src.addToFlowAdjList(dst, 0);
   }

   public void addEdge(E source, E dest, int cost)
   {
      addEdge(source, dest, (double) cost);
   }

   public FHflowVertex<E> addTovertexSet(E x)
   {
      FHflowVertex<E> retVal, vert;
      boolean successfulInsertion;
      Iterator<FHflowVertex<E>> iter;

      FHflowVertex.pushKeyType();
      FHflowVertex.setKeyType(FHflowVertex.KEY_ON_DATA);

      retVal = new FHflowVertex<E>(x);
      successfulInsertion = vertexSet.add(retVal);

      if (successfulInsertion)
      {
         FHflowVertex.popKeyType();
         return retVal;
      }
      for (iter = vertexSet.iterator(); iter.hasNext();)
      {
         vert = iter.next();
         if (vert.equals(retVal))
         {
            FHflowVertex.popKeyType();
            return vert;
         }
      }
      FHflowVertex.popKeyType();
      return null;
   }

   public void showFlowAdjTable()
   {
      Iterator<FHflowVertex<E>> iter;
      System.out.println("------------------------ ");
      for (iter = vertexSet.iterator(); iter.hasNext();)
         (iter.next()).showFlowAdjList();
      System.out.println();
   }

   public void showResAdjTable()
   {
      Iterator<FHflowVertex<E>> iter;
      System.out.println("------------------------ ");
      for (iter = vertexSet.iterator(); iter.hasNext();)
         (iter.next()).showResAdjList();
      System.out.println();
   }

   public void clear()
   {
      endVert = null;
      startVert = null;
      vertexSet.clear();
   }

   public boolean setStartVert(E x)
   {
      if (x == null)
         return false;
      startVert = getVertexWithThisData(x);
      if (startVert == null)
         return false;
      return true;
   }

   public boolean setEndVert(E x)
   {
      if (x == null)
         return false;
      endVert = getVertexWithThisData(x);
      if (endVert == null)
         return false;
      return true;
   }

   protected FHflowVertex<E> getVertexWithThisData(E x)
   {
      FHflowVertex<E> searchVert, vert;
      Iterator<FHflowVertex<E>> iter;
      FHflowVertex.pushKeyType();
      FHflowVertex.setKeyType(FHflowVertex.KEY_ON_DATA);

      searchVert = new FHflowVertex<E>(x);

      for (iter = vertexSet.iterator(); iter.hasNext();)
      {
         vert = iter.next();
         if (vert.equals(searchVert))
         {
            FHflowVertex.popKeyType();
            return vert;
         }
      }

      FHflowVertex.popKeyType();
      return null;
   }

   public double findMaxFlow()
   {
      double totalFlow, limitingFlow;
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      if (startVert == null || endVert == null)
         return 0;
      while (establishNextFlowPath())
      {
         limitingFlow = getLimitingFlowOnResPath();

         if (!adjustPathByCost(limitingFlow))
            break;
      }
      totalFlow = 0;
      for (iter = startVert.flowAdjList.iterator(); iter.hasNext();)
      {
         totalFlow += iter.next().second;
      }

      return totalFlow;
   }

   protected boolean establishNextFlowPath()
   {
      FHflowVertex<E> w, s, v;
      Pair<FHflowVertex<E>, Double> edge;
      Iterator<FHflowVertex<E>> iter;
      Iterator<Pair<FHflowVertex<E>, Double>> edgeIter;
      double costVW;

      Deque<FHflowVertex<E>> partiallyProcessedVerts = 
            new LinkedList<FHflowVertex<E>>();

      for (iter = vertexSet.iterator(); iter.hasNext();)
      {
         s = iter.next();
         s.dist = FHflowVertex.INFINITY;
         s.nextInPath = null;
      }

      startVert.dist = 0;
      partiallyProcessedVerts.addLast(startVert);

      while (!partiallyProcessedVerts.isEmpty())
      {
         v = partiallyProcessedVerts.removeFirst();

         for (edgeIter = v.resAdjList.iterator(); edgeIter.hasNext();)
         {
            edge = edgeIter.next();
            w = edge.first;
            costVW = edge.second;
            if (costVW == 0)
               continue;
            if (v.dist + costVW < w.dist)
            {
               w.dist = v.dist + costVW;
               w.nextInPath = v;
               if (w == endVert)
                  return true;
               partiallyProcessedVerts.addLast(w);
            }
         }
      }
      return false;
   }

   protected double getLimitingFlowOnResPath()
   {
      double flowLim, edgeCost;
      FHflowVertex<E> vert;

      if (startVert == null || endVert == null)
         return 0;
      flowLim = FHflowVertex.INFINITY;
      for (vert = endVert; vert != startVert; vert = vert.nextInPath)
      {
         if (vert.nextInPath == null)
            return 0;
         edgeCost = getCostOfResEdge(vert.nextInPath, vert);
         if (edgeCost < flowLim)
            flowLim = edgeCost;
      }
      return flowLim;
   }

   protected boolean adjustPathByCost(double cost)
   {
      FHflowVertex<E> vert = endVert;

      while (vert != startVert)
      {
         if (vert.nextInPath == null)
            return false;
         if (!addCostToFlowEdge(vert.nextInPath, vert, cost))
            return false;
         if (!addCostResEdge(vert, vert.nextInPath, cost))
            return false;
         if (!addCostResEdge(vert.nextInPath, vert, -cost))
            return false;
         vert = vert.nextInPath;
      }

      return true;
   }

   protected double getCostOfResEdge(FHflowVertex<E> src, FHflowVertex<E> dst)
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;
      if (src == null || dst == null)
         return 0;
      for (iter = src.resAdjList.iterator(); iter.hasNext();)
      {
         pair = iter.next();
         if (pair.first == dst)
            return pair.second;
      }
      return 0;
   }

   protected boolean addCostResEdge(FHflowVertex<E> srce, FHflowVertex<E> dist,
         double cost)
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;

      if (srce == null || dist == null)
         return false;

      for (iter = srce.resAdjList.iterator(); iter.hasNext();)
      {
         pair = iter.next();

         if (pair.first == dist)
         {
            pair.second += cost;
            return true;
         }
      }
      return false;
   }

   protected boolean addCostToFlowEdge(FHflowVertex<E> src, FHflowVertex<E> dst,
         double cost)
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;
      if (src == null || dst == null)
         return false;
      for (iter = src.flowAdjList.iterator(); iter.hasNext();)
      {
         pair = iter.next();
         if (pair.first == dst)
         {
            pair.second += cost;
            return true;
         }
      }

      for (iter = dst.flowAdjList.iterator(); iter.hasNext();)
      {
         pair = iter.next();

         if (pair.first == src)
         {
            pair.second -= cost;
            return true;
         }
      }
      return false;
   }
}
//----------------------RUNS-----------------------------------------------
/*
 * RUN 1
 *    myG.addEdge("s", "a", 3);
      myG.addEdge("s", "b", 2);
      myG.addEdge("a", "b", 1);
      myG.addEdge("a", "c", 3);
      myG.addEdge("a", "d", 4);
      myG.addEdge("b", "d", 2);
      myG.addEdge("c", "t", 2);
      myG.addEdge("d", "t", 3);
 * 
 * ------------------------ 
Adj res list for a: b(1.0) s(0.0) c(3.0) d(4.0) 
Adj res list for b: a(0.0) s(0.0) d(2.0) 
Adj res list for s: a(3.0) b(2.0) 
Adj res list for c: a(0.0) t(2.0) 
Adj res list for d: a(0.0) b(0.0) t(3.0) 
Adj res list for t: c(0.0) d(0.0) 

------------------------ 
Adj flow list for a: b(0.0) c(0.0) d(0.0) 
Adj flow list for b: d(0.0) 
Adj flow list for s: a(0.0) b(0.0) 
Adj flow list for c: t(0.0) 
Adj flow list for d: t(0.0) 
Adj flow list for t: 

Final flow: 5.0
------------------------ 
Adj res list for a: b(1.0) s(3.0) c(1.0) d(3.0) 
Adj res list for b: a(0.0) s(2.0) d(0.0) 
Adj res list for s: a(0.0) b(0.0) 
Adj res list for c: a(2.0) t(0.0) 
Adj res list for d: a(1.0) b(2.0) t(0.0) 
Adj res list for t: c(2.0) d(3.0) 

------------------------ 
Adj flow list for a: b(0.0) c(2.0) d(1.0) 
Adj flow list for b: d(2.0) 
Adj flow list for s: a(3.0) b(2.0) 
Adj flow list for c: t(2.0) 
Adj flow list for d: t(3.0) 
Adj flow list for t: */

/*
 * RUN 2
 * myG.addEdge("a", "s", 2);
myG.addEdge("b", "s", 4);
myG.addEdge("b", "a", 1);
myG.addEdge("c", "a", 3);
myG.addEdge("d", "a", 3);
myG.addEdge("d", "b", 2);
myG.addEdge("t", "c", 3);
myG.addEdge("t", "d", 2);

------------------------ 
Adj res list for a: b(0.0) s(2.0) c(0.0) d(0.0) 
Adj res list for b: a(1.0) s(4.0) d(0.0) 
Adj res list for s: a(0.0) b(0.0) 
Adj res list for c: a(3.0) t(0.0) 
Adj res list for d: a(3.0) b(2.0) t(0.0) 
Adj res list for t: c(3.0) d(2.0) 

------------------------ 
Adj flow list for a: s(0.0) 
Adj flow list for b: a(0.0) s(0.0) 
Adj flow list for s: 
Adj flow list for c: a(0.0) 
Adj flow list for d: a(0.0) b(0.0) 
Adj flow list for t: c(0.0) d(0.0) 

Final flow: 0.0
------------------------ 
Adj res list for a: b(0.0) s(2.0) c(0.0) d(0.0) 
Adj res list for b: a(1.0) s(4.0) d(0.0) 
Adj res list for s: a(0.0) b(0.0) 
Adj res list for c: a(3.0) t(0.0) 
Adj res list for d: a(3.0) b(2.0) t(0.0) 
Adj res list for t: c(3.0) d(2.0) 

------------------------ 
Adj flow list for a: s(0.0) 
Adj flow list for b: a(0.0) s(0.0) 
Adj flow list for s: 
Adj flow list for c: a(0.0) 
Adj flow list for d: a(0.0) b(0.0) 
Adj flow list for t: c(0.0) d(0.0) */


