package main.executor;

import main.java.grafo.Aresta;
import main.java.grafo.Grafo;
import main.java.grafo.Vertice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class ExecutorGrafo
{

   private static final String ARQUIVO_ARESTAS = "arestas.csv";
   private static final String ARQUIVO_VERTICES = "vertices.csv";

   private static final String ARQUIVO_ARESTAS_LAB = "arestas_labirinto.csv";
   private static final String ARQUIVO_VERTICES_LAB = "vertices_labirinto.csv";

   private static final String STRING_VAZIA = "";

   private boolean IS_GRAFO_DIRECIONADO = false;

   public static void main(String[] args)
   {
      List<Vertice> listaVertice = montaArrayVertice(ARQUIVO_VERTICES);
      List<Aresta> listaAresta = montaArrayAresta(ARQUIVO_ARESTAS, listaVertice);

      Grafo grafo = new Grafo(listaVertice.size(), listaAresta);

      grafo.imprimeMatrizAdjacencia();
      System.out.println();

      Grafo grafo2 = new Grafo();
      grafo2.setDirecionado(false);

      for (Vertice v : listaVertice)
      {
         grafo2.addVertice(v);
      }

      montaArrayArestaParaListaDeAdjacencia(ARQUIVO_ARESTAS, grafo2);
      System.out.println(grafo2.toString());

      System.out.println();
      List<Vertice> verticesLabirinto = montaArrayVertice(ARQUIVO_VERTICES_LAB);

      Grafo grafoLabirinto = new Grafo();
      grafoLabirinto.setDirecionado(true);
      for (Vertice v : verticesLabirinto)
      {
         grafoLabirinto.addVertice(v);
      }

      montaArrayArestaParaListaDeAdjacencia(ARQUIVO_ARESTAS_LAB, grafoLabirinto);
      System.out.println(grafoLabirinto.toString());
      System.out.println();

      grafoLabirinto.buscaEmLargura(grafoLabirinto.getVertices().get(0),grafoLabirinto.getVertices().get(9));

   }

   private static List montaArrayVertice(String nomeArquivo)
   {

      String linhaArquivoCsv;

      List listaVertice = new ArrayList<>();

      try
      {
         BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));

         reader.readLine();
         while ((linhaArquivoCsv = reader.readLine()) != null)
         {
            String[] dados = linhaArquivoCsv.split(";");
            Vertice vertice = new Vertice(Integer.parseInt(dados[0]), dados[1], dados[2]);
            listaVertice.add(vertice);

         }
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      return listaVertice;
   }

   private static void montaArrayArestaParaListaDeAdjacencia(String nomeArquivo, Grafo grafo)
   {

      String linhaArquivoCsv;

      List<Vertice> vertices = grafo.getVertices();

      try
      {
         BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));

         reader.readLine();
         while ((linhaArquivoCsv = reader.readLine()) != null)
         {
            String[] dados = linhaArquivoCsv.split(";");

            Vertice origem = buscaVertice(vertices, Integer.parseInt(dados[1]));
            Vertice destino = buscaVertice(vertices, Integer.parseInt(dados[2]));

            grafo.addAresta(Integer.parseInt(dados[0]), origem, destino);

         }
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

   }

   private static List montaArrayAresta(String nomeArquivo, List<Vertice> listaVertice)
   {

      String linhaArquivoCsv;

      List<Aresta> listaAresta = new ArrayList<>();

      try
      {
         BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));

         reader.readLine();
         while ((linhaArquivoCsv = reader.readLine()) != null)
         {
            String[] dados = linhaArquivoCsv.split(";");

            Vertice v1 = buscaVertice(listaVertice, Integer.parseInt(dados[1]));
            Vertice v2 = buscaVertice(listaVertice, Integer.parseInt(dados[2]));

            Aresta aresta = new Aresta(Integer.parseInt(dados[0]), v1, v2, Boolean.getBoolean(dados[3]), 1);
            listaAresta.add(aresta);

         }
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      return listaAresta;
   }

   public static Vertice buscaVertice(List<Vertice> listaVertice, int id)
   {

      for (Vertice v : listaVertice)
      {
         if (v.getId() == id)
         {
            return v;
         }
      }

      return null;
   }

}
