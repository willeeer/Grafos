package main.java.grafo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Vertice
{

   private int id;

   private String nome;

   private String cor;

   private ArrayList<Vertice> listaDeAdjacencia;

   public Vertice(int id, String nome, String cor)
   {
      this.id = id;
      this.nome = nome;
      this.cor = cor;
      this.listaDeAdjacencia = new ArrayList<>();
   }

   public void adicionarAdjacente(Vertice v)
   {
      listaDeAdjacencia.add(v);
   }

   @Override
   public String toString()
   {
      return "Vertice{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", cor='" + cor + '\'' +
               '}';
   }

   public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }

   public String getCor()
   {
      return cor;
   }

   public void setCor(String cor)
   {
      this.cor = cor;
   }

   public ArrayList<Vertice> getListaDeAdjacencia()
   {
      return listaDeAdjacencia;
   }

   public void setListaDeAdjacencia(ArrayList<Vertice> listaDeAdjacencia)
   {
      this.listaDeAdjacencia = listaDeAdjacencia;
   }
}
