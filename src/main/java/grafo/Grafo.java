package main.java.grafo;

import java.util.List;

public class Grafo
{

   private boolean isDirecionado;
   private Aresta[][] matrizAdjacencia;
   int qtdVertices;
   int qtdArestas;

   public Grafo(int qtdArestas, List<Aresta> listaAresta)
   {
      this.isDirecionado = listaAresta.get(0).isDirecionado();
      this.qtdVertices = qtdArestas;
      this.qtdArestas = listaAresta.size();
      this.matrizAdjacencia = montaMatrizAdjacencia(listaAresta);
   }

   public void immprimirAresta(int idV1, int idV2)
   {
      Aresta v =  matrizAdjacencia[idV1-1][idV2-1];
      if(v != null){
         System.out.println(v);
      }else{
         System.out.println("Nao existe aresta para os vertices selecionados");
      }
   }

   public void imprimeMatrizAdjacencia()
   {
      System.out.print("----");
      int tamanho = matrizAdjacencia.length;
      for (int i = 0; i < tamanho; i++)
      {
         System.out.printf("   [%s]", i + 1);
      }
      System.out.println(" ]");
      for (int i = 0; i < tamanho; i++)
      {
         System.out.printf("[%s] [", i + 1);
         for (int j = 0; j < tamanho; j++)
         {
            System.out.printf("   %s  ", matrizAdjacencia[i][j] != null ? matrizAdjacencia[i][j].getPeso() : 0);
         }
         System.out.println("]");
      }
   }

   private Aresta[][] montaMatrizAdjacencia(List<Aresta> listaAresta)
   {
      Aresta[][] matrizAdjacencia = new Aresta[qtdVertices][qtdVertices];

      for (int i = 0; i < qtdVertices; i++)
      {
         for (int j = 0; j < qtdVertices; j++)
         {
            matrizAdjacencia[i][j] = buscaAresta(listaAresta, i + 1, j + 1);
         }
      }
      return matrizAdjacencia;

   }


   public Aresta buscaAresta(List<Aresta> listaAresta, int idV1, int idV2)
   {

      for (Aresta aresta : listaAresta)
      {
         if (aresta.isArestaValida(idV1, idV2))
         {
            return aresta;
         }
      }

      return null;
   }

   public boolean isDirecionado()
   {
      return isDirecionado;
   }

   public void setDirecionado(boolean direcionado)
   {
      isDirecionado = direcionado;
   }

   public Aresta[][] getMatrizAdjacencia()
   {
      return matrizAdjacencia;
   }

   public void setMatrizAdjacencia(Aresta[][] matrizAdjacencia)
   {
      this.matrizAdjacencia = matrizAdjacencia;
   }

   public int getQtdVertices()
   {
      return qtdVertices;
   }

   public void setQtdVertices(int qtdVertices)
   {
      this.qtdVertices = qtdVertices;
   }

   public int getQtdArestas()
   {
      return qtdArestas;
   }

   public void setQtdArestas(int qtdArestas)
   {
      this.qtdArestas = qtdArestas;
   }
}
