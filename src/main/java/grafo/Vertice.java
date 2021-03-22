package main.java.grafo;

public class Vertice
{

   private int id;

   private String nome;

   private String cor;

   public Vertice(int id, String nome, String cor)
   {
      this.id = id;
      this.nome = nome;
      this.cor = cor;
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
}
