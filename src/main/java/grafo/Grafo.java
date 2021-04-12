package main.java.grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Grafo
{

   private boolean isDirecionado;
   private Aresta[][] matrizAdjacencia;
   int qtdVertices;
   int qtdArestas;

   private List<Vertice> vertices;
   private List<Aresta> arestas;

   public Grafo(int qtdArestas, List<Aresta> listaAresta)
   {
      this.isDirecionado = listaAresta.get(0).isDirecionado();
      this.qtdVertices = qtdArestas;
      this.qtdArestas = listaAresta.size();
      this.matrizAdjacencia = montaMatrizAdjacencia(listaAresta);
   }

   public Grafo()
   {
      this.vertices = new ArrayList<>();
      this.arestas = new ArrayList<>();
      this.qtdVertices = 0;
      this.qtdArestas = 0;
   }

   public Vertice addVertice(Vertice v)
   {
      vertices.add(v);
      return v;
   }

   public Aresta addAresta(int id, Vertice origem, Vertice destino)
   {
      Aresta e = new Aresta(id, origem, destino, isDirecionado, 1);

      origem.adicionarAdjacente(destino);

      if (!isDirecionado)
      {
         destino.adicionarAdjacente(origem);
      }

      arestas.add(e);

      return e;
   }

   public void immprimirAresta(int idV1, int idV2)
   {
      Aresta v = matrizAdjacencia[idV1 - 1][idV2 - 1];
      if (v != null)
      {
         System.out.println(v);
      }
      else
      {
         System.out.println("Nao existe aresta para os vertices selecionados");
      }
   }

   @Override
   public String toString()
   {
      StringBuilder listaAdjacencia = new StringBuilder();
      for (Vertice v : vertices)
      {
         listaAdjacencia.append(String.format("%s[%s] -> ", v.getNome(), v.getId()));

         for (Vertice verticeAdjacente : v.getListaDeAdjacencia())
         {
            listaAdjacencia.append(String.format("%s[%s] => ", verticeAdjacente.getNome(), verticeAdjacente.getId()));
         }

         listaAdjacencia.append("null \n");
      }
      return listaAdjacencia.toString();
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

   public void buscaEmLargura(Vertice verticeInicial, Vertice verticeFinal)
   {
      List<Vertice> filaQ = new ArrayList<>();
      HashMap<Vertice, Vertice> mapaNoPai = new HashMap<>();

      for (Vertice v : vertices)
      {
         v.setCor("BRANCO");
      }

      filaQ.add(verticeInicial);

      while (!filaQ.isEmpty())
      {
         imprimirFila(filaQ);

         Vertice primeiroDaFila = filaQ.remove(0);

         for (int i = primeiroDaFila.getListaDeAdjacencia().size(); i > 0; i--)
         {
            Vertice adjacente = primeiroDaFila.getListaDeAdjacencia().get(i - 1);
            if (adjacente.getCor().compareTo("BRANCO") == 0)
            {
               adjacente.setCor("CINZA");
               filaQ.add(adjacente);
               mapaNoPai.put(adjacente, primeiroDaFila);
            }
         }
         primeiroDaFila.setCor("PRETO");
      }

      System.out.println();

      if (verticeFinal.getCor().compareTo("PRETO") == 0)
      {
         imprimirCaminho(verticeInicial, verticeFinal, mapaNoPai);
      }
      else
      {
         System.out.printf("Não existe caminho de [%s] ate [%s]", verticeInicial.getNome(), verticeFinal.getNome());
      }

   }

   private void imprimirFila(List<Vertice> filaQ)
   {
      System.out.print("FILA: ");
      for (Vertice v : filaQ)
      {
         System.out.print(v.getNome() + " ");
      }
      System.out.println();
   }

   private void imprimirCaminho(Vertice inicial, Vertice fim, HashMap<Vertice, Vertice> mapaNoPai)
   {
      List<Vertice> caminho = new ArrayList<>();

      Vertice atual = fim;
      while (atual.getNome().compareTo(inicial.getNome()) != 0)
      {
         caminho.add(atual);
         atual = mapaNoPai.get(atual);
      }
      caminho.add(inicial);

      Collections.reverse(caminho);

      System.out.println("Caminho: ");
      for (Vertice v : caminho)
      {
         System.out.print(v.getNome());
         if (caminho.indexOf(v) != caminho.size() - 1)
         {
            System.out.print("-> ");
         }
      }
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

   public List<Vertice> getVertices()
   {
      return vertices;
   }

   public void setVertices(List<Vertice> vertices)
   {
      this.vertices = vertices;
   }

   public List<Aresta> getArestas()
   {
      return arestas;
   }

   public void setArestas(List<Aresta> arestas)
   {
      this.arestas = arestas;
   }

   private class Fila
   {

      private Vertice[] filaVertice = new Vertice[getVertices().size()];

   }
}
