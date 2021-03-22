package main.executor;

import main.java.grafo.Aresta;
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

   private static final String STRING_VAZIA = "";

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

      int[][] matrizAdjacencia = montaMatrizAdjacencia(listaVertice, listaAresta);
      imprimeMatrizAdjacencia(matrizAdjacencia, matrizAdjacencia.length);

   }

   private static int[][] montaMatrizAdjacencia(List<Vertice> listaVertice, List<Aresta> listaAresta)
   {

      int tamanho = listaVertice.size();

      int[][] matrizAdjacencia = new int[tamanho][tamanho];

      for (int i = 0; i < tamanho; i++)
      {
         for (int j = 0; j < tamanho; j++)
         {

            if (buscaAresta(listaAresta, i+1, j+1) != null)
            {
               matrizAdjacencia[i][j] = 1;
            }
            else
            {
               matrizAdjacencia[i][j] = 0;
            }
         }
      }
      return matrizAdjacencia;

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

            Aresta aresta = new Aresta(Integer.parseInt(dados[0]), v1, v2, Boolean.getBoolean(dados[3]));
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

   public static Aresta buscaAresta(List<Aresta> listaAresta, int idV1, int idV2)
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

   public static void imprimeMatrizAdjacencia(int[][] matrizAdjacencia, int tamanho)
   {
      System.out.print("----");
      for (int i = 0; i < tamanho; i++)
      {
         System.out.print(String.format("   [%s]",i+1));
      }
      System.out.println(" ]");
      for (int i = 0; i < tamanho; i++)
      {
         System.out.print(String.format("[%s] [",i+1));
         for (int j = 0; j < tamanho; j++)
         {
            System.out.print(String.format("   %s  ",matrizAdjacencia[i][j]));
         }
         System.out.println("]");
      }
   }

}
