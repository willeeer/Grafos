package main.java.grafo;

public class Aresta
{

   private int id;

   private Vertice verticeOrigem;

   private Vertice verticeDestino;

   private boolean direcionado;

   private int peso;

   public Aresta(int id, Vertice verticeOrigem, Vertice verticeDestino, boolean direcionado, int peso)
   {
      this.id = id;
      this.verticeOrigem = verticeOrigem;
      this.verticeDestino = verticeDestino;
      this.direcionado = direcionado;
      this.peso = peso;
   }

   @Override
   public String toString()
   {
      return "Aresta{" +
               "id=" + id +
               ", verticeOrigem=" + verticeOrigem +
               ", verticeDestino=" + verticeDestino +
               ", direcionado=" + direcionado +
               '}';
   }

   public boolean isArestaValida(int v1, int v2)
   {
      if (isDirecionado())
      {
         return ((verticeOrigem.getId() == v1 && verticeDestino.getId() == v2) || (verticeOrigem.getId() == v2
                  && verticeDestino.getId() == v1));
      }
      else
      {
         return ((verticeOrigem.getId() == v1 && verticeDestino.getId() == v2));
      }
   }

   public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public Vertice getVerticeOrigem()
   {
      return verticeOrigem;
   }

   public void setVerticeOrigem(Vertice verticeOrigem)
   {
      this.verticeOrigem = verticeOrigem;
   }

   public Vertice getVerticeDestino()
   {
      return verticeDestino;
   }

   public void setVerticeDestino(Vertice verticeDestino)
   {
      this.verticeDestino = verticeDestino;
   }

   public boolean isDirecionado()
   {
      return direcionado;
   }

   public void setDirecionado(boolean direcionado)
   {
      this.direcionado = direcionado;
   }

   public int getPeso()
   {
      return peso;
   }

   public void setPeso(int peso)
   {
      this.peso = peso;
   }
}
