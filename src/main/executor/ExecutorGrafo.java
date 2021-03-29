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

   private static final String ARQUIVO_ARESTAS = "arestas_direcionado.csv";
   private static final String ARQUIVO_VERTICES = "vertices.csv";

   private static final String STRING_VAZIA = "";

   private boolean IS_GRAFO_DIRECIONADO = false;

   public static void main(String[] args)
   {
      List<Vertice> listaVertice = montaArrayVertice(ARQUIVO_VERTICES);
      List<Aresta> listaAresta = montaArrayAresta(ARQUIVO_ARESTAS, listaVertice);

      for (Vertice vertice : listaVertice)
      {
         System.out.println(vertice.toString());
      }

      System.out.println();

      for (Aresta aresta : listaAresta)
      {
         System.out.println(aresta.toString());
      }
      System.out.println();

      Grafo grafo = new Grafo(listaVertice.size(), listaAresta);

      grafo.imprimeMatrizAdjacencia();
      grafo.immprimirAresta(1, 3);
      grafo.immprimirAresta(1, 6);
      grafo.immprimirAresta(6, 1);
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
